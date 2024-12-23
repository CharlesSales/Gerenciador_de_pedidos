package org.example.DAO;

import org.example.Classes.Pedidos;
import org.example.Conexao.Conexao;
import org.example.Conexao.ConexaoMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidosDAO {
    private final Conexao conexao;

    public PedidosDAO() {
        this.conexao = new ConexaoMySQL();
    }

    public void criarPedidos(int mesaID, int funcionarioID, String data, String status, int estoqueID){
        var pedidos = new Pedidos(mesaID, funcionarioID, data, status, estoqueID);
        salvarPedido(pedidos);
    }

    private String salvarPedido(Pedidos pedidos){
        String sql = "insert into pedido(mesaID, funcionarioID, dataPedido, Status_pedido, estoqueID) values (?, ?, ?, ?, ?)";
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
    public List<Pedidos> listar(){
        String sql = "select * from Pedido";
        List<Pedidos> listaDePedidos = new ArrayList<>();
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

    public void editarColuna(String nomeColuna, Object novoValor, int id) throws SQLException {
        String sql = String.format("UPDATE Pedido SET %s = ? WHERE (ID = ?)" , nomeColuna);
        Connection conn = conexao.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, novoValor);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
    public void deletarPedido(int id) throws SQLException {
        String sql = String.format("DELETE FROM Pedido WHERE (ID = ?);");
        Connection conn = conexao.getConnection();
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private void addValor(PreparedStatement preparedStatement, Pedidos pedidos) throws SQLException{
        preparedStatement.setInt(1, pedidos.getMesaId());
        preparedStatement.setInt(2, pedidos.getFuncionarioID());
        preparedStatement.setString(3, pedidos.getData());
        preparedStatement.setString(4, pedidos.getStatus());
        preparedStatement.setInt(5, pedidos.getEstoqueID());
    }

    private  Pedidos getPedidos(ResultSet result) throws SQLException {
        var pedidos = new Pedidos();
        pedidos.setId(result.getInt("ID"));
        pedidos.setMesaId(result.getInt("mesaID"));
        pedidos.setFuncionarioID(result.getInt("funcionarioID"));
        pedidos.setData(result.getString("DataPedido"));
        pedidos.setStatus(result.getString("Status_pedido"));
        pedidos.setEstoqueID(result.getInt("estoqueID"));
        return pedidos;
    }
}
