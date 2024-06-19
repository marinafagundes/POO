# **Sistema de Gerenciamento de banco com Usuários, Carteira de Ações, GUI e Integração com Serviço de Bolsa de um banco fictício**
**Grupo:** *Isabella Arão (9265732), Leonardo Garcia (14615231), Marina Fagundes(9265405)*

## **1) Requisitos**
1. Cadastro de usuários:
    - Permitir cadastro de usuários, com campos: nome completo, idade, saldo e ID;
    - ID deve ser gerado automaticamente;
    - Validação de dados de entrada, como idade e saldo não negativos;
    - Remover usuários por meio da interface do usuário;

2. Operações bancárias:
    - Transferências entre usuários com verificações pertinentes, como saldo não negativo e que não ultrapasse o saldo do usuário de destino;

3. Carteira de ações:
    - Campos para compra, venda, cálculo de perda e ganho, adicionar ações (compradas anterioremente) e remover de ações (que podem ter sido vendidas por fora         do sistema);
    - Verificações necessárias, como símbolo de ação correto, quantidade não negativa e saldo suficiente do usuário para compra;
    - Integração de dados com API Alpha Vantage, para recuperação de dados de preços reais das ações;

4. Carregamento de dados dos usuários em arquivo de texto:
    - Os dados dos usuários são carregados para um arquivo de texto, além de serem atualizados sempre que há alguma modificação na interface;

5. Interface gráfica (GUI): 
    - O programa deve ter uma interface gráfica para interação com o usuário, para que ele possa interagir com o programa (por meio de botões e campos de             texto);	

## **2) Descrição do projeto**
Sistema de banco que visa cadastrar, remover e buscar usuários. Além disso, usuários podem fazer transferências entre si, além de poderem ter uma carteira de ações, com dados em tempo real integrados com a API Alpha Vantage.
Essa carteira de ações permite cadastrar ações compradas anteriormente, comprar e vender novas ações, calcular perdas e ganhos e remover ações, caso sejam vendidas por fora do sistema.
Todos os dados de usuários são escritos em um arquivo de texto, que é atualizado junto com a interface do programa. 
Ao fazer transferências entre usuários, os saldos são atualizados automaticamente e, quando há compra/venda de ações na aba de ações, ao voltar para a aba de usuários, o saldo já estará atualizado. 

## **3) Comentários sobre o código**

### **Projeto de sistema de gerenciamento de bancos**
## **3) Comentários sobre o código**

### **Projeto de sistema de gerenciamento de bancos**
O projeto atual de gerenciamento de bancos está dividido em três grandes pacotes:
- **GUI:** contém todas as classes referentes à interface gráfica (*MainFrame, StockDialog, StockPanel, UserDialog, UserPanel, UserPanelUpdateListener*);
- **Model:** contém as três classes fundamentais do projeto (*User, Stock, StockPriceRetriever*);
- **Utils:** contém as classes de utilidades (*UserUtils, StockUtils*), com métodos adicionais para usuários e ações;

A seguir, faremos um breve resumo de cada classe:
- **MainFrame:** responsável pela implementação da janela principal da nossa interface, que contém duas abas (de Usuários e de Ações). Contém a função main();
- **StockDialog:** responsável pela implementação da janela pop-up na aba de Stock e dos métodos necessários para o funcionamento dessa janela;
- **StockPanel:** responsável pela implementação do painel de conteúdo da aba Stock, que contém as funcionalidades de adicionar ação, comprar ação, editar ação, calcular perdas/ganhos, remover ação e vender ação. Todas essas funções estão relacionadas à carteira de ações de um usuário, que deve ser verificado logo no início;
- **UserDialog:** responsável pela implementação da janela pop-up na aba de User e dos métodos necessários para o funcionamento dessa janela;
- **UserPanel:** responsável pela implementação do painel de conteúdo da aba User, que contém as funcionalidades de adicionar usuário, editar usuário, remover usuário, buscar usuário por id e transferir fundos;
- **UserPanelUpdateListener:** ouvinte necessário para atualizar a aba de usuários sempre que há uma mudança no balanço causada por alguma ação na aba ação;
- **Stock:** classe que contém todos os métodos relacionados a ações;
- **StockProceRetrieval:** classe que contém o 
## **4) Plano e Resultados de Testes**
## **Comandos para Testar o Programa**

