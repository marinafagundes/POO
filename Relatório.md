# **Sistema de Gerenciamento de banco com Usuários, Carteira de Ações, GUI e Integração com Serviço de Bolsa de um banco fictício**
**Grupo:** *Isabella Arão (9265732), Leonardo Garcia (14615231), Marina Fagundes(9265405)*

## **1) Requisitos**
1.1. Cadastro de usuários:
 - Permitir cadastro de usuários, com campos: nome completo, idade, saldo e ID;
 - ID deve ser gerado automaticamente;
 - Validação de dados de entrada, como idade e saldo não negativos;
 - Remover usuários por meio da interface do usuário;

1.2. Operações bancárias:

- Transferências entre usuários com verificações pertinentes, como saldo não negativo e que não ultrapasse o saldo do usuário de destino;

1.3. Carteira de ações:
- Campos para compra, venda, cálculo de perda e ganho, adicionar ações (compradas anterioremente) e remover de ações (que podem ter sido vendidas por fora         do sistema);
- Verificações necessárias, como símbolo de ação correto, quantidade não negativa e saldo suficiente do usuário para compra;
- Integração de dados com API Alpha Vantage, para recuperação de dados de preços reais das ações;

1.4. Carregamento de dados dos usuários em arquivo de texto:
- Os dados dos usuários são carregados para um arquivo de texto, além de serem atualizados sempre que há alguma modificação na interface;

1.5. Interface gráfica (GUI): 
- O programa deve ter uma interface gráfica para interação com o usuário, para que ele possa interagir com o programa (por meio de botões e campos de texto);	

## **2) Descrição do projeto**
Sistema de banco que visa cadastrar, remover e buscar usuários. Além disso, usuários podem fazer transferências entre si, além de poderem ter uma carteira de ações, com dados em tempo real integrados com a API Alpha Vantage.

Essa carteira de ações permite cadastrar ações compradas anteriormente, comprar e vender novas ações, calcular perdas e ganhos e remover ações, caso sejam vendidas por fora do sistema.
Todos os dados de usuários são escritos em um arquivo de texto, que é atualizado junto com a interface do programa.

Ao fazer transferências entre usuários, os saldos são atualizados automaticamente e, quando há compra/venda de ações na aba de ações, ao voltar para a aba de usuários, o saldo já estará atualizado. 

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
- **StockProceRetrieval:** classe que contém o método para recuperar o preço de uma ação através da API AlphaVantage. Por estarmos utilizando a versão gratuita, há um limite de solicitações por dia e admitimos que o preço de fechamento da ação no último dia útil é o seu preço atual;
-  **User:** classe que contém todos os métodos relacionados ao usuário e ao gerenciamento de sua carteira de ações;

Como o presente projeto foi desenvolvido ao longo do semestre, alguns conhecimentos importantes foram adquiridos apenas nas suas últimas etapas. Assim, achamos essencial listar duas melhorias que faríamos caso tivéssemos mais tempo para desenvolver o projeto:
- **Dividir a classe User:** a classe User resultou em uma classe longa, que poderia ser dividida em User e StockManager, permitindo maior coesão e qualidade do código;
- **Melhorias na interface:** implementaríamos algumas melhorias (majoritariamente, redundâncias) no interface. Por exemplo, a funcionalidade de remover o usuário necessita que se selecione o usuário na tabela e aquele usuário será removido. Adicionalmente, achamos interessante se pudéssemos remover o usuário por id também;

Além disso, gostaríamos de pontuar duas alterações no projeto:
- **Inserir múltiplos usuários:** com a implementação da GUI, essa função se tornou irrelevante, uma vez que o usuário do programa (funcionário do banco) precisa apenas apertar um botão para gerar adicionar quantos usuários ele desejar;
- **Remover por id:** com a implementação da GUI, decidimos implementar a função remover por seleção, de modo a ser mais intuitiva e utilizar recursos da GUI. Entretanto, como mencionamos anteriormente, caso tivéssemos mais tempo, implementaríamos redundâncias na interface que complementariam o nosso projeto, como a remoção por id;

## **4) Plano e Resultados de Testes**
### **Comandos para Testar o Programa**

#### **Criar Usuário**
- Execute o programa;
- Vá na aba "User";
- Na GUI, selecione "Add User";
- Preencha os campos de informação;

#### **Editar Usuário**
- Execute o programa;
- Vá na aba "User";
- Na GUI, selecione "Edit User";
- Preencha os campos de informação;
- Ou selecione o campo de informação no usuário;

#### **Remover Usuário**
- Execute o programa;
- Vá na aba "User";
- Na GUI, selecione o usuário e "Remove User";
- Preencha os campos de informação;

#### **Procurar usuário por ID**
- Execute o programa;
- Vá na aba "User";
- Na GUI, selecione "Search User By ID";
- Preencha os campos de informação;

#### **Transferir fundos**
- Execute o programa;
- Vá na aba "User";
- Na GUI, selecione "Transfer Founds";
- Preencha os campos de informação;

#### **Adicionar ação**
- Execute o programa;
- Vá na aba "Stocks";
- Na GUI, selecione "Add Stock";
- Preencha os campos de informação;

