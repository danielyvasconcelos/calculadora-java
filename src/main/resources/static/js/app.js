let entradaAtual = '0';
let deveResetarTela = false;

const telaEntrada = document.querySelector('.input');
const telaResultado = document.querySelector('.result');

// Atualizar tela
function atualizarTela() {
    console.log('Atualizando tela com:', entradaAtual);
    telaEntrada.textContent = entradaAtual;
}

// Adicionar número ou operador
function adicionarNaTela(valor) {
    console.log('Adicionando valor:', valor);
    
    if (deveResetarTela) {
        console.log('Resetando tela');
        entradaAtual = '';
        deveResetarTela = false;
    }
    
    if (entradaAtual === '0' && valor !== '.') {
        entradaAtual = valor;
    } else {
        entradaAtual += valor;
    }
    
    console.log('Entrada atual após adição:', entradaAtual);
    atualizarTela();
}

// Limpar tela
function limparTela() {
    console.log('Limpando tela');
    entradaAtual = '0';
    telaResultado.textContent = '0';
    atualizarTela();
}

// Enviar operação para o backend
function enviarParaBackend(operacao) {
    console.log('Enviando para backend:', operacao);
    
    fetch('/calculate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ operacao: operacao })
    })
    .then(response => {
        console.log('Resposta recebida:', response.status);
        return response.json();
    })
    .then(data => {
        console.log('Resultado do backend:', data);
        if (data.sucesso) {
            telaResultado.textContent = data.resultado;
        } else {
            telaResultado.textContent = data.erro;
        }
        deveResetarTela = true;
    })
    .catch(error => {
        console.error('Erro na comunicação:', error);
        telaResultado.textContent = 'Erro';
        deveResetarTela = true;
    });
}

// Event listeners para os botões
document.addEventListener('DOMContentLoaded', function() {
    console.log('Inicializando calculadora');
    // Botões de números
    document.querySelectorAll('.btn.number').forEach(botao => {
        botao.addEventListener('click', function() {
            console.log('Clique no número:', this.textContent);
            adicionarNaTela(this.textContent);
        });
    });
    
    // Botões de operadores
    document.querySelectorAll('.btn.operator').forEach(botao => {
        botao.addEventListener('click', function() {
            console.log('Clique no operador:', this.textContent);
            adicionarNaTela(this.textContent);
        });
    });
    
    // Botão de igual
    document.querySelector('.btn.equals').addEventListener('click', function() {
        console.log('Clique no botão igual');
        enviarParaBackend(entradaAtual);
    });
    
    // Botão de limpar
    document.querySelectorAll('.btn.clear').forEach(botao => {
        botao.addEventListener('click', function() {
            console.log('Clique no botão:', this.textContent);
            if (this.textContent === 'C') {
                limparTela();
            }
        });
    });
});