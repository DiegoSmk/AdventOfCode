
# Melhora no Tempo de Execução ao Substituir o Map por um Array

## Contexto

Na solução original, foi utilizado um `Map` para contar as ocorrências de elementos na lista "right" e, em seguida, calcular a pontuação de similaridade. No modo "Performance", a implementação foi otimizada substituindo o `Map` por um array de inteiros, o que trouxe uma melhoria significativa no tempo de execução.

### Código Original (Usando `Map`):

No código original, utilizamos o método `groupingBy` para contar as ocorrências dos elementos na lista "right", o que retorna um `Map<Int, Int>`, onde a chave é o número e o valor é a contagem de suas ocorrências.

```kotlin
fun countOccurrences(list: List<Int>): Map<Int, Int> {
    return list.groupingBy { it }.eachCount() // Cria um Map com as contagens
}
```

### Código Otimizado (Usando `IntArray`):

Na versão otimizada, substituímos o `Map` por um array (`IntArray`) para armazenar as frequências dos números na lista "right". Como os valores são inteiros e sabemos o valor máximo na lista, podemos usar um array onde o índice corresponde ao valor do número e o valor armazenado corresponde à frequência desse número.

```kotlin
fun countOccurrencesWithArray(list: List<Int>, maxValue: Int): IntArray {
    val frequencyArray = IntArray(maxValue + 1)
    list.forEach { num -> frequencyArray[num]++ }
    return frequencyArray
}
```

## Benefícios da Substituição do `Map` por um `IntArray`

### 1. **Otimização de Tempo**

O uso de um array para armazenar as frequências permite acessar as contagens em tempo constante, O(1), dado que estamos utilizando o índice do array diretamente para representar os valores dos números. Em contraste, o `Map` exige uma busca de chave para cada operação de acesso, o que pode ter um tempo de execução maior dependendo da implementação interna do `Map`.

### 2. **Redução do Uso de Memória**

Embora um `Map` precise de mais memória para armazenar as chaves e valores, um array simples (`IntArray`) usa uma quantidade fixa de memória proporcional ao maior valor possível na lista. Em vez de manter um mapa de todos os valores possíveis e suas contagens, um array ocupa uma quantidade previsível de memória, o que o torna mais eficiente em termos de espaço quando lidamos com valores inteiros dentro de um intervalo limitado.

### 3. **Complexidade de Tempo (Big O)**

#### Código Original com `Map`:
- **Contagem de Ocorrências**: O método `groupingBy` e o `eachCount()` iteram sobre a lista e, para cada item, fazem uma operação de inserção no mapa. Essa operação de inserção tem complexidade O(1) em média, mas pode ser O(log n) em cenários de colisões de hash ou outras particularidades.
    - **Complexidade Total de Contagem**: O(n), onde n é o número de elementos na lista.

- **Cálculo da Similaridade**: A soma das similaridades é feita iterando sobre a lista "left", com cada acesso à contagem de ocorrências do `Map` tendo complexidade O(1) em média.
    - **Complexidade Total de Similaridade**: O(m), onde m é o número de elementos na lista "left".

#### Código Otimizado com `IntArray`:
- **Contagem de Ocorrências**: A iteração sobre a lista "right" para preencher o array é linear, O(n), mas com a vantagem de que cada operação de incremento é feita em O(1) devido ao acesso direto ao índice do array.
    - **Complexidade Total de Contagem**: O(n), onde n é o número de elementos na lista.

- **Cálculo da Similaridade**: Similarmente, a iteração sobre a lista "left" para calcular a similaridade tem complexidade O(m), onde m é o número de elementos na lista. O acesso ao array também é feito em O(1).
    - **Complexidade Total de Similaridade**: O(m), onde m é o número de elementos na lista.

### 4. **Resumo da Complexidade de Tempo**:
- **Código com `Map`**:
    - **Contagem de Ocorrências**: O(n)
    - **Cálculo da Similaridade**: O(m)

  **Complexidade Total**: O(n + m)

- **Código com `IntArray`**:
    - **Contagem de Ocorrências**: O(n)
    - **Cálculo da Similaridade**: O(m)

  **Complexidade Total**: O(n + m)

### 5. **Melhora no Desempenho**

A melhoria de desempenho ao usar um array em vez de um `Map` é observada principalmente em cenários onde o valor máximo de um número na lista é pequeno e a lista é grande. O uso do array reduz o overhead de operações de hash e permite um acesso mais direto e eficiente aos dados.

## Conclusão

A substituição do `Map` por um `IntArray` para contar as ocorrências de elementos na lista "right" trouxe uma melhoria no desempenho, principalmente devido ao tempo de acesso constante O(1) proporcionado pelo array. Isso se reflete diretamente na otimização do tempo de execução, especialmente em casos com listas grandes e valores inteiros limitados.

A complexidade de tempo continua sendo linear, O(n + m), mas a constante envolvida é menor, o que resulta em uma execução mais rápida e eficiente, principalmente quando o número de elementos nas listas é grande.