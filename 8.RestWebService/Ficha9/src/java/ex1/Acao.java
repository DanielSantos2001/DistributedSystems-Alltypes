//author: Daniel Santos
package ex1;

public class Acao {
    
    private String nome;
    private String sigla;
    private float valor;

    public Acao(String nome, String sigla, float valor) {
        this.nome = nome;
        this.sigla = sigla;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Acao{" + "nome=" + nome + ", sigla=" + sigla + ", valor=" + valor + '}';
    }
}
