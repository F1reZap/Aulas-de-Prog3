class Nodo {
    int chave;
    Nodo esquerdo, direito, pai;

    public Nodo(int chave) {
        this.chave = chave;
    }
}

class ArvoreIterativa {
    Nodo raiz;

    // BUSCAR (Iterativo)
    public Nodo buscar(int chave) {
        Nodo atual = raiz;
        while (atual != null && atual.chave != chave) {
            if (chave < atual.chave) atual = atual.esquerdo;
            else atual = atual.direito;
        }
        return atual;
    }

    // INSERIR (Iterativo)
    public void inserir(int chave) {
        Nodo novo = new Nodo(chave);
        Nodo pai = null;
        Nodo atual = raiz;

        while (atual != null) {
            pai = atual;
            if (chave < atual.chave) atual = atual.esquerdo;
            else if (chave > atual.chave) atual = atual.direito;
            else return;
        }

        novo.pai = pai;
        if (pai == null) {
            raiz = novo;
        } else if (chave < pai.chave) {
            pai.esquerdo = novo;
        } else {
            pai.direito = novo;
        }
    }

    // MINIMO
    public Nodo minimo(Nodo n) {
        if (n == null) return null;
        while (n.esquerdo != null) n = n.esquerdo;
        return n;
    }

    // MAXIMO
    public Nodo maximo(Nodo n) {
        if (n == null) return null;
        while (n.direito != null) n = n.direito;
        return n;
    }

    // SUCESSOR
    public Nodo sucessor(Nodo n) {
        if (n == null) return null;
        if (n.direito != null) return minimo(n.direito);
        Nodo p = n.pai;
        while (p != null && n == p.direito) {
            n = p;
            p = p.pai;
        }
        return p;
    }

    // ANTECESSOR
    public Nodo antecessor(Nodo n) {
        if (n == null) return null;
        if (n.esquerdo != null) return maximo(n.esquerdo);
        Nodo p = n.pai;
        while (p != null && n == p.esquerdo) {
            n = p;
            p = p.pai;
        }
        return p;
    }

    // IMPRIMIR-INFIXA (Iterativo usando propriedades do Sucessor)
    public void imprimirInfixa() {
        Nodo atual = minimo(raiz);
        while (atual != null) {
            System.out.print(atual.chave + " ");
            atual = sucessor(atual);
        }
        System.out.println();
    }

    // IMPRIMIR-PREFIXA (Iterativo simulando a pilha)
    public void imprimirPrefixa() {
        Nodo atual = raiz;
        while (atual != null) {
            System.out.print(atual.chave + " ");
            if (atual.esquerdo != null) {
                atual = atual.esquerdo;
            } else if (atual.direito != null) {
                atual = atual.direito;
            } else {
                Nodo p = atual.pai;
                while (p != null && (p.direito == null || p.direito == atual)) {
                    atual = p;
                    p = p.pai;
                }
                if (p == null) break;
                atual = p.direito;
            }
        }
        System.out.println();
    }

    // IMPRIMIR-POSFIXA (Iterativo sem estruturas prontas)
    public void imprimirPosfixa() {
        Nodo atual = raiz;
        Nodo ultimoVisitado = null;
        while (atual != null) {
            if (atual.esquerdo != null && ultimoVisitado != atual.esquerdo && ultimoVisitado != atual.direito) {
                atual = atual.esquerdo;
            } else if (atual.direito != null && ultimoVisitado != atual.direito) {
                atual = atual.direito;
            } else {
                System.out.print(atual.chave + " ");
                ultimoVisitado = atual;
                atual = atual.pai;
            }
        }
        System.out.println();
    }
}