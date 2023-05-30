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
        lista.computeIfAbsent(v1, k -> new ArrayList<>());
        if (!(lista.get(v1).contains(v2))){ // se a lista não tem v2, adiciona v2
            lista.get(v1).add(v2);
        }
        lista.computeIfAbsent(v2, k -> new ArrayList<>());
        if (!(lista.get(v2).contains(v1))){ // se a lista não tem v1, adiciona v1
            lista.get(v2).add(v1);
        }
        /*
        lista.computeIfAbsent(v1, k -> new ArrayList<>());
        é a mesma coisa que
        if (lista.get(v1) == null) lista.put(v1,new ArrayList<>());
        */
    }

    @Override
    public String toString() {
        List<Integer> aux;
        StringBuilder s = new StringBuilder();
        for (Integer i: lista.keySet()){
            StringBuilder l= new StringBuilder("[ ");
            aux = lista.get(i);
            for (Integer j: aux){
                l.append(j).append(" ");
            }
            l.append("]");
            s.append("Posição: ").append(i).append(" - Lista: ").append(l).append("\n");
        }
        return s.toString();
    }
}
