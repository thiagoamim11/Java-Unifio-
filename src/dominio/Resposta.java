package dominio;

import java.io.Serializable;

public class Resposta implements Serializable {
    private double resultado;


    private String mensagem;

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
