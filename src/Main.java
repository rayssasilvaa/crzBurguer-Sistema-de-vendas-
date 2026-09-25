import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Carrinho carrinho = new Carrinho();
        PedidoDAO pedidoDAO = new PedidoDAO();


        int opcao;

        do {
            System.out.println("\n==============================");
            System.out.println("         CRZ BURGUER");
            System.out.println("==============================");
            System.out.println("1 - Ver produtos");
            System.out.println("2 - Adicionar no carrinho");
            System.out.println("3 - Ver carrinho");
            System.out.println("4 - Finalizar compra");
            System.out.println("0 - Sair");
            System.out.println("==============================");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    List<Produto> produtos = produtoDAO.listarProduto();

                    System.out.println("\n==============================");
                    System.out.println("        CRZ BURGUER");
                    System.out.println("==============================");

                    System.out.println("\n---------- LANCHES ----------");

                    for (Produto produto : produtos) {
                        if (produto.getCategoria().equalsIgnoreCase("lanche")) {
                            System.out.println(produto);
                        }
                    }

                    System.out.println("\n------- REFRIGERANTES -------");

                    for (Produto produto : produtos) {
                        if (produto.getCategoria().equalsIgnoreCase("refrigerante")) {
                            System.out.println(produto);
                        }
                    }

                    System.out.println("\n---- BEBIDAS ALCOÓLICAS ----");

                    for (Produto produto : produtos) {
                        if (produto.getCategoria().equalsIgnoreCase("alcoolico")) {
                            System.out.println(produto);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Digite o ID do produto: ");
                    int id = scanner.nextInt();

                    produtos = produtoDAO.listarProduto();

                    for (Produto produto : produtos) {
                        if (produto.getId() == id) {
                            carrinho.addProduto(produto);
                            System.out.println(produto.getNome() + " adicionado ao carrinho!");
                            break;
                        }
                    }
                    break;

                case 3:
                    carrinho.listarCarrinho();
                    break;

                case 4:
                    pedidoDAO.finalizarPedido(carrinho);
                    break;

                case 0:
                    System.out.println(
                            "Encerrando o sistema..."
                    );
                    break;

                default:
                    System.out.println(
                            "Opção inválida!"
                    );
            }

        } while (opcao != 0);
        scanner.close();
    }
}