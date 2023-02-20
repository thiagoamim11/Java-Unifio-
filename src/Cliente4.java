import dominio.Calculadora4;
import dominio.Resposta;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;


public class Cliente4 {
    public void rodar() throws IOException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        int valor;
        int horas;
        int Salario;
        double Valor_Final;
        double IR;
        double INSS;
        double SINDICATO;

        System.out.println("Criando objeto a ser enviado...");
        Calculadora4 calculadora4 = new Calculadora4();
        System.out.println("Digite quanto voce recebe por horas");
        valor = scanner.nextInt();
        System.out.println("Digite quantas horas voce trabalha");
        horas = scanner.nextInt();

        Salario = valor * horas;

        IR = (Salario /100) * 11;
        INSS = (Salario/100) * 8;
        SINDICATO = (Salario/100) *5;

        Valor_Final= Salario - ( IR + INSS + SINDICATO) ;



        System.out.println("Realizando a conversão de objeto para bytes...");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(calculadora4);
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
        System.out.println("Valor ganho por hora \n" + valor);
        System.out.println("Quantidade de horas trabalahada \n" + horas);
        System.out.println("Salario total do mes " + Salario +"R$");
        System.out.println("Valor do desconto do Imposto de Renda " + IR);
        System.out.println("Valor do do desconto do INSS " + INSS);
        System.out.println("Valor do desconto do Sindicato " + SINDICATO);
        System.out.println("Valor do Salario final " + Valor_Final);


    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Cliente4 cliente = new Cliente4();
        cliente.rodar();
    }
}
