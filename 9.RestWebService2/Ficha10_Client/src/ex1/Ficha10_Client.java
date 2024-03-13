//author: Daniel Santos
package ex1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;

public class Ficha10_Client {

    private static int opcao;
    private static URL url;
    private static HttpURLConnection conn;
    private static Client client;
    private static Calendar calendar;
    private static Item item;

    public static void main(String[] args) {
        client = ClientBuilder.newClient();
        while (true) {
            System.out.println("1.Apresentar item  2.Apresentar itens  3.Adicionar item  4.Remover item  5.Fazer oferta  0.Sair");
            opcao = inputInt("Introduza a opção que deseja: ");

            if (opcao == 0) {
                client.close();
                break;
            } else if (opcao == 1) {
                getItem(inputInt("Introduza o id do item que deseja listar: "));
            } else if (opcao == 2) {
                getItens();
            } else if (opcao == 3) {
                addItem(inputString("Introduza a descrição do produto: "), inputInt("Introduza dia: "), inputInt("Introduza mes: "), inputInt("Introduza ano: "), inputFloat("Introduza o preço mínimo: "));
            } else if (opcao == 4) {
                removeItem(inputInt("Introduza o id do item que deseja apagar: "));
            } else if (opcao == 5) {
                ofertaItem(inputInt("Introduza o id do item que deseja fazer oferta: "), inputFloat("Introduza o valor: "));
            } else {
                System.out.println("Opção inválida");
            }
        }
    }

    public static void ofertaItem(int aID, Float aPrecoAtual) {
        try {
            item = new Item();
            item.setPrecoAtual(aPrecoAtual);
            Response resp = client.target("http://localhost:8080/Ficha10_Server/resources/resources/ofertaItem")
                    .path("/" + aID)
                    .request()
                    .put(Entity.json(item));

            if (resp.getStatus() == 403) {
                throw new RuntimeException("Leilão encerrado");
            }
            if (resp.getStatus() == 404) {
                throw new RuntimeException("ID não encontrado");
            }
            if (resp.getStatus() != 202) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + resp.getStatus());
            }

            resp.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void removeItem(int aID) {
        try {
            item = new Item();
            item.setId(aID);
            Response resp = client.target("http://localhost:8080/Ficha10_Server/resources/resources/deleteItem")
                    .path("/" +aID)
                    .request()
                    .delete();

            if (resp.getStatus() == 404) {
                throw new RuntimeException("Leilão não encontrado");
            }
            if (resp.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + resp.getStatus());
            }
            
            resp.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addItem(String aDescricao, int aDia, int aMes, int aAno, Float aPrecoMin) {
        try {
            calendar = Calendar.getInstance();
            calendar.set(aAno, aMes-1, aDia);
            item = new Item(aDescricao, calendar, aPrecoMin);
            Response resp = client.target("http://localhost:8080/Ficha10_Server/resources/resources/addItem")
                    .request()
                    .post(Entity.json(item));

            if (resp.getStatus() != 202) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + resp.getStatus());
            }
            
            resp.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void getItem(int aID) {
        try {
            url = new URL("http://localhost:8080/Ficha10_Server/resources/resources/printItem/" + aID);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 204) {
                throw new RuntimeException("Leilão não encontrado");
            }
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server:");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void getItens() {
        try {
            url = new URL("http://localhost:8080/Ficha10_Server/resources/resources/printItens");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 204) {
                throw new RuntimeException("Não existem leilões");
            }
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server:");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
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
