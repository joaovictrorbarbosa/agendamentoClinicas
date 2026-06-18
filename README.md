# API de Agendamento de Consultas Médicas

Neste projeto, foi desenvolvida uma API RESTful para o gerenciamento e agendamento de consultas em uma clínica médica. O foco principal do sistema é a aplicação de regras de negócio complexas para validação de agendas, controle de horários e consistência de dados.

## Tecnologias Utilizadas

* **Back-End:** Java 17, Spring Boot, Spring Data JPA
* **Banco de Dados:** PostgreSQL
* **Ferramentas:** Postman (para testes de endpoints), Git e GitHub (controle de versão)

## Camadas do Sistema

A API foi estruturada seguindo o padrão de arquitetura em camadas:

* **Controller:** Responsável por expor os endpoints HTTP, receber as requisições web, validar os dados de entrada (DTOs) e retornar as respostas HTTP corretas.
* **Service:** Concentra todas as regras de negócio e validações lógicas do sistema antes de persistir as informações.
* **Repository:** Interface de comunicação direta com o banco de dados utilizando o Spring Data JPA para geração automática de queries SQL.

## Funcionalidades e Regras de Negócio Implementadas

### 1. Fluxo de Agendamento (POST)
O sistema realiza diversas checagens automáticas na camada Service antes de confirmar uma consulta:
* **Antecedência Mínima:** A consulta deve ser agendada com pelo menos 2 dias de antecedência em relação ao momento atual.
* **Dias Úteis:** Bloqueio automático de agendamentos realizados para sábados e domingos.
* **Horário Comercial:** Restrição de horários, permitindo agendamentos apenas entre 07:00 e 18:00.
* **Conflito de Agenda:** Validação no banco de dados para garantir que o médico escolhido não possua outra consulta marcada exatamente no mesmo horário.

### 2. Pesquisas e Filtros Avançados (GET)
* **Listagem por Período:** Busca de consultas filtrando pelo início e fim de um dia específico utilizando conversões de LocalDate para LocalDateTime (atStartOfDay e LocalTime.MAX).
* **Filtro por Médico:** Endpoint dedicado para listar o histórico de consultas de um profissional específico através do seu identificador (ID).
* **Filtro por Paciente:** Endpoint dedicado para listar todas as consultas vinculadas a um paciente específico.
* **Busca Cadastral:** Consulta de dados cadastrais de médicos por nome utilizando buscas parciais e ignorando letras maiúsculas ou minúsculas (ContainingIgnoreCase).

## Como Executar o Projeto

1. Certifique-se de ter o Java 17 e o Maven instalados.
2. Configure as credenciais do seu banco de dados PostgreSQL/MySQL no arquivo `src/main/resources/application.properties`.
3. Execute o comando para compilar e rodar a aplicação:
   ```bash
   mvn spring-boot:run
