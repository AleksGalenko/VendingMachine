/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package gskela.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author aleks
 */
public enum Coins {
    PENNY(new BigDecimal("0.01")), NICKEL(new BigDecimal("0.05")), DIME(new BigDecimal("0.10")), QUARTER(new BigDecimal("0.25"));
    
    final BigDecimal value;
    
    Coins(BigDecimal value){
        this.value = value;
    }

}
