import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Navegacao {
    Vertice[] grafo;
    public Navegacao() {
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
                //System.out.println(line);
                matriz[i] = line.toCharArray();
            }

            geraGrafo(matriz,altura,largura);
        }
        catch (IOException e) {
            System.err.format("Erro");
        }
    }

    private void geraGrafo(char[][] matriz, int altura, int largura){
        Vertice v1;
        Vertice v2;
        ArrayList<Vertice> arrayList = new ArrayList<>();
        for (int i=0;i<altura;i++){
            for (int j=0;j<largura;j++){
                char c = matriz[i][j];

                // gera um vértice do grafo
                if (c != '*') { // se for um espaço não navegável, não é um vértice do grafo
                    if (c == '.'){ // verifica se é um porto ou não
                        v1 = new Vertice(false); // se não for um porto
                    }
                    else {
                        v1 = new Vertice(true,Integer.parseInt("" + c)); // se for um porto
                    }

                    if (i>0){ // verifica se tem uma posição acima do vértice
                        if (matriz[i-1][j] != '*') { // se for um espaço não navegável, não é um vértice do grafo
                            if (matriz[i-1][j] == '.'){ // verifica se é um porto ou não
                                v2 = new Vertice(false); // se não for um porto
                            }
                            else {
                                v2 = new Vertice(true,Integer.parseInt("" + matriz[i-1][j])); // se for um porto
                            }
                            v1.addEdge(v2); // adiciona a aresta ao vértice
                        }
                    }
                    if (j>0){ // verifica se tem uma posição à esquerda do vértice
                        if (matriz[i][j-1] != '*') { // se for um espaço não navegável, não é um vértice do grafo
                            if (matriz[i][j-1] == '.'){ // verifica se é um porto ou não
                                v2 = new Vertice(false); // se não for um porto
                            }
                            else {
                                v2 = new Vertice(true,Integer.parseInt("" + matriz[i][j-1])); // se for um porto
                            }
                            v1.addEdge(v2); // adiciona a aresta ao vértice
                        }
                    }
                    if (i+1>altura){ // verifica se tem uma posição abaixo do vértice
                        if (matriz[i+1][j] != '*') { // se for um espaço não navegável, não é um vértice do grafo
                            if (matriz[i+1][j] == '.'){ // verifica se é um porto ou não
                                v2 = new Vertice(false); // se não for um porto
                            }
                            else {
                                v2 = new Vertice(true,Integer.parseInt("" + matriz[i+1][j])); // se for um porto
                            }
                            v1.addEdge(v2); // adiciona a aresta ao vértice
                        }
                    }
                    if (j+1>largura){ // verifica se tem uma posição à direita do vértice
                        if (matriz[i][j+1] != '*') { // se for um espaço não navegável, não é um vértice do grafo
                            if (matriz[i][j+1] == '.'){ // verifica se é um porto ou não
                                v2 = new Vertice(false); // se não for um porto
                            }
                            else {
                                v2 = new Vertice(true,Integer.parseInt("" + matriz[i][j+1])); // se for um porto
                            }
                            v1.addEdge(v2); // adiciona a aresta ao vértice
                        }
                    }
                    arrayList.add(v1); // adiciona o vértice ao grafo
                }
            }
        }
        grafo = arrayList.toArray(new Vertice[0]);
    }
}
