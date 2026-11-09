# ADR-0002 — Organização dos pacotes com princípios da Arquitetura Hexagonal

**Status:** Aceita

## Contexto

O FinTrack será desenvolvido inicialmente como uma aplicação de linha de comando (CLI), utilizando armazenamento em memória. Entretanto, o projeto prevê evoluções futuras, incluindo persistência em banco de dados com JDBC e disponibilização de uma API REST com Spring Boot.

Essa evolução exige uma organização que facilite a substituição de componentes de infraestrutura sem provocar alterações desnecessárias nas regras de negócio.

Uma arquitetura excessivamente complexa também não seria adequada ao estágio atual do projeto.

## Decisão

Adotar **Package by Feature** como estratégia de organização dos pacotes, aplicando princípios da Arquitetura Hexagonal para reduzir o acoplamento entre as regras de negócio e os detalhes de infraestrutura.

Cada funcionalidade terá seu próprio pacote, reunindo suas principais classes, como entidades, serviços e interfaces de repositório.

As implementações concretas de persistência ficarão na infraestrutura.

Estrutura inicial proposta:

```text
com.fintrack/
├── FinTrackApplication.java
├── user/
├── transaction/
├── category/
├── account/
├── infrastructure/
│   └── persistence/
│       └── memory/
└── cli/
```

As interfaces de repositório representarão os contratos utilizados pelos serviços. As implementações, por sua vez, serão responsáveis pelos detalhes de armazenamento.

## Consequências

- As funcionalidades ficam organizadas por domínio, facilitando a localização e manutenção do código.
- Os serviços não precisam conhecer os detalhes da implementação de persistência.
- A implementação em memória poderá ser substituída futuramente por uma implementação com JDBC e PostgreSQL.
- A estrutura inicial permanece simples, evitando abstrações e subdivisões desnecessárias.
- Novos pacotes e separações poderão ser introduzidos conforme as necessidades reais do projeto.