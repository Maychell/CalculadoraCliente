package com.example.calculadoracliente;

import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	public static EditText entrada;
	private TextView resultado;
	private Button btnExecute;
	private GridView gridFood;
	final String IP = "10.0.2.2";
	final int PORTA = 777;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		entrada = (EditText) findViewById(R.id.txtEntrada);
		resultado = (TextView) findViewById(R.id.txtResultado);
		btnExecute = (Button) findViewById(R.id.btnExecute);
		
		gridFood = (GridView) findViewById(R.id.gridBtn);
		
		list();
		btnExecute.setOnClickListener(this);
	}

	private void list(){
		gridFood.setAdapter(new Adapter(this));
	}

	@Override
	public void onClick(View v) {
		int[] valores = new int[2];
		String[] aux;
		char operacao = 'c';
		if(entrada.getText().toString().contains("+")) {
			aux = entrada.getText().toString().split("+");
			valores[0] = Integer.parseInt(aux[0]);
			valores[1] = Integer.parseInt(aux[1]);
			operacao = '+';
		}
		if(entrada.getText().toString().contains("-")) {
			aux = entrada.getText().toString().split("-");
			valores[0] = Integer.parseInt(aux[0]);
			valores[1] = Integer.parseInt(aux[1]);
			operacao = '-';
		}
		if(entrada.getText().toString().contains("x")) {
			aux = entrada.getText().toString().split("x");
			valores[0] = Integer.parseInt(aux[0]);
			valores[1] = Integer.parseInt(aux[1]);
			operacao = 'x';
		}
		if(entrada.getText().toString().contains("/")) {
			aux = entrada.getText().toString().split("/");
			valores[0] = Integer.parseInt(aux[0]);
			valores[1] = Integer.parseInt(aux[1]);
			operacao = '/';
		}
		Calculadora calc;
		try {
			calc = new Calculadora(IP, PORTA);
			float resultado = calc.execute(valores[0], valores[1], operacao);
			this.resultado.setText(""+resultado);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
