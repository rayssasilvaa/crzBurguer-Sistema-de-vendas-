import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    public List<Produto> listarProduto() {
        List<Produto> produtos = new ArrayList<>();
        String sql = """
                SELECT id_produto, nome, valor, quantidade, categoria
                FROM produtos
                WHERE disponivel = true
                ORDER BY id_produto
                """;
        try {
            Connection connection = ConexaoBD.conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Produto produto = new Produto(
                        resultSet.getInt("id_produto"),
                        resultSet.getString("nome"),
                        resultSet.getDouble("valor"),
                        resultSet.getInt("quantidade"),
                        resultSet.getString("categoria")
                );

                produtos.add(produto);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return produtos;
    }
}
