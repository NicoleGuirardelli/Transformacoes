public class Transformacoes {
    private double x, y;

    public Transformacoes(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void rotacao(double angulo) {
        double radAngulo =Math.toRadians(angulo);//transforma o angulo em graus p/ radiano
        double cos= Math.cos(radAngulo);//calcula o cosseno
        double sin= Math.sin(radAngulo);//calcula o seno

        double a=x*cos-y*sin;
        double b=y*cos+x*sin;
        this.x=a;
        this.y=b;
    }

    public void reflexaoOrigem(){
       this.x = -x;
       this.y = -y;

    }

    public void reflexaoEixoX(){
        this.x = x;
        this.y = -y;
    }

    public void reflexaoEixoY(){
        this.x = -x;
        this.y = y;
    }
    public void expansao(double expansao){
        if (expansao <= 1) throw new IllegalArgumentException("Número inválido\n" +
                "O valor da expansão deve ser maior que 1");
        this.x = expansao * x;
        this.y = expansao * y;


    }
    public void contracao(double contracao){
        if (contracao <= 0 || contracao >= 1) throw new IllegalArgumentException("Número inválido\n" +
                "O valor da contração deve estar entre 0 e 1");
        this.x= contracao * x;
        this.y = contracao * y;
    }

    @Override
    public String toString() {
        return String.format("x: %.2f, y: %.2f", x, y);
    }
}
