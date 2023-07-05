/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gskela.vendingmachine.ui;

import gskela.vendingmachine.dto.Change;
import gskela.vendingmachine.dto.Slot;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author aleks
 */
@Component
public class VendingMachineView {

    private UserIO io;

    @Autowired
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public String printMenuAndGetSelection(BigDecimal balance) {
        io.print("\n Your balance: $" + balance);
        io.print("\nD. Deposit cash;");
        io.print("Q. Quit.");
        io.print("\nTo load the machine, type the word \"LOAD\" and press Enter");

        return io.readString("\nPlease select from the above choices.");
    }

    public void displayAvailableItems(List<Slot> slotList) {
        System.out.println("\n SLOT :: ITEM             :: PRICE  :: QTY ");
        slotList.stream()
                .filter((Slot slot) -> slot.getQuantity() > 0)
                .forEach((slot) -> {
                    io.print(String.format("%5.5s ::  %-15.15s :: $%-5.5s :: %3.3s ",
                            slot.getCode(),
                            slot.getItemName(),
                            slot.getPrice(),
                            slot.getQuantity()));
                });

    }

    public Slot getNewItemInfo() {
        String code = io.readString("Please enter slot: ").toUpperCase();
        String itemName = io.readString("Please enter name of item: ");
        int quantity = io.readInt("Please enter quantity of items: ");
        BigDecimal price = io.readBigDecimal("Please enter price of one item: ").setScale(2, RoundingMode.HALF_UP);
        Slot currentSlot = new Slot(code);
        currentSlot.setItemName(itemName);
        currentSlot.setQuantity(quantity);
        currentSlot.setPrice(price);
        return currentSlot;
    }

    public void displayAvailableItemsBanner() {
        io.print("\n\n=== Available items ===");
    }

    public void displayMachineLoadingBanner() {
        io.print("\n=== Machine Loading ===\n");
    }

    public void displayItemsAddedMessage(Slot slot) {
        io.print("Items successfully added.\n");
        io.print("SLOT: " + slot.getCode());
        io.print("Item name: " + slot.getItemName());
        io.print("Item quantity: " + slot.getQuantity());
        io.print("Item price: " + slot.getPrice());
        io.readString("\nPlease hit enter to continue");
    }

    public void displayPurchaseMessage(Slot slot) {
        io.print("");
        io.print("Good choice! Here is your " + slot.getItemName() + ".");
        io.readString("\nPlease hit enter to continue");
    }

    public BigDecimal displayAddCashBannerAndGetBalance() {
        io.print("\n=== Depositing cash ===\n");
        return io.readBigDecimal("Enter the amount to increase the balance: ").setScale(2, RoundingMode.HALF_UP);
    }

    public void displayGoodbyeMessage(BigDecimal balance) {
        Change change = new Change(balance);
        io.print("\n=== Thank you! ===\n");
        io.print("Your change:");
        io.print(String.format("QUARTERS: %s\nDIMES:    %s\nNICKELS:  %s\nPENNIES:  %s\n",
                change.getQuarters(),
                change.getDimes(),
                change.getNickels(),
                change.getPennies()));
        io.print("===  Goodbye!  ===");
    }

    public void displayUnknownCommandMessage() {
        io.print("UNKNOWN COMMAND!!!");
        io.readString("\nPlease hit enter to continue");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
        io.readString("\nPlease hit enter to continue");
    }
}
