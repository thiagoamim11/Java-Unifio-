import dominio.Calculadora;
import dominio.Calculadora1;
import dominio.Resposta;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Cliente1 {
    public void rodar() throws IOException, ClassNotFoundException {
        System.out.println("Criando objeto a ser enviado...");
        Calculadora1 calculadora1 = new Calculadora1();
        calculadora1.setMetros(20);
        calculadora1.setConversao('*');
        calculadora1.setCentimetros(10);

        System.out.println("Realizando a conversão de objeto para bytes...");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(calculadora1);
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
        System.out.println("X: " + calculadora1.getMetros());
        System.out.println("Operação: " + calculadora1.getOperacao());
        System.out.println("Y: " + calculadora1.getCentimetros());
        System.out.println("Resultado: " + resposta.getResultado());
        System.out.println("Mensagem: " + resposta.getMensagem());
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Cliente1 cliente1 = new Cliente1();
        cliente1.rodar();
    }
}
