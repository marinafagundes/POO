# **Sistema de Gerenciamento de banco com Usuários, Carteira de Ações, GUI e Integração com Serviço de Bolsa de um banco fictício**
**Grupo:** *Isabella Arão (9265732), Leonardo Garcia (14615231), Marina Fagundes(9265405)*

## **1) Requisitos**


## **2) Descrição do projeto**


## **3) Comentários sobre o código**

### **Projeto de sistema de gerenciamento de bancos**
O projeto atual de gerenciamento de bancos está dividido em três grandes pacotes:
- **GUI:** contém todas as classes referentes à interface gráfica (*MainFrame, StockDialog, StockPanel, UserDialog, UserPanel*);
- **Model:** contém as três classes fundamentais do projeto (*User, Stock e StockPriceRetriever*);
- **Utils:** contém as classes *UserUtils e StockUtils*, com métodos adicionais para usuários e ações;

A seguir, faremos um breve resumo de cada classe:
- **MainFrame:** responsável pela implementação da janela principal da nossa interface, que contém duas abas (de Usuários e de Ações). Contém a função main();
- **StockDialog:** co

## **4) Plano e Resultados de Testes**
### **Comandos para Testar o Programa**
#### **Criar Usuário**
- Execute o programa;
- Vá na aba "User";
- Na GUI selecione "Add User";
- Preencha os campos de informação;

#### **Editar Usuário***
- Execute o programa;
- Vá na aba "User";
- Na GUI selecione "Edit User";
- Preencha os campos de informação;
- Ou selecione o campo de informação no usuário;

#### **Remover Usuário**
- Execute o programa;
- Vá na aba "User";
- Na GUI selecione o usuário e "Remove User";
- Preencha os campos de informação;

#### **Procurar usuário por ID**
- Execute o programa;
- Vá na aba "User";
- Na GUI selecione "Search User By ID";
- Preencha os campos de informação;

#### **Transferir fundos**
- Execute o programa;
- Vá na aba "User";
- Na GUI selecione "Transfer Founds";
- Preencha os campos de informação;

#### **Adicionar ação**
- Execute o programa;
- Vá na aba "Stocks";
- Na GUI selecione "Add Stock";
- Preencha os campos de informação;

#### **Comprar ação**
- Execute o programa;
- Vá na aba "Stocks";
- Na GUI selecione "Buy Stock";
- Preencha os campos de informação;

#### **Editar ação**
- Execute o programa;
- Vá na aba "Stocks";
- Na GUI selecione "Edit Stock";
- Preencha os campos de informação;

#### **Calcular ganhos/perdas**
- Execute o programa;
- Vá na aba "Stocks";
- Na GUI selecione "Calculate Gains/Loss";
- Preencha os campos de informação;

#### **Remover ação**
- Execute o programa;
- Vá na aba "Stocks";
- Na GUI selecione "Remove Stock";
- Preencha os campos de informação;

#### **Vender ação**
- Execute o programa;
- Vá na aba "Stocks";
- Na GUI selecione "Sell Stock";
- Preencha os campos de informação;


## **5) Procedimentos de Construção**

### **Comandos para Executar o programa**
#### **Passos para Abrir o Programa**
##### **TERMINAL**
A seguir estão listados os comandos para executar o programa. O programa pode ser executado de duas
formas: pelo terminal do Windows (CMD) ou pela IDE Eclipse.
Opção 1:    
- Faça o download dos arquivos;

- Faça o download da biblioteca JSon;

- Digitar CMD na barra de tarefas do Windows;
    
- Ao abrir o prompt, usar o comando “cd” e colar o diretório da pasta onde está o projeto;
    
- Ao verificar que está no diretório completo, usar o comando “javac -cp ”.;json-20240303.jar”main.java”
para compilar o programa;

- Depois de compilado, usar o comando “java -cp ”.;json-20240303.jar”main para executar o programa;

##### **IDE**

A seguir serão listados os processos para executar o programa sistemas de bancos em uma IDE qualquer:

- Faça o download dos arquivos;
- Faça o download da biblioteca JSon;
- Abra a IDE de sua preferência;
- Na IDE crie um novo projeto;
- Vá em abrir arquivos;
- Abra apenas os arquivos com terminação ”.java”do programa na pasta ”src” e adicione-os ao projeto;
- Em bibliotecas, abra todas os arquivos bibliotecas na pasta ”bibliotecas” e adicione-as a bibliotecas no
projeto;
- Ainda em biblioteca adicione a biblioteca Json;
- Agora o programa está completo para ser executado. Vá em executar programa e o execute;
  
Procedimento para executar o programa no Eclipse:
- Faça o download dos arquivos e extraia;
- Faça o download da biblioteca JSon;
- Abra a IDE ”Eclipse”;
- Selecione o diretório de ́area de trabalho;
- Vá na janela ”New” em selecionar ”wizard” e crie um novo projeto java;
- Coloque o nome desejado para o projeto;
- Em ”window” abra uma nova janela e exclua a anterior;
- Vá em ”file”, abra ”open projects files from system” e adicione a pasta com os arquivos;
- Adicione a biblioteca JSon;
- Clique em executar o programa;


## **6) Problemas**

### **Problemas Durante o Projeto**
- Os três participantes do projeto não eram providos de conhecimentos a priori em java, o que dificultou a execução do projeto;
- A criação e funcionalidade da interface de programação de aplicação ou API com o retorno de dados do site, por ter requisitado conhecimentos não abordados durante a programação do curso;
- A criação e aplicação da interface gráfica do usuário, pela transição entre executar funções dadas em um terminal e executar tais funções em uma GUI, pela necessidade de integração forte entre as partes da interface;


## **7) Comentários**
