import dominio.Calculadora1;
import dominio.Resposta;
import dominio.Calculadora5;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Cliente5 {
    public void rodar() throws IOException, ClassNotFoundException {
        System.out.println("Criando objeto a ser enviado...");
        Calculadora1 calculadora5 = new Calculadora1();

        System.out.println("Realizando a conversão de metros para centimetros...");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(calculadora5);
        byte[] bufferCalculadora = baos.toByteArray();

        System.out.println("Enviando o pacote...");
        InetSocketAddress endereco = new InetSocketAddress("127.0.0.1", 50000);
        DatagramPacket pacoteSaida = new DatagramPacket(bufferCalculadora, bufferCalculadora.length, endereco);
        DatagramSocket socket = new DatagramSocket();
        socket.send(pacoteSaida);

        System.out.println("Aguardando mensagens...");
        byte[] bufferEntrada = new byte[256];
        DatagramPacket pacoteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
        socket.receive(pacoteEntrada);

        System.out.println("Realizando a conversão de bytes para objeto ...");
        double nota1 = 7.0;
        double nota2 = 8.0;

        double soma = nota1 + nota2;
        System.out.println(soma);
        byte[] bufferResposta = pacoteEntrada.getData();
        ByteArrayInputStream bais = new ByteArrayInputStream(bufferResposta);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Resposta resposta = (Resposta) ois.readObject();
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Cliente1 cliente1 = new Cliente1();
        cliente1.rodar();
    }
}
