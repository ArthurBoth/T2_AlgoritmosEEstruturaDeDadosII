import java.util.*;

public class Grafo {
    private final int size; // armazena o tamanho do mapa
    private final HashMap<Integer, List<Integer>> lista; // lista de adjacências
    public final int[] portos; // guarda a posição dos portos no mapa

    public Grafo(int altura, int largura){
        lista = new HashMap<>();
        portos = new int[10];
        Arrays.fill(portos,-1); // inicializa 'portos' com -1 ao invés de 0;
        size = altura*largura;
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

    /**
     * Calcula a distância de um vértice qualquer para um porto utilizando o algoritmo de busca em largura
     * @param start é o vértice inicial de cálculo à distância
     * @return é um array com as ditâcias até cada porto (a distância é -1 se o vértice for 
     * inalcançável a partir do vértice inicial)
     */
    public int[] bfsDisPortos(int start){
        int [] distancias = bfsDisTotal(start); // a distância de 'start' a todos os vértices alcançáveis
        int [] retorno = new int[10]; // arrays de int inicializam com '0' em todas as posições
        Arrays.fill(retorno,-1);

        for (int i=1;i<10;i++){
            retorno[i-1] = distancias[portos[i-1]]; // salva as distâncias certas no array
        }

        return retorno;
    }

    /**
     * Calcula a distância de um vértice do grafo para todos os outros utilizando o algoritmo de busca em largura
     * @param start é o vértice inicial de cálculo à distância
     * @return é um array com todas as distâncias do vértice inicial
     */
    private int[] bfsDisTotal(int start){
        boolean[] visitado = new boolean[size]; // arrays de boolean se inicializam com false
        int[] distancia = new int[size]; // distância para cada um dos portos
        Queue<Integer> fila = new LinkedList<>();

         // altera a distância para um número inválido, assim, vértices inalcançáveis têm distância '-1'
        Arrays.fill(distancia, -1);

        fila.add(start);
        visitado[start] = true;
        distancia[start] = 0;

        while (!(fila.isEmpty())){ // enquanto a fila não estiver vazia
            int v = fila.poll(); // remove o vértice visitado
            for (int w : lista.get(v)){ // para cada vértice adjacente que start tem
                if (!(visitado[w])){ // verifica se não tiver visitado o adjacente ainda
                    fila.add(w); // adiciona à fila para visitar no futuro
                    visitado[w] = true;
                    distancia[w] = distancia[v] + 1;
                }
            }
        }
        return distancia;
    }
}
