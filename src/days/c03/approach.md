# Desafio: Multiplicações Corrompidas

## Objetivo
O objetivo deste desafio é analisar uma memória corrompida e identificar instruções válidas do tipo `mul(X,Y)`, onde `X` e `Y` são números de 1 a 3 dígitos. Após identificar as instruções válidas, some os resultados de todas as multiplicações.

## Passo a Passo para Resolver o Desafio

### 1. **Entendimento das Instruções Válidas**
Uma instrução válida é representada como `mul(X,Y)`:
- `X` e `Y` são números inteiros de 1 a 3 dígitos.
- Não deve conter caracteres extras, como `mul(4*` ou `mul[3,7]!`.
- A multiplicação é realizada como \( X \times Y \).

#### Exemplo:
- `mul(2,4)` é válido e resulta em \( 2 \times 4 = 8 \).
- `mul(3,7)` é válido e resulta em \( 3 \times 7 = 21 \).

### 2. **Filtragem das Instruções Corrompidas**
Analise a memória fornecida e filtre somente as instruções que seguem o formato correto de `mul(X,Y)`. Isso pode ser feito utilizando expressões regulares para validar o formato.

#### Exemplo:
Na memória:
```
xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
```
As instruções válidas são:
- `mul(2,4)`
- `mul(5,5)`
- `mul(11,8)`
- `mul(8,5)`

### 3. **Realizar as Multiplicações**
Após identificar as instruções válidas, multiplique os números \( X \) e \( Y \) de cada instrução.

#### Para os exemplos acima:
- `mul(2,4)` resulta em (2 * 4 = 8).
- `mul(5,5)` resulta em (5 * 5 = 25).
- `mul(11,8)` resulta em (11 * 8 = 88).
- `mul(8,5)` resulta em (8 * 5 = 40).

### 4. **Somar os Resultados**
Some os resultados de todas as multiplicações:
```
8 + 25 + 88 + 40 = 161
```

### 5. **Resultado Final**
Após processar toda a memória e calcular as multiplicações, o resultado final é a soma total dos resultados.

#### Para o exemplo acima:
```
161
```

---

## Considerações
- Utilize expressões regulares para filtrar as instruções no formato correto.
- Certifique-se de validar a entrada antes de realizar a multiplicação.
- Ignore quaisquer caracteres ou instruções que não estejam no formato `mul(X,Y)`.

---