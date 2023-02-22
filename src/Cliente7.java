import dominio.Calculadora7;
import dominio.Resposta;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;


public class Cliente7 {
    public void rodar() throws IOException, ClassNotFoundException {
        double NumeroA = 0;
        double NumeroB = 0;
        double NumeroC = 0;
        double delta = 0;
        double x1 = 0;
        double x2 = 0;

        Scanner scanner = new Scanner(System.in);


        Calculadora7 calculadora7 = new Calculadora7();
        System.out.println("Digite o primeiro numero");
        NumeroA = scanner.nextInt();

        System.out.println("Digite o segundo numero");
        NumeroB = scanner.nextInt();

        System.out.println("Digite o terceiro numero");
        NumeroC = scanner.nextInt();

        if (NumeroA != 0) {
            delta = NumeroB * NumeroB * 4 * NumeroA * NumeroC;

            if (delta >= 0){
                x1 = (int) ((- NumeroB + (Math.sqrt (delta) )) / (2*NumeroA));
                x2 = (int) ((- NumeroB + (Math.sqrt (delta) )) / (2*NumeroA));

                System.out.println("O valor de x1 e :" + x1);
                System.out.println("O valor de x2 e :" + x2);
            }
            else {
                System.out.println("Nao foi possivel resolver ,pois " + delta +"e menor que 0");
            }
        }
            else {
            System.out.println("Nao e uma equação de  2 grau,pois A e = 0");
        }





        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(calculadora7);
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


    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Cliente7 cliente = new Cliente7();
        cliente.rodar();
    }
}
