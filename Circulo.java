
/**
 * Escreva uma descrição da classe Circulo aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */

public class Circulo
{
    private Nodo primeiraCrianca;
    private Nodo criancaDoMomento;
    
    public Circulo(){
        primeiraCrianca = null;
        criancaDoMomento = null;
    }
    public void adicionar(Crianca crianca){
        if (primeiraCrianca == null){
            primeiraCrianca = new Nodo(crianca);
            criancaDoMomento = primeiraCrianca;
        } else {
            Nodo novaCrianca = new Nodo(crianca);
            Nodo ultimaCrianca = primeiraCrianca.getProximo();
            ultimaCrianca.setAnterior(novaCrianca);
            novaCrianca.setAnterior(primeiraCrianca);
            primeiraCrianca.setProximo(novaCrianca);
            novaCrianca.setProximo(ultimaCrianca);
        }
    }
    public void mostrar(){
        Nodo aux = primeiraCrianca;
        System.out.printf("%s ->", aux.getCrianca().getNome());
        aux = aux.getProximo();
        
        while (aux != primeiraCrianca){
        }
    }
    
    class Nodo {
        private Crianca crianca;
        private Nodo anterior;
        private Nodo proximo;
        
        public Nodo(Crianca crianca) {
            this.crianca = crianca;
        }
        public void setAnterior(Nodo anterior) {this.anterior = anterior; }
        public void setProximo(Nodo proximo) {this.proximo = proximo; }
        public Nodo getAnterior() {return anterior;}
        public Nodo getProximo() {return proximo;}
        public Nodo getCrianca() {return Crianca;}
    }
}