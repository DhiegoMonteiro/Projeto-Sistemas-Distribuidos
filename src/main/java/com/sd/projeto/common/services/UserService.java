package com.sd.projeto.common.services;

import com.sd.projeto.common.models.UserModel;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserService extends Remote {
    UserModel criarUsuario(String id, String nome, String email, BigDecimal balance) throws RemoteException;
    UserModel buscarPorId(String id) throws RemoteException;
    List<UserModel> listarUsuarios() throws RemoteException;
    void removerUsuario(String id) throws  RemoteException;

}