package com.sd.projeto.common.services;

import com.sd.projeto.common.models.TransactionModel;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TransactionService extends Remote {
    boolean novaTransacao(String sender, BigDecimal amount, String receiver, String title) throws  RemoteException;
    TransactionModel buscarPorId(String transactionId) throws RemoteException;
    List<TransactionModel> listarTransacoes() throws RemoteException;
    List<TransactionModel> listarPorSender(String sender) throws RemoteException;
    void removerTransacao(String idTransacao) throws  RemoteException;
}
