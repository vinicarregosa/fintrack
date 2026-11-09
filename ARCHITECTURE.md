# FinTrack — Arquitetura

**Versão:** 1.1  
**Status:** Definida  
**Linguagem:** Java  
**Versão inicial:** V1 — CLI

## 1. Visão geral

O FinTrack utilizará a organização de pacotes **Package by Feature**, aplicando princípios da **Arquitetura Hexagonal (Ports and Adapters)**.

A aplicação será desenvolvida inicialmente em Java puro, com interface CLI e armazenamento em memória.

A arquitetura deverá permitir a evolução da persistência para JDBC e PostgreSQL e, posteriormente, a disponibilização de uma API REST com Spring Boot.

## 2. Organização dos pacotes

A organização principal será baseada nas funcionalidades do sistema.

```text
com.fintrack/
│
├── FinTrackApplication.java
│
├── user/
│   ├── User.java
│   ├── UserService.java
│   └── UserRepository.java
│
├── transaction/
│   ├── Transaction.java
│   ├── TransactionType.java
│   ├── TransactionService.java
│   └── TransactionRepository.java
│
├── category/
│   ├── Category.java
│   ├── CategoryService.java
│   └── CategoryRepository.java
│
├── account/
│   ├── FinancialAccount.java
│   ├── FinancialAccountService.java
│   └── FinancialAccountRepository.java
│
├── infrastructure/
│   └── persistence/
│       └── memory/
│           ├── InMemoryUserRepository.java
│           ├── InMemoryTransactionRepository.java
│           ├── InMemoryCategoryRepository.java
│           └── InMemoryFinancialAccountRepository.java
│
└── cli/
```

Essa estrutura representa a organização inicial. Novos pacotes e classes serão adicionados conforme as necessidades do projeto.

## 3. Responsabilidades

### 3.1 Pacotes de funcionalidades

Cada pacote representa uma área do domínio.

- `user`: cadastro e gerenciamento de usuários.
- `transaction`: registro e gerenciamento de receitas e despesas.
- `category`: gerenciamento de categorias.
- `account`: gerenciamento de contas financeiras.

As classes `Service` coordenam as operações e regras de negócio da funcionalidade.

As interfaces `Repository` definem os contratos necessários para acessar os dados.

### 3.2 Infrastructure

Contém implementações técnicas necessárias para o funcionamento da aplicação.

Na V1, será responsável pela implementação dos repositórios utilizando Collections do Java.

### 3.3 CLI

Responsável pela interação com o usuário por meio do terminal.

Deve receber entradas, apresentar informações e solicitar a execução das operações disponibilizadas pela aplicação.

A CLI não deve concentrar regras de negócio.

## 4. Aplicação dos princípios hexagonais

O FinTrack adotará os seguintes princípios:

- **Inversão de dependência:** os serviços devem depender de abstrações quando precisarem acessar componentes externos.
- **Separação de responsabilidades:** regras de negócio não devem ficar misturadas com detalhes de armazenamento ou interação com o terminal.
- **Ports and Adapters:** interfaces representam contratos de comunicação, enquanto implementações concretas funcionam como adaptadores.
- **Independência de infraestrutura:** a lógica central não deve depender diretamente de Collections, JDBC, PostgreSQL ou Spring Boot.

Não será implementada uma estrutura hexagonal completa antecipadamente. As abstrações serão introduzidas conforme as necessidades justificarem.

## 5. Persistência e evolução

| Versão | Tecnologia | Objetivo |
|---|---|---|
| V1 | Java Collections | Armazenamento em memória. |
| V2 | JDBC + PostgreSQL | Persistência relacional. |
| V3 | Spring Boot | Exposição das funcionalidades por API REST. |

As futuras implementações de persistência deverão respeitar os contratos definidos pelas interfaces de repositório.

## 6. Diretrizes

1. Priorizar simplicidade e clareza.
2. Organizar o código por funcionalidade.
3. Evitar abstrações sem necessidade concreta.
4. Manter as regras de negócio independentes da CLI e da persistência.
5. Não antecipar complexidade de versões futuras.
6. Testar as regras de negócio independentemente dos detalhes de infraestrutura.