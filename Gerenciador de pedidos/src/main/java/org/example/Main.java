package org.example;

import org.example.Classes.*;
import org.example.Conexao.Conexao;
import org.example.Conexao.ConexaoMySQL;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Conexao conn = new ConexaoMySQL();
        var it = new PedidosDAO();



        it.updateColumnDescricao("Status","preparando",8);

        System.out.println("Itens");
        System.out.println(it.listar() + "\n");


 }

}
