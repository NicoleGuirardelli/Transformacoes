import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("------------------------------------------------------\n" +
                    "Digite o número da transformação que deseja aplicar:\n" +
                    " (1)Rotação\n (2)Reflexão na origem\n " +
                    "(3)Reflexão em torno de x\n (4)Reflexão em torno de y\n" +
                    " (5)Expansão\n (6)Contração\n (0)Sair do programa\n"+
                    "------------------------------------------------------\n");
            int opcao = scanner.nextInt();
            if (opcao ==0 ) {break;}
            if (opcao < 1 || opcao > 6) throw new IllegalArgumentException("Opcão inválida");

            System.out.print("Informe coordenada x: ");
            double x = scanner.nextDouble();
            System.out.print("Informe coordenada y: ");
            double y = scanner.nextDouble();

            double xOriginal=x,yOriginal=y;
            Transformacoes transformacao = new Transformacoes(x, y);

            switch (opcao) {
                case 1:
                    System.out.println("Informe o ângulo: ");
                    double angulo = scanner.nextDouble();
                     transformacao.rotacao(angulo);

                    break;
                case 2:
                    transformacao.reflexaoOrigem();

                    break;
                case 3:
                    transformacao.reflexaoEixoX();

                    break;
                case 4:
                    transformacao.reflexaoEixoY();

                    break;
                case 5:
                    System.out.println("Informe o valor da expansão:");
                    double exp = scanner.nextDouble();
                    transformacao.expansao(exp);

                    break;
                case 6:
                    System.out.println("Informe o valor da contração");
                    double cont = scanner.nextDouble();
                    transformacao.contracao(cont);

                    break;
            }
            System.out.printf("\nPonto original: (%.2f, %.2f)%n", xOriginal, yOriginal);
            System.out.printf("Ponto transformado:%s ",transformacao+"\n");


        }
    }
}