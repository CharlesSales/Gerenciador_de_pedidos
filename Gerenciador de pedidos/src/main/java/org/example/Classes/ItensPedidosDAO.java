package org.example.Classes;

import org.example.Conexao.Conexao;
import org.example.Conexao.ConexaoMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItensPedidosDAO {
    private final Conexao conexao;

    public ItensPedidosDAO() {
        this.conexao = new ConexaoMySQL();
    }

    public String salvarIP(ItensPedidos itensP){
        String sql = "insert into ItensPedidos(pedidoID, descrição, quantidade, preço) values (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = conexao.getConnection().prepareStatement(sql);
            addValor(preparedStatement, itensP);
            preparedStatement.executeUpdate();
            return "Feito";
        }
        catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }
    public List<ItensPedidos> listar() {
        String sql = "select * from ItensPedidos";
        List<ItensPedidos> listaDosPedidos = new ArrayList<>();

        try {
            ResultSet result = conexao.getConnection().prepareStatement(sql).executeQuery();
            while (result.next()) {
                listaDosPedidos.add(getIP(result));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return listaDosPedidos;


    }
    public void updateColumnDescricao(String nomeColuna, Object novoValor, int id) throws SQLException {
        String sql = String.format("UPDATE itenspedidos SET %s = ? WHERE (ID = ?)" , nomeColuna);
        Connection conn = conexao.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, novoValor);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            stmt.close();
            stmt.close();
        }
        catch (SQLException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
    private void addValor(PreparedStatement preparedStatement, ItensPedidos itensP) throws SQLException {
        preparedStatement.setInt(1, itensP.getPedidoID());
        preparedStatement.setString(2, itensP.getDescricao());
        preparedStatement.setInt(3, itensP.getQuantidade());
        preparedStatement.setDouble(4, itensP.getPreco());
    }
    private  ItensPedidos getIP(ResultSet result) throws SQLException {
        var iP = new ItensPedidos();
        iP.setId(result.getInt("ID"));
        iP.setDescricao(result.getString("Descrição"));
        iP.setPreco(result.getInt("Preço"));
        iP.setQuantidade(result.getInt("Quantidade"));
        iP.setPedidoID(result.getInt("PedidoID"));
        return iP;
    }
}
