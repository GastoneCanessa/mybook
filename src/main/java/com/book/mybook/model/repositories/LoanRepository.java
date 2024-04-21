package com.book.mybook.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.mybook.model.entities.Loan;

public interface LoanRepository extends JpaRepository <Loan, Integer>
{

}
