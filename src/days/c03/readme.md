# Dia 3: Reflita Sobre Isso

"Os nossos computadores estão com problemas, então não faço ideia se temos algum Historiador Chefe em estoque! Você pode verificar no armazém, no entanto", diz o atendente levemente frustrado na Loja de Aluguel de Trenós do Pólo Norte. Os Historiadores saem para dar uma olhada.

O atendente se volta para você. "Alguma chance de descobrir por que nossos computadores estão com problemas novamente?"

O computador parece estar tentando executar um programa, mas sua memória (o seu enigma de entrada) está **corrompida**. Todas as instruções estão embaralhadas!

Parece que o objetivo do programa é apenas **multiplicar alguns números**. Ele faz isso com instruções como `mul(X,Y)`, onde `X` e `Y` são números de 1 a 3 dígitos. Por exemplo, `mul(44,46)` multiplica 44 por 46 para obter o resultado 2024. Da mesma forma, `mul(123,4)` multiplicaria 123 por 4.

No entanto, devido à memória corrompida, há muitos caracteres inválidos que devem ser **ignorados**, mesmo que pareçam fazer parte de uma instrução `mul`. Sequências como `mul(4*`, `mul(6,9!`, `?(12,34)`, ou `mul ( 2 , 4 )` não fazem **nada**.

Por exemplo, considere a seguinte seção de memória corrompida:
```
xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
```

Apenas as quatro seções destacadas são instruções reais de `mul`. Somando o resultado de cada instrução, temos **161** (`2*4 + 5*5 + 11*8 + 8*5`).

Analise a memória corrompida em busca de instruções `mul` válidas. **Qual é o resultado da soma de todas as multiplicações?**

## Parte 2

Ao examinar a memória corrompida, você percebe que algumas declarações condicionais ainda estão intactas. Se você lidar com algumas dessas declarações condicionais não corrompidas no programa, poderá obter um resultado ainda mais preciso.

## Novas Instruções

Existem duas novas instruções que você precisará lidar:

1. **A instrução `do()` habilita futuras instruções `mul`.**
2. **A instrução `don't()` desabilita futuras instruções `mul`.**

Somente a instrução `do()` ou `don't()` mais recente se aplica. No início do programa, as instruções `mul` estão habilitadas por padrão.

## Exemplo

Considere a seguinte memória corrompida:
```
xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
```
Neste caso:
- As instruções `mul(5,5)` e `mul(11,8)` estão desabilitadas porque há uma instrução `don't()` antes delas.
- As outras instruções `mul` funcionam normalmente, incluindo a última `mul(8,5)`, que é reabilitada por uma instrução `do()`.

A soma dos resultados válidos é:
```
(2 * 4) + (8 * 5) = 48
```
Lide com as novas instruções; **o que você obtém se somar todos os resultados apenas das multiplicações habilitadas?**


---
