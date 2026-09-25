public class Produto {
    private int id;
    private String nome;
    private double valor;
    private int quantidade;
    private String categoria;

    public Produto(int id, String nome, double valor, int quantidade, String categoria) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return id + " - " + nome +
                " | R$ " + String.format("%.2f", valor);
    }
}