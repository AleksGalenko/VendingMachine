/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package gskela.vendingmachine.dao;

import gskela.vendingmachine.dto.Slot;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author aleks
 */
public class VendingMachineDaoFileImplTest {

    VendingMachineDao testDao;
    static String testFile = "testInventory.txt";

    public VendingMachineDaoFileImplTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        new FileWriter(testFile);
        testDao = new VendingMachineDaoFileImpl(testFile);
    }

//    @AfterAll
//    public static void tearDownClass() throws Exception{
//        new FileWriter(testFile).close();
//    }
    @Test
    public void testLoadGetSlotInfo() throws Exception {
        String code = "T0";
        Slot testSlot = new Slot(code);
        testSlot.setItemName("Water");
        testSlot.setQuantity(3);
        testSlot.setPrice(new BigDecimal("1.25"));

        testDao.loadSlot(code, testSlot);

        Slot retrievedSlot = testDao.getSlotInfo(code);

        assertEquals(testSlot.getCode(), retrievedSlot.getCode(), "Checking Item Code");
        assertEquals(testSlot.getItemName(), retrievedSlot.getItemName(), "Checking Item Name");
        assertEquals(testSlot.getQuantity(), retrievedSlot.getQuantity(), "Checking Quantity");
        assertEquals(testSlot.getPrice(), retrievedSlot.getPrice(), "Checking Item Price");
    }

    @Test
    public void testLoadReadAllSlots() throws Exception {
        Slot firstSlot = new Slot("T1");
        firstSlot.setItemName("Schweppes");
        firstSlot.setQuantity(4);
        firstSlot.setPrice(new BigDecimal("1.40"));

        Slot secondSlot = new Slot("T2");
        secondSlot.setItemName("7Up");
        secondSlot.setQuantity(5);
        secondSlot.setPrice(new BigDecimal("1.45"));

        testDao.loadSlot(firstSlot.getCode(), firstSlot);
        testDao.loadSlot(secondSlot.getCode(), secondSlot);

        List<Slot> allSlots = testDao.readAllSlots();

        assertNotNull(allSlots, "The list of slots must not be null");
        assertEquals(2, allSlots.size(), "The list of slots must contain 2 slots.");
        assertTrue(testDao.readAllSlots().contains(firstSlot), "The list of slots should include T1::Schweppes..");
        assertTrue(testDao.readAllSlots().contains(secondSlot), "The list of slots should includ T2::7Up..");
    }

    @Test
    public void testVendingItem() throws Exception {
        Slot testSlot = new Slot("T3");
        testSlot.setItemName("Crush");
        testSlot.setQuantity(6);
        testSlot.setPrice(new BigDecimal("1.50"));

        testDao.loadSlot(testSlot.getCode(), testSlot);

        testDao.vendingItem(testSlot.getCode());
        Slot retrievedSlot = testDao.getSlotInfo(testSlot.getCode());

        assertEquals(testSlot.getCode(), retrievedSlot.getCode(), "Checking Item Code");
        assertEquals(testSlot.getItemName(), retrievedSlot.getItemName(), "Checking Item Name");
        assertNotEquals(testSlot.getQuantity(), retrievedSlot.getQuantity(), "Checking Quantity");
        assertEquals((testSlot.getQuantity() - 1), retrievedSlot.getQuantity(), "Checking Quantity");
        assertEquals(testSlot.getPrice(), retrievedSlot.getPrice(), "Checking Item Price");
    }

}
