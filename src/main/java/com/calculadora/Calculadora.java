package com.calculadora;

import java.util.*;

public class Calculadora {
    
    public static double calcular(String expressao) throws Exception {
        // Remove espaços e substitui símbolos
        expressao = expressao.replace(" ", "")
                           .replace("×", "*")
                           .replace("÷", "/");
        
        // Validar expressão
        if (expressao.isEmpty()) {
            throw new Exception("Expressão vazia");
        }
        
        return avaliarExpressao(expressao);
    }
    
    private static double avaliarExpressao(String expressao) throws Exception {
        // Primeiro passo: resolver * e /
        List<String> tokens = tokenizar(expressao);
        
        // Resolver multiplicação e divisão primeiro
        for (int i = 1; i < tokens.size(); i += 2) {
            String op = tokens.get(i);
            if (op.equals("*") || op.equals("/")) {
                double a = Double.parseDouble(tokens.get(i-1));
                double b = Double.parseDouble(tokens.get(i+1));
                double resultado;
                
                if (op.equals("*")) {
                    resultado = a * b;
                } else {
                    if (b == 0) throw new Exception("Divisão por zero");
                    resultado = a / b;
                }
                
                tokens.set(i-1, String.valueOf(resultado));
                tokens.remove(i+1);
                tokens.remove(i);
                i -= 2;
            }
        }
        
        // Resolver soma e subtração
        double resultado = Double.parseDouble(tokens.get(0));
        for (int i = 1; i < tokens.size(); i += 2) {
            String op = tokens.get(i);
            double valor = Double.parseDouble(tokens.get(i+1));
            
            if (op.equals("+")) {
                resultado += valor;
            } else if (op.equals("-")) {
                resultado -= valor;
            }
        }
        
        return resultado;
    }
    
    private static List<String> tokenizar(String expressao) {
        List<String> tokens = new ArrayList<>();
        StringBuilder numero = new StringBuilder();
        
        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);
            
            if (Character.isDigit(c) || c == '.') {
                numero.append(c);
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                if (numero.length() > 0) {
                    tokens.add(numero.toString());
                    numero.setLength(0);
                }
                tokens.add(String.valueOf(c));
            }
        }
        
        if (numero.length() > 0) {
            tokens.add(numero.toString());
        }
        
        return tokens;
    }
}