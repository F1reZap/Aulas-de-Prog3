
/**
 * Uma pilha cujo acesso aos elementos é LIFO.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Pilha
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private int[] elementos;
    private int topo;

    /**
     * Construtor para objetos da classe Pilha
     */
    public Pilha(int capacidade)
    {
        elementos = new int[capacidade];
        topo = -1;
    }

    public void push(int item) {
        topo++;
        elementos[topo]=item;

    }
    
    public int pop() {
        //return 0;
        if(topo == -1){
            return 0;
        }
        else{
            topo = topo-1;
            return elementos[topo];
        }
    }
    
    public boolean isVazia() {
        return false;
    }
    
    public boolean isCheia() {
        return false;
    }
    
    public int peek() {
        return 0;
    }
}
