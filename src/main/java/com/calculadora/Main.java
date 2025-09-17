package com.calculadora;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import static spark.Spark.*;

public class Main {
    private static final Gson gson = new Gson();
    
    public static void main(String[] args) {
        // Configurar porta (Render usa PORT environment variable)
        int porta = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        port(porta);
        
        // Servir arquivos estáticos
        staticFiles.location("/static");
        
        // Rota principal
        get("/", (req, res) -> {
            res.redirect("/html/index.html");
            return null;
        });
        
        // Rota para calcular operações
        post("/calculate", (req, res) -> {
            res.type("application/json");
            
            try {
                // Parse do JSON recebido
                JsonObject requestJson = gson.fromJson(req.body(), JsonObject.class);
                String operacao = requestJson.get("operacao").getAsString();
                
                System.out.println("Operação recebida: " + operacao);
                
                // Calcular resultado
                double resultado = Calculadora.calcular(operacao);
                
                // Criar resposta JSON
                JsonObject response = new JsonObject();
                response.addProperty("resultado", resultado);
                response.addProperty("sucesso", true);
                
                System.out.println("Resultado calculado: " + resultado);
                
                return gson.toJson(response);
                
            } catch (Exception e) {
                System.err.println("Erro no cálculo: " + e.getMessage());
                
                // Resposta de erro
                JsonObject errorResponse = new JsonObject();
                errorResponse.addProperty("erro", e.getMessage());
                errorResponse.addProperty("sucesso", false);
                
                res.status(400);
                return gson.toJson(errorResponse);
            }
        });
        
        System.out.println("Servidor rodando na porta: " + porta);
        System.out.println("Calculadora disponível em: /");
    }
}