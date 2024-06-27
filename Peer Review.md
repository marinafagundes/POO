# Review Group:

Name: Isabella Arão Azevedo Lopes de Brito, 9265732

Name: Leonardo Garcia Ferreira, 14615231

Name: Marina Fagundes e Souza,  9265405


# Group Being Revised:

Name: Ana Flor Oliveira de Stefani, 4770143

Name: Ana Lívia de Magalhães Garbin, 14557394 

Name: Nícolas Martins de Oliveira, 14600902

 
# 1. Project Quality (4 stars):

## a) Requirements:

Os requisitos são abundantes, claros e bem definidos. Através deles, é possível entender o propósito do projeto e suas principais funcionalidades.

## b) Project Description:

A descrição do projeto descreve, de modo direto e sucinto, as classes que compõem o código, bem como todos os seus atributos, construtores e seus principais métodos. Alguns métodos das classes não estão listados, mas eles desempenham funções auxiliares. Além disso, o relatório descreve todas as principais funcionalidades do sistema, o que foi muito útil para o seu entendimento.

## c) Comments About Code:

Dentro de comentários sobre o código, o grupo complementou o relatório com pontos que auxiliam no entendimento do projeto, como, por exemplo, um resumo sobre a estrutura do projeto e suas classes e breve descrição sobre como funciona a manipulação e validação de dados, bem como a Interface Gráfica. 

## d) Plans and Test Results:

A equipe desenvolveu o plano de testes com testes unitários, através do padrão AAA (Arrange, Act, Assert). O projeto se destaca no desenvolvimento de tal plano de testes, principalmente por ter tentado explorar diversas ferramentas de teste e por reconhecer a importância de uma abordagem diversificada para testes.

## e) Build Procedures:

Os procedimentos de construção se referiam apenas para o sistema operacional Linux, o que dificultou um pouco o processo, uma vez que tínhamos acesso a computadores com Windows. Entretanto, adaptando-o para o sistema operacional em questão, conseguimos executar o código no terminal e através da IDE Eclipse sem grandes dificuldades.

## g) Problems, General Work Quality, etc:

Os problemas encontrados pelo grupo foram semelhantes aos enfrentados pelo grupo revisor: complexidade da interface, problemas de construção, falta de experiência, entre outros.
No geral, o relatório é consistente e claro, consegue descrever bem o projeto de Gerenciamento de Voos e descreve as principais funcionalidades, bem como os principais desafios e problemas encontrados pelo grupo.

## f) Group Interactions:

Não houve interação com o grupo antes da presente revisão.
 
# 2. Code Quality (4 stars):

## a) Code Design and frameworks’ use:

O código está separado em pacotes (app, forms e tests.app) e, dentro de cada pasta, em classes. Além disso, foi feito o uso de interfaces, que agrupam os métodos que serão utilizados em cada classe. 
Em relação à utilização de frameworks, foi utilizado o framework JUnit, para testes automatizados em Java.

## b) Code Organization:

O código está bem organizado, no geral. As classes são bem estruturadas e têm coesão. O uso de interfaces foi uma boa escolha, pois agrupou métodos usados nas classes. Apenas sentimos falta de uma classe para métodos auxiliares, que aparecem em mais de uma classe, como os métodos equals e clone. 

As classes estão separadas em pastas (app, forms, tests.app), o que mostrou uma boa organização. Por exemplo, todas as classes que se referem à GUI estão agrupadas em forms. Isso ajudou no entendimento do projeto.

## c) How the code works:

O código se refere a um sistema de cadastro de aeroportos e seus respectivos voos. Conta com uma interface estruturada, que permite a visualização de cada aeroporto cadastrado e uma lista com todos os voos que foram adicionados. Também conta com opções de adicionar novos aeroportos, adicionar e remover voos, além da opção de listagem. 

Compilamos e executamos o código a partir da IDE Eclipse, então não enfrentamos problemas quanto a isso. O código contém uma pasta com classes de testes JUnit. 
Sentimos falta de testagem de algumas verificações de erro no cadastro, por exemplo: (i) verificação de horários de voo com letras, ao invés de números; (ii) nomes de cidades compostos por números. Além disso, ao emitir um aviso de erro, a mensagem é sempre a mesma, se referindo ao código de voo inválido, mesmo quando o campo sendo testado é outro. 

Adicionalmente, percebemos que os dados cadastrados não estão sendo inseridos no arquivo de texto “airports.txt” e os dados do arquivo (cadastrados anteriormente) não estão aparecendo na GUI quando o código é executado. 
Não vemos o que foi dito acima como problemas graves, temos certeza de que, se tivessem mais tempo, o grupo teria conseguido arrumar esses bugs com facilidade. Também temos em mente que podemos ter enfrentado algumas dificuldades por conta de sistemas operacionais diferentes, além da versão do java.

## d) Code Documentation:

O código tem extensa documentação, contando com Javadoc, nos padrões esperados, a respeito de todos os métodos, o que foi de grande ajuda para conseguirmos compreender melhor o código lido. 
 
# 3. What you would make differently:

A GUI, apesar de ser bem estruturada, não mostra todas as informações dos voos que foram cadastrados para cada aeroporto. Os campos cidade de destino, número do voo, cidade natal e aeroporto natal aparecem, mas o horário e contagem de passageiros de cada voo não aparece, então achamos que seria interessante ter adicionado essas informações também. Principalmente porque a função Excluir necessita que sejam inseridos os dados de horário também, portanto, seria interessante que eles estivessem facilmente visíveis.

**Figura 01 -** Lista de voos do aeroporto GYN.
![image](https://github.com/marinafagundes/POO/assets/166514758/a4fcad7f-08b6-4ff9-90da-0c7dc9f970b7)
**Fonte:** Elaborada pelos autores.

**Figura 02 -** Campos necessários para exclusão de um voo.

![image](https://github.com/marinafagundes/POO/assets/166514758/5a511530-5907-4634-8d54-477ad3b1589e)

**Fonte:** Elaborada pelos autores.

Caso houvesse mais tempo, adicionaríamos algumas redundâncias na GUI, de forma a torná-la mais intuitiva e amigável. Por exemplo, o cadastro de aeroportos e voos só pode ser finalizado com o mouse e não com o teclado, mas seria interessante que a interface oferecesse ambas possibilidades. Além disso, para excluir um voo, não há a opção de clicar na lista com o mouse e apenas excluir, só é possível fazer isso pelo teclado, inserindo todas as informações do voo.
Como mencionado anteriormente, também achamos que a criação de uma classe com os métodos auxiliares iria agregar ao projeto, melhorando ainda mais a sua qualidade.
Por fim, é importante notar que o projeto consegue cumprir as funcionalidades a que se propõe e que, apesar de pequenas falhas ou alterações que poderiam ser implementadas caso houvesse mais tempo disponível, o código tem qualidade e o relatório consegue explicar bem o projeto.



