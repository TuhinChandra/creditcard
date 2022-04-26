package com.sapient.creditcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapient.creditcard.entity.CreditCard;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

}
