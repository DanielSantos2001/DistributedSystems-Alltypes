//author:Daniel Santos
package ex1;

import java.net.InetAddress;
import java.rmi.*;
import java.util.Calendar;
import java.util.Scanner;

public class Main_ClientAtualizacao {

    static String ip;
    static int opcao;
    static Item item;
    static Item itemArray[];
    static String descricao;
    static Calendar dataHoraFecho;
    static float precoMinimo;
    static float precoAtual;
    static int id;

    public static void main(String[] args) {
        try {

            while (true) {
                try {
                    ip = inputString("Introduza o ip: ");
                    InetAddress.getByName(ip);
                    break;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            //LocateRegistry.createRegistry(1100);
            String url = "//" + ip + ":1099/teste";

            InterfaceLeilao objRemoto = (InterfaceLeilao) Naming.lookup(url);
            System.out.println("Conectado...");

            while (true) {
                System.out.println("1.Apresentar item  2.Apresentar itens  3.Adicionar item  4.Remover item  5.Fazer oferta  0.Sair");
                opcao = inputInt("Introduza a opção que deseja:");

                if (opcao == 0) {
                    break;
                } else if (opcao == 1) {
                    System.out.println(objRemoto.obtemItem(inputInt("Introduza id do item:")));

                } else if (opcao == 2) {
                    itemArray = objRemoto.obtemTodosItens();
                    for (int i = 0; i < itemArray.length; i++) {
                        System.out.println(itemArray[i]);
                    }

                } else if (opcao == 3) {
                    descricao = inputString("Descrição: ");
                    precoMinimo = inputFloat("Preço minimo: ");
                    Calendar calendar = Calendar.getInstance();
                    System.out.println("Indique a data de fecho do item...");
                    calendar.set(inputInt("Introduza ano: "), inputInt("Introduza mês: "), inputInt("Introduza dia: "), inputInt("Introduza hora: "), inputInt("Introduza minuto: "));

                    item = new Item(descricao, calendar, precoMinimo);
                    if (objRemoto.adicionaItem(item) == true) {
                        System.out.println("Item adicionado com sucesso");
                    } else {
                        System.out.println("Erro!");
                    }

                } else if (opcao == 4) {
                    if (objRemoto.removeItem(inputInt("Introduza id do item que deseja remover: ")) == true) {
                        System.out.println("Item removido com sucesso");
                    } else {
                        System.out.println("Erro!");
                    }

                } else if (opcao == 5) {
                    id = inputInt("Introduza id do item que deseja fazer oferta: ");
                    precoAtual = inputFloat("Introduza preço que deseja oferecer: ");
                    if (objRemoto.oferece(id, precoAtual) == true) {
                        System.out.println("Oferta a item feito com sucesso");
                    } else {
                        System.out.println("Erro! Preço inferior ou leilão encerrado");
                    }

                } else {
                    System.out.println("Opção inválida");
                }
            }

            System.out.println("Adeus...");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public static String inputString(String n) {
        Scanner myObj = new Scanner(System.in);
        System.out.print(n);
        String read = myObj.nextLine();
        return read;
    }

    public static float inputFloat(String n) {
        Scanner myObj = new Scanner(System.in);
        System.out.print(n);
        float read = myObj.nextFloat();
        return read;
    }

    public static int inputInt(String n) {
        Scanner myObj = new Scanner(System.in);
        System.out.print(n);
        int read = myObj.nextInt();
        return read;
    }
}
