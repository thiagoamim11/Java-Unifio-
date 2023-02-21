package dominio;

import java.io.Serializable;

public class Calculadora8 implements Serializable {

    private int Valor_Min;
    public int Valor_Min() {return Valor_Min;}
    public void setValor_Min(int Valor_Min) { this.Valor_Min= Valor_Min;}

    private int Valor_Max;
    public int Valor_Max() {return Valor_Max;}
    public void setValor_Max(int Valor_Max) { this.Valor_Max= Valor_Max;}

    private int Valor;
    public int Valor() {return Valor;}
    public void setValor(int Valor) { this.Valor= Valor;}

}
