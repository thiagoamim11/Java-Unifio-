import dominio.Calculadora;
import dominio.Resposta;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor {
    public void rodar () throws IOException, ClassNotFoundException {
        System.out.println("Iniciando o servidor...");
        DatagramSocket socketEntrada = new DatagramSocket(50000);

        while(true){
            System.out.println("Aguardando mensagens...");
            byte[] bufferEntrada = new byte[256];
            DatagramPacket pacoteEntrada = new DatagramPacket  (bufferEntrada, bufferEntrada.length );
            socketEntrada.receive ( pacoteEntrada );

            System.out.println("Realizando a conversão de bytes para objeto ...");
            byte[] bufferCalculadora = pacoteEntrada.getData ();
            ByteArrayInputStream bais = new ByteArrayInputStream ( bufferCalculadora );
            ObjectInputStream ois = new ObjectInputStream ( bais );
            Calculadora calculadora = (Calculadora) ois.readObject ( );

            System.out.println("Realizando a operação...");
            Resposta resposta = new Resposta();

            if(calculadora.getOperacao() == '+'){
                resposta.setResultado(calculadora.getX() + calculadora.getY());
                resposta.setMensagem("A operação foi realizada com sucesso");
            } else if (calculadora.getOperacao() == '*') {
                resposta.setResultado(calculadora.getX() * calculadora.getY());
                resposta.setMensagem("A operação foi realizada com sucesso");
            } else {
                resposta.setMensagem("Operação não implementada no servidor");
            }


            System.out.println("Realizando a conversão de objeto para bytes...");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(resposta);
            byte[] bufferResposta = baos.toByteArray();

            System.out.println("Enviando o pacote...");
            DatagramPacket pacoteSaida = new DatagramPacket(bufferResposta, bufferResposta.length, pacoteEntrada.getAddress(), pacoteEntrada.getPort());
            DatagramSocket socketSaida = new DatagramSocket();
            socketSaida.send(pacoteSaida);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Servidor servidor = new Servidor();
        servidor.rodar();
    }
}
