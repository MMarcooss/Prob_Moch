package Prob_Estoque;

public class Produto {
    private String nome;
    private double valor=0;
    private int peso=0;
    private int qnt=0;
    private int pesoTot=0;

    public Produto(String nome,double valor, int peso, int qnt) {
        this.nome=nome;
        this.valor = valor;
        this.peso = peso;
        this.qnt = qnt;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public int getPeso() {
        return peso;
    }

    public int getQnt() {
        return qnt;
    }
    public int calcTotal(){
        pesoTot=peso*qnt;
        return pesoTot;
    }
    public double getValorPorPeso() {
        return valor / peso;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "qnt=" + getQnt() +
                ", peso=" + getPeso() +
                ", valor=" + getValor() +
                ", nome='" + getNome() + '\'' +
                '}';
    }
}
