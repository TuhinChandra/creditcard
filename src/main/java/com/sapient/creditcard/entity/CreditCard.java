package com.sapient.creditcard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sapient.creditcard.constants.Constants;

import lombok.Data;

@Entity
@Table(name = Constants.TBL_CREDIT_CARD)
@Data
public class CreditCard {

	@Id
	@Column(name = "card_number")
	private String creditCardNumber;

	@Column(name = "name_on_card")
	private String nameOnCard;

	@Column(name = "expiry_month")
	private int expiryMonth;

	@Column(name = "expiry_year")
	private int expiryYear;

	@Column(name = "card_limit")
	private double limit;
	@Column
	private double balance;

}
