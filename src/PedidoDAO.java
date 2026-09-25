import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PedidoDAO {

    public void finalizarPedido(Carrinho carrinho) {

        if (carrinho.getItemsCarrinho().isEmpty()) {
            System.out.println("Carrinho vazio!");
            return;
        }

        String sqlPedido = """
                INSERT INTO pedido (valor_total, status)
                VALUES (?, ?)
                RETURNING id_pedido
                """;

        String sqlItem = """
                INSERT INTO item_pedido
                (id_pedido, id_produto, quantidade, preco_unit)
                VALUES (?, ?, ?, ?)
                """;

        Connection connection = null;

        try {

            connection = ConexaoBD.conectar();

            // Começa a transação
            connection.setAutoCommit(false);

            double total = carrinho.calcularTotal();

            PreparedStatement pedidoStatement =
                    connection.prepareStatement(sqlPedido);

            pedidoStatement.setDouble(1, total);
            pedidoStatement.setString(2, "finalizado");

            ResultSet resultSet =
                    pedidoStatement.executeQuery();

            resultSet.next();

            int idPedido =
                    resultSet.getInt("id_pedido");

            //cria pedido
            PreparedStatement itemStatement =
                    connection.prepareStatement(sqlItem);

            for (ItemCarrinho item : carrinho.getItemsCarrinho()) {

                Produto produto = item.getProduto();

                itemStatement.setInt(1, idPedido);
                itemStatement.setInt(2, produto.getId());
                itemStatement.setInt(3, item.getQuantidade());
                itemStatement.setDouble(4, produto.getValor());

                itemStatement.executeUpdate();
            }

            connection.commit();

            System.out.println();
            System.out.println("==============================");
            System.out.println("      COMPRA FINALIZADA!");
            System.out.println("==============================");
            System.out.println("Pedido #" + idPedido);
            System.out.printf("Total: R$ %.2f%n", total);

            resultSet.close();
            pedidoStatement.close();
            itemStatement.close();

            // Limpa o carrinho depois da compra
            carrinho.limpar();

        } catch (Exception e) {

            e.printStackTrace();

            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (Exception erroRollback) {
                erroRollback.printStackTrace();
            }

        } finally {

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}