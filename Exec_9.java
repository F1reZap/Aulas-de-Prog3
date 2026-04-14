public class FilaCircular {
    private int[] Q;
    private int head;
    private int tail;
    private int n;
    private int totalElementos;

    public FilaCircular(int tamanho) {
        this.n = tamanho;
        this.Q = new int[n];
        this.head = 0;
        this.tail = 0;
        this.totalElementos = 0;
    }

    //ENFILEIRAR 
    public void enfileirar(int x) {
        if (totalElementos == n) {
            System.out.println("!ERRO!: Overflow (Fila cheia)");
            return;
        }
        Q[tail] = x;
        tail = (tail + 1) % n; 
        totalElementos++;
    }

    //DESENFILEIRAR 
    public Integer desenfileirar() {
        if (totalElementos == 0) {
            System.out.println("!ERRO!: Underflow (Fila vazia)");
            return null;
        }
        int x = Q[head];
        head = (head + 1) % n; 
        totalElementos--;
        return x;
    }

    public boolean estaVazia() {
        return totalElementos == 0;
    }

    public boolean estaCheia() {
        return totalElementos == n;
    }
}