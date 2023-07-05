/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gskela.vendingmachine.dao;

import gskela.vendingmachine.dto.Slot;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 *
 * @author aleks
 */
@Component
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    public final String INVENTORY_FILE;

    public static final String DELIMITER = "::";

    private Map<String, Slot> items = new HashMap<>();

   
    public VendingMachineDaoFileImpl() {
        INVENTORY_FILE = "inventory.txt";
    }

    public VendingMachineDaoFileImpl(String inventoryTextFile) {
        INVENTORY_FILE = inventoryTextFile;
    }

    @Override
    public void loadSlot(String code, Slot item) throws VendingMachinePersistenceException {
        loadData();
        items.put(code, item);
        writeData();
    }

    @Override
    public List<Slot> readAllSlots() throws VendingMachinePersistenceException {
        loadData();
        return new ArrayList<Slot>(items.values());
    }

    @Override
    public Slot getSlotInfo(String code) throws VendingMachinePersistenceException {
        loadData();
        return items.get(code);
    }

    @Override
    public void vendingItem(String code) throws VendingMachinePersistenceException {
        loadData();
        Slot currentSlot = items.get(code);
        int currentQTY = currentSlot.getQuantity();
        currentSlot.setQuantity(currentQTY - 1);
        items.replace(code, currentSlot);
        writeData();
    }

    private void writeData() throws VendingMachinePersistenceException {

        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save data file", e);
        }

        String slotAsText;
        List<Slot> slots = this.readAllSlots();
        for (Slot currentSlot : slots) {
            slotAsText = marshallSlot(currentSlot);
            out.println(slotAsText);
            out.flush();
        }
        out.close();
    }

    private void loadData() throws VendingMachinePersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("Could not load data file.", e);
        }
        String currentLine;
        Slot currentSlot;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentSlot = unmarshallSlots(currentLine);
            items.put(currentSlot.getCode(), currentSlot);
        }
    }

    private Slot unmarshallSlots(String slotAsText) {
        String[] slotTokens = slotAsText.split(DELIMITER);
        Slot slotFromFile = new Slot();
        slotFromFile.setCode(slotTokens[0]);
        slotFromFile.setItemName(slotTokens[1]);
        slotFromFile.setPrice(new BigDecimal(slotTokens[2]));
        slotFromFile.setQuantity(parseInt(slotTokens[3]));

        return slotFromFile;
    }

    private String marshallSlot(Slot slot) {
        String slotAsText = slot.getCode() + DELIMITER;
        slotAsText += slot.getItemName() + DELIMITER;
        slotAsText += slot.getPrice() + DELIMITER;
        slotAsText += slot.getQuantity();
        return slotAsText;
    }

}
