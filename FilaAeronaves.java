import java.util.*;

// Fila de espera 
public class FilaAeronaves {
    private Queue<Aeronave> fila;
    private String nome;
    
    public FilaAeronaves(String nome) {
        this.nome = nome;
        this.fila = new LinkedList<>();
    }
    
    public void adicionar(Aeronave aeronave) {
        fila.add(aeronave);
    }
    
    public Aeronave remover() {
        return fila.poll();
    }
    
    public Aeronave peek() {
        return fila.peek();
    }
    
    public int getTamanho() {
        return fila.size();
    }
    
    public boolean estaVazia() {
        return fila.isEmpty();
    }
    
    public void imprimir() {
        if (fila.isEmpty()) {
            System.out.println(nome + ": [VAZIA]");
        } else {
            System.out.println(nome + ": " + fila);
        }
    }
    
    public List<Aeronave> getTodasAeronaves() {
        return new ArrayList<>(fila);
    }
}
