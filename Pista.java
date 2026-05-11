// Pista que não é de dança (in-felizmente?)
public class Pista {
    private int numero;
    private TipoPista tipo;
    private Aeronave aeronaveEmUso; // Aeronave que ta na pista
    
    public enum TipoPista {
        POUSO, DECOLAGEM, MISTA
    }
    
    public Pista(int numero, TipoPista tipo) {
        this.numero = numero;
        this.tipo = tipo;
        this.aeronaveEmUso = null;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public TipoPista getTipo() {
        return tipo;
    }
    
    public boolean estaLivre() {
        return aeronaveEmUso == null;
    }
    
    public void usarPista(Aeronave aeronave) {
        this.aeronaveEmUso = aeronave;
    }
    
    public Aeronave liberarPista() {
        Aeronave temp = aeronaveEmUso;
        this.aeronaveEmUso = null;
        return temp;
    }
    
    public Aeronave getAeronaveEmUso() {
        return aeronaveEmUso;
    }
}
 