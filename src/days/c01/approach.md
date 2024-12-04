# Desafio: Histeria Histórica

## Objetivo:
O objetivo deste desafio é calcular a distância total entre dois conjuntos de números (listas) de IDs de locais, quando esses números são emparelhados da menor para a maior ordem. A distância entre dois números é dada pela diferença absoluta entre eles, e a distância total é a soma das distâncias entre todos os pares correspondentes.

## Passo a Passo para Resolver o Desafio:

### 1. **Leitura das Listas de IDs de Local**
Você tem duas listas de IDs de locais que precisam ser comparadas. Cada lista contém vários números, e o desafio está em comparar os números correspondentes em cada lista para calcular a distância entre eles.

### 2. **Ordenar Ambas as Listas**
Para poder comparar as listas de maneira correta, você precisa ordenar ambas as listas em ordem crescente. Isso ajuda a garantir que os menores números de cada lista sejam emparelhados, o que é o que o enunciado pede.

Por exemplo, ao ordenar as listas:

### 3. **Calcular a Distância Entre os Pares**
Após ordenar as listas, você emparelha os números correspondentes de cada lista (o primeiro número da lista da esquerda com o primeiro número da lista da direita, o segundo com o segundo, e assim por diante).

Para cada par de números, calcule a distância entre eles. A distância é dada pela fórmula da diferença absoluta entre os dois números:
```
distância = |a - b|
```
Onde `a` é um número da lista da esquerda e `b` é o número correspondente da lista da direita.

### 4. **Somar as Distâncias**
Depois de calcular a distância de cada par, você deve somá-las para obter a distância total. A fórmula será:

```
distância total = distância1 + distância2 + ... + distânciaN
```
No exemplo, a distância total seria a soma das distâncias calculadas entre os números das listas ordenadas.

### 5. **Conclusão**
Após aplicar esses passos e calcular a distância entre todas as listas, você terá a distância total entre as duas listas de IDs de locais. Esse valor será o que você deverá reportar como resultado do desafio.

--- Parte 2 ---

### 1. **Leitura das Listas de ID**
* A lista da esquerda: números que serão avaliados.
* A lista da direita: números usados para contar as ocorrências.

### 2. **Contar Ocorrência na Lista da Direita**
* Use uma estrutura de contagem para calcular quantas vezes cada número aparece na lista da direita.
* Resultado: um ```Map``` onde a chave é o número e o valor é o número de ocorrências.

```kotlin
val rightList = listOf(3, 5, 3, 9, 3, 3)
val frequencyMap = rightList.groupingBy { it }.eachCount()
```

### 3. **Calcular o Score de Similariedade**
* Para cada **número da esquerda**, siga os passos:
1. Verifique se o número existe no ```Map``` de frequências. 
2. Se existir, multiplique o número pela sua frequência. 
3. Adicione o resultado ao score total. 
4. Caso o número não exista, ele não contribui para o score (multiplicação por zero).

### 4. **Resultado Final**
Após processar todos os números da lista da esquerda, o valor final do ```similarityScore``` será o **score de similaridade**.