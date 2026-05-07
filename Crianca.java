
/**
 * Escreva uma descrição da classe Criança aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Crianca
{
    private String nome;
    private Ficha ficha;
    
    public Crianca(String nome, Ficha ficha) {
        this.nome = nome;
        this.ficha = ficha;
    }
    public String getNome(){
        return nome;
    }
    public Ficha getFicha() {
        return ficha;
    }
}