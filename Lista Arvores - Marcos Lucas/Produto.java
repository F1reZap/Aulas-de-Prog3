class Produto {
    int codigo;
    String descricao;
    double preco;
    int quantidade;

    public Produto(int codigo, String descricao, double preco, int quantidade) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "[Cód: " + codigo + "] " + descricao + " | Preço: R$" + preco + " | Qtd: " + quantidade;
    }
}

class ArvoreEstoque {
    Produto produto;
    ArvoreEstoque esquerdo, direito;

    public ArvoreEstoque(Produto produto) {
        this.produto = produto;
    }

    // Cadastrar (Inserir por Código)
    public void cadastrar(Produto novoProd) {
        if (novoProd.codigo < this.produto.codigo) {
            if (this.esquerdo == null) this.esquerdo = new ArvoreEstoque(novoProd);
            else this.esquerdo.cadastrar(novoProd);
        } else if (novoProd.codigo > this.produto.codigo) {
            if (this.direito == null) this.direito = new ArvoreEstoque(novoProd);
            else this.direito.cadastrar(novoProd);
        }
    }

    // Buscar por Código
    public Produto buscar(int codigo) {
        if (this.produto.codigo == codigo) return this.produto;
        if (codigo < this.produto.codigo && this.esquerdo != null) return this.esquerdo.buscar(codigo);
        if (codigo > this.produto.codigo && this.direito != null) return this.direito.buscar(codigo);
        return null;
    }

    // Listar todos por ordem de código (Infixa)
    public void listarTodos() {
        if (this.esquerdo != null) this.esquerdo.listarTodos();
        System.out.println(this.produto);
        if (this.direito != null) this.direito.listarTodos();
    }

    // Listar todos com baixo estoque (< 7 unidades)
    public void listarBaixoEstoque() {
        if (this.esquerdo != null) this.esquerdo.listarBaixoEstoque();
        if (this.produto.quantidade < 7) {
            System.out.println(this.produto);
        }
        if (this.direito != null) this.direito.listarBaixoEstoque();
    }
}