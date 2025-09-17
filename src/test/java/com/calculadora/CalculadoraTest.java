package com.calculadora;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    @Test
    @DisplayName("Teste soma simples")
    public void testeSomaSimples() throws Exception {
        assertEquals(5.0, Calculadora.calcular("2+3"));
        assertEquals(10.0, Calculadora.calcular("7+3"));
        assertEquals(15.0, Calculadora.calcular("8+7"));
    }

    @Test
    @DisplayName("Teste subtração simples")
    public void testeSubtracaoSimples() throws Exception {
        assertEquals(2.0, Calculadora.calcular("5-3"));
        assertEquals(-1.0, Calculadora.calcular("4-5"));
        assertEquals(0.0, Calculadora.calcular("8-8"));
    }

    @Test
    @DisplayName("Teste multiplicação simples")
    public void testeMultiplicacaoSimples() throws Exception {
        assertEquals(6.0, Calculadora.calcular("2*3"));
        assertEquals(15.0, Calculadora.calcular("3×5"));
        assertEquals(0.0, Calculadora.calcular("0*10"));
    }

    @Test
    @DisplayName("Teste divisão simples")
    public void testeDivisaoSimples() throws Exception {
        assertEquals(2.0, Calculadora.calcular("6/3"));
        assertEquals(3.0, Calculadora.calcular("12÷4"));
        assertEquals(2.5, Calculadora.calcular("5/2"));
    }

    @Test
    @DisplayName("Teste precedência: multiplicação antes de soma")
    public void testePrecedenciaMultiplicacao() throws Exception {
        assertEquals(14.0, Calculadora.calcular("2+3*4"));
        assertEquals(11.0, Calculadora.calcular("5+2*3"));
        assertEquals(26.0, Calculadora.calcular("2*3+4*5"));
    }

    @Test
    @DisplayName("Teste precedência: divisão antes de subtração")
    public void testePrecedenciaDivisao() throws Exception {
        assertEquals(7.0, Calculadora.calcular("10-6/2"));  // 10-(6/2) = 10-3 = 7
        assertEquals(1.0, Calculadora.calcular("9-8/1"));   // 9-(8/1) = 9-8 = 1
        assertEquals(7.0, Calculadora.calcular("15/3+2"));  // (15/3)+2 = 5+2 = 7
    }

    @Test
    @DisplayName("Teste expressões complexas com precedência")
    public void testeExpressoesComplexas() throws Exception {
        assertEquals(20.0, Calculadora.calcular("2+3*4+6"));
        assertEquals(14.0, Calculadora.calcular("20-2*3"));
        assertEquals(8.0, Calculadora.calcular("2*2+8/2"));
        assertEquals(1.0, Calculadora.calcular("10/2-4"));
    }

    @Test
    @DisplayName("Teste números decimais")
    public void testeNumerosDecimais() throws Exception {
        assertEquals(4.0, Calculadora.calcular("2.5+1.5"));
        assertEquals(1.0, Calculadora.calcular("2.5-1.5"));
        assertEquals(6.25, Calculadora.calcular("2.5*2.5"));
        assertEquals(2.0, Calculadora.calcular("5.0/2.5"));
    }

    @Test
    @DisplayName("Teste divisão por zero")
    public void testeDivisaoPorZero() {
        Exception exception = assertThrows(Exception.class, () -> {
            Calculadora.calcular("5/0");
        });
        assertEquals("Divisão por zero", exception.getMessage());
    }

    @Test
    @DisplayName("Teste expressão vazia")
    public void testeExpressaoVazia() {
        Exception exception = assertThrows(Exception.class, () -> {
            Calculadora.calcular("");
        });
        assertEquals("Expressão vazia", exception.getMessage());
    }

    @Test
    @DisplayName("Teste operações sequenciais")
    public void testeOperacoesSequenciais() throws Exception {
        assertEquals(1.0, Calculadora.calcular("10-5-4"));
        assertEquals(24.0, Calculadora.calcular("2*3*4"));
        assertEquals(2.0, Calculadora.calcular("16/4/2"));
        assertEquals(10.0, Calculadora.calcular("1+2+3+4"));
    }

    @Test
    @DisplayName("Teste precedência mista")
    public void testePrecedenciaMista() throws Exception {
        assertEquals(23.0, Calculadora.calcular("5+6*3"));   // 5+(6*3) = 5+18 = 23
        assertEquals(33.0, Calculadora.calcular("5*6+3"));   // (5*6)+3 = 30+3 = 33
        assertEquals(17.0, Calculadora.calcular("20-6/2"));  // 20-(6/2) = 20-3 = 17
        assertEquals(7.0, Calculadora.calcular("20/4+2"));   // (20/4)+2 = 5+2 = 7
    }
}