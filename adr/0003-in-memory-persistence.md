# ADR-0003 — Persistência em memória na primeira versão

**Status:** Aceita

## Contexto

O FinTrack será desenvolvido em etapas, começando por uma aplicação de linha de comando em Java.

A primeira versão tem como objetivo implementar e validar as principais funcionalidades do sistema, incluindo o gerenciamento de usuários, contas financeiras, categorias e transações.

A utilização de um banco de dados neste estágio acrescentaria configurações e responsabilidades de infraestrutura antes que as regras fundamentais da aplicação estivessem consolidadas.

## Decisão

Utilizar estruturas de dados em memória, por meio das Collections do Java, para armazenar as informações durante a execução da primeira versão.

Os dados não serão mantidos após o encerramento da aplicação.

A persistência será acessada por meio de interfaces de repositório, permitindo que os serviços não dependam diretamente das estruturas utilizadas para armazenar os dados.

A utilização de banco de dados será introduzida em uma etapa posterior, com JDBC e PostgreSQL.

## Consequências

- A primeira versão terá menos dependências e configurações de infraestrutura.
- Será possível concentrar os esforços nas regras de negócio e no funcionamento das funcionalidades.
- Os dados serão perdidos quando a aplicação for encerrada.
- Será necessário substituir as implementações em memória quando a persistência em banco de dados for introduzida.
- A separação entre contratos de repositório e implementações permitirá realizar essa substituição com menor impacto nos serviços.