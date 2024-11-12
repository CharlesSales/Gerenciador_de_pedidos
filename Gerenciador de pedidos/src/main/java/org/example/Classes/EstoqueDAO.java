package org.example.Classes;

import org.example.Conexao.Conexao;
import org.example.Conexao.ConexaoMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {
        private final Conexao conexao;


        public EstoqueDAO() {
            this.conexao = new ConexaoMySQL();
        }
        public void criarEstoque(String nome, int quantidade, int unidade){
            List<Estoque> listadeEstoque = new ArrayList<>();
            var estoque = new Estoque(nome, quantidade, unidade);
            listadeEstoque.add(estoque);
        }
        public String salvarEstoque(Estoque estoque){
            String sql = "insert into Estoque(nome, quantidade, unidade) values (? ,? ,?)";
            try {
                PreparedStatement preparedStatement = conexao.getConnection().prepareStatement(sql);
                addValor(preparedStatement, estoque);
                preparedStatement.executeUpdate();
                return "Feito";
            }
            catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
            return null;
        }
        public List<Estoque> listar(){
            String sql = "select * from estoque";
            List<Estoque> listadeEstoque = new ArrayList<>();

            try{
                ResultSet result = conexao.getConnection().prepareStatement(sql).executeQuery();
                while(result.next()){
                    listadeEstoque.add(getEstoque(result));
                }
            }
            catch (SQLException e){
                System.out.println("Erro: " + e.getMessage());
            }
            return listadeEstoque;
        }
    public void updateColumnDescricao(String nomeColuna, Object novoValor, int id) throws SQLException {
        String sql = String.format("UPDATE Estoque SET %s = ? WHERE (ID = ?)" , nomeColuna);
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

    private void addValor(PreparedStatement preparedStatement, Estoque estoque) throws SQLException{
        preparedStatement.setString(1, estoque.getNome());
        preparedStatement.setInt(2, estoque.getQuantidade());
        preparedStatement.setInt(3, estoque.getUnidade());
    }

        private  Estoque getEstoque(ResultSet result) throws SQLException {
            var estoque = new Estoque();
            estoque.setID(result.getInt("ID"));
            estoque.setNome(result.getString("Nome"));
            estoque.setQuantidade(result.getInt("quantidade"));
            estoque.setUnidade(result.getInt("unidade"));
            return estoque;
        }
}
