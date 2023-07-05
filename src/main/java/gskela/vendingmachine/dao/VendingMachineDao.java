/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gskela.vendingmachine.dao;

import gskela.vendingmachine.dto.Slot;
import java.util.List;

/**
 *
 * @author aleks
 */
public interface VendingMachineDao {

    void loadSlot(String code, Slot item) throws VendingMachinePersistenceException;

    List<Slot> readAllSlots() throws VendingMachinePersistenceException;

    Slot getSlotInfo(String code) throws VendingMachinePersistenceException;

    void vendingItem(String code) throws VendingMachinePersistenceException;
}
