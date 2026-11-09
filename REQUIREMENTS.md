# FinTrack — Requisitos do Sistema

## 1. Visão geral

O **FinTrack** é um sistema de controle financeiro pessoal que permite cadastrar usuários, registrar receitas e despesas, organizar movimentações por categorias e contas financeiras e acompanhar a evolução da situação financeira.

O sistema será desenvolvido inicialmente como uma aplicação CLI (Command Line Interface), utilizando Java e armazenamento em memória.

O projeto será evoluído progressivamente, incorporando persistência em banco de dados e, posteriormente, uma API REST.

## 2. Escopo do projeto

### 2.1. V1 — Aplicação CLI

A primeira versão deverá oferecer:

- Cadastro e seleção de usuários.
- Cadastro e gerenciamento de receitas e despesas.
- Gerenciamento de categorias financeiras.
- Gerenciamento de contas financeiras.
- Consulta e filtragem de transações.
- Cálculo de saldos.
- Relatórios e análises financeiras.
- Armazenamento dos dados em memória durante a execução.

A V1 não implementará autenticação ou autorização por senha. O usuário selecionará um perfil previamente cadastrado ao iniciar a aplicação.

### 2.2. Evolução planejada

| Versão | Escopo |
|---|---|
| V1 | Java CLI, Collections e armazenamento em memória. |
| V2 | Persistência com JDBC e PostgreSQL. |
| V3 | API REST com Spring Boot, autenticação e autorização. |

---

# 3. Requisitos funcionais

## 3.1. Gerenciamento de usuários

**RF001 — Cadastro de usuários**

O sistema deve permitir o cadastro de usuários.

**RF002 — Identificação do usuário**

Cada usuário deve possuir:
- Identificador único, gerado automaticamente.
- Nome.
- E-mail.

**RF003 — Unicidade do e-mail**

O sistema não deve permitir o cadastro de usuários com e-mails duplicados.

**RF004 — Seleção de usuário**

Ao iniciar a aplicação, o sistema deve apresentar os usuários cadastrados e permitir que o usuário selecione um perfil.

**RF005 — Isolamento dos dados**

O sistema deve garantir que cada usuário visualize e manipule exclusivamente seus próprios dados.

**RF006 — Exclusão de usuário**

O sistema deve permitir a exclusão de usuários.

A exclusão deverá remover também todas as categorias, contas financeiras e transações pertencentes ao usuário.

A operação deverá exigir confirmação explícita, informando que os dados serão excluídos permanentemente.

## 3.2. Gerenciamento de categorias

**RF007 — Cadastro de categorias**

O sistema deve permitir que cada usuário cadastre categorias financeiras.

Cada categoria deve possuir:
- Identificador único, gerado automaticamente.
- Nome.
- Tipo (receita ou despesa).
- Usuário proprietário.

**RF008 — Tipos de categoria**

O sistema deve disponibilizar dois tipos de categoria:
- `INCOME` — Receita.
- `EXPENSE` — Despesa.

**RF009 — Unicidade de categorias**

O sistema não deve permitir categorias com o mesmo nome e tipo para um mesmo usuário.

Categorias com o mesmo nome, mas tipos diferentes, serão permitidas.

**RF010 — Edição de categorias**

O sistema deve permitir a edição do nome e do tipo de uma categoria.

A alteração do nome será permitida desde que respeite a regra de unicidade.

A alteração do tipo somente será permitida quando não existirem transações associadas à categoria.

**RF011 — Exclusão de categorias**

O sistema deve permitir a exclusão de categorias.

Antes da exclusão, deverá informar quantas transações estão associadas à categoria e avisar que elas permanecerão cadastradas, mas ficarão sem categoria.

A operação deverá exigir confirmação.

**RF012 — Consulta de categorias**

O sistema deve permitir que o usuário consulte suas categorias cadastradas.

## 3.3. Gerenciamento de contas financeiras

**RF013 — Cadastro de contas**

O sistema deve permitir que cada usuário cadastre contas financeiras.

Cada conta deve possuir:
- Identificador único, gerado automaticamente.
- Nome.
- Usuário proprietário.

**RF014 — Unicidade de contas**

O sistema não deve permitir contas com nomes duplicados para um mesmo usuário.

Usuários diferentes poderão possuir contas com o mesmo nome.

**RF015 — Edição de contas**

O sistema deve permitir a alteração do nome de uma conta financeira, independentemente da existência de transações associadas.

A alteração não deverá modificar os vínculos existentes entre a conta e suas transações.

**RF016 — Exclusão de contas**

O sistema deve permitir a exclusão de contas financeiras somente quando não existirem transações associadas.

