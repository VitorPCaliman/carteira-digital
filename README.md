# 🏦 API de Carteira Digital

Esta é a API de backend para a aplicação de Carteira Digital, responsável por gerenciar transferências financeiras e autenticação de usuários. A aplicação oferece funcionalidades como cálculo de taxas com base em datas de agendamento, autenticação com JWT, e operações CRUD para transferências e usuários. As persistências podem ser configuradas em memória (H2) ou em banco de dados externos.

## 🛠️ Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![H2](https://img.shields.io/badge/H2-003B57?style=for-the-badge&logo=h2&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)

## 🚀 Instruções para Rodar a Aplicação

1. Clone o repositório:
    ```bash
    git clone https://github.com/VitorPCaliman/Carteira-Digital-Back-End.git
    cd carteira-digital
    ```

2. A aplicação usa o banco de dados em memória H2 por padrão, eliminando a necessidade de configuração externa. 

3. (Opcional) Configure o arquivo `application.properties` para personalizar o acesso ao H2:
    ```properties
    spring.datasource.url=jdbc:h2:mem:wallet_db
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=password
    spring.h2.console.enabled=true
    spring.jpa.hibernate.ddl-auto=update
    ```

4. Compile e execute a aplicação:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

5. Acesse os endpoints da API em: `http://localhost:8080/`.

6. Teste os endpoints com ferramentas como Postman ou Insomnia.

7. Para acessar o console H2:
    - URL: `http://localhost:8080/h2-console`
    - Username: `sa`
    - Password: `password`

## 📐 Arquitetura e Estrutura de Pacotes

A API foi desenvolvida seguindo os princípios de **Clean Architecture**, garantindo modularidade e fácil manutenção. Abaixo, segue a descrição de cada camada:

### Estrutura de Pacotes:

- **`config`**: Configurações da aplicação, como segurança (JWT) e filtros.
- **`controller`**: Contém os endpoints REST para autenticação, transferências e usuários.
- **`exception`**: Tratamento centralizado de erros e exceções customizadas.
- **`model`**:
  - `dto`: Objetos de transferência de dados (Data Transfer Objects).
  - Entidades que mapeiam as tabelas do banco de dados.
- **`repository`**: Interfaces para interação com o banco de dados.
- **`service`**: Contém a lógica de negócios, como cálculo de taxas e autenticação.
  
### Decisões Arquiteturais

- **Segurança**: Implementada com Spring Security e JWT, garantindo autenticação e autorização para proteger os endpoints.
- **API RESTful**: Todos os endpoints seguem os princípios REST, facilitando a integração com qualquer frontend.
- **Camadas Separadas**: A lógica de negócios (Service), controle (Controller) e persistência (Repository) estão desacopladas, melhorando a escalabilidade.
- **Tratamento de Erros**: O `GlobalExceptionHandler` centraliza o tratamento de exceções, fornecendo respostas padronizadas para o cliente.

## 📊 Principais Modelos e DTOs

### Transferência

| Campo               | Tipo        | Descrição                                        |
|---------------------|-------------|--------------------------------------------------|
| `id`                | `Long`      | Identificador único da transferência             |
| `contaOrigem`       | `String`    | Conta de origem da transferência                 |
| `contaDestino`      | `String`    | Conta de destino                                 |
| `valorTransferencia`| `BigDecimal`| Valor da transferência                           |
| `taxa`              | `Double`    | Taxa calculada                                   |
| `dataAgendamento`   | `LocalDate` | Data do agendamento                              |
| `dataTransferencia` | `LocalDate` | Data da transferência                            |

### LoginRequestDTO

| Campo   | Tipo     | Descrição                  |
|---------|----------|----------------------------|
| `email` | `String` | E-mail do usuário          |
| `senha` | `String` | Senha do usuário           |

### LoginResponseDTO

| Campo   | Tipo     | Descrição                  |
|---------|----------|----------------------------|
| `token` | `String` | Token JWT para autenticação|

## 💡 Melhorias Planejadas

1. **Endpoints de Usuário**: Expandir funcionalidades, incluindo atualização e remoção de usuários.
2. **Logs e Monitoramento**: Adicionar logs detalhados e configurar monitoramento com ferramentas como Prometheus e Grafana.
3. **Suporte a Bancos Relacionais**: Migrar para PostgreSQL em produção, com suporte a containers Docker.
4. **Cache**: Implementar cache em endpoints de leitura para melhorar a performance.
5. **Validações Avançadas**: Customizar validações em nível de DTO e entidade.
