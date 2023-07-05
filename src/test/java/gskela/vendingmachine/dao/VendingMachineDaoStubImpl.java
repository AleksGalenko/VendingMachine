/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gskela.vendingmachine.dao;

import gskela.vendingmachine.dto.Slot;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aleks
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {

    public Slot stubSlot;

    public VendingMachineDaoStubImpl() {
        stubSlot = new Slot("T4");
        stubSlot.setItemName("Mug Cream Soda");
        stubSlot.setQuantity(1);
        stubSlot.setPrice(new BigDecimal("1.70"));
    }

//    public VendingMachineDaoStubImpl(Slot stubSlot) {
//        this.stubSlot = stubSlot;
//    }

    @Override
    public void loadSlot(String code, Slot item) throws VendingMachinePersistenceException {
    }

    @Override
    public List<Slot> readAllSlots() throws VendingMachinePersistenceException {
        List<Slot> slotList = new ArrayList<>();
        slotList.add(stubSlot);
        return slotList;
    }

    @Override
    public Slot getSlotInfo(String code) throws VendingMachinePersistenceException {
        if (code.equals(stubSlot.getCode())) {
            return stubSlot;
        } else {
            return null;
        }
    }

    @Override
    public void vendingItem(String code) throws VendingMachinePersistenceException {
        int currentQTY = stubSlot.getQuantity();
        stubSlot.setQuantity(currentQTY - 1);
    }

}
