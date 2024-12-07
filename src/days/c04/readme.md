# Dia 4: Busca de Ceres

# Procurando a Palavra XMAS

"Não parece que o Chefe está aqui. Próximo!"  
Um dos Historiadores puxa um dispositivo e aperta o único botão que há nele. Após um breve clarão, você reconhece o interior da **estação de monitoramento de Ceres**!

Enquanto a busca pelo Chefe continua, um pequeno Elfo que vive na estação puxa sua camisa; ela gostaria de saber se você poderia ajudá-la com sua caça-palavras (o seu desafio). Ela só precisa encontrar uma palavra: **XMAS**.

Este caça-palavras permite que as palavras sejam encontradas nas seguintes direções:

- Horizontal;
- Vertical;
- Diagonal;
- Escritas ao contrário;
- Sobrepondo outras palavras.

É um pouco incomum, no entanto, pois você não precisa encontrar apenas uma instância de **XMAS** – você precisa encontrar **todas** elas. Aqui estão alguns exemplos de como **XMAS** pode aparecer, onde caracteres irrelevantes foram substituídos por `.`:
```
..X...
.SAMX.
.A..A.
XMAS.S
.X....
```
O caça-palavras real estará cheio de letras. Por exemplo:
```
MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
XMASAMXAMM
XXAMMXXAMA
SMSMSASXSS
SAXAMASAAA
MAMMMXMMMM
MXMXAXMASX
```
Neste exemplo, a palavra **XMAS** aparece um total de **18 vezes**. Veja o mesmo caça-palavras novamente, mas com as letras não envolvidas em **XMAS** substituídas por `.`:
```
....XXMAS.
.SAMXMS...
...S..A...
..A.A.MS.X
XMASAMX.MM
X.....XA.A
S.S.S.S.SS
.A.A.A.A.A
..M.M.M.MM
.X.X.XMASX
```
Ajude o pequeno Elfo a resolver seu desafio. Quantas vezes a palavra **XMAS** aparece no caça-palavras?

# Parte Dois: X-MAS

O pequeno Elfo olha para você com curiosidade. Será que você entendeu errado a tarefa?

Procurando pelas instruções, você vira o caça-palavras e descobre que não é realmente um enigma sobre **XMAS**, mas sim sobre **X-MAS**, no qual você precisa encontrar dois **MAS** dispostos na forma de um "X".

Uma maneira de alcançar isso seria assim:
```
M.S
.A.
M.S
```
No diagrama acima, os caracteres irrelevantes foram substituídos por `.` novamente. Dentro do "X", cada **MAS** pode ser escrito tanto na ordem normal quanto de trás para frente.
---

## Exemplo de Entrada e Saída

### Entrada
O mesmo exemplo fornecido anteriormente para o caça-palavras. Aqui estão as ocorrências de **X-MAS** destacadas:
```
.M.S......
..A..MSMS.
.M.S.MAA..
..A.ASMSM.
.M.S.M....
..........
S.S.S.S.S.
.A.A.A.A..
M.M.M.M.M.
..........
```
### Saída
Neste exemplo, a forma **X-MAS** aparece **9 vezes**.

---
Vire a caça-palavras das instruções de volta para o lado da caça-palavras e tente novamente. **Quantas vezes um X-MAS aparece?**