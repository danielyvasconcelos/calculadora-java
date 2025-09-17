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
        Stack<Double> numeros = new Stack<>();
        Stack<Character> operadores = new Stack<>();
        
        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);
            
            // Se é número ou ponto decimal
            if (Character.isDigit(c) || c == '.') {
                StringBuilder numero = new StringBuilder();
                
                // Captura o número completo
                while (i < expressao.length() && 
                       (Character.isDigit(expressao.charAt(i)) || expressao.charAt(i) == '.')) {
                    numero.append(expressao.charAt(i));
                    i++;
                }
                i--; // Volta um índice
                
                numeros.push(Double.parseDouble(numero.toString()));
            }
            // Se é operador
            else if (c == '+' || c == '-' || c == '*' || c == '/') {
                // Processa operadores com maior ou igual precedência
                while (!operadores.isEmpty() && 
                       temPrecedencia(c, operadores.peek())) {
                    numeros.push(aplicarOperacao(operadores.pop(), numeros.pop(), numeros.pop()));
                }
                operadores.push(c);
            }
        }
        
        // Processa operadores restantes
        while (!operadores.isEmpty()) {
            numeros.push(aplicarOperacao(operadores.pop(), numeros.pop(), numeros.pop()));
        }
        
        return numeros.pop();
    }
    
    private static boolean temPrecedencia(char op1, char op2) {
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }
    
    private static double aplicarOperacao(char operador, double b, double a) throws Exception {
        switch (operador) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new Exception("Divisão por zero");
                }
                return a / b;
            default:
                throw new Exception("Operador inválido: " + operador);
        }
    }
}