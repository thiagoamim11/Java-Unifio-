import dominio.Calculadora2;
import dominio.Resposta;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


public class Cliente2 {
    public void rodar() throws IOException, ClassNotFoundException {

        System.out.println("Criando objeto a ser enviado...");
        Calculadora2 calculadora2 = new Calculadora2();
        calculadora2.setRaio(20);
        calculadora2.setPI(3.14);
        calculadora2.setConta(calculadora2.getRaio() * calculadora2.getPI());
        calculadora2.setContaf(calculadora2.getConta() * calculadora2.getConta());

        System.out.println("Realizando a conversão de objeto para bytes...");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(calculadora2);
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
        System.out.println("Raio: " + calculadora2.getRaio());
        System.out.println("PI: " + calculadora2.getPI());
        System.out.println("Valor do Raio x PI: " + calculadora2.getConta());
        System.out.println("Area do ciculo : " + calculadora2.getContaf());

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Cliente2 cliente = new Cliente2();
        cliente.rodar();
    }
}
