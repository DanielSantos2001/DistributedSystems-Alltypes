//author: Daniel Santos
package ex1;

import java.util.Calendar;

public class Item {

    private int id;
    private String descricao;
    private Calendar dataHoraFecho;
    private float precoMinimo;
    private float precoAtual;
    private int numeroOfertas;
    private boolean terminado;
    
    public Item(){
        
    }

    public Item(int id, String descricao, Calendar dataHoraFecho, float precoMinimo) {
        this.id = id;
        this.descricao = descricao;
        this.dataHoraFecho = dataHoraFecho;
        this.precoMinimo = precoMinimo;
    }
    
    public Item(String descricao, Calendar dataHoraFecho, float precoMinimo) {
        this.descricao = descricao;
        this.dataHoraFecho = dataHoraFecho;
        this.precoMinimo = precoMinimo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Calendar getDataHoraFecho() {
        return dataHoraFecho;
    }

    public void setDataHoraFecho(Calendar dataHoraFecho) {
        this.dataHoraFecho = dataHoraFecho;
    }

    public float getPrecoMinimo() {
        return precoMinimo;
    }

    public void setPrecoMinimo(float precoMinimo) {
        this.precoMinimo = precoMinimo;
    }

    public float getPrecoAtual() {
        return precoAtual;
    }

    public void setPrecoAtual(float precoAtual) {
        this.precoAtual = precoAtual;
    }

    public int getNumeroOfertas() {
        return numeroOfertas;
    }

    public void setNumeroOfertas(int numeroOfertas) {
        this.numeroOfertas = numeroOfertas;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", descricao=" + descricao + ", dataHoraFecho=" + dataHoraFecho.getTime() + ", precoMinimo=" + precoMinimo + ", precoAtual=" + precoAtual + ", numeroOfertas=" + numeroOfertas + ", terminado=" + terminado + '}';
    }
    
}
