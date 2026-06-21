public class Arvore {
    int chave;
    Arvore esquerda, direita, pai;

    public Arvore(int chave) {
        this.chave = chave;
        this.esquerda = null;
        this.direita = null;
        this.pai = null;
    }

    // BUSCAR (Recursivo)
    public Arvore buscar(int chave) {
        if (this.chave == chave) return this;
        if (chave < this.chave && this.esquerda != null) return this.esquerda.buscar(chave);
        if (chave > this.chave && this.direita != null) return this.direita.buscar(chave);
        return null;
    }

    // INSERIR (Recursivo)
    public void inserir(int novaChave) {
        if (novaChave < this.chave) {
            if (this.esquerda == null) {
                this.esquerda = new Arvore(novaChave);
                this.esquerda.pai = this;
            } else {
                this.esquerda.inserir(novaChave);
            }
        } else if (novaChave > this.chave) {
            if (this.direita == null) {
                this.direita = new Arvore(novaChave);
                this.direita.pai = this;
            } else {
                this.direita.inserir(novaChave);
            }
        }
    }

    // MINIMO
    public Arvore minimo() {
        if (this.esquerda == null) return this;
        return this.esquerda.minimo();
    }

    // MAXIMO
    public Arvore maximo() {
        if (this.direita == null) return this;
        return this.direita.maximo();
    }

    // SUCESSOR
    public Arvore sucessor() {
        if (this.direita != null) {
            return this.direita.minimo();
        }
        Arvore atual = this;
        Arvore p = this.pai;
        while (p != null && atual == p.direita) {
            atual = p;
            p = p.pai;
        }
        return p;
    }

    // ANTECESSOR
    public Arvore antecessor() {
        if (this.esquerda != null) {
            return this.esquerda.maximo();
        }
        Arvore atual = this;
        Arvore p = this.pai;
        while (p != null && atual == p.esquerda) {
            atual = p;
            p = p.pai;
        }
        return p;
    }

    // IMPRIMIR-INFIXA (Esquerda, Raiz, Direita)
    public void imprimirInfixa() {
        if (this.esquerda != null) this.esquerda.imprimirInfixa();
        System.out.print(this.chave + " ");
        if (this.direita != null) this.direita.imprimirInfixa();
    }

    // IMPRIMIR-PREFIXA (Raiz, Esquerda, Direita)
    public void imprimirPrefixa() {
        System.out.print(this.chave + " ");
        if (this.esquerda != null) this.esquerda.imprimirPrefixa();
        if (this.direita != null) this.direita.imprimirPrefixa();
    }

    // IMPRIMIR-POSFIXA (Esquerda, Direita, Raiz)
    public void imprimirPosfixa() {
        if (this.esquerda != null) this.esquerda.imprimirPosfixa();
        if (this.direita != null) this.direita.imprimirPosfixa();
        System.out.print(this.chave + " ");
    }
}