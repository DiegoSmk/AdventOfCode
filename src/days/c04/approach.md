# Desafio: Busca de Ceres

## Objetivo
O desafio consiste em encontrar todas as ocorrências da palavra **XMAS** em uma grade de caça-palavras fornecida. A palavra pode estar disposta em várias direções e pode sobrepor outras palavras.

---

## Regras do Caça-Palavras

A palavra **XMAS** pode aparecer de diversas maneiras na grade, incluindo:

1. **Horizontalmente** (da esquerda para a direita ou da direita para a esquerda).
2. **Verticalmente** (de cima para baixo ou de baixo para cima).
3. **Diagonalmente** (em qualquer direção).
4. **Sobrepondo outras palavras** (compartilhando letras com outras ocorrências).  

## Como Resolver o Desafio

### 1. **Análise da Grade**
A grade fornecida consiste em linhas de caracteres. Seu objetivo é encontrar todas as combinações possíveis da palavra **XMAS**, respeitando as direções permitidas.

### 2. **Exploração das Direções**
Verifique todas as direções possíveis para encontrar a palavra:
- Esquerda para a direita.
- Direita para a esquerda.
- Cima para baixo.
- Baixo para cima.
- Todas as diagonais (nas quatro direções possíveis).

### 3. **Contagem das Ocorrências**
Para cada direção, percorra a grade buscando sequências de caracteres que formem a palavra **XMAS**. Cada vez que uma sequência for encontrada, incremente um contador.

### 4. **Resultado Final**
A soma de todas as ocorrências encontradas nas diferentes direções é o resultado final.

---