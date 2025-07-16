package com.sd.projeto.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            UserImplementation userImplementation = new UserImplementation();
            Naming.rebind("rmi://localhost/UserService", userImplementation);
            Naming.rebind("rmi://localhost/CardService", new CardImplementation());
            Naming.rebind("rmi://localhost/TransactionService", new TransactionImplementation(userImplementation));
            System.out.println("Servidor RMI pronto.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}