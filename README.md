# **SUGESTÃO: Sistema de Gerenciamento de Usuários, Carteira de Ações e Integração com Serviço de Bolsa de um banco fictício**

## **1. Estruturas:**
### **Usuario:**
- Atributos:
    - char nome[101]: nome do usuário.
    - int idade_correta: idade correta do usuário.
    - float saldo_correto: saldo correto do usuário.
    - int id: identificador único do usuário.
### **Acao:**
- Atributos:
    - char simbolo[11]: símbolo da ação.
    - int quantidade: quantidade de ações.
    - float preco_compra: preço de compra da ação.

## **2. Funções:**
### **inserirUsuario():**
- Descrição: Insere um novo usuário no arquivo de dados.
- Parâmetros: N/A
- Retorno: N/A
### **inserirMultiplosUsuarios():**
- Descrição: Insere vários usuários de uma vez no arquivo de dados.
- Parâmetros: N/A
- Retorno: N/A
### **buscarUsuarioPorId(int busca_Id):**
- Descrição: Busca um usuário pelo ID.
- Parâmetros: busca_Id - o ID do usuário a ser buscado.
- Retorno: N/A
### **transferirEntreUsuarios():**
- Descrição: Realiza uma transferência de saldo entre dois usuários e atualiza os respectivos saldos.
- Parâmetros: N/A
- Retorno: N/A
### **removerUsuarioPorId(int id_para_apagar):**
- Descrição: Remove um usuário com base no ID.
- Parâmetros: id_para_apagar - o ID do usuário a ser removido.
- Retorno: N/A
### **adicionarAcao(Usuario usuario, Acao acao):**
- Descrição: Adiciona uma ação à carteira do usuário.
- Parâmetros: usuario - o usuário para adicionar a ação; acao - a ação a ser adicionada.
- Retorno: N/A
### **removerAcao(Usuario usuario, char simbolo_acao):**
- Descrição: Remove uma ação da carteira do usuário.
- Parâmetros: usuario - o usuário para remover a ação; simbolo_acao - o símbolo da ação a ser removida.
- Retorno: N/A
### **calcularGanhoPerda(Usuario usuario):**
- Descrição: Calcula os ganhos ou perdas com base nas variações de preço das ações na carteira do usuário.
- Parâmetros: usuario - o usuário para calcular os ganhos ou perdas.
- Retorno: N/A
### **atualizarCarteira(Usuario usuario):**
- Descrição: Atualiza os preços das ações na carteira do usuário com base nos dados do serviço de bolsa.
- Parâmetros: usuario - o usuário para atualizar a carteira.
- Retorno: N/A

## **3. Fluxo do Programa:**
O programa oferece um menu com as seguintes opções:
- Inserir um novo usuário.
- Inserir vários usuários.
- Buscar um usuário por ID.
- Transferência entre usuários.
- Remoção de um usuário por ID.
- Adicionar uma ação à carteira do usuário.
- Remover uma ação da carteira do usuário.
- Calcular ganhos ou perdas na carteira do usuário.
- Sair.

Cada opção do menu corresponde a uma função específica que executa a operação desejada.

O programa utiliza um arquivo de texto (example.txt) para armazenar os dados dos usuários e suas carteiras de ações. As operações de leitura e escrita são realizadas nesse arquivo.

## **4. Considerações Adicionais:**
- Verificações de entrada: O programa inclui verificações para garantir que os dados inseridos sejam válidos, como idade não negativa, saldo não negativo, caracteres inválidos para nome etc.
- Integração com Serviço de Bolsa:
    - O programa integra-se a um serviço de bolsa, como o Alpha Vantage, para obter dados de ações em tempo real ou histórico.
    - Os preços das ações na carteira do usuário são atualizados com base nos dados do serviço de bolsa.
- Interface do usuário:
    - A interação com o usuário é feita por meio de uma interface gráfica simples, implementada utilizando uma biblioteca Java (por ex.: JavaFX).
    - A GUI apresenta botões e campos de entrada para cada função do programa, proporcionando uma interação mais amigável.
    - A carteira de ações do usuário e as variações de ganhos e perdas são exibidas de forma clara na interface, permitindo uma visualização rápida e fácil das informações financeiras.