#### **Comprar ação**
- Execute o programa;
- Vá na aba "Stocks";
- Na GUI, selecione "Buy Stock";
- Preencha os campos de informação;

#### **Editar ação**
- Execute o programa;
- Vá na aba "Stocks";
- Na GUI, selecione "Edit Stock";
- Preencha os campos de informação;

#### **Calcular ganhos/perdas**
- Execute o programa;
- Vá na aba "Stocks";
- Na GUI, selecione "Calculate Gains/Loss";
- Preencha os campos de informação;

#### **Remover ação**
- Execute o programa;
- Vá na aba "Stocks";
- Na GUI, selecione "Remove Stock";
- Preencha os campos de informação;

#### **Vender ação**
- Execute o programa;
- Vá na aba "Stocks";
- Na GUI, selecione "Sell Stock";
- Preencha os campos de informação;

### **Teste de Erros**

#### **Criar Usuário**
- No campo de de ID, insira: números, mais de cem caracteres;
- No campo idade, insira: números negativos, idade maior que 200, letras, caracteres especiais, números não inteiros;
- No campo de saldo, insira: número negativos, letras, caracteres especiais, números com vírgulas;

#### **Editar Usuário***
- Tente selecionar um usuário e editar;
- No campo de de ID, insira: números, mais de cem caracteres;
- No campo idade, insira: números negativos, idade maior que 200, letras, caracteres especiais, números racionais;
- No campo de saldo, insira: número negativos, letras, caracteres especiais, números com vírgulas;

#### **Remover Usuário**
- Tente não selecionar um usuário e remover;

#### **Procurar usuário por ID**
- No campo de de ID, insira: números não inteiros, letras, caracteres especiais,números negativos;

#### **Transferir fundos**
- Insira: letras, caracteres especiais,números negativos;

#### **Adicionar ação**
- No campo de quantidade, insira: número negativos, números não inteiros, caracteres especiais;
- No campo de valor, insira: número negativos, letras, caracteres especiais;

#### **Comprar ação**
- No campo de símbolo, insira: números racionais;
- No campo de quantidade, insira: número negativos, números não inteiros, caracteres especiais;

### **Editar ação**
- Tente selecionar uma ação e editar;
- No campo de de símbolo, insira: números racionais, ação não presente no portifólio;
- No campo quantidade, insira: número negativos, números não inteiros, caracteres especiais;
- No campo de valor, insira: número negativos, letras, caracteres especiais;

### **Calcular ganhos/perdas**
- Selecione "Calculate Gain/Loss";

### **Remover ação**
- No campo símbolo, insira: números racionais, ação não presente no portifólio;

### **Vender ação**
- No campo de de símbolo, insira: números racionais, ação não presente no portifólio;
- No campo quantidade, insira: número negativos, números não inteiros, caracteres especiais;


## **5) Procedimentos de Construção**

### **Comandos para Executar o programa**
#### **Passos para Abrir o Programa**
##### **TERMINAL**
Estrutura das pastas para compilação:


![image](https://github.com/marinafagundes/POO/assets/166514758/740eb7bc-cb8e-4c34-a4b9-ccede576fcbc)

Compilar o Projeto:

Abra o PowerShell/CMD;

Navegue até o diretório src do projeto:
cd meucaminho\ProjetoFinal\src

Compile os arquivos Java, incluindo a biblioteca JSON no classpath:
javac -d meucaminho\ProjetoFinal\bin -cp meucaminho\ProjetoFinal\lib\json.jar meucaminho\ProjetoFinal\src\gui\*.java meucaminho\ProjetoFinal\src\model\*.java meucaminho\ProjetoFinal\src\utils\*.java

Executar a Aplicação:
Navegue até o diretório bin do projeto:
cd meucaminho\ProjetoFinal\bin
java -cp "meucaminho\ProjetoFinal\bin;meucaminho\ProjetoFinal\lib\json.jar" gui.MainFrame

Certifique-se de substituir meucaminho pelo caminho real onde o projeto está armazenado.

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

Se possível, dar preferência por construir na IDE Eclipse. Além disso, destacamos a necessidade do download do plugin WindowBuilder, para permitir a criação da interface gráfica.


## **6) Problemas**

### **Problemas Durante o Projeto**
- Os três participantes do projeto não possuíam conhecimentos a priori em java, o que dificultou a execução do projeto;
- A criação e funcionalidade da interface de programação de aplicação ou API com o retorno de dados do site, por ter requisitado conhecimentos não abordados durante a programação do curso;
- A criação e aplicação da interface gráfica do usuário, pela transição entre executar funções dadas em um terminal e executar tais funções em uma GUI, pela necessidade de integração forte entre as partes da interface;


## **7) Comentários**
- Como este projeto foi desenvolvido ao longo da didática das aulas, há algumas alterações que poderiam ser feitas com os novos conhecimentos adquiridos;
- O grupo se propôs a criar um código coeso, utilizando das técnicas de POO. Esse projeto indicou como essas técnicas afetam a interface com o usuário final e como sua falta é refletida na perda de intuitividade da interface;
