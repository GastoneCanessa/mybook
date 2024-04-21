package com.book.mybook.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.book.mybook.model.dto.loan.LoanDtoBase;
import com.book.mybook.model.dto.services.LoanConverter;
import com.book.mybook.model.entities.Loan;
import com.book.mybook.model.repositories.BookkRepository;
import com.book.mybook.model.repositories.LoanRepository;
import com.book.mybook.model.repositories.UserRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class LoanController 
{
    @Autowired
    LoanRepository lRepo;

    @Autowired
    LoanConverter lConv;

    @Autowired
    UserRepository uRepo;

    @Autowired
    BookkRepository bRepo;

    @PostMapping("/loans")
    public ResponseEntity<?> postLoan(@RequestBody LoanDtoBase loanDto) 
    {  
        Loan newLoan = lConv.loanDtoBaseToLoan(loanDto);
        return ResponseEntity.ok(lRepo.save(newLoan));
    }

    @GetMapping("/loans")
    public List<Loan> getAllLoans() {
        return lRepo.findAll();
    }

    @GetMapping("/loans/{id}")
    public Loan getAllLoans(@PathVariable Integer id) {
        return lRepo.findById(id).get();
    }

    @PutMapping("/loans/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable Integer id, @RequestBody LoanDtoBase loanDto) {
        Optional<Loan> loan = lRepo.findById(id);
        if (loan.isPresent())
        {
            Loan existingLoan = loan.get();
            existingLoan.setDueDate(loanDto.getDueDate());
            existingLoan.setLoanDate(loanDto.getLoanDate());
            existingLoan.setReturnDate(loanDto.getLoanDate());
            existingLoan.setUser(uRepo.findById(loanDto.getUserId()).get());
            existingLoan.setBook(bRepo.findById(loanDto.getBookId()).get());

            Loan savedLoan = lRepo.save(existingLoan);
            return new ResponseEntity<Loan>(savedLoan, HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>("No loan with id " + id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/loans/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        Optional<Loan> loan = lRepo.findById(id);
        if(loan.isPresent())
        {
            lRepo.deleteById(id);
            return new ResponseEntity<String>("Loan deleted", HttpStatus.OK);
        } else
            return new ResponseEntity<String>("No loan with id " + id, HttpStatus.NOT_FOUND);
    }
}
