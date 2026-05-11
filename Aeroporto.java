import java.util.*;

// Aeroporto pra testar issaqui
public class Aeroporto {
    private List<Pista> pistas;
    private List<FilaAeronaves> filasPouso;
    private List<FilaAeronaves> filasDecolagem;
    private int proximoIdPouso;
    private int proximoIdDecolagem;
    private List<Aeronave> aeronavesQuePousaram;
    private List<Aeronave> aeronavesQueDecolaram;
    private int aeronavesEmCombustvelCritico;
    
    public Aeroporto() {
        proximoIdPouso = 1; // IDs ímpares para pouso
        proximoIdDecolagem = 2; // IDs pares para decolagem
        aeronavesQuePousaram = new ArrayList<>();
        aeronavesQueDecolaram = new ArrayList<>();
        aeronavesEmCombustvelCritico = 0;
        inicializarPistas();
        inicializarFilas();
    }
    
    private void inicializarPistas() {
        pistas = new ArrayList<>();
        pistas.add(new Pista(1, Pista.TipoPista.MISTA));
        pistas.add(new Pista(2, Pista.TipoPista.MISTA));
        pistas.add(new Pista(3, Pista.TipoPista.DECOLAGEM));
    }
    
    private void inicializarFilas() {
        filasPouso = new ArrayList<>();
        filasDecolagem = new ArrayList<>();
        filasPouso.add(new FilaAeronaves("Pouso Pista 1"));
        filasPouso.add(new FilaAeronaves("Pouso Pista 2"));
        filasDecolagem.add(new FilaAeronaves("Decolagem Pista 1"));
        filasDecolagem.add(new FilaAeronaves("Decolagem Pista 2"));
        filasDecolagem.add(new FilaAeronaves("Decolagem Pista 3"));
    }
    
    public void simular(int unidadesDeTempoTotal) {
        for (int tempo = 0; tempo < unidadesDeTempoTotal; tempo++) {
            System.out.println("\n========== UNIDADE DE TEMPO: " + tempo + " ==========");
            
            // 1. Gerar novos aviões
            gerarNovasAeronaves(tempo);
            
            // 2. Decrementar combustível ANTES de processar movimentos
            decrementarCombustivelTodasFilas();
            
            // 3. Processar decolagens e pousos
            processarMovimentos(tempo);
            
            // 4. Imprimir estado
            imprimirRelatorio(tempo);
        }
    }
    
    private void gerarNovasAeronaves(int tempo) {
        Random random = new Random();
        
        // Gerar 0-3 aeronaves para pouso
        int numAeronavesPouso = random.nextInt(4); // 0 a 3
        for (int i = 0; i < numAeronavesPouso; i++) {
            int combustivel = 1 + random.nextInt(20); // 1 a 20
            Aeronave aeronave = new Aeronave(proximoIdPouso, combustivel, tempo);
            proximoIdPouso += 2; // Próximo ID ímpar
            
            // Adicionar na fila menor (balanceamento)
            if (filasPouso.get(0).getTamanho() <= filasPouso.get(1).getTamanho()) {
                filasPouso.get(0).adicionar(aeronave);
            } else {
                filasPouso.get(1).adicionar(aeronave);
            }
            System.out.println("<vião> Pouso: Nova aeronave ID=" + aeronave.getId() + 
                             " com combustível=" + combustivel);
        }
        
        // Gerar 0-3 aeronaves para decolagem
        int numAeronavesDecolagem = random.nextInt(4); // 0 a 3
        for (int i = 0; i < numAeronavesDecolagem; i++) {
            Aeronave aeronave = new Aeronave(proximoIdDecolagem, 100, tempo);
            proximoIdDecolagem += 2; // Próximo ID par
            
            // Adicionar na fila menor
            FilaAeronaves filaEscolhida = filasDecolagem.get(0);
            for (FilaAeronaves fila : filasDecolagem) {
                if (fila.getTamanho() < filaEscolhida.getTamanho()) {
                    filaEscolhida = fila;
                }
            }
            filaEscolhida.adicionar(aeronave);
            System.out.println("<vião> Decolagem: Nova aeronave ID=" + aeronave.getId());
        }
    }
    
