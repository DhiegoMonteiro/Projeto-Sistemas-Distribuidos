package com.sd.projeto.server;

import com.sd.projeto.common.models.UserModel;
import com.sd.projeto.common.services.UserService;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserImplementation extends UnicastRemoteObject implements UserService {

    private final Map<String, UserModel> usuarios = new HashMap<>();

    public UserImplementation() throws RemoteException {
        super();
    }

    public UserModel criarUsuario(String nome, String email, BigDecimal balance) {
        UserModel user = new UserModel(nome, email, balance);
        usuarios.put(user.getId(), user);
        return user;
    }

    public UserModel buscarPorId(String id) {
        return usuarios.get(id);
    }

    public List<UserModel> listarUsuarios() {
        return new ArrayList<>(usuarios.values());
    }
    public void removerUsuario(String id){
        usuarios.remove(id);
    }
}
