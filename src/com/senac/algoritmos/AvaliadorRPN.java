package com.senac.algoritmos;

import java.util.Scanner;

import com.senac.estruturas.PilhaCheia;
import com.senac.estruturas.PilhaOperador;
import com.senac.estruturas.PilhaOperando;
import com.senac.estruturas.PilhaVazia;

public class AvaliadorRPN {
	public static double avalia(String expressao) throws PilhaCheia,
	PilhaVazia, InvalidOperator {
		PilhaOperando pilha = new PilhaOperando(50);

		Scanner sc = new Scanner(expressao);

		while (sc.hasNext()) {
			if (sc.hasNextInt()) {
				pilha.push(sc.nextInt());
			} else {
				String op = sc.next();
				double rhs = pilha.pop();
				double lhs = pilha.pop();
				pilha.push(executaOperador(op.charAt(0), lhs, rhs));
			}
		}

		return pilha.pop();
	}

	/*
	 * public static final char PARENTESES ='('; public static final char
	 * MAIS='+'; public static final char MENOS='-'; public static final char
	 * DIVISAO='/'; public static final char MULTIPLICACAO='*';
	 */

	public static void inversorPosFixo(String expressao)throws PilhaCheia, PilhaVazia, InvalidOperator{
		Scanner entrada= new Scanner(expressao);

		PilhaOperador pilha = new PilhaOperador(50);
		String saida = "";


		while (entrada.hasNext())
		{

			if (entrada.hasNextInt())
			{
				saida +=" " + entrada.next();
			}

			else 
			{
				String opLido = entrada.next();

				/*Se for o operador ")", desempilhar ate que o
			   		operador "(" seja o operador "desempilhado";
			   		a cada operador desempilhado, enviar para a
			   		sa’da (com excecao do "(").*/
				if	(ehFechaParentes(opLido)){
					do{
						//  if(ehAbreParenteses(pilha.peek()))
						//  pilha.pop();
						//else
						saida += " " + pilha.pop();

					}while(!ehAbreParenteses(pilha.peek()));	  
				}

				/*Sen‹o, se o operador tiver prioridade MAIOR
			   		que o operador do topo da pilha, ou se a pilha 
			   		estiver vazia, ou o operador do topo da pilha
			   		for "(", empilhar o operador.*/

				else 
				{				
					if	( pilha.isEmpty() || ehAbreParenteses(pilha.peek()) )
					{

					//if(prioridade(opLido)>prioridade(pilha.peek()))
						pilha.push(opLido);

					/*Sen‹o, dempilhar (enviando para a sa’da) os
				   operadores da pilha atŽ que o operador tenha
				   prioridade MAIOR que o operador do topo, ou
				   que o operador do topo da pilha seja "(",
				   ou que a pilha esteja vazia. Empilhar o operador.*/
					}
					else if(prioridade(opLido)>prioridade(pilha.peek()))
							pilha.push(opLido);

					else
					{
						while	(prioridade(opLido)<prioridade(pilha.peek()) || !ehAbreParenteses(pilha.peek()) || !pilha.isEmpty())
						{
							if	(ehAbreParenteses(pilha.peek()))
								pilha.pop();
							else
								saida +=" "+pilha.pop();
						}
					//Empilhar o operador.
						pilha.push(opLido);
					}
			}
		}
			}



	/* Desempilhar os operadores da pilha enviando para a sa’da atŽ
   que a pilha fique vazia. Nenhum desse operadores pode ser o "(".*/


	while	(!pilha.isEmpty())
	{
		if	(ehAbreParenteses(pilha.peek()))
			pilha.pop();
		else			
			saida += " "+pilha.pop();
	}	

	System.out.print("Notação Fixa Polonesa: "+saida+"\n");	
	System.out.print(AvaliadorRPN.avalia(saida));
}

public static boolean ehAbreParenteses(String op) {
	return op.equals("(");
}

public static boolean ehFechaParentes(String op) {
	return op.equals(")");

}

public static int prioridade(String operador) throws InvalidOperator {
	int precedencia = 0;
	switch (operador.charAt(0)) {
	case '(':
		precedencia = 3;
		break;

	case '*':
		precedencia = 2;
		break;

	case '/':
		precedencia = 2;
		break;

	case '+':
		precedencia = 1;
		break;

	case '-':
		precedencia = 1;
		break;

	case ')':
		precedencia = 0;
		break;

	default:
		throw new InvalidOperator(operador.charAt(0));
	}

	return precedencia;
}

private static double executaOperador(char op, double lhs, double rhs)
		throws InvalidOperator {
	switch (op) {
	case '+':
		return lhs + rhs;
	case '-':
		return lhs - rhs;
	case '*':
		return lhs * rhs;
	case '/':
		return lhs / rhs;
	default:
		throw new InvalidOperator(op);
	}
}
}
