package dominio;
import dominio.Resposta;
import java.io.Serializable;


public class  Calculadora3 implements  Serializable {

    private int feh;

    private char conversao;




    public int getFeh() {
        return feh;
    }


    public void setConversao(char conversao) {
        this.conversao = conversao;
    }
}
