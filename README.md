# Projeto básico JPA e REST

Para executar o projeto tenha o Maven e o Java 8 instalados e configurados. 
Baixe também o STS (Spring Tool Suite) para utilizar como IDE. O projeto está configurado
para usar o Derby tanto para execução normal, quanto para os testes.

O projeto está organizado da seguinte forma:
- pacote **br.com.rbh.springbootws** contém a classe de inicialização do Spring Boot;
- pacote **br.com.rbh.springbootws.configurations** contém configurações que vão além da configuração básica;
- pacote **br.com.rbh.springbootws.entities** contém as entidades JPA do projeto;
- pacote **br.com.rbh.springbootws.repositories** contém as classes que permitem o acesso ao banco além de também habilitar o serviço REST para a entidade automaticamente.

A carga inicial de dados está definida no arquivo **data.sql** para a execução normal 
e no arquivo **data-test.sql** para os testes.
