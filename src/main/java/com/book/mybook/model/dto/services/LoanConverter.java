package com.book.mybook.model.dto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.mybook.model.dto.loan.LoanDtoBase;
import com.book.mybook.model.entities.Loan;
import com.book.mybook.model.repositories.BookkRepository;
import com.book.mybook.model.repositories.UserRepository;

@Service
public class LoanConverter {

    @Autowired
    UserRepository uRepo;

    @Autowired
    BookkRepository bRepo;

    public Loan loanDtoBaseToLoan(LoanDtoBase loanDto)
    {
        Loan newLoan = Loan
        .builder()
        .loanDate(loanDto.getLoanDate())
        .dueDate(loanDto.getDueDate())
        .returnDate(loanDto.getReturnDate())
        .user(uRepo.findById(loanDto.getUserId()).get())
        .book(bRepo.findById(loanDto.getBookId()).get())
        .build();

        return newLoan;
    }
}
// @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private int loanId;
//     private LocalDate loanDate;
//     private LocalDate dueDate;
//     private LocalDate returnDate;

//     @JsonIgnore
//     @ToString.Exclude
//     @ManyToOne(fetch = FetchType.EAGER)
//     @JoinColumn(name = "user_id")
//     private User user;

//     @JsonIgnore
//     @ToString.Exclude
//     @ManyToOne(fetch = FetchType.EAGER)
//     @JoinColumn(name = "book_id")
//     private Book book;