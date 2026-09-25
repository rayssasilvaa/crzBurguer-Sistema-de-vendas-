import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private final List<ItemCarrinho> itemsCarrinho = new ArrayList<>();

    public void addProduto(Produto produto) {

        for(ItemCarrinho i : itemsCarrinho){
            if (i.getProduto().getValor() == produto.getId()){
                i.aumentarQuantidade();
                return;
            }
        }
            itemsCarrinho.add(new ItemCarrinho(produto));
    }

    public void listarCarrinho(){
        if (itemsCarrinho.isEmpty()){
            System.out.println("Carrinho vazio.");
            return;
        }

        System.out.println("\n===== CARRINHO =====");

        double total = 0;

        for (ItemCarrinho i : itemsCarrinho){
            Produto produto = i.getProduto();
            int quantidade = i.getQuantidade();

            double subtotal = produto.getValor() * quantidade;

            System.out.printf(
                    "%s | Quantidade: %d | Subtotal: R$ %.2f%n",
                    produto.getNome(),
                    quantidade,
                    subtotal
            );

            total += subtotal;
        }

        System.out.println("---------------------");
        System.out.printf("TOTAL: R$%.2f\n", total);
    }

    public List<ItemCarrinho> getItemsCarrinho(){
        return itemsCarrinho;
    }

    public double calcularTotal(){
        double total = 0;

        for (ItemCarrinho i : itemsCarrinho){
            total += i.getProduto().getValor() * i.getQuantidade();
        }

        return total;
    }

    public void limpar(){
        itemsCarrinho.clear();
    }
}