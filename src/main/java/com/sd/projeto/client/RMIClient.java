package com.sd.projeto.client;

import com.sd.projeto.common.models.UserModel;
import com.sd.projeto.common.models.CardModel;
import com.sd.projeto.common.models.TransactionModel;
import com.sd.projeto.common.services.UserService;
import com.sd.projeto.common.services.CardService;
import com.sd.projeto.common.services.TransactionService;

import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class RMIClient {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            UserService userService = (UserService) Naming.lookup("rmi://localhost/UserService");
            CardService cardService = (CardService) Naming.lookup("rmi://localhost/CardService");
            TransactionService transactionService = (TransactionService) Naming.lookup("rmi://localhost/TransactionService");

            int opcao;
            do {
                System.out.println("\n--- MENU ---");
                System.out.println("1. Registrar usuário");
                System.out.println("2. Ver usuários registrados");
                System.out.println("3. Buscar usuário por ID");
                System.out.println("4. Remover usuário por ID");
                System.out.println("5. Cadastrar cartão");
                System.out.println("6. Listar todos os cartões cadastrados");
                System.out.println("7. Listar cartões por dono");
                System.out.println("8. Buscar cartão por ID");
                System.out.println("9. Remover cartão por ID");
                System.out.println("10. Realizar uma tranferência");
                System.out.println("11. Listar todos as transferências");
                System.out.println("12. Listar transferências por remetente");
                System.out.println("13. Buscar transferência por ID");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("_____________________________");
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        userService.criarUsuario(nome, email, new BigDecimal(50));
                        System.out.println("Usuário registrado com sucesso!");
                        System.out.println("_____________________________");
                        break;

                    case 2:
                        List<UserModel> usuarios = userService.listarUsuarios();
                        System.out.println("_____________________________");
                        if (usuarios.isEmpty()) {
                            System.out.println("Nenhum usuário registrado.");
                        } else {
                            System.out.println("Usuários registrados:");
                            System.out.println("_____________________________");
                            for (UserModel u : usuarios) {
                                System.out.println("- " + u.getId() + ": " + u.getNome() + " (" + u.getEmail() + ") (Saldo: " + u.getBalance() + ")");
                            }
                            System.out.println("_____________________________");
                        }
                        break;

                    case 3:
                        System.out.println("_____________________________");
                        System.out.print("Digite o ID do usuário: ");
                        String buscarId = scanner.nextLine();
                        UserModel usuario = userService.buscarPorId(buscarId);
                        if (usuario != null) {
                            System.out.println("Encontrado: " + usuario.getNome() + " - " + usuario.getEmail());
                        } else {
                            System.out.println("Usuário não encontrado.");
                        }
                        System.out.println("_____________________________");
                        break;

                    case 4:
                        System.out.println("_____________________________");
                        System.out.print("Digite o ID do usuário: ");
                        String idRemover = scanner.nextLine();
                        UserModel user = userService.buscarPorId(idRemover);
                        if (user != null) {
                            userService.removerUsuario(idRemover);
                            System.out.println("Usuário removido: " + user.getNome() + " - " + user.getEmail());
                        } else {
                            System.out.println("Usuário não encontrado.");
                        }
                        System.out.println("_____________________________");
                        break;

                    case 5:
                        System.out.println("_____________________________");
                        System.out.print("ID do dono do cartão: ");
                        String ownerId = scanner.nextLine();
                        System.out.print("Tipo do cartão: ");
                        String type = scanner.nextLine();
                        if(userService.buscarPorId(ownerId) == null){
                            System.out.println("Usuário com Id " + ownerId +  " não encontrado.");
                        } else{
                            cardService.criarCartao(ownerId, type);
                            System.out.println("Cartão cadastrado com sucesso!");
                        }
                        System.out.println("_____________________________");
                        break;

                    case 6:
                        List<CardModel> todosCartoes = cardService.listarCards();
                        System.out.println("_____________________________");
                        if (todosCartoes.isEmpty()) {
                            System.out.println("Nenhum cartão cadastrado.");
                        } else {
                            System.out.println("Cartões cadastrados:");
                            System.out.println("_____________________________");
                            for (CardModel c : todosCartoes) {
                                System.out.println("- " + c.getCardId() + ": " + c.getNumber() + " (Id do usuário dono: " + c.getIdOwner() + ")");
                            }
                            System.out.println("_____________________________");
                        }
                        break;

                    case 7:
                        System.out.println("_____________________________");
                        System.out.print("Digite o ID do dono: ");
                        String ownerBusca = scanner.nextLine();
                        List<CardModel> cartoesDoOwner = cardService.listarPorOwner(ownerBusca);
                        if(userService.buscarPorId(ownerBusca) == null){
                            System.out.println("Usuário com Id " + ownerBusca +  " não encontrado.");
                        }else{
                            if (cartoesDoOwner.isEmpty()) {
                                System.out.println("Nenhum cartão encontrado para este usuário.");
                            } else {
                                System.out.println("Cartões do usuário " + ownerBusca + ":");
                                for (CardModel c : cartoesDoOwner) {
                                    System.out.println("- " + c.getCardId() + ": " + c.getNumber() + " (Validade: " + c.getExpireDate() + ")");
                                    System.out.println("_____________________________");
                                }
                            }
                        }
                        break;

                    case 8:
                        System.out.println("_____________________________");
                        System.out.print("Digite o ID do cartão: ");
                        String buscarCartaoId = scanner.nextLine();
                        CardModel cartaoBuscado = cardService.buscarPorId(buscarCartaoId);
                        if (cartaoBuscado != null) {
                            System.out.println("Cartão encontrado: " + cartaoBuscado.getNumber() + " (ID do usuário: " + cartaoBuscado.getIdOwner() + ")");
                        } else {
                            System.out.println("Cartão não encontrado.");
                        }
                        System.out.println("_____________________________");
                        break;

                    case 9:
                        System.out.println("_____________________________");
                        System.out.print("Digite o ID do cartão: ");
                        String removerCartaoId = scanner.nextLine();
                        CardModel cartaoRemovido = cardService.buscarPorId(removerCartaoId);
                        if (cartaoRemovido != null) {
                            cardService.removerCartao(removerCartaoId); //
                            System.out.println("Cartão removido com sucesso.");
                        } else {
                            System.out.println("Cartão não encontrado.");
                        }
                        System.out.println("_____________________________");
                        break;
                        
                    case 10:
                        System.out.println("_____________________________");
                        System.out.print("ID do remetente: ");
                        String senderId = scanner.nextLine();
                        System.out.print("ID do destinatário: ");
                        String receiverId = scanner.nextLine();
                        System.out.print("Valor da transferência: ");
                        BigDecimal amount = scanner.nextBigDecimal();
                        scanner.nextLine();
                        System.out.print("Título/descrição: ");
                        String title = scanner.nextLine();

                        try {
                            transactionService.novaTransacao(senderId, amount, receiverId, title);
                            System.out.println("Transferência realizada com sucesso.");
                        } catch (RemoteException e) {
                            System.out.println("Erro ao realizar transferência: " + e.getMessage());
                        }

                        System.out.println("_____________________________");
                        break;

                    case 11:
                        List<TransactionModel> todas = transactionService.listarTransacoes();
                        System.out.println("_____________________________");
                        if (todas.isEmpty()) {
                            System.out.println("Nenhuma transferência registrada.");
                        } else {
                            for (TransactionModel t : todas) {
                                System.out.println("- ID: " + t.getTransactionId() + " | De: " + t.getSender() + " | Para: " + t.getReciever() + " | Valor: R$" + t.getAmount() + " | Título: " + t.getTitle() + " | Data: " + t.getSentAt());
                            }
                        }
                        System.out.println("_____________________________");
                        break;

                    case 12:
                        System.out.println("_____________________________");
                        System.out.print("Digite o ID do remetente: ");
                        String remetenteId = scanner.nextLine();
                        if (userService.buscarPorId(remetenteId) == null) {
                            System.out.println("Usuário remetente não encontrado.");
                        } else {
                            List<TransactionModel> transRemetente = transactionService.listarPorSender(remetenteId);
                            if (transRemetente.isEmpty()) {
                                System.out.println("Nenhuma transferência encontrada para este remetente.");
                            } else {
                                for (TransactionModel t : transRemetente) {
                                    System.out.println("- ID: " + t.getTransactionId() + " | Para: " + t.getReciever() + " | Valor: R$" + t.getAmount() + " | Título: " + t.getTitle() +" | Data: " + t.getSentAt());
                                }
                            }
                        }
                        System.out.println("_____________________________");
                        break;

                    case 13:
                        System.out.println("_____________________________");
                        System.out.print("Digite o ID da transferência: ");
                        String transId = scanner.nextLine();
                        TransactionModel transacao = transactionService.buscarPorId(transId);
                        if (transacao != null) {
                            System.out.println("Transferência encontrada:");
                            System.out.println("- De: " + transacao.getSender());
                            System.out.println("- Para: " + transacao.getReciever());
                            System.out.println("- Valor: R$" + transacao.getAmount());
                            System.out.println("- Título: " + transacao.getTitle());
                            System.out.println("- Data: " + transacao.getSentAt());
                        } else {
                            System.out.println("Transferência não encontrada.");
                        }
                        System.out.println("_____________________________");
                        break;

                        
                    case 0:
                        System.out.println("_____________________________");
                        System.out.println("Encerrando...");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                        System.out.println("_____________________________");
                }

            } while (opcao != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
