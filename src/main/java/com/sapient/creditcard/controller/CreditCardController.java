package com.sapient.creditcard.controller;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.creditcard.constants.Constants;
import com.sapient.creditcard.entity.CreditCard;
import com.sapient.creditcard.exception.CreditCardException;
import com.sapient.creditcard.service.CreditCardService;

@RestController
@RequestMapping(Constants.URL_API_V1_CREDITCARD_PROCESSING)
public class CreditCardController {

	public static final String METHOD_CREDITCARD_ADD = "/add";
	public static final String URL_API_V1_CREDITCARD_PROCESSING = "/api/v1/creditcard/processing";

	@Autowired
	private CreditCardService creditCardService;

	@PostMapping(METHOD_CREDITCARD_ADD)
	public ResponseEntity<CreditCard> addCreditCard(@RequestBody final CreditCard creditCard)
			throws CreditCardException {
		return new ResponseEntity<>(creditCardService.addCreditCard(creditCard), CREATED);
	}

}
