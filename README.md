Fintech Backend (Spring Boot + Oracle)

Descrição
Aplicação backend desenvolvida em **Java com Spring Boot**, responsável por fornecer as APIs REST utilizadas pelo frontend React.  
O sistema realiza operações de CRUD (criar, listar, atualizar e excluir) para usuários, contas e transações, conectando-se à instância Oracle da FIAP.

---

Tecnologias utilizadas
- Java 17  
- Spring Boot  
- Spring Data JPA  
- Oracle Database (FIAP)  
- Maven  

---

Como executar o projeto

Clonar o repositório
git clone https://github.com/seu-usuario/fintech-backend-spring.git
cd fintech-backend-spring

Configurar o banco Oracle no arquivo application.properties

---

spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
spring.datasource.username=RMxxxxxx
spring.datasource.password=suaSenha
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect

server.port=8080

---

Abra no IntelliJ IDEA e execute a classe:
src/main/java/com/rf/fintech/FintechApplication.java
o backend estará rodando em: http://localhost:8080

---

Observações importantes
	O backend deve estar rodando antes do frontend React.
	O CORS está configurado para permitir o acesso via http://localhost:5173.
	Para testes locais em diferentes portas, pode ser usado: .allowedOrigins("http://localhost:*")
   A API retorna respostas em formato JSON.
   Testes podem ser feitos diretamente em http://localhost:8080/usuarios para verificar a comunicação com o banco Oracle.

---

Desenvolvido por

Renan Mafra Bacheschi
Análise e Desenvolvimento de Sistemas — FIAP 2025
