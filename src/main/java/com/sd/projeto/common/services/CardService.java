package com.sd.projeto.common.services;

import com.sd.projeto.common.models.CardModel;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CardService extends Remote {
    void criarCartao(String idOwner, String type) throws  RemoteException;
    CardModel buscarPorId(String cardId) throws RemoteException;
    List<CardModel> listarCards() throws  RemoteException;
    List<CardModel> listarPorOwner(String idOwner) throws RemoteException;
    void removerCartao(String cardId) throws  RemoteException;
}
