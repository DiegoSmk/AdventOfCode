# Dia 9: Fragmentador de Disco

Outro pressionamento do botão deixa você nos corredores familiares de alguns amigáveis **amfipodos**! Ainda bem que cada um de vocês de alguma forma conseguiu seu próprio mini-submarino. Os **Historiadores** partem rapidamente em busca do **Chefe**, principalmente dirigindo diretamente nas paredes.

Enquanto os **Historiadores** rapidamente descobrem como pilotar essas coisas, você nota um **amfipodo** no canto lutando com seu computador. Ele está tentando criar mais espaço livre contíguo compactando todos os arquivos, mas seu programa não está funcionando; você oferece ajuda.

Ele te mostra o `mapa do disco` (seu input para o puzzle) que ele já gerou. Por exemplo:

```
2333133121414131402
```

O mapa do disco usa um formato denso para representar o layout dos arquivos e do **espaço livre** no disco. Os dígitos alternam entre indicar o tamanho de um arquivo e o tamanho de um espaço livre.

Então, um mapa de disco como `12345` representaria um arquivo de 1 bloco, dois blocos de espaço livre, um arquivo de 3 blocos, quatro blocos de espaço livre, e então um arquivo de 5 blocos. Um mapa de disco como `90909` representaria três arquivos de 9 blocos em sequência (sem espaço livre entre eles).

Cada arquivo no disco também tem um número de ID baseado na ordem em que os arquivos aparecem antes de serem reorganizados, começando com o ID 0. Assim, o mapa de disco `12345` tem três arquivos: um arquivo de 1 bloco com o ID 0, um arquivo de 3 blocos com o ID 1, e um arquivo de 5 blocos com o ID 2. Usando um único caractere para cada bloco, onde os dígitos são o ID do arquivo e `.` é espaço livre, o mapa de disco `12345` representa esses blocos individuais:

```
0..111....22222
```

O primeiro exemplo acima, `2333133121414131402`, representa esses blocos individuais:

```
00...111...2...333.44.5555.6666.777.888899
```

O **amfipodo** gostaria de mover os blocos de arquivos um de cada vez do final do disco para o bloco de espaço livre mais à esquerda (até que não haja mais lacunas entre os blocos de arquivos). Para o mapa de disco `12345`, o processo seria assim:

```
0..111....22222
02.111....2222.
022111....222..
0221112...22...
02211122..2....
022111222......
```

O primeiro exemplo requer alguns passos a mais:

```
00...111...2...333.44.5555.6666.777.888899
009..111...2...333.44.5555.6666.777.88889.
0099.111...2...333.44.5555.6666.777.8888..
00998111...2...333.44.5555.6666.777.888...
009981118..2...333.44.5555.6666.777.88....
0099811188.2...333.44.5555.6666.777.8.....
009981118882...333.44.5555.6666.777.......
0099811188827..333.44.5555.6666.77........
00998111888277.333.44.5555.6666.7.........
009981118882777333.44.5555.6666...........
009981118882777333644.5555.666............
00998111888277733364465555.66.............
0099811188827773336446555566..............
```

A etapa final desse processo de compactação de arquivos é atualizar a soma de verificação do sistema de arquivos. Para calcular a soma de verificação, some o resultado da multiplicação da posição de cada um desses blocos pelo número do ID do arquivo que ele contém. O bloco mais à esquerda está na posição 0. Se um bloco contiver espaço livre, ignore-o.

Continuando o primeiro exemplo, as primeiras posições multiplicadas pelo número do ID do arquivo são 0 * 0 = 0, 1 * 0 = 0, 2 * 9 = 18, 3 * 9 = 27, 4 * 8 = 32, e assim por diante. Neste exemplo, a soma de verificação é a soma desses valores, 1928.

Compacte o disco rígido do **amfipodo** usando o processo solicitado. Qual é a soma de verificação final do sistema de arquivos? (Cuidado ao copiar e colar o input para este puzzle; ele é uma única linha muito longa).

# --- Parte Dois ---

Ao concluir a primeira parte, duas coisas ficam imediatamente claras. Primeiro, o disco definitivamente tem muito mais espaço livre contíguo, exatamente como o anfípode esperava. Segundo, o computador está funcionando muito mais lentamente! Talvez introduzir toda essa fragmentação no sistema de arquivos tenha sido uma má ideia.

O anfípode entusiasmado já tem um novo plano: em vez de mover blocos individuais, ele gostaria de tentar compactar os arquivos no disco movendo arquivos inteiros.

Desta vez, tente mover arquivos inteiros para o primeiro espaço livre contíguo à esquerda que seja grande o suficiente para caber o arquivo. Tente mover cada arquivo exatamente uma vez, em ordem decrescente de número de ID do arquivo, começando pelo arquivo com o maior ID. Se não houver um espaço livre grande o suficiente à esquerda de um arquivo para movê-lo, o arquivo não será movido.

O primeiro exemplo acima agora procede de forma diferente:

```
00...111...2...333.44.5555.6666.777.888899
0099.111...2...333.44.5555.6666.777.8888..
0099.1117772...333.44.5555.6666.....8888..
0099.111777244.333....5555.6666.....8888..
00992111777.44.333....5555.6666.....8888..
```

O processo de atualização do checksum do sistema de arquivos é o mesmo; agora, o checksum deste exemplo seria **2858**.

Comece do zero, agora compactando o disco rígido do anfípode usando este novo método. Qual é o checksum do sistema de arquivos resultante?
