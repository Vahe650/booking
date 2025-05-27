package com.booking.bookingservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Budget {

    private BigDecimal total = BigDecimal.ZERO;
    List<BudgetDetail> budgetDetails = new ArrayList<>();

    public void addBudgetDetail(BudgetDetail budgetDetail) {
        budgetDetails.add(budgetDetail);
        total = total.add(budgetDetail.getTotal());
    }

}
