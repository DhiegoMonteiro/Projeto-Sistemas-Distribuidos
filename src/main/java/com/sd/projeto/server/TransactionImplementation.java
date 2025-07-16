package com.sd.projeto.server;

import com.sd.projeto.common.models.TransactionModel;
import com.sd.projeto.common.services.TransactionService;
import com.sd.projeto.common.models.UserModel;

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
    private final Map<String, UserModel> usuarios = new HashMap<>();

    public TransactionImplementation() throws RemoteException {
        super();
    }
   
    public void novaTransacao(String sender, BigDecimal amount, String receiver, String title) throws RemoteException {
        UserModel remetente = usuarios.get(sender);
        UserModel destinatario = usuarios.get(receiver);

        if (remetente == null || destinatario == null) {
            throw new RemoteException("Usuário inválido.");
        }

        if (!remetente.withdraw(amount)) {
            throw new RemoteException("Saldo insuficiente.");
        }

        destinatario.deposit(amount);

        TransactionModel transacao = new TransactionModel(sender, amount, receiver, title, LocalDateTime.now());
        transacoes.put(transacao.getTransactionId(), transacao);
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


}
