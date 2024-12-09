# --- Dia 6: Patrulha do Guarda ---

Os Historiadores usam novamente seu `dispositivo` sofisticado, desta vez para transportar todos para o laboratório de fabricação de protótipos de roupas no Polo Norte... no ano de 1518! Aparentemente, ter acesso direto à história é muito conveniente para um grupo de historiadores.

No entanto, é importante tomar cuidado com paradoxos temporais, e por isso será essencial evitar contato com qualquer pessoa de `1518` enquanto os Historiadores procuram pelo Chefe. Infelizmente, um único **guarda** está patrulhando esta parte do laboratório.

Talvez você consiga descobrir para onde o guarda irá com antecedência, permitindo que os Historiadores procurem com segurança.

Você começa criando um mapa (a entrada do seu desafio) para entender a situação. Por exemplo:

```
....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...
```

O mapa mostra a posição atual do guarda com `^` (indicando que o guarda está atualmente voltado para **cima**, na perspectiva do mapa). Qualquer **obstrução** – caixas, mesas, reatores alquímicos, etc. – é representada por `#`.

Os guardas do laboratório em 1518 seguem um protocolo de patrulha muito rigoroso, que envolve repetir os seguintes passos:

1. Se houver algo diretamente à sua frente, vire 90 graus para a direita.
2. Caso contrário, dê um passo para frente.

Seguindo esse protocolo, o guarda se move para cima várias vezes até encontrar um obstáculo (neste caso, uma pilha de protótipos de roupas falhadas):

```
....#.....
....^....#
..........
..#.......
.......#..
..........
.#........
........#.
#.........
......#...
```

Como há agora um obstáculo à frente do guarda, ele vira para a direita antes de continuar reto em sua nova direção:

```
....#.....
........>#
..........
..#.......
.......#..
..........
.#........
........#.
#.........
......#...
```

Ao encontrar outro obstáculo (um carretel de polímeros **muito** longos), ele vira novamente à direita e continua descendo:

```
....#.....
.........#
..........
..#.......
.......#..
..........
.#......v.
........#.
#.........
......#...
```

Esse processo continua por um tempo, mas eventualmente o guarda sai da área mapeada (depois de passar por um tanque de solvente universal):

```
....#.....
.........#
..........
..#.......
.......#..
..........
.#........
........#.
#.........
......#v..
```

Prevendo a rota do guarda, você pode determinar quais posições específicas no laboratório estarão no caminho da patrulha. Incluindo a posição inicial do guarda, as posições visitadas pelo guarda antes de sair da área são marcadas com `X`:

```
....#.....
....XXXXX#
....X...X.
..#.X...X.
..XXXXX#X.
..X.X.X.X.
.#XXXXXXX.
.XXXXXXX#.
#XXXXXXX..
......#X..
```

Neste exemplo, o guarda visitará **41 posições distintas** no mapa.

**Preveja o caminho do guarda. Quantas posições distintas o guarda visitará antes de sair da área mapeada?**

--- Parte Dois ---

Enquanto os Historiadores começam a trabalhar ao redor da rota de patrulha do guarda, você pega o dispositivo deles e sai do laboratório. Da segurança de um armário de suprimentos, você viaja no tempo pelos últimos meses e `registra` o status noturno do posto de guarda nas paredes do armário.

Ao retornar, depois do que parece ter sido apenas alguns segundos para os Historiadores, eles explicam que a área de patrulha do guarda é simplesmente grande demais para que possam procurar pelo laboratório com segurança sem serem pegos.

Felizmente, eles estão **bastante certos** de que adicionar uma única nova obstrução **não causará** um paradoxo temporal. Eles gostariam de colocar a nova obstrução de maneira que o guarda fique **preso em um loop**, tornando o restante do laboratório seguro para pesquisa.

Para ter a menor chance de criar um paradoxo temporal, os Historiadores gostariam de saber **todas** as posições possíveis para tal obstrução. A nova obstrução não pode ser colocada na posição inicial do guarda - o guarda está lá agora e notaria.

No exemplo acima, existem apenas 6 diferentes posições onde uma nova obstrução faria o guarda ficar preso em um loop. Os diagramas dessas seis situações usam O para marcar a nova obstrução, | para mostrar uma posição onde o guarda se move para cima/para baixo, - para mostrar uma posição onde o guarda se move para a esquerda/direita, e + para mostrar uma posição onde o guarda se move tanto para cima/para baixo quanto para a esquerda/direita.

Opção um, coloque uma impressora ao lado da posição inicial do guarda:
```
....#.....
....+---+#
....|...|.
..#.|...|.
....|..#|.
....|...|.
.#.O^---+.
........#.
#.........
......#...
```
Opção dois, coloque uma pilha de protótipos de traje falhados no quadrante inferior direito da área mapeada:
```
....#.....
....+---+#
....|...|.
..#.|...|.
..+-+-+#|.
..|.|.|.|.
.#+-^-+-+.
......O.#.
#.........
......#...
```
Opção três, coloque uma caixa de tecido para protótipos de chimineia no canto inferior direito:
```
....#.....
....+---+#
....|...|.
..#.|...|.
..+-+-+#|.
..|.|.|.|.
.#+-^-+-+.
.+----+O#.
#+----+...
......#...
```
Opção quatro, coloque um retroencabulator alquímico perto do canto inferior esquerdo:
```
....#.....
....+---+#
....|...|.
..#.|...|.
..+-+-+#|.
..|.|.|.|.
.#+-^-+-+.
..|...|.#.
#O+---+...
......#...
```
Opção cinco, coloque o retroencabulator alquímico um pouco à direita:
```
....#.....
....+---+#
....|...|.
..#.|...|.
..+-+-+#|.
..|.|.|.|.
.#+-^-+-+.
....|.|.#.
#..O+-+...
......#...
```


Opção seis, coloque um tanque de cola soberana ao lado do tanque de solvente universal:
```
....#.....
....+---+#
....|...|.
..#.|...|.
..+-+-+#|.
..|.|.|.|.
.#+-^-+-+.
.+----++#.
#+----++..
......#O..
```
Não importa muito o que você escolha usar como obstáculo, desde que você e os Historiadores possam colocá-lo em posição sem que o guarda perceba. O importante é ter opções suficientes para encontrar uma que minimize os paradoxos temporais, e neste exemplo, existem 6 diferentes posições que você poderia escolher.

Você precisa fazer o guarda ficar preso em um loop adicionando uma única nova obstrução. Quantas posições diferentes você poderia escolher para essa obstrução?






