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

@Path("resources")
//localhost:8080/ficha9/resources/resources/
public class Rest {

    private Acao acao;
    private ArrayList<Acao> listAcao = new ArrayList<Acao>();
    private String str = "";

    @GET
    @Path("printSigla")
    @Produces(MediaType.TEXT_PLAIN)
    public String siglas() {
        if (listAcao != null) {
            str = "";
            for (int i = 0; i < listAcao.size(); i++) {
                str = str + "\n" + listAcao.get(i).getSigla();
            }
        }
        if (str.equals("")) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }
        return str;
    }

    @GET
    @Path("printAcao/{sigla}")
    @Produces(MediaType.TEXT_PLAIN)
    public String printAcao(@PathParam("sigla") String newSigla) {
        if (listAcao != null) {
            for (int i = 0; i < listAcao.size(); i++) {
                if (listAcao.get(i).getSigla().equals(newSigla)) {
                    return listAcao.get(i).toString();
                }
            }
        } else {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }
        return null;
    }

    //localhost:8080/ficha9/resources/resources/addAcao
    @POST
    @Path("addAcao")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addAcao(@FormParam("nome") String newNome,
            @FormParam("sigla") String newSigla,
            @FormParam("valor") Float newValor) {

        if (listAcao != null) {
            for (int i = 0; i < listAcao.size(); i++) {
                if (listAcao.get(i).getSigla().equals(newSigla)) {
                    throw new WebApplicationException(Response.Status.CONFLICT);
                }
            }
        }

        acao = new Acao(newNome, newSigla, newValor);
        listAcao.add(acao);
        throw new WebApplicationException(Response.Status.ACCEPTED);
    }

    @PUT
    @Path("alterarAcao/{sigla}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void alterarAcao(@FormParam("nome") String newNome,
            @PathParam("sigla") String newSigla,
            @FormParam("valor") Float newValor) {

        if (listAcao != null) {
            for (int i = 0; i < listAcao.size(); i++) {
                if (listAcao.get(i).getSigla().equals(newSigla)) {
                    listAcao.get(i).setNome(newNome);
                    listAcao.get(i).setValor(newValor);
                    throw new WebApplicationException(Response.Status.ACCEPTED);
                }
            }
        }
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @DELETE
    @Path("deleteAcao/{sigla}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void apagarAcao(@PathParam("sigla") String newSigla) {

        if (listAcao != null) {
            for (int i = 0; i < listAcao.size(); i++) {
                if (listAcao.get(i).getSigla().equals(newSigla)) {
                    listAcao.remove(i);
                    throw new WebApplicationException(Response.Status.OK);
                }
            }
        }
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

}
