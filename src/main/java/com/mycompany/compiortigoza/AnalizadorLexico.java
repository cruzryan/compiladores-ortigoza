package com.mycompany.compiortigoza;

import java.util.Stack;

public class AnalizadorLexico {
	boolean EdoAceptacion;
	int iniLexema;
	int finLexema;
	int caracterActual;
	String[][] afd;
	String lexema;
	String sigma;
	Stack<Integer> indicesDeCaracteres;
	
	public AnalizadorLexico(String[][] afd,String sigma) {
		this.sigma = sigma;
		this.afd = afd;
		this.caracterActual = 0;
		indicesDeCaracteres = new Stack<>();
	}
	
	public int yylex() {
		indicesDeCaracteres.push(caracterActual);
		if(caracterActual>=sigma.length()) {
			lexema = "";
			return SimbolosEspeciales.FIN;
		}
		iniLexema = caracterActual;
		int estadoActual = 0;
		EdoAceptacion = false;
		finLexema = -1;
		int token = SimbolosEspeciales.TOKENERROR;
		lexema = "";
		int ultimoPosicionEstadoDeAceptacionVisto = -1;
		
		while(caracterActual<sigma.length()) {
			int transicion = Integer.parseInt(afd[estadoActual][(int)sigma.charAt(caracterActual)]);
			if(transicion>=0) {
				estadoActual = transicion;
				caracterActual++;
				
				if(Integer.parseInt(afd[estadoActual][255])>=0) {
					ultimoPosicionEstadoDeAceptacionVisto = caracterActual-1;
					EdoAceptacion = true;
					token = Integer.parseInt(afd[estadoActual][255]);
				}
				
			}else {
				if(!EdoAceptacion) {
					lexema = sigma.charAt(caracterActual)+"";
					token = SimbolosEspeciales.TOKENERROR;
					caracterActual = iniLexema+1;
					estadoActual = 0;
					return token;
				}else {
					finLexema = ultimoPosicionEstadoDeAceptacionVisto;
					lexema = sigma.substring(iniLexema, finLexema+1);
					caracterActual = finLexema+1;
					estadoActual = 0;
					return token;
				}
			}
		}
		if(!EdoAceptacion) {
			lexema = sigma.charAt(caracterActual)+"";
			token = SimbolosEspeciales.TOKENERROR;
			caracterActual = iniLexema+1;
			estadoActual = 0;
			return token;
		}else {
			finLexema = ultimoPosicionEstadoDeAceptacionVisto;
			lexema = sigma.substring(iniLexema, finLexema+1);
			caracterActual = finLexema+1;
			estadoActual = 0;
			return token;
		}   
	}
	
	public String getLexema() {
		return lexema;
	}
	
	public boolean undoToken() {
		if(indicesDeCaracteres.empty()) return false;
		caracterActual=indicesDeCaracteres.pop();
		return true;
	}
	
	public void printIndices() {
		for(Integer i: indicesDeCaracteres) {
			System.out.println(i);
		}
	}
}
