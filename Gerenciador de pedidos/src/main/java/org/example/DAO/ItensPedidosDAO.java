package org.example.DAO;

import org.example.Classes.ItensPedidos;
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

    public void criarItens(int idPedido, String descricao, int quantidade, double preco){
        ItensPedidos ip = new ItensPedidos(idPedido, descricao, quantidade, preco);
        salvarItens(ip);
    }
    private String salvarItens(ItensPedidos itensP){
        String sql = "insert into IPedidos(pedidoID, descrição, quantidade, preço) values (?, ?, ?, ?)";
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
        String sql = "select * from IPedidos";
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
    public void editarColuna(String nomeColuna, Object novoValor, int id) throws SQLException {
        String sql = String.format("UPDATE ipedidos SET %s = ? WHERE (ID = ?)" , nomeColuna);
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
    public void deletarItens(int id) throws SQLException {
        String sql = String.format("DELETE FROM ipedidos WHERE (ID = ?);");
        Connection conn = conexao.getConnection();
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
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
        iP.setDescricao(result.getString("Descricao"));
        iP.setPreco(result.getInt("Preco"));
        iP.setQuantidade(result.getInt("Quantidade"));
        iP.setPedidoID(result.getInt("PedidoID"));
        return iP;
    }
}