    private void processarMovimentos(int tempo) {
        // Prioridade 1: Aviões com combustível crítico
        List<Aeronave> avioesCriticos = buscarAvioesEmCombustvelCritico();
        
        if (!avioesCriticos.isEmpty()) {
            System.out.println("⚠ ALERTA: " + avioesCriticos.size() + 
                             " avião(ões) em combustível crítico!");
            aeronavesEmCombustvelCritico += avioesCriticos.size();
            
            // Usar pistas para pousos de emergência
            int pistaUsada = 0;
            for (Aeronave aeronave : avioesCriticos) {
                if (pistaUsada < 3) {
                    Pista pista = pistas.get(pistaUsada);
                    aeronave.setTempoSaida(tempo);
                    aeronavesQuePousaram.add(aeronave);
                    System.out.println("<vião> POUSO EMERGÊNCIA: Aeronave ID=" + 
                                     aeronave.getId() + " na Pista " + pista.getNumero());
                    pistaUsada++;
                }
            }
            removerAvioesProcessados(avioesCriticos);
        }
        
        // Prioridade 2: Decolagens normais (Pistas 1, 2, 3)
        for (int i = 0; i < 3 && i < filasDecolagem.size(); i++) {
            Pista pista = pistas.get(i);
            FilaAeronaves fila = filasDecolagem.get(i);
            
            if (!fila.estaVazia()) {
                Aeronave aeronave = fila.remover();
                aeronave.setTempoSaida(tempo);
                aeronavesQueDecolaram.add(aeronave);
                System.out.println("<vião> DECOLAGEM: Aeronave ID=" + 
                                 aeronave.getId() + " da Pista " + pista.getNumero());
            }
        }
        
        // Prioridade 3: Pousos normais (Pistas 1, 2 - se não há decolagem)
        for (int i = 0; i < 2 && i < filasPouso.size(); i++) {
            Pista pista = pistas.get(i);
            FilaAeronaves fila = filasPouso.get(i);
            
            if (!fila.estaVazia()) {
                Aeronave aeronave = fila.remover();
                aeronave.setTempoSaida(tempo);
                aeronavesQuePousaram.add(aeronave);
                System.out.println("<vião> POUSO: Aeronave ID=" + 
                                 aeronave.getId() + " na Pista " + pista.getNumero());
            }
        }
    }
    
    private List<Aeronave> buscarAvioesEmCombustvelCritico() {
        List<Aeronave> criticos = new ArrayList<>();
        for (FilaAeronaves fila : filasPouso) {
            for (Aeronave aeronave : fila.getTodasAeronaves()) {
                if (aeronave.temCombustvelCritico()) {
                    criticos.add(aeronave);
                }
            }
        }
        return criticos;
    }
    
    private void removerAvioesProcessados(List<Aeronave> avioes) {
        for (FilaAeronaves fila : filasPouso) {
            for (Aeronave aeronave : avioes) {
                fila.getTodasAeronaves().remove(aeronave);
            }
        }
    }
    
    private void decrementarCombustivelTodasFilas() {
        for (FilaAeronaves fila : filasPouso) {
            for (Aeronave aeronave : fila.getTodasAeronaves()) {
                aeronave.decrementarCombustivel();
            }
        }
    }
    
    private double calcularTempoMedioPouso() {
        if (aeronavesQuePousaram.isEmpty()) {
            return 0;
        }
        int totalTempo = 0;
        for (Aeronave aeronave : aeronavesQuePousaram) {
            totalTempo += aeronave.getTempoEspera();
        }
        return (double) totalTempo / aeronavesQuePousaram.size();
    }
    
    private double calcularTempoMedioDecolagem() {
        if (aeronavesQueDecolaram.isEmpty()) {
            return 0;
        }
        int totalTempo = 0;
        for (Aeronave aeronave : aeronavesQueDecolaram) {
            totalTempo += aeronave.getTempoEspera();
        }
        return (double) totalTempo / aeronavesQueDecolaram.size();
    }
    
    private void imprimirRelatorio(int tempo) {
        System.out.println("\n--- ESTADO DAS FILAS ---");
        for (FilaAeronaves fila : filasPouso) {
            fila.imprimir();
        }
        for (FilaAeronaves fila : filasDecolagem) {
            fila.imprimir();
        }
        System.out.println("\n--- ESTATÍSTICAS ---");
        System.out.println("Aviões que pousaram: " + aeronavesQuePousaram.size() + 
                         " | Tempo médio: " + String.format("%.2f", calcularTempoMedioPouso()));
        System.out.println("Aviões que decolaram: " + aeronavesQueDecolaram.size() + 
                         " | Tempo médio: " + String.format("%.2f", calcularTempoMedioDecolagem()));
        System.out.println("Aviões com combustível crítico: " + aeronavesEmCombustvelCritico);
    }
}
