package transformacoesImagens.atividade4_comTonalidades;

import java.awt.image.BufferedImage; // Classe para manipular dados de imagem em memória
import javax.imageio.ImageIO;       // Classe para ler e escrever imagens
import java.io.File;                  // Classe para representar caminhos de arquivos e diretórios
import java.io.IOException;           // Classe para lidar com exceções de Entrada/Saída

/**
 * Classe principal que aplica várias transformações de tonalidade a uma imagem.
 * Carrega uma imagem original
 * Cria um diretório de saída
 * Chama um metodo auxiliar aplicarTonalidade repetidamente
 * para gerar e salvar versões da imagem com diferentes filtros de cor
 * vermelho, verde, azul, amarelo e laranja
 */
public class Main {

    /**
     * Ponto de entrada do programa.
     * Orquestra o carregamento da imagem, a criação da pasta de saída
     * e a chamada das transformações.
     *
     * @param args Argumentos não utilizados
     * @throws IOException Se ocorrer um erro durante a leitura ou escrita dos arquivos.
     */
    public static void main(String[] args) throws IOException {

        // LEITURA DA IMAGEM ORIGINAL

        // Caminho da imagem original (deve estar na pasta indicada)
        File arquivoEntrada = new File("src/transformacoesImagens/Imagem_orig.png");

        // Verificação de existência do arquivo
        System.out.println("Existe? " + arquivoEntrada.exists());
        if (!arquivoEntrada.exists()) {
            System.out.println("Erro: imagem não encontrada no caminho especificado.");
            return; // Encerra o programa se a imagem não for encontrada
        }

        // Carrega a imagem original em memória
        BufferedImage imagem = ImageIO.read(arquivoEntrada);

        //PREPARAÇÃO DA PASTA DE SAÍDA

        // Define o caminho para a pasta onde as imagens geradas serão salvas
        File pastaSaida = new File("src/transformacoesImagens/atividade4_comTonalidades/imagens_geradas");
        // Se a pasta não existir, cria ela (e qualquer diretório pai necessário)
        if (!pastaSaida.exists()) {
            pastaSaida.mkdirs();
        }

        // APLICAÇÃO DAS TONALIDADES

        // Chama o metodo auxiliar aplicarTonalidade,
        // passando a imagem original e os "pesos" para cada canal (R, G, B).
        // A imagem original 'imagem' não é modificada;cria uma nova imagem a cada chamada.
        // Tonalidade vermelha: Fator alto para Vermelho (0.3), baixo para Verde (0.1) e Azul (0.1)
        aplicarTonalidade(imagem, "vermelha", 0.3, 0.1, 0.1, pastaSaida);

        // Tonalidade verde: Fator alto para Verde (0.3)
        aplicarTonalidade(imagem, "verde", 0.1, 0.3, 0.1, pastaSaida);

        // Tonalidade azul: Fator alto para Azul (0.3)
        aplicarTonalidade(imagem, "azul", 0.1, 0.1, 0.3, pastaSaida);

        // Tonalidade amarela: Fatores altos para Vermelho (0.5) e Verde (0.4)
        // (Vermelho + Verde = Amarelo no sistema RGB)
        aplicarTonalidade(imagem, "amarela", 0.5, 0.4, 0.1, pastaSaida);

        // Tonalidade alaranjada: Fator alto para Vermelho (0.5) e médio para Verde (0.3)
        aplicarTonalidade(imagem, "alaranjada", 0.5, 0.3, 0.2, pastaSaida);

        System.out.println(" Imagens transformadas com sucesso!");
        System.out.println("Verifique a pasta: " + pastaSaida.getAbsolutePath());
    }

    /**
     * Cria e salva uma nova imagem aplicando uma tonalidade de cor específica.
     * Este metodo cria uma NOVA imagem em memória ({@code novaImagem})
     * e não modifica a imagem original passada como parâmetro.</p>
     *
     * A lógica de tonalidade usada é:
     * 1. Calcula a "soma de intensidade" do pixel original: {@code soma = R + G + B}
     * 2. Calcula os novos canais de cor multiplicando a soma pelos fatores fornecidos:
     * - {@code novoR = vr * soma}
     * - {@code novoG = vg * soma}
     * - {@code novoB = vb * soma}
     * 3. Garante que os valores não ultrapassem 255 (usando {@code Math.min}).</p>
     *
     * @param original A imagem de origem (BufferedImage) a ser processada (lida, não modificada).
     * @param nome     O nome base para o arquivo de saída (ex: "vermelha", "verde").
     * @param vr       O fator (multiplicador) para o canal Vermelho (Red).
     * @param vg       O fator (multiplicador) para o canal Verde (Green).
     * @param vb       O fator (multiplicador) para o canal Azul (Blue).
     * @param pasta    O diretório (File) onde a nova imagem será salva.
     * @throws IOException Se houver um erro ao escrever o arquivo de imagem.
     */
    private static void aplicarTonalidade(BufferedImage original, String nome, double vr, double vg, double vb, File pasta) throws IOException {
        int largura = original.getWidth();
        int altura = original.getHeight();

        // Cria um novo objeto BufferedImage para armazenar o resultado da transformação.
        // Isso é crucial para que a imagem original não seja alterada e possa ser
        // usada para a próxima chamada do métod.
        BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

        // Percorre cada pixel da imagem ORIGINAL
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {

                // 1. Obtém o pixel da imagem ORIGINAL
                int rgb = original.getRGB(x, y);

                // 2. Extrai os canais R, G, B originais
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                // --- Lógica Principal da Tonalidade ---

                // 3. Calcula uma "soma de intensidade" total do pixel.
                // Esta é uma forma simples de medir o "brilho" geral do pixel.
                int soma = r + g + b;

                // 4. Calcula os novos valores de canal (novoR, novoG, novoB).
                // A "soma" é distribuída para os novos canais de acordo com os
                // fatores (vr, vg, vb) passados para o metodo.
                // Math.min(255, ...) é usado para "saturar" ou "clamping",
                // garantindo que o valor nunca seja maior que 255.
                int novoR = (int) Math.min(255, vr * soma);
                int novoG = (int) Math.min(255, vg * soma);
                int novoB = (int) Math.min(255, vb * soma);

                // 5. Combina os novos valores de R, G, B em um único inteiro RGB.
                int novoRGB = (novoR << 16) | (novoG << 8) | novoB;

                // 6. Define o pixel na NOVA imagem (novaImagem), não na original.
                novaImagem.setRGB(x, y, novoRGB);
            }
        }

        // --- Salvamento ---

        // Define o nome do arquivo de saída (ex: "imagem_vermelha.jpg")
        File arquivoSaida = new File(pasta, "imagem_" + nome + ".jpg");

        // Escreve a nova imagem (que está em 'novaImagem') no arquivo
        ImageIO.write(novaImagem, "jpg", arquivoSaida);

        // Imprime uma mensagem de sucesso para esta tonalidade específica
        System.out.println(" Tonalidade " + nome + " salva em: " + arquivoSaida.getAbsolutePath());
    }
}