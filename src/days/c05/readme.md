# Dia 5: Fila de Impressão

Satisfeitos com a pesquisa em Ceres, o esquadrão de estudiosos sugere, em seguida, vasculhar as pilhas de papelaria no subsolo 17.

O departamento de impressão do Polo Norte está mais ocupado do que nunca com a proximidade do Natal, e enquanto os Historiadores continuam sua busca neste local historicamente significativo, um Elfo `operando uma impressora muito familiar` chama sua atenção.

O Elfo parece reconhecê-lo, pois não perde tempo explicando que as **novas atualizações do manual** de segurança para o lançamento do trenó não estão sendo impressas corretamente. Falhar em atualizar os manuais de segurança seria terrível, então você oferece sua ajuda.

Os protocolos de segurança indicam claramente que novas páginas para os manuais de segurança devem ser impressas em uma **ordem muito específica**. A notação `X|Y` significa que, se tanto o número da página `X` quanto o número da página `Y` forem produzidos como parte de uma atualização, o número da página `X` **deve** ser impresso em algum momento antes do número da página `Y`.

O Elfo fornece a você tanto as **regras de ordenação das páginas** quanto as **páginas a serem produzidas em cada atualização** (seu input do problema), mas não consegue descobrir se cada atualização está com as páginas na ordem correta.

## Exemplo:

Regras de ordenação:

```
47|53
97|13
97|61
97|47
75|29
61|13
75|53
29|13
97|29
53|29
61|53
97|53
61|29
47|13
75|47
97|75
47|61
75|61
47|29
75|13
53|13

75,47,61,53,29
97,61,53,29,13
75,29,13
75,97,47,61,53
61,13,29
97,13,75,29,47
```

A primeira seção especifica as **regras de ordenação das páginas**, uma por linha. A primeira regra, `47|53`, significa que, se uma atualização incluir tanto o número da página `47` quanto o número da página `53`, então a página `47` **deve** ser impressa antes da página `53`. (A página `47` não precisa estar **imediatamente** antes da página `53`; outras páginas podem estar entre elas.)

A segunda seção especifica os números das páginas de cada **atualização**. Como a maioria dos manuais de segurança são diferentes, as páginas necessárias para as atualizações também são diferentes. A primeira atualização, `75,47,61,53,29`, significa que a atualização consiste nos números das páginas `75`, `47`, `61`, `53` e `29`.

## Avaliação das Atualizações

Para colocar as impressoras em funcionamento o mais rápido possível, comece identificando **quais atualizações já estão na ordem correta**.

No exemplo acima, a primeira atualização (`75,47,61,53,29`) está na ordem correta:

- `75` está corretamente em primeiro lugar porque existem regras que colocam cada outra página depois dele: `75|47`, `75|61`, `75|53`, e `75|29`.
- `47` está corretamente em segundo lugar porque `75` deve vir antes dele (`75|47`) e todas as outras páginas devem vir depois dele, de acordo com `47|61`, `47|53`, e `47|29`.
- `61` está corretamente no meio porque `75` e `47` vêm antes dele (`75|61` e `47|61`) e `53` e `29` vêm depois dele (`61|53` e `61|29`).
- `53` está corretamente em quarto lugar porque vem antes da página `29` (`53|29`).
- `29` é a única página restante e, portanto, está corretamente por último.

Porque a primeira atualização não inclui alguns números de páginas, as regras de ordenação envolvendo essas páginas ausentes são ignoradas.

A segunda e a terceira atualizações também estão na ordem correta, de acordo com as regras. Assim como a primeira atualização, elas também não incluem todos os números de páginas, e, portanto, apenas algumas das regras de ordenação se aplicam — dentro de cada atualização, as regras de ordenação que envolvem números de páginas ausentes não são usadas.

A quarta atualização, `75,97,47,61,53`, **não** está na ordem correta: ela imprimiria `75` antes de `97`, o que viola a regra `97|75`.

A quinta atualização, `61,13,29`, também **não** está na ordem correta, pois quebra a regra `29|13`.

A última atualização, `97,13,75,29,47`, **não** está na ordem correta devido a várias violações de regras.

## Resumo da Tarefa

Por algum motivo, os Elfos também precisam saber o **número da página do meio** de cada atualização sendo impressa. Como você está imprimindo apenas as atualizações corretamente ordenadas, será necessário encontrar o número da página do meio de cada atualização corretamente ordenada. No exemplo acima, as atualizações corretamente ordenadas são:

```
75,47,61,53,29
97,61,53,29,13
75,29,13
```

Essas têm os números da página do meio de `61`, `53` e `29`, respectivamente. Somando esses números de página, temos `143`.

Claro, você precisará ter cuidado: a lista real de **regras de ordenação das páginas** é maior e mais complicada do que o exemplo acima.

**Determine quais atualizações já estão na ordem correta. O que você obtém se somar o número da página do meio dessas atualizações corretamente ordenadas?**

# Parte Dois

Enquanto os Elfos começam a imprimir as atualizações ordenadas corretamente, você tem um pouco de tempo para corrigir o restante.

Para cada uma das atualizações que estão fora de ordem, use as regras de ordenação das páginas para colocá-las na ordem correta. No exemplo acima, estas são as três atualizações fora de ordem e suas respectivas ordens corrigidas:

- `75,97,47,61,53` torna-se `97,75,47,61,53`.
- `61,13,29` torna-se `61,29,13`.
- `97,13,75,29,47` torna-se `97,75,47,29,13`.

Após considerar apenas as atualizações fora de ordem e ordená-las corretamente, os números das páginas centrais são `47`, `29` e `47`. Somando esses valores, temos o resultado `123`.

Encontre as atualizações que não estão na ordem correta. Qual é o resultado da soma dos números centrais após corrigir a ordem apenas dessas atualizações?
