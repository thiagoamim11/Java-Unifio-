import dominio.Calculadora;
import dominio.Resposta;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Cliente {
    public void rodar() throws IOException, ClassNotFoundException {
        System.out.println("Criando objeto a ser enviado...");
        Calculadora calculadora = new Calculadora();
        calculadora.setX(20);
        calculadora.setOperacao('*');
        calculadora.setY(10);

        System.out.println("Realizando a conversão de objeto para bytes...");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(calculadora);
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


    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Cliente cliente = new Cliente();
        cliente.rodar();
    }
}
