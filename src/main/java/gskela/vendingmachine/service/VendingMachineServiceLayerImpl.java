/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gskela.vendingmachine.service;

import gskela.vendingmachine.dao.VendingMachineAuditDao;
import gskela.vendingmachine.dao.VendingMachineDao;
import gskela.vendingmachine.dao.VendingMachinePersistenceException;
import gskela.vendingmachine.dto.Slot;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author aleks
 */
@Component
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    @Autowired
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public BigDecimal makePurchase(BigDecimal balance, String userChoice) throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException {

        Slot currentSlot = dao.getSlotInfo(userChoice);

        if ((balance.compareTo(currentSlot.getPrice())) == -1) {
            throw new InsufficientFundsException("Insufficient Funds please deposit more money.");
        }
        if (dao.getSlotInfo(userChoice).getQuantity() == 0) {
            throw new NoItemInventoryException("Item out of stock.");
        }
        vendingItem(userChoice);
        balance = balance.subtract(currentSlot.getPrice());
        auditDao.writeAuditEntry("Item purchase: " + currentSlot.getItemName() + ", remaining quantity: " + (currentSlot.getQuantity() - 1) + ".");
        return balance;
    }

    @Override
    public BigDecimal addCash(BigDecimal balance, BigDecimal cashDeposit) {
        balance = (balance.add(cashDeposit));
        return balance;
    }

    @Override
    public void loadSlot(String code, Slot item) throws VendingMachinePersistenceException {
        dao.loadSlot(code, item);
        auditDao.writeAuditEntry("Added " + item.getQuantity() + " item(s) of " + item.getItemName() + " to slot " + item.getCode() + ".");
    }

    @Override
    public List<Slot> readAllSlots() throws VendingMachinePersistenceException {
//        return dao.readAllSlots().stream().filter((Slot slot) -> slot.getQuantity() > 0).collect(Collectors.toList());
        return dao.readAllSlots();
    }

    @Override
    public Slot getSlotInfo(String code) throws VendingMachinePersistenceException {
        return dao.getSlotInfo(code);
    }

    @Override
    public void vendingItem(String code) throws VendingMachinePersistenceException {
        dao.vendingItem(code);
    }

}
