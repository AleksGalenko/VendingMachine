/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gskela.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author aleks
 */
public class Change {

    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;
    private BigDecimal balance;

    public Change(BigDecimal balance) {
        this.balance = balance.setScale(2, RoundingMode.HALF_UP);
        BigDecimal[] quartersArr = balance.divideAndRemainder(Coins.QUARTER.value);
        BigDecimal[] dimesArr = quartersArr[1].divideAndRemainder(Coins.DIME.value);
        BigDecimal[] nickelsArr = dimesArr[1].divideAndRemainder(Coins.NICKEL.value);
        BigDecimal[] penniesArr = nickelsArr[1].divideAndRemainder(Coins.PENNY.value);
        this.quarters = quartersArr[0].intValue();
        this.dimes = dimesArr[0].intValue();
        this.nickels = nickelsArr[0].intValue();
        this.pennies = penniesArr[0].intValue();
    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public int getPennies() {
        return pennies;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(2, RoundingMode.HALF_UP);
    }

}
