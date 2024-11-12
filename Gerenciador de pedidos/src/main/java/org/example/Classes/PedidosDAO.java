package org.example.Classes;

import org.example.Conexao.Conexao;
import org.example.Conexao.ConexaoMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidosDAO {
    private final Conexao conexao;
    List<Pedidos> listaDePedidos = new ArrayList<>();

    public PedidosDAO() {
        this.conexao = new ConexaoMySQL();
    }

    public void criarPedidos(int mesaID, int funcionarioID, String data, String status){
        var pedidos = new Pedidos(mesaID, funcionarioID, data, status);
        salvarPedido(pedidos);
    }

    public String salvarPedido(Pedidos pedidos){
        String sql = "insert into pedido(mesaID, funcionarioID, dataPedido, Status) values (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = conexao.getConnection().prepareStatement(sql);
            addValor(preparedStatement, pedidos);
            preparedStatement.executeUpdate();
            return "Feito";
        }
        catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }

    public String exibirListaDePedidos(){
        String sql = "select * from pedido";
        try {
            ResultSet result = conexao.getConnection().prepareStatement(sql).executeQuery();
            StringBuilder sb = new StringBuilder();
            while(result.next()){
                sb.append(getPedidos(result)).append("\n");
            }
            return sb.toString();
        } catch (SQLException e) {
            String.format("Error: %s", e.getMessage());
        }
        return "";
    }

    private void addValor(PreparedStatement preparedStatement, Pedidos pedidos) throws SQLException{
        preparedStatement.setInt(1, pedidos.getMesaId());
        preparedStatement.setInt(2, pedidos.getFuncionarioID());
        preparedStatement.setString(3, pedidos.getData());
        preparedStatement.setString(4, pedidos.getStatus());
    }

    public List<Pedidos> listaDePedidos(){
        String sql = "select * from Pedido";
        try{
            ResultSet result = conexao.getConnection().prepareStatement(sql).executeQuery();
            while(result.next()){
                listaDePedidos.add(getPedidos(result));
            }
        }
        catch (SQLException e){
            System.out.println("Erro: " + e.getMessage());
        }
        return listaDePedidos;
    }

    private  Pedidos getPedidos(ResultSet result) throws SQLException {
        var pedidos = new Pedidos();

        pedidos.setId(result.getInt("ID"));
        pedidos.setMesaId(result.getInt("mesaID"));
        pedidos.setFuncionarioID(result.getInt("funcionarioID"));
        pedidos.setData(result.getString("DataPedido"));
        pedidos.setStatus(result.getString("Status"));

        return pedidos;
    }
}
