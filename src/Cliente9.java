import dominio.Calculadora9;
import dominio.Resposta;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;


public class Cliente9 {
    public void rodar() throws IOException, ClassNotFoundException {

        int  sinal = 0;
         int TriA;
         int TriB;
         int TriC;


        Scanner scanner = new Scanner(System.in);
        Calculadora9 calculadora9 = new Calculadora9();

        System.out.println("Valor do Lado A do triangulo");
        TriA = scanner.nextInt();

        System.out.println("Valor do Lado B do triangulo");
        TriB = scanner.nextInt();

        System.out.println("Valor do Lado C do triangulo");
        TriC = scanner.nextInt();


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(calculadora9);
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

        if ((TriA + TriB > TriC) && (TriA + TriB > TriC) || (TriB + TriC > TriA)){
            System.out.println("E um triangulo ");
            sinal = 1 ;
        }
        if (sinal  != 1 ){
            System.out.println("Valores informado não fazem um triangulo");
        }



        if ((TriA == TriB)&&(TriA == TriC)&&(TriB == TriC)){
            System.out.println("E um triangulo Equilátero");
        }

        if ((TriA == TriB)||(TriA == TriC)){
            System.out.println("E um triangulo Isósceles");
        }

        if ((TriA != TriB)&&(TriA !=TriC)&&(TriB!=TriC)){
            System.out.println("E um triangulo Escaleno");
        }



    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Cliente9 cliente = new Cliente9();
        cliente.rodar();
    }
}
