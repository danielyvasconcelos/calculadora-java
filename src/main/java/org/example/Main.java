package org.example;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        // Configurar porta
        port(8080);
        
        // Servir arquivos estáticos
        staticFiles.location("/static");
        
        // Rota principal - redireciona para a calculadora
        get("/", (req, res) -> {
            res.redirect("/html/index.html");
            return null;
        });
        
        System.out.println("Servidor rodando em: http://localhost:8080");
        System.out.println("Acesse a calculadora em: http://localhost:8080");
    }
}