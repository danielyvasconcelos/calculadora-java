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
        // Aqui será implementada a comunicação com o backend Java
        console.log('Enviando para backend:', entradaAtual);
        deveResetarTela = true;
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