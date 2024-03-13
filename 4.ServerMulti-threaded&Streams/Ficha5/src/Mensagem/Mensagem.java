//author: Daniel Santos
package Mensagem;

import java.io.Serializable;

public class Mensagem implements Serializable {
    
    private int operacao;
    //0. login
    //1. mensagem
    //2. logout
    //3. login ok
    //4. login nok
    private String nome;
    private String texto;
    
    public Mensagem(int operacao, String nome, String texto){
        this.operacao = operacao;
        this.nome = nome;
        this.texto = texto;
    }
    
    public Mensagem(){
        
    }

    public int getOperacao() {
        return operacao;
    }

    public void setOperacao(int operacao) {
        this.operacao = operacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Mensagem{" + "operacao=" + operacao + ", nome=" + nome + ", texto=" + texto + '}';
    }
    
}
