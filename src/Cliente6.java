import dominio.Calculadora6;
import dominio.Resposta;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


public class Cliente6 {
    public void rodar() throws IOException, ClassNotFoundException {
        double NumeroA = 100;
        double NumeroB = 2;
        double NumeroC = 99;
        double maior = NumeroA;
        double menor = NumeroA ;

        Calculadora6 calculadora6 = new Calculadora6();
        calculadora6.setNumeroA(0);
        calculadora6.setNumeroB(30);
        calculadora6.setNumeroA(15);

        if(menor >NumeroB){
            menor = NumeroB;
        }
        if(menor > NumeroC){
            menor = NumeroC;
        }
        if(maior <NumeroB){
            maior = NumeroB;
        }
        if(maior <NumeroC) {
           maior = NumeroC;
        }

        System.out.println("Criando objeto a ser enviado...");


        System.out.println("Realizando a conversão de objeto para bytes...");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(calculadora6);
        byte[] bufferCalculadora = baos.toByteArray();

        System.out.println("Enviando o pacote...");
        InetSocketAddress endereco = new InetSocketAddress("127.0.0.1", 50000);
        DatagramPacket pacoteSaida = new DatagramPacket(bufferCalculadora, bufferCalculadora.length, endereco);
        DatagramSocket socket = new DatagramSocket();
        socket.send(pacoteSaida);

        System.out.println("Aguardando mensagens...");
        byte[] bufferEntrada = new byte[256];
        DatagramPacket pacoteEntrada = new DatagramPacket (bufferEntrada, bufferEntrada.length );
        socket.receive(pacoteEntrada);

        System.out.println("Realizando a conversão de bytes para objeto ...");
        byte[] bufferResposta = pacoteEntrada.getData();
        ByteArrayInputStream bais = new ByteArrayInputStream(bufferResposta);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Resposta resposta = (Resposta) ois.readObject();


        System.out.println("Imprimindo a resposta na tela...");
        System.out.println("Numero Maior \n"+ maior);
        System.out.println("Numero Menor \n"+ menor);



    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Cliente6 cliente = new Cliente6();
        cliente.rodar();
    }
}
