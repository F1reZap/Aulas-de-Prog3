class Tarefa {
    String descricao;
    boolean concluida;
    Tarefa filhoEsquerdo;
    Tarefa irmaoDireito;   

    public Tarefa(String descricao) {
        this.descricao = descricao;
        this.concluida = false;
        this.filhoEsquerdo = null;
        this.irmaoDireito = null;
    }
}

class SistemaTarefas {
    Tarefa raizPrincipal = null;

    // Adiciona uma tarefa raiz ou uma subtarefa a um pai específico
    public void adicionarTarefa(String descPai, String descNova) {
        Tarefa nova = new Tarefa(descNova);
        if (raizPrincipal == null) {
            raizPrincipal = nova;
            return;
        }
        if (descPai == null) {
            // Adiciona como nova tarefa no nível raiz
            Tarefa atual = raizPrincipal;
            while (atual.irmaoDireito != null) atual = atual.irmaoDireito;
            atual.irmaoDireito = nova;
        } else {
            // Busca o pai e insere como subtarefa
            Tarefa pai = buscarTarefa(raizPrincipal, descPai);
            if (pai != null) {
                if (pai.filhoEsquerdo == null) {
                    pai.filhoEsquerdo = nova;
                } else {
                    Tarefa atual = pai.filhoEsquerdo;
                    while (atual.irmaoDireito != null) atual = atual.irmaoDireito;
                    atual.irmaoDireito = nova;
                }
            }
        }
    }

    // Remove uma tarefa e todas as suas subtarefas
    public void removerTarefa(String descricao) {
        if (raizPrincipal == null) return;

        if (raizPrincipal.descricao.equals(descricao)) {
            raizPrincipal = raizPrincipal.irmaoDireito;
            return;
        }

        removerBusca(raizPrincipal, descricao);
    }

    private void removerBusca(Tarefa atual, String descricao) {
        if (atual == null) return;

        // Verifica os filhos do nó atual
        if (atual.filhoEsquerdo != null && atual.filhoEsquerdo.descricao.equals(descricao)) {
            atual.filhoEsquerdo = atual.filhoEsquerdo.irmaoDireito;
            return;
        }

        // Verifica os irmãos do nó atual
        if (atual.irmaoDireito != null && atual.irmaoDireito.descricao.equals(descricao)) {
            atual.irmaoDireito = atual.irmaoDireito.irmaoDireito;
            return;
        }

        removerBusca(atual.filhoEsquerdo, descricao);
        removerBusca(atual.irmaoDireito, descricao);
    }

    // Altera o estado de conclusão
    public void marcarConcluida(String descricao, boolean status) {
        Tarefa t = buscarTarefa(raizPrincipal, descricao);
        if (t != null) {
            t.concluida = status;
        }
    }

    // Busca auxiliar recursiva
    private Tarefa buscarTarefa(Tarefa atual, String descricao) {
        if (atual == null) return null;
        if (atual.descricao.equals(descricao)) return atual;

        Tarefa achouNoFilho = buscarTarefa(atual.filhoEsquerdo, descricao);
        if (achouNoFilho != null) return achouNoFilho;

        return buscarTarefa(atual.irmaoDireito, descricao);
    }

    // Exibir o painel de tarefas
    public void exibirTarefas(Tarefa atual, String indentacao) {
        if (atual == null) return;
        String status = atual.concluida ? "[X]" : "[ ]";
        System.out.println(indentacao + status + " " + atual.descricao);

        exibirTarefas(atual.filhoEsquerdo, indentacao + "   ");
        exibirTarefas(atual.irmaoDireito, indentacao);
    }

    public void mostrarPainel() {
        exibirTarefas(raizPrincipal, "");
    }
}