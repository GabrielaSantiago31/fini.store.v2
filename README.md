# fini.store.v2
Sistema de gerenciamento de vendas e estoque de uma loja de doces. 

<p>
<img src="https://img.shields.io/badge/STATUS-EM PROGRESSO-yellow"/>
</p>

### :arrow_forward: Abrir e rodar o projeto
---
Após baixar o projeto, você pode abri-lo com o Eclipse IDE 2022-12. Para isso, na tela de launcher:
- Clique com o botão esquerdo do mouse em File -> Import -> Existing Maven Projects -> Clique no botão Browse... 
-> Selecione o projeto -> Selecione a opção finish.
  
### :hammer_and_pick: Funcionalidade do Projeto 
---
- <kbd>Sign in:</kbd> O sistema permite o cadastro do cliente no sistema. Para isso, deverá ser selecionada a opção sign in, a qual pedirá os seguintes dados: nome, sobrenome, email, telefone, cpf, data de nascimento e senha.<br/>
Os dados são verificados assim que inseridos, não permitindo o registro de dados inválidos.<br/>
A confirmação do endereço se dá através de um Web Service, o qual é utilizado para completar e validar o endereço do cliente quando o mesmo insere o cep.<br/>
Caso o cliente já esteja registrado no banco de dados, a ele serão apresentadas as opções de fazer log in ou sair.

- <kbd>Log in:</kbd> O usuário poderá fazer o login no sistema utilizando o email e a senha cadastrados. Caso haja um equívoco no login e/ou na senha, o sistema informará que há um erro e o usuário poderá tentar novamente ou encerrar a sessão.

- <kbd>Catálogo de produtos:</kbd> Uma vez que o login é confirmado, o cliente poderá conferir os produtos disponíveis.
  
- <kbd>Fazer um pedido:</kbd> O cliente poderá fazer um pedido. Ao clicar na opção, ele deverá informar o código do produto e a quantidade desejada.
A quantidade de ingredientes no estoque é decrescida de acordo com o produto (e os ingredientes que são necessários para fabricar um pacote) e a quantidade desejada (a quantidade total dos ingredientes para a quantidade de pacotes desejados),
assim, caso não haja ingredientes suficientes para a realização do pedido, o cliente é informado e, assim, poderá escolher outro produto ou retornar ao menu.<br/>
Caso o pedido seja confirmado, o cliente receberá um email de confirmação.

- <kbd>Atualizar dados:</kbd> O cliente ainda poderá atualizar os seus dados cadastrais.

- <kbd>Log out:</kbd> O cliente poderá sair do sistema.

O sistema ainda pode ser acessado pelo administrador, o qual pode se logar no sistema através de um email e uma senha já cadastrada no banco de dados. Uma vez logado, poderá consultar o catálogo e:

- <kbd>Registrar um produto:</kbd> O administrador poderá cadastrar um produto novo, inserindo as informações: sabor, preço, gramas por pacote, nome, ingredientes dentre outras.

- <kbd>Registrar ingredientes:</kbd> O admnistrador pode aumentar a quantidade de ingredientes no estoque, bem como adicionar um ingrediente novo caso necessário.

- <kbd>Atualizar dados:</kbd> O administrador poderá atualizar os dados de acesso ao sistema.

- <kbd>Log out:</kbd> O administrador poderá encerrar a sessão.

###  :octocat: Técnicas e Tecnologias Utilizadas
---
- Java 17
- Spring Boot
- Web Service (Via Cep)
- Banco de dados H2
- Eclipse IDE
- POO
