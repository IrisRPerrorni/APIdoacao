# API DE DOAÇÃO DE ALIMENTOS NÃO PERECÍVEIS 
Esta API foi desenvolvida com o objetivo de combater o desperdício de alimentos em residências e estabelecimentos comerciais, ao mesmo tempo em que busca fornecer refeições para famílias carentes. Para participar, os doadores precisam realizar um cadastro informando seu nome, endereço e número de telefone, além de especificar o tipo de alimento e a quantidade que desejam doar.
Após o registro no site, uma organização não governamental (ONG) entrará em contato com o doador para agendar a retirada dos alimentos no endereço informado. Dessa forma, juntos, podemos fazer a diferença na redução do desperdício de alimentos e na ajuda a quem mais precisa.
___________________________________

A funcionalidade de autenticação com login, senha e geração de token de acesso é uma abordagem comum em muitas APIs para garantir a segurança e o controle de acesso aos dados e funcionalidades disponíveis. Aqui está uma descrição mais detalhada de como essa API funcionaria com esse sistema de autenticação:

A funcionalidade de autenticação com login, senha e geração de token de acesso é uma abordagem comum em muitas APIs para garantir a segurança e o controle de acesso aos dados e funcionalidades disponíveis. Aqui está uma descrição mais detalhada de como essa API funcionaria com esse sistema de autenticação:

 - ## 1) Cadastro de Usuário:

O usuário interessado em doar alimentos se cadastra na plataforma, fornecendo informações e dados da doação como nome, endereço, número de telefone e criando um login e senha.
 - ## 2) Autenticação:

Após o registro, o usuário pode fazer login na plataforma, inserindo seu nome de usuário e senha.
 - ## 3) Token de Acesso:

Uma vez autenticado com sucesso, a API gera um token de acesso único para esse usuário. Esse token é usado para autorizar as ações subsequentes do usuário na plataforma.
 - ## 4) Doações de Alimentos:

Após a autenticação, o usuário tem acesso às funcionalidades da API, como registrar doações de alimentos. Ele pode especificar o tipo de alimento, a quantidade a ser doada, o  numero de telefone para contato ,  a data de vencimento do alimento doado, sendo que o sistema não permite a inserção de alimentos com data vencida ,  e o endereço para retirada.

- ## 5) Gerenciamento de Doações:

Somente o Administrador poderá visualizar e gerenciar as doações . Isso inclui verificar as doações por alimento , cidade , nome do doador e Id.
Após o registro da doação, a API facilita a comunicação entre o doador e a organização não governamental (ONG). A ONG é notificada sobre a nova doação e pode entrar em contato com o doador para agendar a retirada dos alimentos, quando a doação já foi buscada no endereço solicitado, o administrador poderá excluir do seu bancos de dados especificando que não há mais doações a serem pegas naquele endereço.
Comunicação com a ONG:

- ## 6) Segurança e Privacidade:

A segurança é fundamental. A API deve garantir a proteção das informações pessoais dos usuários e a confidencialidade das transações. Isso inclui a proteção adequada dos tokens de acesso e a implementação de práticas seguras de armazenamento de senhas.
Lembre-se de que a implementação específica pode variar de acordo com a tecnologia utilizada e os requisitos de segurança da API. É essencial seguir as melhores práticas de segurança e privacidade para proteger os dados dos usuários e garantir o bom funcionamento do sistema.

_________________________________________________________________________________________________________________________________________________

Essa API está documentada no swagger disponivel ao público.

http://localhost:8080/swagger-ui.html

![swagger](https://github.com/IrisRPerrorni/APIdoacao/assets/133882090/34781524-1f10-42dc-97af-5a649e8e183d)

________________________________________________________

A API conta com a configuração de dois bancos de dados sendo  Postgres para o armazenamento dos dados e H2 (banco de dados) para os testes;


