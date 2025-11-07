/**
 * Pacote contendo classes para atividades de transformações de imagens.
 * Este específico (atividade3_tonsDeCinza) foca na conversão de uma imagem colorida
 * para tons de cinza.
 */
package transformacoesImagens.atividade3_tonsDeCinza;

import java.awt.image.BufferedImage; // Classe para manipular dados de imagem em memória
import javax.imageio.ImageIO;       // Classe para ler e escrever imagens
import java.io.File;                  // Classe para representar caminhos de arquivos e diretórios
import java.io.IOException;           // Classe para lidar com exceções de Entrada/Saída

/**
 * Classe Main tem como função:
 * Carregar uma imagem original ("Imagem_orig.png").
 * Percorree cada pixel da imagem.
 * Aplicar uma fórmula de conversão para tons de cinza.
 * Salvar a imagem resultante em um novo arquivo ("imagem_transformada.png").
 */
public class Main {

    /**
     * @param args não utilizado
     * @throws IOException Se ocorrer um erro durante a leitura ou escrita do arquivo de imagem.
     */
    public static void main(String[] args) throws IOException {

        // 1. LEITURA DA IMAGEM ORIGINAL

        // Define o caminho para o arquivo de imagem original.
        File arquivo = new File("src/transformacoesImagens/Imagem_orig.png");

        // Impressão de depuração: verifica se o arquivo foi encontrado no caminho especificado.
        System.out.println("Existe? " + arquivo.exists());

        // Validação: Se o arquivo não existir, exibe uma mensagem de erro e encerra o programa.
        if (!arquivo.exists()) {
            System.out.println("Erro: imagem não encontrada no caminho especificado.");
            return; // Encerra o método main
        }

        // Lê o arquivo de imagem e o carrega em um objeto BufferedImage.
        // O BufferedImage armazena a imagem na memória para que possamos manipulá-la.
        BufferedImage imagem = ImageIO.read(arquivo);

        // Obtém as dimensões da imagem (largura e altura) em pixels.
        int largura = imagem.getWidth();
        int altura = imagem.getHeight();

        // 2.TRANSFORMAÇÃO PARA TONS DE CINZA

        // Percorre cada pixel da imagem usando loops aninhados.
        // O loop externo itera pelas linhas (coordenada y).
        for (int y = 0; y < altura; y++) {
            // O loop interno itera por cada pixel dentro da linha atual (coordenada x).
            for (int x = 0; x < largura; x++) {

                // Obtém o valor RGB do pixel na posição (x, y).
                // O valor é um inteiro único que combina os canais Alfa, Vermelho, Verde e Azul.
                int rgb = imagem.getRGB(x, y);

                // Extrai os componentes de cor individuais (Vermelho, Verde, Azul).
                // Isso é feito usando operações bitwise (deslocamento de bits e mascaramento).
                // ">> 16" desloca os bits 16 posições para a direita (para pegar o Vermelho).
                // ">> 8"  desloca os bits 8 posições para a direita (para pegar o Verde).
                // "& 0xFF" (máscara) isola os últimos 8 bits, dando um valor entre 0 e 255.
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                // Aplica a fórmula de luminância para calcular o valor de tom de cinza.
                // Esta fórmula é uma média ponderada que leva em conta como o olho humano
                // percebe o brilho de cada cor (verdes parecem mais brilhantes que azuis).
                // 0.299 * R + 0.587 * G + 0.114 * B
                int novoValor = (int)(0.299 * r + 0.587 * g + 0.114 * b);

                // Cria o novo valor RGB. Em tons de cinza, R, G e B são todos iguais.
                // Movemos o `novoValor` para as posições corretas (Vermelho, Verde e Azul)
                // e os combinamos usando o operador OR (|).
                int novoRGB = (novoValor << 16) | (novoValor << 8) | novoValor;

                // Define o pixel na posição (x, y) com o novo valor RGB (tom de cinza).
                imagem.setRGB(x, y, novoRGB);
            }
        }

        //3.SALVAMENTO DA NOVA IMAGEM

        // Define o diretório onde a imagem transformada será salva.
        File pasta = new File("src/transformacoesImagens/atividade3_tonsDeCinza/imagem");

        // Verifica se a pasta de saída já existe.
        if (!pasta.exists()) {
            // Se não existir, cria a pasta (e qualquer pasta "pai" necessária com mkdirs()).
            pasta.mkdirs();
        }

        // Cria um objeto File para a imagem de saída, dentro da pasta criada.
        File arquivoSaida = new File(pasta, "imagem_transformada.png");

        // Escreve o conteúdo do BufferedImage (que agora está em tons de cinza)
        // no arquivo de saída, no formato PNG.
        ImageIO.write(imagem, "png", arquivoSaida);

        // Imprime mensagens de sucesso no console.
        System.out.println(" Imagem convertida para tons de cinza com sucesso!");
        System.out.println(" Imagem salva em: " + arquivoSaida.getAbsolutePath());
    }
}