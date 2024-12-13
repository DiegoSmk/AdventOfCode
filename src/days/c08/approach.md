# Day 8: Colinearidade Ressonante

## Passo 1: Entendendo o Alinhamento e Antinódos
## Passo 2: Antinódos com Mais de Duas Antenas
## Passo 3: Frequências Diferentes
## Passo 4: Calculando o Impacto do Sinal
Para calcular o impacto do sinal, precisamos determinar quantos locais únicos no mapa contêm antinódos dentro dos limites do mapa.

## Passo 5: Solução
- **Entrada:** O mapa com antenas.
- **Saída:** O número total de locais únicos dentro do mapa que contêm antinódos.

## Como Resolver
1. **Parse a entrada:** Converta o mapa de antenas em uma estrutura de dados que contenha as antenas e suas frequências.
2. **Encontre os pares de antenas com a mesma frequência:** Compare as antenas para determinar se estão em alinhamento e se a distância entre elas é duas vezes maior.
3. **Calcule os antinódos:** Para cada par de antenas que se alinham, calcule os antinódos que são gerados.
4. **Armazene os antinódos:** Use uma estrutura de dados como um conjunto (`Set`) para garantir que apenas locais únicos sejam contados.
5. **Conte os antinódos únicos:** O tamanho do conjunto ao final será o número total de locais únicos com antinódos.