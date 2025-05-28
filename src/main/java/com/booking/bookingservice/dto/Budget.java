package com.booking.bookingservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Budget {

    private String propertyName;
    private BigDecimal total = BigDecimal.ZERO;
    private BigDecimal totalExpense = BigDecimal.ZERO;
    private BigDecimal totalPriceWithTax = BigDecimal.ZERO;
    private BigDecimal totalPriceWithOutTax = BigDecimal.ZERO;
    List<BudgetDetail> budgetDetails = new ArrayList<>();

    public void addBudgetDetail(BudgetDetail budgetDetail) {
        budgetDetails.add(budgetDetail);
        total = total.add(budgetDetail.getTotal());
        totalExpense = totalExpense.add(budgetDetail.getExpense());
        totalPriceWithTax = totalPriceWithTax.add(budgetDetail.getPriceWithTax());
        totalPriceWithOutTax = totalPriceWithOutTax.add(budgetDetail.getPriceWithoutTax());
    }

}
