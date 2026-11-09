# ADR-0001 — Organização por funcionalidades

**Data:** 08/11/2026  
**Status:** Aceita

## Contexto

O FinTrack possui diferentes funcionalidades, como gerenciamento de usuários, transações, categorias e contas financeiras.

Uma organização baseada exclusivamente em responsabilidades técnicas poderia concentrar muitas classes de funcionalidades distintas nos mesmos pacotes, dificultando a localização e a manutenção do código conforme o sistema cresce.

## Decisão

Adotar a abordagem *Package by Feature*, organizando os pacotes principais de acordo com as funcionalidades do domínio.

Cada funcionalidade deverá manter seus componentes relacionados próximos, como entidades, serviços e interfaces de repositório.

## Consequências

- Facilita localizar componentes relacionados a uma funcionalidade.
- Favorece a organização do código conforme o sistema cresce.
- Reduz a necessidade de manter pacotes técnicos globais muito abrangentes.
- Exige atenção às dependências entre funcionalidades.
- A organização interna poderá ser aprofundada conforme surgirem necessidades reais.