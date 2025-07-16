package com.sd.projeto.server;

import com.sd.projeto.common.models.TransactionModel;
import com.sd.projeto.common.services.TransactionService;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionImplementation extends UnicastRemoteObject implements TransactionService {

    private final Map<String, TransactionModel> transacoes = new HashMap<>();
    private final UserImplementation userImplementation;

    public TransactionImplementation(UserImplementation userImplementation) throws RemoteException {
        super();
        this.userImplementation = userImplementation;
    }
   
    public boolean novaTransacao(String sender, BigDecimal amount, String receiver, String title) throws RemoteException {
        if (!userImplementation.withdraw(sender, amount)) {
            return false;
        }
        userImplementation.deposit(receiver,amount);
        TransactionModel transacao = new TransactionModel(sender, amount, receiver, title, LocalDateTime.now());
        transacoes.put(transacao.getTransactionId(), transacao);
        return true;
    }

	
    public TransactionModel buscarPorId(String transactionId) {
        return transacoes.get(transactionId);
    }


	public List<TransactionModel> listarTransacoes() {
		return new ArrayList<>(transacoes.values());
	}
    
	public List<TransactionModel> listarPorSender(String sender) {
		 List<TransactionModel> transacoesSender = new ArrayList<>();
	        for (TransactionModel transacao : transacoes.values()) {
	            if (transacao.getSender().equals(sender)) {
	            	transacoesSender.add(transacao);
	            }
	        }
	        return transacoesSender;
	}


    public void removerTransacao(String idTransacao) throws RemoteException {
        transacoes.remove(idTransacao);
    }


}
