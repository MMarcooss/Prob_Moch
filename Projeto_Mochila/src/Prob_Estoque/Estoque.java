package Prob_Estoque;

import java.util.List;

public class Estoque {
    private int capMax = 10;
    private int capUtil = 0;
    private List<Produto> produtos;

    public Estoque(int capMax) {
        this.capMax = capMax;
    }

    public int getCapMax() {
        return capMax;
    }

    public void setCapMax(int capMax) {
        this.capMax = capMax;
    }

    public void otimizarEstoque(List<Produto> todosProdutos) {
        List<Produto> produtosOtimizados = AlgoritimoAEstrela.executar(todosProdutos, capMax);
        this.produtos = produtosOtimizados; 
        this.capUtil = calcularCapacidadeUsada();
    }

    private int calcularCapacidadeUsada() {
        int totalPeso = 0;
        for (Produto produto : produtos) {
            totalPeso += produto.calcTotal();
        }
        return totalPeso;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Estoque Atualizado (Capacidade Máxima: ").append(capMax).append("):\n");
        for (Produto produto : produtos) {
            sb.append(produto).append("\n");
        }
        return sb.toString();
    }
}
