package com.example.calculadoracliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Calculadora {

	private float resultado;
	private Socket socket;
	private DataOutputStream out;
	private DataInputStream in;
	
	public Calculadora(String ip, int porta) throws IOException {
		socket = new Socket(ip, porta);
		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());
	}
	
	public float execute(float n1, float n2, char operacao) throws IOException {
		try {
			out.writeFloat(n1);
			out.writeFloat(n2);
			out.writeChar(operacao);
			out.flush();
			resultado = in.readFloat();
			return resultado;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return 0;
	}
	
	public void close() throws IOException {
		out.close();
		in.close();
		socket.close();
	}
}
