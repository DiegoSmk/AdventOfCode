# Desafio: Relatórios do Nariz Vermelho

## Objetivo:
O objetivo é analisar uma lista de relatórios e determinar quantos desses relatórios são "seguros", de acordo com as regras fornecidas. Para isso, precisamos verificar se os níveis de cada relatório seguem uma ordem crescente ou decrescente, além de garantir que a diferença entre dois níveis consecutivos seja sempre entre 1 e 3.

## Passo a Passo para Resolver o Desafio:

### 1. **Entrada de Dados**
O primeiro passo é ler os dados fornecidos (relatórios). Cada relatório é uma linha contendo uma lista de números (níveis), separados por espaços.

### 2. **Verificação de Ordem (Crescente ou Decrescente)**
Para cada relatório, verificamos se os números estão todos em ordem crescente ou decrescente.
- **Ordem crescente**: Cada número é maior ou igual ao anterior.
- **Ordem decrescente**: Cada número é menor ou igual ao anterior.

### 3. **Verificação da Diferença Entre Níveis**
Para cada par de números consecutivos, calculamos a diferença entre eles.
- A diferença entre dois números consecutivos deve ser no mínimo 1 e no máximo 3.
- Se a diferença entre dois números for menor que 1 ou maior que 3, o relatório é considerado "inseguro".

### 4. **Contagem de Relatórios Seguros**
- Após verificar as duas condições (ordem crescente/decrescente e diferença entre os números), contamos quantos relatórios atendem a ambas as condições e, portanto, são considerados seguros.

### 5. **Saída do Resultado**
- Exibir a quantidade de relatórios seguros.