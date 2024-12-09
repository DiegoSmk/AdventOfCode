# Desafio: Reparação da Ponte

## Objetivo
Determinar quais equações podem ser verdadeiras ao inserir operadores de soma (`+`) e multiplicação (`*`) entre os números fornecidos e calcular a soma dos valores de teste dessas equações válidas.

## 1. **Entenda a Avaliação de Operadores**
- Os operadores devem ser avaliados da esquerda para a direita.
- Não há precedência entre `+` e `*`.

Exemplo:
Para `10 + 20 * 3`, o resultado será `(10 + 20) * 3 = 90`, e não `10 + (20 * 3) = 70`

## 2. **Processar a Entrada**
- Cada linha consiste em:
   1. Um valor de teste **antes** dos dois-pontos.
   2. Uma sequência de números separados por espaços **após** os dois-pontos.
- Exemplo de linha: `190: 10 19`

Objetivo: Determinar se os números fornecidos podem ser combinados com os operadores para produzir o valor de teste.

## 3. **Gerar Combinações de Operadores**
- Para uma sequência de `N` números, há `N-1` posições para inserir operadores.
- As combinações possíveis de operadores para essas posições são baseadas nas permutações de `+` e `*`.

Exemplo:
Para três números (`10 20 30`), há duas posições:
Possibilidades: `+ +`, `+ *`, `* +`, `* *`.

## 4. **Avaliar Todas as Combinações**
- Para cada combinação de operadores:
  1. Insira os operadores entre os números.
  2. Avalie a expressão da esquerda para a direita.
- Compare o resultado com o valor de teste.

Exemplo:
`190: 10 19`
- Testar `10 + 19`: `29` (não corresponde).
- Testar `10 * 19`: `190` (corresponde).

## 5. **Registrar Equações Válidas**
- Se pelo menos uma combinação de operadores torna a equação **verdadeira**, **registre** o valor de teste como válido.

## 6. **Somar os Valores de Teste Válidos**
- Depois de avaliar todas as linhas, **some** os valores de teste de todas as equações válidas.