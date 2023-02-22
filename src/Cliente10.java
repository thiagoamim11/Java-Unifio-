import dominio.Calculadora10;
import dominio.Resposta;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;


public class Cliente10 {
    public void rodar() throws IOException, ClassNotFoundException {


        double PaisA = 0;
        double PaisB = 0;
        double CrecimentoA = 0;
        double CrecimentoB = 0;
        int tempo = 0;



        Scanner scanner = new Scanner(System.in);
        Calculadora10 calculadora10 = new Calculadora10();

        System.out.println("População Do Pais A");
        PaisA= scanner.nextDouble();

        System.out.println("Taxa de crescimento Do Pais A");
        CrecimentoA = scanner.nextDouble();

        System.out.println("População Do Pais B");
        PaisB = scanner.nextDouble();

        System.out.println("Taxa de crescimento Do Pais B");
        CrecimentoB = scanner.nextDouble();


        while (PaisA <= PaisB){
            PaisA =PaisA  + CrecimentoA;
            PaisB = PaisB  + CrecimentoB;
            tempo ++;
        }


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(calculadora10);
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

        System.out.println("Tempo para se igualar" + tempo);




    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Cliente10 cliente = new Cliente10();
        cliente.rodar();
    }
}
