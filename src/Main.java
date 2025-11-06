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

            double a = x, b = y;//valores transformados
            switch (opcao) {
                case 1:
                    System.out.println("Informe o ângulo: ");
                    double angulo = scanner.nextDouble();
                    double radAngulo =Math.toRadians(angulo);//transforma o angulo em graus p/ radiano
                   double cos= Math.cos(radAngulo);//calcula o cosseno
                   double sin= Math.sin(radAngulo);//calcula o seno

                    a=x*cos-y*sin;
                    b=y*cos+x*sin;
                /*
                   (x cos(ang)- y sin(ang), y cos(ang)+ x sin(ang))
                    */

                    break;
                case 2:
                    a = -x;
                    b = -y;
                    break;
                case 3:
                    a = x;
                    b = -y;
                    break;
                case 4:
                    a = -x;
                    b = y;
                    break;
                case 5:
                    System.out.println("Informe o valor da expansão:");
                    double exp = scanner.nextDouble();
                    if (exp <= 1) throw new IllegalArgumentException("Número inválido\n" +
                            "O valor da expansão deve ser maior que 1");
                    a = exp * x;
                    b = exp * y;
                    break;
                case 6:
                    System.out.println("Informe o valor da contração");
                    double cont = scanner.nextDouble();
                    if (cont <= 0 || cont >= 1) throw new IllegalArgumentException("Número inválido\n" +
                            "O valor da contração deve estar entre 0 e 1");
                    a = cont * x;
                    b = cont * y;
                    break;
            }
            System.out.printf("\nPonto original: (%.2f, %.2f)%n", x, y);
            System.out.printf("Ponto transformado: (%.2f, %.2f)%n", a, b);
        }
    }
}