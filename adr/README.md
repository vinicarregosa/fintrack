# Architecture Decision Records

Este diretório contém os registros de decisões arquiteturais (*Architecture Decision Records — ADRs*) do FinTrack.

Cada ADR documenta uma decisão relevante para a arquitetura do sistema, incluindo seu contexto, justificativa e consequências.

## Decisões registradas

| ADR | Decisão | Status |
|---|---|---|
| [ADR-0001](0001-package-by-feature.md) | Organização por funcionalidades | Aceita |
| [ADR-0002](0002-hexagonal-principles.md) | Adoção de princípios da Arquitetura Hexagonal | Aceita |
| [ADR-0003](0003-in-memory-persistence.md) | Persistência em memória na V1 | Aceita |
| [ADR-0004](0004-bigdecimal-for-money.md) | Utilização de BigDecimal para valores monetários | Aceita |

## Convenções

- Cada decisão relevante deve possuir seu próprio arquivo.
- Os arquivos devem utilizar numeração sequencial.
- Decisões aceitas não devem ser apagadas quando substituídas.
- Uma decisão substituída deve indicar qual ADR passou a prevalecer.
- O documento deve registrar os motivos da decisão, não apenas sua conclusão.

## Estrutura de um ADR

Cada registro deverá conter:

1. Título e identificação.
2. Status.
3. Contexto.
4. Decisão.
5. Consequências.