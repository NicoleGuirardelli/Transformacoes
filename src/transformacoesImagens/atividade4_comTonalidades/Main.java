package transformacoesImagens.atividade4_comTonalidades;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // Caminho da imagem original (coloque sua imagem nessa pasta)
        File arquivoEntrada = new File("src/transformacoesImagens/atividade4_comTonalidades/WIN_20250202_20_37_30_Pro.jpg");

        System.out.println("Existe? " + arquivoEntrada.exists());
        if (!arquivoEntrada.exists()) {
            System.out.println("Erro: imagem não encontrada no caminho especificado.");
            return;
        }

        BufferedImage imagem = ImageIO.read(arquivoEntrada);

        // Pasta de saída das imagens transformadas
        File pastaSaida = new File("src/transformacoesImagens/atividade4_comTonalidades/imagens_geradas");
        if (!pastaSaida.exists()) {
            pastaSaida.mkdirs();
        }

        // Aplica as transformações de tonalidades
        aplicarTonalidade(imagem, "vermelha", 0.3, 0.1, 0.1, pastaSaida);
        aplicarTonalidade(imagem, "verde", 0.1, 0.3, 0.1, pastaSaida);
        aplicarTonalidade(imagem, "azul", 0.1, 0.1, 0.3, pastaSaida);
        aplicarTonalidade(imagem, "amarela", 0.5, 0.4, 0.1, pastaSaida);
        aplicarTonalidade(imagem, "alaranjada", 0.5, 0.3, 0.2, pastaSaida);

        System.out.println(" Imagens transformadas com sucesso!");
        System.out.println("Verifique a pasta: " + pastaSaida.getAbsolutePath());
    }

    private static void aplicarTonalidade(BufferedImage original, String nome, double vr, double vg, double vb, File pasta) throws IOException {
        int largura = original.getWidth();
        int altura = original.getHeight();

        BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                int rgb = original.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                int soma = r + g + b;

                int novoR = (int) Math.min(255, vr * soma);
                int novoG = (int) Math.min(255, vg * soma);
                int novoB = (int) Math.min(255, vb * soma);

                int novoRGB = (novoR << 16) | (novoG << 8) | novoB;
                novaImagem.setRGB(x, y, novoRGB);
            }
        }

        // Salva a imagem gerada
        File arquivoSaida = new File(pasta, "imagem_" + nome + ".jpg");
        ImageIO.write(novaImagem, "jpg", arquivoSaida);
        System.out.println("✔️ Tonalidade " + nome + " salva em: " + arquivoSaida.getAbsolutePath());
    }
}
