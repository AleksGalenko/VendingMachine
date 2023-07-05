/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gskela.vendingmachine.controller;

import gskela.vendingmachine.dao.VendingMachinePersistenceException;
import gskela.vendingmachine.dto.Slot;
import gskela.vendingmachine.service.InsufficientFundsException;
import gskela.vendingmachine.service.NoItemInventoryException;
import gskela.vendingmachine.service.VendingMachineServiceLayer;
import gskela.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author aleks
 */
@Component
public class VendingMachineController {

    private VendingMachineView view;
    private VendingMachineServiceLayer service;
    public BigDecimal balance = new BigDecimal("0.00");

    @Autowired
    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() throws InsufficientFundsException, NoItemInventoryException {

        String userChoice;
        Boolean keepGoing = true;
        try {
            while (keepGoing) {
                view.displayAvailableItemsBanner();
                view.displayAvailableItems(service.readAllSlots());
                userChoice = getMenuSelection();

                if (service.getSlotInfo(userChoice) == null) {
                    if (userChoice.equals("LOAD")) {
                        loadSlot();
                    } else {
                        if (userChoice.equals("D")) {
                            addCash();
                        } else {
                            if (userChoice.equals("Q")) {
                                quitApp();
                                keepGoing = false;
                            } else {
                                unknownCommand();
                            }
                        }
                    }

                } else {
                    makePurchase(userChoice);
                }
            }
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private String getMenuSelection() {
        return view.printMenuAndGetSelection(balance).toUpperCase();
    }

    private void makePurchase(String userChoice) throws VendingMachinePersistenceException {
        try {
            balance = service.makePurchase(balance, userChoice);
            view.displayPurchaseMessage(service.getSlotInfo(userChoice));
        } catch (InsufficientFundsException | NoItemInventoryException e) {
            view.displayErrorMessage(e.getMessage());

        }
    }

    private void addCash() {
        balance = service.addCash(balance, view.displayAddCashBannerAndGetBalance());
    }

    private void quitApp() {
        view.displayGoodbyeMessage(balance);
    }

    private void loadSlot() throws VendingMachinePersistenceException {
        view.displayMachineLoadingBanner();
        Slot newSlot = view.getNewItemInfo();
        service.loadSlot(newSlot.getCode(), newSlot);
        view.displayItemsAddedMessage(newSlot);
    }

    private void unknownCommand() {
        view.displayUnknownCommandMessage();
    }
}
