import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Navegacao {
    public Navegacao() {
    }

    private void prep(String arquivo){

        Path path = Paths.get(arquivo);
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
            String line = reader.readLine();
            int altura = Integer.parseInt(line.substring(0,line.indexOf(" ")));
            int largura = Integer.parseInt(line.substring(line.indexOf(" ")+1));
            int[][] grafo = new int[altura][largura];

        }
        catch (IOException e) {
            System.err.format("Erro");
        }
    }
}
