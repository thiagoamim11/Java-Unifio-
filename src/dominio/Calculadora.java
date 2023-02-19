package dominio;

import java.io.Serializable;

public class Calculadora implements Serializable {
    private double x;
    private double y;
    private char operacao;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public char getOperacao() {
        return operacao;
    }

    public void setOperacao(char operacao) {
        this.operacao = operacao;
    }
}
