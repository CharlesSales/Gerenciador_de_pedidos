package org.example.DAO;

import org.example.Classes.Mesa;
import org.example.Conexao.Conexao;
import org.example.Conexao.ConexaoMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MesaDAO {
    private final Conexao conexao;


    public MesaDAO() {
        this.conexao = new ConexaoMySQL();
    }

    public void criarMesa(){

    }
    public String salvarMesa(Mesa mesa){
        String sql = "insert into Mesa(status) values (? )";
        try {
            PreparedStatement preparedStatement = conexao.getConnection().prepareStatement(sql);
            addValor(preparedStatement, mesa);
            preparedStatement.executeUpdate();
            return "Feito";
        }
        catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }
    public List<Mesa> listar(){
        String sql = "select * from mesa";
        List<Mesa> listaDeMesas = new ArrayList<>();

        try{
            ResultSet result = conexao.getConnection().prepareStatement(sql).executeQuery();
            while(result.next()){
                listaDeMesas.add(getMesas(result));
            }
        }
        catch (SQLException e){
            System.out.println("Erro: " + e.getMessage());
        }
        return listaDeMesas;
    }
    public Mesa buscarPorId(int id){
        String sql = String.format("select * from mesa where id = %d",id);
        try {
            ResultSet result = conexao.getConnection().prepareStatement(sql).executeQuery();
            while(result.next()){
                return getMesas(result);
            }
        } catch (SQLException e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
        }
        return null;
    }

    public void updateColumnDescricao(String nomeColuna, Object novoValor, int id) throws SQLException {
        String sql = String.format("UPDATE Mesa SET %s = ? WHERE (ID = ?)" , nomeColuna);
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
    public void deletarMesa(int id) throws SQLException {
        String sql = String.format("DELETE FROM Mesa WHERE (ID = ?);");
        Connection conn = conexao.getConnection();
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private void addValor(PreparedStatement preparedStatement, Mesa mesa) throws SQLException{
        preparedStatement.setString(1, mesa.getStatus());

    }

    private  static Mesa getMesas(ResultSet result) throws SQLException {
        var mesa = new Mesa();
        mesa.setId(result.getInt("ID"));
        mesa.setStatus(result.getString("Status"));
        return mesa;
    }
}
