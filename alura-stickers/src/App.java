import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        //String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";

        String url = "http://localhost:8080/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        
        //Exibir e manipular os dados
        List<Conteudo> extraiConteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for(Conteudo conteudo : extraiConteudos) {

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.titulo() + ".png";
            nomeArquivo = nomeArquivo.replace(":", "-");

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.titulo());
            System.out.println();
        }
    }
}