### **Criar Usuário**
- Execute o programa;
- Vá na aba "User";
- Na GUI selecione "Add User";
- Preencha os campos de informação;

### **Editar Usuário**
- Execute o programa;
- Vá na aba "User";
- Na GUI selecione "Edit User";
- Preencha os campos de informação;
- Ou selecione o campo de informação no usuário;

### **Remover Usuário**
- Execute o programa;
- Vá na aba "User";
- Na GUI selecione o usuário e "Remove User";
- Preencha os campos de informação;

### **Procurar usuário por ID**
- Execute o programa;
- Vá na aba "User";
- Na GUI selecione "Search User By ID";
- Preencha os campos de informação;

### **Transferir fundos**
- Execute o programa;
- Vá na aba "User";
- Na GUI selecione "Transfer Founds";
- Preencha os campos de informação;

### **Adicionar ação**
- Execute o programa;
- Vá na aba "Stocks";
- Na GUI selecione "Add Stock";
- Preencha os campos de informação;

### **Comprar ação**
- Execute o programa;
- Vá na aba "Stocks";
- Na GUI selecione "Buy Stock";
- Preencha os campos de informação;

### **Editar ação**
- Execute o programa;
- Vá na aba "Stocks";
- Na GUI selecione "Edit Stock";
- Preencha os campos de informação;

### **Calcular ganhos/perdas**
- Execute o programa;
- Vá na aba "Stocks";
- Na GUI selecione "Calculate Gains/Loss";
- Preencha os campos de informação;

### **Remover ação**
- Execute o programa;
- Vá na aba "Stocks";
- Na GUI selecione "Remove Stock";
- Preencha os campos de informação;

### **Vender ação**
- Execute o programa;
- Vá na aba "Stocks";
- Na GUI selecione "Sell Stock";
- Preencha os campos de informação;

## **Teste de Erros**

### **Criar Usuário**
- No campo de de ID insira: números, mais de cem caracteres;
- No campo idade insira: números negativos, idade maior que 200, letras, caracteres especiais, números não inteiros;
- No campo de saldo insira: número negativos, letras, caracteres especiais, números com vírgulas;

### **Editar Usuário***
- Tente selecionar um usuário e editar;
- No campo de de ID insira: números, mais de cem caracteres;
- No campo idade insira: números negativos, idade maior que 200, letras, caracteres especiais, números racionais;
- No campo de saldo insira: número negativos, letras, caracteres especiais, números com vírgulas;

### **Remover Usuário**
- Tente não selecionar um usuário e remover;

### **Procurar usuário por ID**
- No campo de de ID insira: números não inteiros, letras, caracteres especiais,números negativos;

### **Transferir fundos**
- Insira: letras, caracteres especiais,números negativos;

### **Adicionar ação**
- No campo de quantidade insira: número negativos, números não inteiros, caracteres especiais;
- No campo de valor insira: número negativos, letras, caracteres especiais;

### **Comprar ação**
- No campo de símbolo insira: números racionais;
- No campo de quantidade insira: número negativos, números não inteiros, caracteres especiais;

### **Editar ação**
- Tente selecionar uma ação e editar;
- No campo de de Símbolo insira: números racionais, ação não presente no portifólio;
- No campo quantidade insira: número negativos, números não inteiros, caracteres especiais;
- No campo de valor insira: número negativos, letras, caracteres especiais;

### **Calcular ganhos/perdas**
- Selecione "Calculate Gain/Loss";

### **Remover ação**
- No campo símbolo insira: números racionais, ação não presente no portifólio;

### **Vender ação**
- No campo de de Símbolo insira: números racionais, ação não presente no portifólio;
- No campo quantidade insira: número negativos, números não inteiros, caracteres especiais;


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
- Como este projeto foi desenvolvido ao longo da didática das aulas, há algumas alterações que poderiam ser feitas com os novos conhecimentos adquiridos.
- O grupo se propôs a criar um código coeso, utilizando das técnicas de POO. Esse projeto indicou como essas técnicas afetam a interface com o usuário final e como sua falta é refletida na perda de intuitividade da interface;

