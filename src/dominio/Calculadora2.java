package dominio;

import java.io.Serializable;

public class Calculadora2 implements Serializable {

    private double PI;
    public double getPI() {
        return PI;
    }
    public void setPI(double PI) {
        this.PI = PI;
    }

    private double raio;
    public double getRaio() {
        return raio;
    }
    public void setRaio(double raio) {
        this.raio = raio;
    }

    private char operacao;
    public char getOperacao() {
        return operacao;
    }
    public void setOperacao(char operacao) {
        this.operacao = operacao;
    }

    private double contaf;
    public double getContaf() {
        return contaf;
    }
    public void setContaf(double contaf) {this.contaf = contaf; }

    private double conta;
    public double getConta() {
        return conta;
    }
    public void setConta(double conta) {this.conta = conta; }

}
