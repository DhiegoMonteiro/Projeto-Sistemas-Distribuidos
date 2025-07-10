package com.sd.projeto.server;

import com.sd.projeto.common.models.CardModel;
import com.sd.projeto.common.services.CardService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.*;

public class CardImplementation extends UnicastRemoteObject implements CardService {

    private final Map<String, CardModel> cards = new HashMap<>();

    public CardImplementation() throws RemoteException {
        super();
    }

    public CardModel criarCartao(String cardId, String idOwner, String type) throws RemoteException {
        CardModel cartao = new CardModel(cardId, generateCardNumber(), generateExpireDate(), generateCvv(),type, idOwner);
        cards.put(cardId, cartao);
        return cartao;
    }

    public CardModel buscarPorId(String cardId) throws RemoteException {
        return cards.get(cardId);
    }
    public List<CardModel> listarCards() throws RemoteException{
        return new ArrayList<>(cards.values());
    }

    public List<CardModel> listarPorOwner(String idOwner) throws RemoteException {
        List<CardModel> cartoesOwner = new ArrayList<>();
        for (CardModel card : cards.values()) {
            if (card.getIdOwner().equals(idOwner)) {
                cartoesOwner.add(card);
            }
        }
        return cartoesOwner;
    }


    public void removerCard(String cardId) throws RemoteException {
        cards.remove(cardId);
    }

    private String generateCardNumber() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            stringBuilder.append(random.nextInt(10));
            if (i == 3 || i == 7 || i == 11) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    private LocalDate generateExpireDate() {
        return LocalDate.now().plusYears(2)
                .withMonth(12)
                .withDayOfMonth(31);
    }
    private String generateCvv() {
        Random random = new Random();
        return String.format("%03d", random.nextInt(1000));
    }

}
