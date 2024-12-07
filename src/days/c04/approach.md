# Desafio: Busca de Ceres

## Objetivo
O desafio consiste em encontrar todas as ocorrências da palavra **XMAS** em uma grade de caça-palavras fornecida. A palavra pode estar disposta em várias direções e pode sobrepor outras palavras.

---
```
MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
XMASAMXAMM
XXAMMXXAMA
SMSMSASXSS
SAXAMASAAA
MAMMMXMMMM
MXMXAXMASX
```

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

## Regras para Encontrar X-MAS

1. **Forma de X**
    - O padrão **X-MAS** é formado por dois **MAS** dispostos em diagonal.
    - Um **MAS** é encontrado na diagonal principal e o outro na diagonal oposta.
    - Ambos podem ser escritos tanto na ordem normal quanto invertidos.

2. **Caracteres Irrelevantes**
    - Assim como na primeira parte, os caracteres não relacionados podem ser ignorados.

3. **Contagem Total**
    - Cada padrão **X-MAS** encontrado na grade deve ser contado.
    - Não há limites para a quantidade de padrões sobrepostos.
   
---

## Exemplo Explicado

### Entrada
A mesma grade utilizada na Parte Um.

### Passo a Passo
1. Procure padrões que formem a estrutura de um "X".
2. Certifique-se de que cada diagonal contém **MAS** válido.
3. Conte todas as combinações possíveis.

---

### Saída
O número total de vezes que o padrão **X-MAS** aparece.  
Para o exemplo acima: **9 vezes**.

---