import dominio.Calculadora8;
import dominio.Resposta;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;


public class Cliente8 {
    public void rodar() throws IOException, ClassNotFoundException {

        int valor = 0;
        int saque = 0;
        int sinal = 0;
        int n100 = 0, n50 = 0,
            n20 = 0,
            n10 = 0,
            n5 = 0;
        double conta = 0 ;


        Scanner scanner = new Scanner(System.in);
        Calculadora8 calculadora8 = new Calculadora8();

        System.out.println("Valor que sera sacado");
        valor = scanner.nextInt();
        saque = valor;

        conta = saque %2 ;


        if (valor > 600) {
            System.out.println("Valor maxixo e de 600R$");
        }
        if (valor < 10){
            System.out.println("Valor minimo e de 10R$");
        }

        while (valor !=0){

            if(valor >=100){
                n100 = valor /100;
                valor = valor % 100;
                 } else if (valor >=50){
                 n50 = valor / 50;
                valor = valor % 50;
                } else if (valor >=20){
                n20 = valor / 20;
                valor = valor % 20;
                } else if (valor >=10){
                 n10 = valor / 10;
                valor = valor %10;
                } else if (valor >=5){
                  n5 = valor / 5;
                  valor = valor %5;
                }else {
                System.out.println("Valor nao compativel com as notas ");
                sinal = 1;
                break;
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(calculadora8);
        byte[] bufferCalculadora = baos.toByteArray();


        InetSocketAddress endereco = new InetSocketAddress("127.0.0.1", 50000);
        DatagramPacket pacoteSaida = new DatagramPacket(bufferCalculadora, bufferCalculadora.length, endereco);
        DatagramSocket socket = new DatagramSocket();
        socket.send(pacoteSaida);


        byte[] bufferEntrada = new byte[256];
        DatagramPacket pacoteEntrada = new DatagramPacket (bufferEntrada, bufferEntrada.length );
        socket.receive(pacoteEntrada);


        byte[] bufferResposta = pacoteEntrada.getData();
        ByteArrayInputStream bais = new ByteArrayInputStream(bufferResposta);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Resposta resposta = (Resposta) ois.readObject();



          if ((saque > 10) && (saque < 600) &&(sinal == 0)) {

            System.out.println("Imprimindo a resposta na tela...");
            System.out.println("Valor sacado \n" + "R$" + saque);
            System.out.println("Notas de R$ 100 \n " + n100);
            System.out.println("Notas de R$ 50 \n " + n50);
            System.out.println("Notas de R$ 20 \n " + n20);
            System.out.println("Notas de R$ 10 \n " + n10);
            System.out.println("Notas de R$ 5 \n " + n5);

        }

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Cliente8 cliente = new Cliente8();
        cliente.rodar();
    }
}
