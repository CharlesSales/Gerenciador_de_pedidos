package org.example;

import org.example.Classes.Estoque;
import org.example.Classes.Mesa;
import org.example.Classes.Pedidos;
import org.example.Conexao.Conexao;
import org.example.Conexao.ConexaoMySQL;
import org.example.DAO.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Conexao conn = new ConexaoMySQL();
        EstoqueDAO estoque = new EstoqueDAO();
        PedidosDAO pedido = new PedidosDAO();
        ItensPedidosDAO itensDoPedido = new ItensPedidosDAO();
        MesaDAO mesa = new MesaDAO();
        FuncionariosDAO funcionarios = new FuncionariosDAO();

        System.out.println(pedido.listar());


 }

}
