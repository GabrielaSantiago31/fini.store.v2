# fini.store.v2
Sistema de gerenciamento de vendas e estoque de uma loja de doces. 

<p>
<img src="https://img.shields.io/badge/STATUS-COMPLETO-green"/>
</p>

### :arrow_forward: Abrir e rodar o projeto
---
Após baixar o projeto, você pode abri-lo com o Eclipse IDE 2022-12. Para isso, na tela de launcher:
- Clique com o botão esquerdo do mouse em File -> Import -> Existing Maven Projects -> Clique no botão Browse... 
-> Selecione o projeto -> Selecione a opção finish.

É necessário baixar ms.email também, pois este é um microsserviço responsável pelo envio de e-mails.
  
### :hammer_and_pick: Funcionalidade do Projeto 
---
- <kbd>Sign in:</kbd> O sistema permite o cadastro do cliente no sistema. Para isso, deverá ser selecionada a opção sign in, a qual pedirá os seguintes dados: nome, sobrenome, email, telefone, cpf, data de nascimento e senha.<br/>
Os dados são verificados assim que inseridos, não permitindo o registro de dados inválidos.<br/>
A confirmação do endereço se dá através de um Web Service, o qual é utilizado para completar e validar o endereço do cliente quando o mesmo insere o cep.<br/>
Caso o cliente já esteja registrado no banco de dados, a ele serão apresentadas as opções de fazer log in ou sair.

- <kbd>Log in:</kbd> O usuário poderá fazer o login no sistema utilizando o email e a senha cadastrados. Caso haja um equívoco no login e/ou na senha, o sistema não permitirá o acesso.

- <kbd>Catálogo de produtos:</kbd> Uma vez que o login é confirmado, o cliente poderá conferir os produtos disponíveis.
  
- <kbd>Fazer um pedido:</kbd> O cliente poderá fazer um pedido, informando o produto selecionado e a quantidade. O pedido só será aceito se houver a quantidade necessária em estoque.
Caso o pedido seja confirmado, o cliente receberá um email de confirmação.

- <kbd>Cancelar um pedido:</kbd> O cliente poderá cancelar um pedido que já foi pago.

- <kbd>Atualizar dados:</kbd> O cliente ainda poderá atualizar os seus dados cadastrais.
  
- <kbd>Realizar pagamento:</kbd> Após a realização do pedido, é necessário que o cliente efetue o pagamento. O status do pedido será alterado e o seu envio ocorrerá quando o pagamento for efetuado.

- <kbd>Atualizar dados:</kbd> O cliente poderá alterar seus dados, exceto seu cpf.

- <kbd>Log out:</kbd> O cliente poderá sair do sistema.

O sistema ainda pode ser acessado pelo administrador, o qual pode se logar no sistema através de um email e uma senha já cadastrada no banco de dados. Uma vez logado, poderá consultar o catálogo e:

- <kbd>Registrar um produto:</kbd> O administrador poderá cadastrar um produto novo, inserindo as informações: sabor, preço, gramas por pacote, nome, ingredientes dentre outras.

- <kbd>Administrar o estoque:</kbd> O admnistrador pode ver os produtos em estoque e as suas informações, aumentar a quantidade de um ou mais produtos, bem como adicionar um produto novo caso seja necessário.

- <kbd>Acompanhar o status de um pedido:</kbd> O admnistrador pode verificar o status de um pedido.

- <kbd>Log out:</kbd> O administrador poderá encerrar a sessão.

###  :octocat: Técnicas e Tecnologias Utilizadas
---
- Java 17
- Spring Boot
- Spring security - JWT token
- Swagger
- Web Service (Via Cep)
- MySQL
- H2
- JUnit
- Eclipse IDE
- POO
