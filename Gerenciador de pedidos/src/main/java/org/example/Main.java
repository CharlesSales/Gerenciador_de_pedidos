package org.example;

import org.example.Classes.*;
import org.example.Conexao.Conexao;
import org.example.Conexao.ConexaoMySQL;

public class Main {
    public static void main(String[] args) {
        Conexao conn = new ConexaoMySQL();
        var it = new ItensPedidosDAO();
        var pedidosDAO = new PedidosDAO();

        var pd1 = new ItensPedidos(7, " Pedido 7 ", 2, 25.00);

        it.salvarIP(pd1);

        System.out.println(it.listaDositens());
    }
}
