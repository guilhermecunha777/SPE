# SPE — Sistema de Presença Escolar

> API REST desenvolvida em Spring Boot para gerenciar chamadas escolares, registrar presenças/faltas e acompanhar a frequência dos alunos.

---

## Tecnologias

- Java 17
- Spring Boot 4.0.6
- Spring Data JPA
- Spring Web MVC
- MySQL
- Lombok

---

## Funcionalidades

- Login de professor com autenticação por usuário e senha
- 3 turmas disponíveis: A (4 aulas/dia), B (5 aulas/dia) e C (3 aulas/dia)
- 7 alunos pré-cadastrados por turma — 21 alunos no total
- Registro de presença ou falta por aluno a cada chamada
- Configuração de 1 a 4 faltas por ausência
- Cálculo automático de frequência por aluno
- Status de frequência: Regular (≥75%), Atenção (≥50%), Crítico (<50%)
- Proteção contra chamada duplicada no mesmo dia

---

## Estrutura do Projeto

```
src/main/java/com/escolar/presenca/spe/
├── config/
│   └── DataInitializer.java       ← popula o banco na primeira execução
│  
├── controller/
│   ├── AuthController.java        ← POST /api/auth/login
│   └── ChamadaController.java     ← endpoints de chamada
├── dto/
│   ├── ChamadaRequest.java
│   └── FrequenciaResponse.java
├── model/
│   ├── Professor.java
│   ├── Turma.java
│   ├── Aluno.java
│   └── Presenca.java
├── repository/
│   ├── ProfessorRepository.java
│   ├── TurmaRepository.java
│   ├── AlunoRepository.java
│   └── PresencaRepository.java
├── service/
│   ├── AuthService.java
│   └── ChamadaService.java
└── SpeApplication.java
```

---

## Como Executar

### Pré-requisitos

- Java 17+
- MySQL rodando na porta 3306
- Maven (ou usar o `mvnw` incluso)

### 1. Criar o banco de dados

```sql
CREATE DATABASE chamada_escolar;
```

### 2. Configurar o `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/chamada_escolar
spring.datasource.username=root
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

### 3. Rodar o projeto

```bash
./mvnw spring-boot:run
```

A aplicação sobe na porta `8080`. Na primeira execução, o `DataInitializer` popula automaticamente as turmas, os 21 alunos e o professor padrão.

### Credenciais padrão

| Campo   | Valor      |
|---------|------------|
| Usuário | professor  |
| Senha   | senha123   |

---

## Endpoints da API

Base URL: `http://localhost:8080`

---

### `POST /api/auth/login`

Autentica o professor.

**Body:**
```json
{
  "usuario": "professor",
  "senha": "senha123"
}
```

**Resposta (200):**
```json
{
  "sucesso": true,
  "nome": "prof. Jython",
  "usuario": "professor"
}
```

**Resposta (401):**
```json
{
  "sucesso": false,
  "mensagem": "Usuário ou senha incorretos"
}
```

---

### `GET /api/chamada/turma/{letra}`

Retorna os alunos de uma turma (A, B ou C).

**Exemplo:** `GET /api/chamada/turma/A`

**Resposta (200):**
```json
[
  { "id": 1, "nome": "Ana Beatriz Silva" },
  { "id": 2, "nome": "Bruno Oliveira" }
]
```

---

### `POST /api/chamada/salvar`

Salva o registro de presença/falta do dia.

**Body:**
```json
{
  "turmaLetra": "A",
  "faltasporAusencia": 2,
  "registros": [
    { "alunoId": 1, "presente": true,  "quantidadeFaltas": 0 },
    { "alunoId": 2, "presente": false, "quantidadeFaltas": 2 }
  ]
}
```

**Resposta (200):**
```json
{
  "mensagem": "Chamada salva com sucesso!"
}
```

---

### `GET /api/chamada/frequencia/{turmaLetra}`

Retorna a frequência acumulada de todos os alunos da turma.

**Exemplo:** `GET /api/chamada/frequencia/A`

**Resposta (200):**
```json
[
  {
    "alunoId": 1,
    "alunoNome": "Ana Beatriz Silva",
    "turma": "A",
    "totalFaltas": 0,
    "totalAlunasPossiveis": 400,
    "frequenciaPercent": 100.0,
    "status": "regular"
  }
]
```

---

## Cálculo de Frequência

A frequência é calculada com base em um semestre de **100 dias letivos**:

```
Total de Aulas = aulasPorDia × 100
Frequência (%) = ((Total de Aulas - Total de Faltas) / Total de Aulas) × 100
```

| Faixa          | Status   |
|----------------|----------|
| 75% ou mais    | Regular  |
| 50% a 74%      | Atenção  |
| Abaixo de 50%  | Crítico  |

---

## Alunos Cadastrados

| Turma A (4 aulas/dia) | Turma B (5 aulas/dia) | Turma C (3 aulas/dia) |
|-----------------------|-----------------------|-----------------------|
| Ana Beatriz Silva     | Henrique Souza        | Olivia Ribeiro        |
| Bruno Oliveira        | Isabela Martins       | Pedro Carvalho        |
| Carla Mendes          | João Pedro Alves      | Quiteria Nunes        |
| Diego Ferreira        | Karina Pereira        | Rafael Dias           |
| Eduarda Costa         | Lucas Araújo          | Sabrina Moura         |
| Felipe Rocha          | Mariana Santos        | Thiago Barros         |
| Gabriela Lima         | Nicolas Gomes         | Ursula Vieira         |

---

## Autor

Desenvolvido por [guilhermecunha777](https://github.com/guilhermecunha777)

Repositório: [https://github.com/guilhermecunha777/SPE](https://github.com/guilhermecunha777/SPE)
