package com.sd.projeto.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost/UserService", new UserImplementation());
            Naming.rebind("rmi://localhost/CardService", new CardImplementation());
            Naming.rebind("rmi://localhost/TransactionService", new TransactionImplementation());
            System.out.println("Servidor RMI pronto.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}