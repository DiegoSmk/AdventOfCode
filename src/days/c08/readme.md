
# Dia 8: Colinearidade Ressonante

Você se encontra no `telhado` de uma instalação ultrassecreta do Coelho da Páscoa.

Enquanto **Os Historiadores** realizam suas tarefas, você observa uma enorme antena familiar. Para sua surpresa, ela parece ter sido reconfigurada para emitir um sinal que torna as pessoas 0,1% mais propensas a comprar chocolates medíocres de marca do Coelho da Páscoa como presente de Natal! **Inaceitável!**

Ao escanear a cidade, você descobre que há muitas dessas antenas. Cada antena está sintonizada em uma frequência específica indicada por uma única letra minúscula, letra maiúscula ou número. Você cria um mapa (a entrada do problema) dessas antenas. Por exemplo:

```
............
........0...
.....0......
.......0....
....0.......
......A.....
............
............
........A...
.........A..
............
............
```

O sinal só causa seu efeito nefasto em pontos específicos chamados **antinodos**, baseados nas frequências ressonantes das antenas. Em particular, um **antinodo** ocorre em qualquer ponto que esteja perfeitamente alinhado com **duas antenas da mesma frequência**, **mas apenas quando uma das antenas está a uma distância duas vezes maior que a outra**. Isso significa que, para qualquer par de antenas com a mesma frequência, há dois antinodos, um de cada lado delas.

Por exemplo, para estas duas antenas com frequência `a`, elas criam os dois antinodos marcados com `#`:

```
..........
...#......
..........
....a.....
..........
.....a....
..........
......#...
..........
..........
```

Adicionando uma terceira antena com a mesma frequência, vários outros antinodos são criados. Idealmente, seriam adicionados quatro antinodos, mas dois estão fora do lado direito do mapa, então apenas dois são adicionados:

```
..........
...#......
#.........
....a.....
........a.
.....a....
..#.......
......#...
..........
..........
```

Antenas com frequências diferentes **não criam antinodos**; `A` e `a` contam como frequências diferentes. No entanto, antinodos podem ocorrer em locais que já contêm antenas. Neste diagrama, a antena isolada com a frequência maiúscula `A` não cria antinodos, mas há um antinodo de frequência minúscula `a` em sua localização:

```
..........
...#......
#.........
....a.....
........a.
.....a....
..#.......
......A...
..........
..........
```

No primeiro exemplo, há antenas com duas frequências diferentes, então os antinodos que elas criam ficam assim, além de um antinodo que se sobrepõe à antena mais alta de frequência `A`:

```
......#....#
...#....0...
....#0....#.
..#....0....
....0....#..
.#....A.....
...#........
#......#....
........A...
.........A..
..........#.
..........#.
```

Como a antena mais alta de frequência `A` se sobrepõe a um antinodo de frequência `0`, há **14 locais únicos no total dentro dos limites do mapa** que contêm um antinodo.

---

## Objetivo

Calcule o impacto do sinal. **Quantos locais únicos dentro dos limites do mapa contêm um antinodo?**

# --- Parte Dois ---

Enquanto você trabalha, um dos Historiadores, observando por cima do seu ombro, pergunta se você levou em consideração os efeitos das harmônicas ressonantes em seus cálculos.

Ops!

Após atualizar seu modelo, descobriu-se que um antinódulo ocorre em qualquer posição da grade que esteja exatamente alinhada com pelo menos duas antenas da mesma frequência, **independentemente da distância**. Isso significa que alguns dos novos antinódulos agora também ocorrem na posição de cada antena (a menos que aquela antena seja a única de sua frequência).

Assim, estas três antenas de frequência **T** agora criam muitos antinódulos:

```
T....#....
...T......
.T....#...
.........#
..#.......
..........
...#......
..........
....#.....
..........
```

De fato, as três antenas de frequência **T** estão todas exatamente alinhadas com duas outras antenas, então todas elas também são antinódulos! Isso traz o número total de antinódulos no exemplo acima para **9**.

O exemplo original agora tem **34 antinódulos**, incluindo os antinódulos que aparecem em cada antena:

```
##....#....#
.#.#....0...
..#.#0....#.
..##...0....
....0....#..
.#...#A....#
...#..#.....
#....#.#....
..#.....A...
....#....A..
.#........#.
...#......##
```

## Tarefa
Calcule o impacto do sinal usando este modelo atualizado. **Quantos locais únicos dentro dos limites do mapa contêm um antinódulo?**