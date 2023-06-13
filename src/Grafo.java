import java.util.*;

public class Grafo {

    private int count =0;
    private final int numPortos;
    private final int maxSize; // armazena o tamanho do mapa
    private final HashMap<Integer, List<Integer>> lista; // lista de adjacências
    public final int[] portos; // guarda a posição dos portos no mapa

    public Grafo(int altura, int largura, int numPortos){
        lista = new HashMap<>();
        portos = new int[numPortos];
        Arrays.fill(portos,-1); // inicializa 'portos' com -1 ao invés de 0;
        maxSize = altura*largura;
        this.numPortos = numPortos;
        count +=4;
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
            count++;
        }
        lista.computeIfAbsent(v2, k -> new ArrayList<>());
        if (!(lista.get(v2).contains(v1))){ // se a lista não tem v1, adiciona v1
            lista.get(v2).add(v1);
            count++;
        }
        count+=8;
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
        int [] retorno = new int[numPortos]; // arrays de int inicializam com '0' em todas as posições
        Arrays.fill(retorno,-1);

        for (int i=0;i<numPortos;i++){
            retorno[i] = distancias[portos[i]]; // salva as distâncias certas no array
            count++;
        }

        count+=2;
        return retorno;
    }

    /**
     * Calcula a distância de um vértice do grafo para todos os outros utilizando o algoritmo de busca em largura
     * @param start é o vértice inicial de cálculo à distância
     * @return é um array com todas as distâncias do vértice inicial
     */
    private int[] bfsDisTotal(int start){
        boolean[] visitado = new boolean[maxSize]; // arrays de boolean se inicializam com false
        int[] distancia = new int[maxSize]; // distância para cada um dos portos
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
                    count+=3;
                }
                count++;
            }
            count++;
        }
        count+=6;
        return distancia;
    }

    public int count() {
        int aux = count;
        count = 0;
        return aux;
    }

    @Override
    public String toString() {
        List<Integer> aux;
        StringBuilder s = new StringBuilder();
        for (Integer i : lista.keySet()){
            StringBuilder l= new StringBuilder("[ ");
            aux = lista.get(i);
            for (Integer j: aux){
                l.append(j).append(" ");
            }
            l.append("]");
            s.append("Posição: ").append(i).append(" - Lista: ").append(l).append("\n");
        }
        s.append("\n");
        for (int i=0;i<portos.length;i++){
            s.append("Porto: ").append(i+1).append(" - Posição: ").append(portos[i]).append("\n");
        }

        s.append("\nNúmero de Vértices: ").append(lista.size());
        return s.toString();
    }
}
