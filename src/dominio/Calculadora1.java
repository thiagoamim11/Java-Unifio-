package dominio;

import java.io.Serializable;

public class Calculadora1 implements Serializable {
    private int metros;
    private int centimetros;
    private char conversao;

    public double getMetros() {
        return metros;
    }

    public void setMetros(int metros) {
        this.metros = metros;
    }

    public double getCentimetros() {
        return centimetros;
    }
    public void setCentimetros(int centimetros) {
        this.centimetros = centimetros;
    }
    public char getOperacao() {
        return conversao;
    }

    public void setConversao(char conversao) {
        this.conversao = conversao;
    }
}
