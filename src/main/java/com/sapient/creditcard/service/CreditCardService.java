package com.sapient.creditcard.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.sapient.creditcard.entity.CreditCard;
import com.sapient.creditcard.exception.CreditCardException;
import com.sapient.creditcard.repository.CreditCardRepository;

@Service
public class CreditCardService {

	@Autowired
	private CreditCardRepository creditCardRepository;

	public CreditCard addCreditCard(final CreditCard creditCard) throws CreditCardException {
		if (validateMandatoryParams(creditCard)
				&& validCreditCardNumberAgainstLuhnAlgo(creditCard.getCreditCardNumber().trim())) {
			setDefaulValue(creditCard);
			return Optional.ofNullable(creditCardRepository.save(creditCard))
					.orElseThrow(() -> new CreditCardException("error while adding credit card into repository"));
		}else {
			throw new CreditCardException("credit card number does not pass the Luhn Algorithm");
		}
	}

	private void setDefaulValue(final CreditCard creditCard) {
		creditCard.setCreditCardNumber(creditCard.getCreditCardNumber().trim());
		creditCard.setExpiryMonth(12);
		creditCard.setExpiryYear(2024);
	}

	private boolean validateMandatoryParams(final CreditCard creditCard) throws CreditCardException {
		if (ObjectUtils.isEmpty(creditCard.getCreditCardNumber())) {
			throw new CreditCardException("Please provide card number");
		} else if (!org.h2.util.StringUtils.isNumber(creditCard.getCreditCardNumber())) {
			throw new CreditCardException("Card Number must be numeric");
		} else if (ObjectUtils.isEmpty(creditCard.getNameOnCard())) {
			throw new CreditCardException("Please provide card holder name");
		}
		return true;
	}

	public static boolean validCreditCardNumberAgainstLuhnAlgo(final String cardNumber) {
		boolean isValidCardNumber = false;
		final int[] cardIntArray = new int[cardNumber.length()];

		for (int i = 0; i < cardNumber.length(); i++) {
			final char c = cardNumber.charAt(i);
			cardIntArray[i] = Integer.parseInt("" + c);
		}

		for (int i = cardIntArray.length - 2; i >= 0; i = i - 2) {
			int num = cardIntArray[i];
			num = num * 2;
			if (num > 9) {
				num = num % 10 + num / 10;
			}
			cardIntArray[i] = num;
		}

		final int sum = sumDigits(cardIntArray);

		if (sum % 10 == 0) {
			isValidCardNumber = true;
		}

		return isValidCardNumber;

	}

	public static int sumDigits(final int[] arr) {
		return Arrays.stream(arr).sum();
	}
}
