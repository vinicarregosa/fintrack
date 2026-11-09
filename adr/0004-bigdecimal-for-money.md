# ADR-0004 — Utilização de BigDecimal para valores monetários

**Status:** Aceita

## Contexto

O FinTrack é uma aplicação de controle financeiro. Portanto, precisa realizar operações envolvendo valores monetários, como somas de receitas, despesas e cálculos de saldo.

Em Java, os tipos primitivos `float` e `double` utilizam representação binária de ponto flutuante. Essa representação pode produzir imprecisões em operações aritméticas com valores decimais.

Em uma aplicação financeira, essas imprecisões podem comprometer os resultados apresentados ao usuário.

## Decisão

Utilizar a classe `BigDecimal` da API Java para representar e realizar operações com valores monetários.

Os valores financeiros não deverão ser armazenados utilizando `float` ou `double`.

A criação de valores monetários deverá utilizar representações decimais adequadas, evitando a construção de `BigDecimal` diretamente a partir de valores do tipo `double`.

## Consequências

- As operações financeiras terão maior precisão decimal.
- Será necessário utilizar os métodos próprios de `BigDecimal` para operações aritméticas, como `add`, `subtract` e `multiply`.
- Comparações entre valores deverão considerar os métodos da classe, como `compareTo`.
- Será necessário definir regras de escala e arredondamento nos cálculos em que essas operações forem aplicáveis.