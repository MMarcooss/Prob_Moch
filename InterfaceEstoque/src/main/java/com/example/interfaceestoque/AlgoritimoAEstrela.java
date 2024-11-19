package com.example.interfaceestoque;
import java.util.*;

public class AlgoritimoAEstrela {

    public static List<Produto> executar(List<Produto> todosProdutos, int capacidadeMaxima) {
        PriorityQueue<Estado> openList = new PriorityQueue<>(Comparator.comparingDouble(Estado::getF));
        Set<Estado> closedList = new HashSet<>();

        Estado inicial = new Estado(new ArrayList<>(), 0, 0);
        openList.add(inicial);

        Estado melhorSolucao = null;

        while (!openList.isEmpty()) {
            Estado atual = openList.poll();
            if (atual.getPesoTotal() > capacidadeMaxima) continue;

            if (melhorSolucao == null || atual.getValorTotal() > melhorSolucao.getValorTotal()) {
                melhorSolucao = atual;
            }

            for (Produto produto : todosProdutos) {
                if (!atual.getProdutos().contains(produto)) {
                    List<Produto> novosProdutos = new ArrayList<>(atual.getProdutos());
                    novosProdutos.add(produto);
                    int novoPeso = atual.getPesoTotal() + produto.calcTotal();
                    double novoValor = atual.getValorTotal() + produto.getValor();

                    Estado novoEstado = new Estado(novosProdutos, novoPeso, novoValor);
                    if (!closedList.contains(novoEstado)) {
                        openList.add(novoEstado);
                    }
                }
            }

            closedList.add(atual);
        }

        return melhorSolucao != null ? melhorSolucao.getProdutos() : new ArrayList<>();
    }
}

class Estado {
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
        if (!(obj instanceof Estado)) return false;
        Estado other = (Estado) obj;
        return produtos.equals(other.produtos);
    }

    @Override
    public int hashCode() {
        return produtos.hashCode();
    }
}
