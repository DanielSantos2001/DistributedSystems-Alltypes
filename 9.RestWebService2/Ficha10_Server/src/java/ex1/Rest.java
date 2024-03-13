//author: Daniel Santos
package ex1;

import java.text.DecimalFormat;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Calendar;

@Path("resources")
//localhost:8080/ficha10_Server/resources/resources/
public class Rest {

    private Item item;
    private ArrayList<Item> listItem = new ArrayList<Item>();
    private String str = "";
    private int id = 1;
    private Calendar cal = Calendar.getInstance();

    @GET
    @Path("printItens")
    @Produces({"application/xml", "application/json"})
    public synchronized String printItens() {
        if (listItem != null) {
            str = "";
            for (int i = 0; i < listItem.size(); i++) {
                str = str + "\n" + listItem.get(i).toString();
            }
        }
        if (str.equals("")) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }
        return str;
    }

    @GET
    @Path("printItem/{id}")
    @Produces({"application/xml", "application/json"})
    public synchronized String printItem(@PathParam("id") int newID) {
        if (listItem != null) {
            for (int i = 0; i < listItem.size(); i++) {
                if (listItem.get(i).getId() == newID) {
                    return listItem.get(i).toString();
                }
            }
        } else {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }
        return null;
    }

    @POST
    @Path("addItem")
    @Consumes({"application/xml", "application/json"})
    public synchronized void addAcao(Item aItem) {

        item = new Item(id, aItem.getDescricao(), aItem.getDataHoraFecho(), aItem.getPrecoMinimo());
        listItem.add(item);
        id++;
        throw new WebApplicationException(Response.Status.ACCEPTED);
    }

    @PUT
    @Path("ofertaItem/{id}")
    @Consumes({"application/xml", "application/json"})
    public synchronized void alterarAcao(Item aItem, @PathParam("id") int newID) {

        if (listItem != null) {
            for (int i = 0; i < listItem.size(); i++) {
                if ((listItem.get(i).getId() == newID) && (listItem.get(i).getPrecoAtual() < aItem.getPrecoAtual())) {
                    if (listItem.get(i).getDataHoraFecho().before(Calendar.getInstance())) {
                        listItem.get(i).setTerminado(true);
                        throw new WebApplicationException(Response.Status.FORBIDDEN);
                    } else {
                        listItem.get(i).setPrecoAtual(aItem.getPrecoAtual());
                        listItem.get(i).setNumeroOfertas(listItem.get(i).getNumeroOfertas() + 1);
                        throw new WebApplicationException(Response.Status.ACCEPTED);
                    }
                }
            }
        }
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @DELETE
    @Path("deleteItem/{id}")
    @Consumes({"application/xml", "application/json"})
    public synchronized void apagarAcao(@PathParam("id") int newID) {

        if (listItem != null) {
            for (int i = 0; i < listItem.size(); i++) {

                if (listItem.get(i).getId() == newID) {
                    listItem.remove(i);
                    throw new WebApplicationException(Response.Status.OK);
                }
            }
        }
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

}
