/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gskela.vendingmachine.dao;

/**
 *
 * @author aleks
 */
public interface VendingMachineAuditDao {

    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
