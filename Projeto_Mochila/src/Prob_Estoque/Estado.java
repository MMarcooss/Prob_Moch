package Prob_Estoque;
import Prob_Estoque.Produto;

import java.util.List;

public class Estado  {
    private List<Produto> produtos;
    private int pesoTotal;
    private double valorTotal;

    public Estado(List<Produto> produtos, int pesoTotal, double valorTotal) {
        this.produtos = produtos;
        this.pesoTotal = pesoTotal;
        this.valorTotal = valorTotal;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public int getPesoTotal() {
        return pesoTotal;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getF() {
        return valorTotal;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Prob_Estoque.Estado)) return false;
        Prob_Estoque.Estado other = (Prob_Estoque.Estado) obj;
        return produtos.equals(other.produtos);
    }

    @Override
    public int hashCode() {
        return produtos.hashCode();
    }
}
