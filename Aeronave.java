// Classe que representa uma aeronave
public class Aeronave {
    private int id;
    private int combustivel;
    private int tempoChegada;
    private int tempoSaida;
    
    public Aeronave(int id, int combustivel, int tempoChegada) {
        this.id = id;
        this.combustivel = combustivel;
        this.tempoChegada = tempoChegada;
        this.tempoSaida = -1; // -1 indica que ainda não saiu
    }
    
    public void decrementarCombustivel() {
        if (combustivel > 0) {
            combustivel--;
        }
    }
    
    public boolean temCombustvelCritico() {
        return combustivel <= 0;
    }
    
    public int getId() {
        return id;
    }
    
    public int getCombustivel() {
        return combustivel;
    }
    
    public int getTempoChegada() {
        return tempoChegada;
    }
    
    public int getTempoSaida() {
        return tempoSaida;
    }
    
    public void setTempoSaida(int tempo) {
        this.tempoSaida = tempo;
    }
    
    public int getTempoEspera() {
        if (tempoSaida == -1) {
            return -1; // Ainda não saiu
        }
        return tempoSaida - tempoChegada;
    }
    
    @Override
    public String toString() {
        return "Av[" + id + ",combustivel=" + combustivel + "]";
    }
}
