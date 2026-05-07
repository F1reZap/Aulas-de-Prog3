
/**
 * Escreva uma descrição da classe Ficha aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Ficha {
    private int valor;
    public Ficha() {
        this.valor = valor;
    }
    public int getValor() {
        return valor;
    }
    public boolean isPar(){
        return valor%2 == 0;
    }
    public boolean isImpar() {
        return !isPar();
    }
}