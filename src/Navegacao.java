import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;

public class Navegacao {
    Grafo grafo;
    public Navegacao() {
    }
    
    public void start(String arquivo){
        prep(arquivo);
        responde();
    }

    /**
     * Lê o arquivo com o mapa e envia as informações lidas para o método 'geraGrafo' para que o grafo seja criado
     * @param arquivo é a String com o nome do arquivo
     */
    private void prep(String arquivo){

        Path path = Paths.get(arquivo);
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
            String line = reader.readLine();
            int altura = Integer.parseInt(line.substring(0,line.indexOf(" ")));
            int largura = Integer.parseInt(line.substring(line.indexOf(" ")+1));
            char[][] matriz = new char[altura][largura];
            for (int i = 0; i<altura; i++){
                line = reader.readLine();
                matriz[i] = line.toCharArray();
            }
            geraGrafo(matriz,altura,largura);
        }
        catch (IOException e) {
            System.err.format("Erro");
        }
    }

    /**
     * Gera um grafo a partir de uma matriz de charcteres
     * @param matriz é a matriz de 'char' que originará o grafo
     * @param altura é o número de linhas da matriz
     * @param largura é o número de colunas da matriz
     */
    private void geraGrafo(char[][] matriz, int altura, int largura){
        grafo = new Grafo(altura, largura); // inicializa o grafo
        int v1;
        int v2;

        for(int i=0;i<altura;i++){
            for(int j=0;j<largura;j++){
                if (matriz[i][j] != '*'){
                    v1 = ((i * largura) + j); // posição absoluta do vérice atual no mapa

                    if ((i>0) && (matriz[i-1][j] != '*')){ // verifica a posição de cima do vértice atual
                        v2 = (((i - 1) * largura)+ j);
                        grafo.addAdj(v1,v2);
                    }
                    if ((i<altura-1) && (matriz[i+1][j] != '*')){ // verifica a posição de baixo do vértice atual
                        v2 = (((i + 1) * largura) + j);
                        grafo.addAdj(v1,v2);
                    }
                    if ((j>0) && (matriz[i][j-1] != '*')){ // verifica a posição à esquerda do vértice atual
                        v2 = ((i * largura) + j - 1);
                        grafo.addAdj(v1,v2);
                    }
                    if ((j<largura-1) && (matriz[i][j+1] != '*')){ // verifica a posição à direita do vértice atual
                        v2 = ((i * largura) + j + 1);
                        grafo.addAdj(v1,v2);
                    }


                    // depois das asjacências do vértice terem sido adicionadas
                    if (matriz[i][j] != '.'){ // verifica se o vértice em sí é um porto
                        // guara o valor númerico, pois 'matriz' armazena um char
                        int porto = Character.getNumericValue(matriz[i][j]);
                        grafo.portos[porto-1] = v1;
                    }
                }
            }
        }
    }

    /**
     * imprime na tela, com base nas informações do grafo, a distância entre os portos
     */
    private void responde(){
        int p2; // porto 2
        int cTotal = 0; // combustível total
        System.out.println("-------------------------------RESPOSTA-------------------------------");
        for (int i=1;i<10;i++){
            int[] distancias = grafo.bfsDisPortos(grafo.portos[i-1]);
            p2 = calcProx(distancias,i);
            if ((distancias[p2] > 0) || (distancias[p2] == 0) && (p2 == 0)){
                System.out.print("A viagem entre ");
                System.out.printf("Porto %d e Porto %d",i,p2+1);
                System.out.printf(" custará %d Unidades de combustível\n",distancias[p2]);
                cTotal += distancias[p2];
            } else {
                System.out.printf("O Porto %d é inacessível\n",i);
            }
        }
        System.out.printf("\nCombustível total: %d\n",cTotal);
        System.out.println("-------------------------------RESPOSTA-------------------------------");
    }

    /**
     * retorna o número do próximo porto de destino na viagem
     * @param distancias é o array que guarda as distâncias entre o porto atual e os outros
     * @param start é o número do porto atual
     * @return o número do próximo porto alcançável
     */
    private int calcProx(int[] distancias, int start){
        for (int i=0;i<8;i++){
            if (distancias[((start+i)%(9))] != -1){
                return ((start+i)%(9));
            }
        }
        return start;
    }
}