Caso existam transações, o sistema deverá informar a quantidade de registros vinculados e orientar o usuário a transferi-los para outra conta de sua propriedade.

Após a transferência de todas as transações, a exclusão poderá ser realizada mediante confirmação explícita.

**RF017 — Consulta de contas**

O sistema deve permitir que o usuário consulte suas contas financeiras.

## 3.4. Gerenciamento de transações

**RF018 — Cadastro de transações**

O sistema deve permitir o cadastro de receitas e despesas.

Cada transação deve possuir:
- Identificador único, gerado automaticamente.
- Descrição.
- Valor.
- Data.
- Tipo (receita ou despesa).
- Categoria.
- Conta financeira.
- Usuário proprietário.

**RF019 — Tipos de transação**

O sistema deve disponibilizar dois tipos:
- `INCOME` — Receita.
- `EXPENSE` — Despesa.

**RF020 — Associação de categorias**

Toda transação deverá possuir uma categoria no momento do cadastro.

A categoria deverá pertencer ao mesmo usuário da transação e possuir o mesmo tipo da movimentação.

**RF021 — Associação de contas financeiras**

Toda transação deverá estar associada a exatamente uma conta financeira.

A conta deverá pertencer ao mesmo usuário da transação.

**RF022 — Registro de datas**

O sistema deverá permitir transações com datas passadas, atuais e futuras.

A data representa o momento em que a movimentação ocorreu ou está prevista para ocorrer.

**RF023 — Edição de transações**

O sistema deverá permitir a edição das transações cadastradas pelo usuário proprietário.

**RF024 — Exclusão de transações**

O sistema deverá permitir a exclusão de transações pelo usuário proprietário.

A exclusão será física (remoção definitiva do registro).

Antes da operação, o sistema deverá apresentar os dados da transação, informar que a exclusão é irreversível e solicitar confirmação explícita.

**RF025 — Consulta de transações**

O sistema deverá permitir que o usuário consulte suas transações cadastradas.

**RF026 — Transações sem categoria**

O sistema deverá manter as transações quando suas categorias forem excluídas.

Essas transações deverão continuar participando dos cálculos financeiros e aparecer agrupadas como "Sem categoria" nos relatórios.

## 3.5. Consulta e filtragem

**RF027 — Filtros de consulta**

O sistema deverá permitir a filtragem de transações pelos seguintes critérios:

- Tipo de transação.
- Período.
- Mês.
- Conta financeira.
- Categoria.
- Valor mínimo.
- Valor máximo.

**RF028 — Combinação de filtros**

O sistema deverá permitir a combinação independente dos filtros.

Exemplo: consultar despesas da categoria Alimentação, realizadas em outubro, na conta corrente, com valores entre R$ 50,00 e R$ 500,00.

Os filtros não deverão ser mutuamente exclusivos.

## 3.6. Controle e análise financeira

**RF029 — Cálculo do saldo**

O sistema deverá calcular o saldo financeiro considerando:

`Saldo = Total de receitas - Total de despesas`

Os valores deverão respeitar os tipos das transações.

**RF030 — Consulta de saldo**

O sistema deverá permitir a consulta do saldo financeiro do usuário.

**RF031 — Consulta de receitas e despesas**

O sistema deverá apresentar:
- Total de receitas.
- Total de despesas.
- Saldo financeiro.

**RF032 — Análise por período**

O sistema deverá permitir a consulta dos resultados financeiros considerando um período selecionado.

**RF033 — Análise por conta financeira**

O sistema deverá permitir a consulta dos resultados financeiros considerando uma conta financeira específica.

**RF034 — Despesas por categoria**

O sistema deverá agrupar as despesas por categoria e apresentar o valor total gasto em cada uma.

**RF035 — Identificação das maiores despesas por categoria**

O sistema deverá permitir identificar as categorias nas quais o usuário possui os maiores gastos.

A análise deverá considerar o período selecionado.

Transações sem categoria deverão aparecer agrupadas como "Sem categoria".

**RF036 — Evolução do saldo**

O sistema deverá apresentar a evolução do saldo financeiro ao longo do tempo.

O usuário deverá poder acompanhar o comportamento do saldo em um período selecionado.

**RF037 — Saldo realizado**

O sistema deverá apresentar o saldo realizado, considerando somente transações com data até o dia atual.

**RF038 — Saldo projetado**

O sistema deverá apresentar o saldo projetado, considerando transações passadas, atuais e futuras.

O objetivo é permitir que o usuário acompanhe tanto sua situação financeira atual quanto uma projeção baseada nas movimentações planejadas.

---

# 4. Regras de negócio

As regras de negócio estabelecem as condições que devem ser respeitadas pelas operações do FinTrack.

