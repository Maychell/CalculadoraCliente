package com.example.calculadoracliente;

import java.util.Hashtable;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

public class Adapter extends BaseAdapter {

	private Context ctx;
	private static Map<Integer, String> valores;
	public static float n1;
	public static float n2;
	public static float operacao;
	
	public Adapter(Context ctx) {
		this.ctx = ctx;
		valores = new Hashtable<Integer, String>();
		
		valores.put(0, "7");
		valores.put(1, "8");
		valores.put(2, "9");
		valores.put(3, "/");
		valores.put(4, "4");
		valores.put(5, "5");
		valores.put(6, "6");
		valores.put(7, "x");
		valores.put(8, "1");
		valores.put(9, "2");
		valores.put(10, "3");
		valores.put(11, "-");
		valores.put(12, "0");
		n1 = -1;
		n2 = -1;
		operacao = -1;
	}
	
	@Override
	public int getCount() {
		return valores.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		final String elemento = valores.get(position);
		
		LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.activity_buttons, null);
		
		Button btn = (Button) view.findViewById(R.id.btn);
		btn.setText(valores.get(position));
		
		btn.setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				if(!MainActivity.entrada.getText().toString().contains("+") &&
						!MainActivity.entrada.getText().toString().contains("-") &&
						!MainActivity.entrada.getText().toString().contains("x") &&
						!MainActivity.entrada.getText().toString().contains("/"))
					MainActivity.entrada.setText(MainActivity.entrada.getText().toString()+elemento);
				else
					if(elemento == "+" || elemento == "-" || elemento == "x" || elemento == "/" || elemento == ",")
						Toast.makeText(ctx.getApplicationContext(), "Apenas uma operação por vez.", Toast.LENGTH_SHORT).show();
					else
						MainActivity.entrada.setText(MainActivity.entrada.getText().toString()+elemento);
				
			}
			
		});
		
		return view;
	}

}
