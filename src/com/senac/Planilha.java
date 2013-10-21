package com.senac;

import java.util.Scanner;

import com.senac.algoritmos.AvaliadorRPN;

import static java.lang.System.*;

public class Planilha {
	public static void main(String[] args) throws Exception {
		out.println( AvaliadorRPN.avalia("2 3 4 + *") );
	
	Scanner entradaTeclado = new Scanner(System.in);
	String expressao="";
	while(entradaTeclado.hasNext()){
		expressao=entradaTeclado.nextLine();
		if(expressao.equals("fim"))
			System.exit(0);
		else
			AvaliadorRPN.inversorPosFixo(expressao);
		
	}
	
	
	}
}
