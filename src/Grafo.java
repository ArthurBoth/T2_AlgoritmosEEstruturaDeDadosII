import java.util.*;

public class Grafo {
    private final HashMap<Integer, List<Integer>> lista; // lista de adjacências
    public final Integer[] portos; // guarda a posição dos portos no mapa

    public Grafo(){
        lista = new HashMap<>();
        portos = new Integer[10]; // a posição 0 nunca será utilizada
    }

    /**
     * Adiciona uma adjacência (aresta) ao grafo
     * @param v1 Um dos vértices da adjacência
     * @param v2 Um dos vértices de adjacência
     */
    public void addAdj(Integer v1, Integer v2){
        // adiciona duas vezes porque é um grafo não direcionado
        lista.computeIfAbsent(v1, k -> new ArrayList<>()).add(v2);
        lista.computeIfAbsent(v2, k -> new ArrayList<>()).add(v1);
        /*
        lista.computeIfAbsent(v1, k -> new ArrayList<>()).add(v2);
        é a mesma coisa que
        if (lista.get(v1) == null) lista.put(v1,new ArrayList<>()).add(v2);
        */
    }
}
