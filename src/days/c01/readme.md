# Dia 1: Histeria Histórica

O Chefe Historiador está sempre presente no grande lançamento do trenó de Natal, mas ninguém o vê há meses! A última vez que se soube dele, ele estava visitando locais historicamente significativos no Polo Norte; um grupo de Historiadores Seniores pediu para você acompanhá-los enquanto verificam os locais que eles acham que ele visitou com mais probabilidade.

À medida que cada local é verificado, eles marcam na lista com uma estrela. Eles acham que o Chefe Historiador deve estar em um dos primeiros cinquenta locais que irão procurar, então, para salvar o Natal, você precisa ajudá-los a conseguir cinquenta estrelas na lista antes do Papai Noel partir no dia 25 de dezembro.

Colete estrelas resolvendo enigmas. Dois enigmas serão disponibilizados a cada dia no calendário do Advento; o segundo enigma é desbloqueado quando você concluir o primeiro. Cada enigma concede uma estrela. Boa sorte!

Você ainda nem saiu e o grupo de Historiadores Seniores já encontrou um problema: a lista de locais a verificar está atualmente vazia. Eventualmente, alguém decide que o melhor lugar para começar a busca seria o escritório do Chefe Historiador.

Ao entrarem no escritório, todos confirmam que o Chefe Historiador realmente não está lá. Em vez disso, os Elfos descobrem uma variedade de anotações e listas de locais historicamente significativos! Isso parece ser o planejamento que o Chefe Historiador estava fazendo antes de sair. Talvez essas anotações possam ser usadas para determinar quais locais verificar?

Em todo o escritório do Chefe, os locais historicamente significativos são listados não pelo nome, mas por um número único chamado ID de local. Para garantir que nada seja esquecido, os Historiadores se dividem em dois grupos, cada um buscando no escritório e tentando criar sua própria lista completa de IDs de locais.

Há apenas um problema: ao comparar as duas listas lado a lado (seu input de problema), rapidamente fica claro que as listas não são muito semelhantes. Talvez você possa ajudar os Historiadores a reconciliar suas listas?

### Exemplo:
```
3   4
4   3
2   5
1   3
3   9
3   3
```

Talvez as listas estejam apenas um pouco desajustadas! Para descobrir isso, emparelhe os números e meça o quanto eles estão distantes. Emparelhe o menor número da lista da esquerda com o menor número da lista da direita, depois o segundo menor número da esquerda com o segundo menor número da direita, e assim por diante.

Dentro de cada par, calcule a distância entre os dois números; você precisará somar todas essas distâncias. Por exemplo, se você emparelhar um 3 da lista da esquerda com um 7 da lista da direita, a distância entre eles é 4; se você emparelhar um 9 com um 3, a distância é 6.

No exemplo acima, os pares e as distâncias seriam os seguintes:

1. O menor número na lista da esquerda é 1, e o menor número na lista da direita é 3. A distância entre eles é 2.
2. O segundo menor número da lista da esquerda é 2, e o segundo menor número da lista da direita é outro 3. A distância entre eles é 1.
3. O terceiro menor número em ambas as listas é 3, portanto, a distância entre eles é 0.
4. Os próximos números a emparelhar são 3 e 4, uma distância de 1.
5. Os próximos números a emparelhar são 3 e 5, uma distância de 2.
6. Finalmente, o maior número na lista da esquerda é 4, enquanto o maior número na lista da direita é 9; esses números têm uma distância de 5.

Para encontrar a distância total entre a lista da esquerda e a lista da direita, some todas as distâncias entre os pares encontrados. No exemplo acima, isso seria 2 + 1 + 0 + 1 + 2 + 5, uma distância total de 11!

Suas listas reais de locais possuem muitos IDs de locais. Qual é a distância total entre suas listas?

--- Parte 2 ---

Sua análise apenas confirmou o que todos temiam: as duas listas de IDs de localização são realmente muito diferentes. Ou são?
Os Historiadores não conseguem concordar sobre qual grupo cometeu os erros ou como ler a maioria da caligrafia do Chefe, mas em meio à confusão, você percebe um detalhe interessante: muitos IDs de localização aparecem nas duas listas! Talvez os outros números não sejam IDs de localização, mas sim interpretações erradas da caligrafia.

Desta vez, você precisará descobrir exatamente quantas vezes cada número da lista da esquerda aparece na lista da direita. Calcule uma pontuação de similaridade total somando cada número da lista da esquerda após multiplicá-lo pelo número de vezes que esse número aparece na lista da direita.

Aqui estão os mesmos exemplos de listas novamente:
```
3   4
4   3
2   5
1   3
3   9
3   3
```
Para essas listas de exemplo, aqui está o processo para encontrar a pontuação de similaridade:

1. O primeiro número na lista da esquerda é 3. Ele aparece na lista da direita três vezes, então a pontuação de similaridade aumenta em 3 * 3 = 9.
2. O segundo número na lista da esquerda é 4. Ele aparece na lista da direita uma vez, então a pontuação de similaridade aumenta em 4 * 1 = 4.
3. O terceiro número na lista da esquerda é 2. Ele não aparece na lista da direita, então a pontuação de similaridade não aumenta (2 * 0 = 0).
4. O quarto número, 1, também não aparece na lista da direita.
5. O quinto número, 3, aparece na lista da direita três vezes; a pontuação de similaridade aumenta em 9.
6. O último número, 3, aparece na lista da direita três vezes; a pontuação de similaridade aumenta novamente em 9.
Então, para essas listas de exemplo, a pontuação de similaridade no final desse processo é 31 (9 + 4 + 0 + 0 + 9 + 9).

Considere novamente suas listas esquerda e direita. Qual é a pontuação de similaridade delas?
