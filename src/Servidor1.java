import java.io.IOException;
import java.util.Scanner;

public class Servidor1 {
    public void  rodar () throws IOException, ClassNotFoundException{




                Scanner entrada = new Scanner(System.in);

                System.out.print("Metros? ");
                float metros = entrada.nextFloat();

                float centimetros = metros * 100;

                System.out.printf("%.1f cm",centimetros);



    }
}
