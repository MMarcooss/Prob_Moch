package Prob_Estoque;

import java.util.ArrayList;
import java.util.List;

public class TesteOtimizacao {
    public static void main(String[] args) {
        Produto p1 = new Produto("Produto A", 10, 5, 2);
        Produto p2 = new Produto("Produto B", 20, 10, 1);
        Produto p3 = new Produto("Produto C", 15, 8, 3);
        Produto p4 = new Produto("Produto D", 12, 3, 4);
        Produto p5 = new Produto("Produto E", 5, 1, 10);
        Produto p6 = new Produto("Produto F", 9.99, 4, 3);
        Produto p7 = new Produto("Produto G", 15.99, 7, 5);
        Produto p8 = new Produto("Produto H", 12, 6, 4);
        Produto p9 = new Produto("Produto I", 18, 5, 6);
        Produto p10 = new Produto("Produto J", 17, 3, 4);
        Produto p11 = new Produto("Produto H", 1000, 50, 1);

        List<Produto> todosProdutos = new ArrayList<>();
        todosProdutos.add(p1);
        todosProdutos.add(p2);
        todosProdutos.add(p3);
        todosProdutos.add(p4);
        todosProdutos.add(p5);
        todosProdutos.add(p6);
        todosProdutos.add(p7);
        todosProdutos.add(p8);
        todosProdutos.add(p9);
        todosProdutos.add(p10);
        todosProdutos.add(p11);

        Estoque estoque = new Estoque(50);
        estoque.otimizarEstoque(todosProdutos);

        System.out.println("Estoque Otimizado:");
        System.out.println(estoque);
    }
}
