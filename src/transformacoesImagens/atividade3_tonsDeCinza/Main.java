package transformacoesImagens.atividade3_tonsDeCinza;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        // Caminho correto da imagem original dentro da pasta src
        File arquivo = new File("src/transformacoesImagens/atividade3_tonsDeCinza/WIN_20250202_20_37_30_Pro.jpg");

        // Verifica se o arquivo existe antes de tentar ler
        System.out.println("Existe? " + arquivo.exists());

        if (!arquivo.exists()) {
            System.out.println("Erro: imagem não encontrada no caminho especificado.");
            return;
        }

        // Lê a imagem
        BufferedImage imagem = ImageIO.read(arquivo);

        int largura = imagem.getWidth();
        int altura = imagem.getHeight();

        // Percorre cada pixel aplicando a transformação em tons de cinza
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                int rgb = imagem.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                // Transformação para tons de cinza
                int novoValor = (int)(0.299 * r + 0.587 * g + 0.114 * b);
                int novoRGB = (novoValor << 16) | (novoValor << 8) | novoValor;

                imagem.setRGB(x, y, novoRGB);
            }
        }

        // Garante que a pasta de saída exista
        File pasta = new File("src/transformacoesImagens/atividade3_tonsDeCinza/imagem");
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        // Salva a imagem transformada
        File arquivoSaida = new File(pasta, "imagem_transformada.png");
        ImageIO.write(imagem, "png", arquivoSaida);

        // Mensagens de sucesso
        System.out.println(" Imagem convertida para tons de cinza com sucesso!");
        System.out.println(" Imagem salva em: " + arquivoSaida.getAbsolutePath());
    }
}
