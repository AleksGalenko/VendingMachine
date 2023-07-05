/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package gskela.vendingmachine;

import gskela.vendingmachine.controller.VendingMachineController;
import gskela.vendingmachine.dao.VendingMachinePersistenceException;
import gskela.vendingmachine.service.InsufficientFundsException;
import gskela.vendingmachine.service.NoItemInventoryException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author aleks
 */
public class App {

    public static void main(String[] args) throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException {

//        UserIO io = new UserIOConsoleImpl();
//        VendingMachineView view = new VendingMachineView(io);
//        VendingMachineDao dao = new VendingMachineDaoFileImpl();
//        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
//        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao, auditDao);
//        VendingMachineController controller = new VendingMachineController(view, service);

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("gskela.vendingmachine");
        ctx.refresh();
        
        VendingMachineController controller = ctx.getBean("vendingMachineController", VendingMachineController.class);
        controller.run();
    }

}
