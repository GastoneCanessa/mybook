package com.book.mybook.model.dto.loan;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class LoanDtoBase 
{
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Integer userId;
    private Integer bookId;
}