### Usuários

**RN001:** Todo usuário deve possuir um identificador único, nome e e-mail.

**RN002:** O e-mail deve ser único entre os usuários cadastrados.

**RN003:** Cada usuário somente poderá acessar e manipular seus próprios registros.

**RN004:** A exclusão de um usuário deverá remover todos os registros associados a ele.

### Categorias

**RN005:** Cada categoria pertence obrigatoriamente a um único usuário.

**RN006:** Um usuário poderá possuir várias categorias ou nenhuma.

**RN007:** Uma categoria poderá classificar várias transações ou nenhuma.

**RN008:** Uma categoria poderá possuir o mesmo nome de outra categoria do mesmo usuário somente quando os tipos forem diferentes.

**RN009:** O tipo da categoria deverá ser compatível com o tipo da transação associada.

**RN010:** Uma categoria poderá ter seu nome alterado mesmo quando possuir transações associadas.

**RN011:** O tipo de uma categoria somente poderá ser alterado quando não houver transações associadas.

**RN012:** A exclusão de uma categoria não deverá excluir suas transações associadas.

### Contas financeiras

**RN013:** Cada conta financeira pertence obrigatoriamente a um único usuário.

**RN014:** Um usuário poderá possuir várias contas financeiras ou nenhuma.

**RN015:** Um usuário não poderá possuir duas contas com o mesmo nome.

**RN016:** Contas de usuários diferentes poderão possuir o mesmo nome.

**RN017:** Uma conta financeira poderá existir sem possuir transações associadas.

**RN018:** Cada transação deverá estar vinculada a exatamente uma conta financeira.

**RN019:** Uma conta financeira poderá estar associada a várias transações ou nenhuma.

**RN020:** Uma conta financeira não poderá ser excluída enquanto possuir transações associadas.

**RN021:** A alteração do nome de uma conta não deverá modificar suas transações associadas.

### Transações

**RN022:** Cada transação pertence obrigatoriamente a um único usuário.

**RN023:** Um usuário poderá possuir várias transações ou nenhuma.

**RN024:** O valor de uma transação deverá ser estritamente maior que zero.

**RN025:** Valores monetários deverão utilizar `BigDecimal`.

**RN026:** Valores negativos não serão permitidos.

**RN027:** O tipo da transação determinará se o valor será somado ou subtraído do saldo.

**RN028:** Uma transação deverá possuir uma categoria no momento de sua criação.

**RN029:** A categoria e a conta financeira associadas deverão pertencer ao mesmo usuário da transação.

**RN030:** O tipo da categoria deverá corresponder ao tipo da transação.

**RN031:** Transações poderão possuir datas passadas, atuais ou futuras.

**RN032:** A exclusão de uma transação será física e exigirá confirmação explícita.

**RN033:** Transações poderão ser editadas e excluídas somente pelo usuário proprietário.

**RN034:** Uma transação poderá ficar sem categoria após a exclusão de sua categoria original.

**RN035:** Uma transação nunca poderá ficar sem uma conta financeira.

### Validações

**RN036:** Campos obrigatórios não poderão ser nulos ou vazios.

**RN037:** Campos textuais não poderão conter apenas espaços em branco.

**RN038:** Espaços desnecessários no início e no final dos textos deverão ser removidos antes do armazenamento.

**RN039:** O e-mail deverá possuir formato válido.

**RN040:** A data da transação será obrigatória.

---

# 5. Restrições de escopo da V1

- Aplicação executada por interface de linha de comando (CLI).
- Desenvolvimento em Java.
- Armazenamento dos dados em memória.
- Utilização de Collections para gerenciar os registros.
- Não haverá autenticação por senha na V1.
- Não haverá persistência em banco de dados na V1.
- A exclusão de transações e usuários será definitiva.
- O sistema deverá respeitar o isolamento dos dados entre usuários.

---

# 6. Pontos de atenção para a próxima etapa

Os seguintes aspectos deverão ser detalhados ou validados durante a consolidação do modelo de domínio e das decisões de implementação:

1. Definir os atributos e tipos de dados definitivos de cada entidade.
2. Validar as cardinalidades e os relacionamentos entre as entidades.
3. Definir como será representada uma transação sem categoria após a exclusão de sua categoria.
4. Detalhar o cálculo do saldo realizado e projetado.
5. Definir como a evolução histórica do saldo será apresentada e agrupada temporalmente.
6. Detalhar o comportamento dos relatórios quando houver filtros combinados.

---

## 7. Status

**Documento:** Requisitos do FinTrack  
**Versão:** 1.0 — Consolidação inicial da V1  
**Status:** Em revisão, aguardando validação do modelo de domínio.