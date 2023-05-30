import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Navegacao {
    Grafo grafo;
    public Navegacao() {
        grafo = new Grafo();
    }
    
    public void start(String arquivo){
        prep(arquivo);
    }

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
            debugPrintMap(matriz);
            debugPrintGraph();
            debugPrintPositions(altura,largura);
        }
        catch (IOException e) {
            System.err.format("Erro");
        }
    }

    private void geraGrafo(char[][] matriz, int altura, int largura){
        int v1;
        int v2;

        for(int i=0;i<altura;i++){
            for(int j=0;j<largura;j++){
                if (matriz[i][j] != '*'){
                    v1 = ((i * largura) + j); // posição do vérice atual no grafo

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
                        grafo.portos[porto] = v1;
                    }
                }
            }
        }
    }
    private void debugPrintMap(char[][] matriz){
        System.out.println("--------------------------DEBUG - PRINT - MAPA--------------------------");
        for (char[] i: matriz){
            for (char j : i){
                System.out.print(j);
            }
            System.out.print("\n");
        }
        System.out.println("--------------------------DEBUG - PRINT - MAPA--------------------------");
    }
    private void debugPrintGraph(){
        System.out.println("--------------------------DEBUG - PRINT - GRAFO-------------------------");
        System.out.println(grafo.toString());
        System.out.println("--------------------------DEBUG - PRINT - GRAFO-------------------------");
    }
    private void debugPrintPositions(int altura, int largura){
        System.out.println("--------------------------DEBUG - PRINT - POSIÇÕES----------------------");
        for (int i=0;i<altura;i++){
            for (int j=0;j<largura;j++){
                System.out.print((i*largura+j) + " ");
            }
            System.out.print("\n");
        }
        System.out.println("--------------------------DEBUG - PRINT - POSIÇÕES----------------------");
    }
}

