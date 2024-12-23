package org.example.DAO;

import org.example.Classes.Funcionarios;
import org.example.Conexao.Conexao;
import org.example.Conexao.ConexaoMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionariosDAO {
    private final Conexao conexao;

    public FuncionariosDAO() {
        this.conexao = new ConexaoMySQL();
    }
    public void criarFuncionario(String nome, String cargo, double salario){
        Funcionarios func = new Funcionarios(nome, cargo, salario);
        salvarFuncionario(func);
    }
    private String salvarFuncionario(Funcionarios func){
        String sql = "insert into Funcionarios(nome, cargo, salario) values (? ,? ,?)";
        try {
            PreparedStatement preparedStatement = conexao.getConnection().prepareStatement(sql);
            addValor(preparedStatement, func);
            preparedStatement.executeUpdate();
            return "Feito";
        }
        catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }
    public List<Funcionarios> listar(){
        String sql = "select * from Funcionarios";
        List<Funcionarios> listaDeFuncionarios = new ArrayList<>();

        try{
            ResultSet result = conexao.getConnection().prepareStatement(sql).executeQuery();
            while(result.next()){
                listaDeFuncionarios.add(getFuncionario(result));
            }
        }
        catch (SQLException e){
            System.out.println("Erro: " + e.getMessage());
        }
        return listaDeFuncionarios;
    }
    public void editarColuna(String nomeColuna, Object novoValor, int id) throws SQLException {
        String sql = String.format("UPDATE Funcionarios SET %s = ? WHERE (ID = ?)" , nomeColuna);
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
    public void deletarFuncionario(int id) throws SQLException {
        String sql = String.format("DELETE FROM Funcionarios WHERE (ID = ?);");
        Connection conn = conexao.getConnection();
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    private void addValor(PreparedStatement preparedStatement, Funcionarios func) throws SQLException{
        preparedStatement.setString(1, func.getNome());
        preparedStatement.setString(2, func.getCargo());
        preparedStatement.setDouble(3, func.getSalario());
    }

    private  Funcionarios getFuncionario(ResultSet result) throws SQLException {
        var func = new Funcionarios();
        func.setId(result.getInt("ID"));
        func.setNome(result.getString("Nome"));
        func.setCargo(result.getString("Cargo"));
        func.setSalario(result.getDouble("Salario"));
        return func;
    }
}
