import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.awt.font.TextLayout;


import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception{

        //Lê a imagem
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //Cria nova imagem em memória com transparência e tamanho novo
        int larguraImagem = imagemOriginal.getWidth();
        int alturaImagem = imagemOriginal.getHeight();
        int novaAltura = alturaImagem + 200;

        BufferedImage novaImagem = new BufferedImage(larguraImagem, novaAltura, BufferedImage.TRANSLUCENT);

        //Copiar imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D)novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);
        
        //Configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 72);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);


        //Centraliza o texto
        String frase = "TOPZERA";
        TextLayout textLayout = new TextLayout(frase, graphics.getFont(),
        graphics.getFontRenderContext());
        //double alturaTexto = textLayout.getBounds().getHeight();
        double larguraTexto = textLayout.getBounds().getWidth();

        //Escrever uma frase na nova imagem
        graphics.drawString(frase, larguraImagem / 2 - (int) larguraTexto / 2, novaAltura - 100);

        //Escrever a nova imagem em um arquivo
        File file = new File(nomeArquivo);

        //Se n existir o diretório e o arquivo, ele cria
        file.getParentFile().mkdirs();
        file.createNewFile();
        
        ImageIO.write(novaImagem, "png", file);
        
    }
}
