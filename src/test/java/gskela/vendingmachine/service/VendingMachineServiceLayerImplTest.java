/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package gskela.vendingmachine.service;

import gskela.vendingmachine.dao.VendingMachinePersistenceException;
import gskela.vendingmachine.dto.Slot;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author aleks
 */
public class VendingMachineServiceLayerImplTest {

    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerImplTest() {

//        VendingMachineDao dao = new VendingMachineDaoStubImpl();
//        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
//
//        service = new VendingMachineServiceLayerImpl(dao, auditDao);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);

    }

    @Test
    public void testInsufficientFunds() {

        BigDecimal balance = new BigDecimal("0.00");
        String userChoice = "T4";

        try {
            service.makePurchase(balance, userChoice);
            fail("Expected \"Insufficient Funds Exception\" was not thrown");
        } catch (NoItemInventoryException | VendingMachinePersistenceException e) {
            fail("Incorrect exception was thrown.");
        } catch (InsufficientFundsException e) {
        }
    }

    @Test
    public void testAddCash() {
        BigDecimal balance = new BigDecimal("0.00");
        BigDecimal cashDeposit = new BigDecimal("2.00");

        BigDecimal newBalance = service.addCash(balance, cashDeposit);

        assertTrue(!(balance.compareTo(newBalance) == 0), "The balance must change");
        assertTrue((balance.compareTo(newBalance.subtract(cashDeposit)) == 0), "The balance should change by the amount of the deposit");
    }

    @Test
    public void testMakePurchase() throws Exception {
        BigDecimal balance = new BigDecimal("2.00");
        String userChoice = "T4";

        BigDecimal newBalance = service.makePurchase(balance, userChoice);
        Slot testSlot = service.getSlotInfo(userChoice);

        assertTrue(!(balance.compareTo(newBalance) == 0), "The balance must change");
        assertTrue((newBalance.compareTo(new BigDecimal("0.30")) == 0), "The new balance must be exactly 0.30");
        assertTrue(testSlot.getQuantity() == 0, "Must be 0");

    }

    @Test
    public void testNoItemInventory() {

        BigDecimal balance = new BigDecimal("2.00");
        String userChoice = "T4";

        try {
            service.makePurchase(balance, userChoice);
            service.makePurchase(balance, userChoice);
            fail("Expected \"Out Of Stock Item Exception\" was not thrown. Real quantity: ");
        } catch (InsufficientFundsException | VendingMachinePersistenceException e) {
            fail("Incorrect exception was thrown.");
        } catch (NoItemInventoryException e) {
        }
    }
}
