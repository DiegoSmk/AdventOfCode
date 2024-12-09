# --- Dia 7: Reparação da Ponte ---

Os Historiadores levam você até uma `ponte de corda` familiar sobre um rio no meio de uma selva. O Chefe não está desse lado da ponte, então talvez ele esteja do outro lado?

Ao tentar atravessar a ponte, você percebe um grupo de engenheiros tentando repará-la. (Aparentemente, ela quebra com bastante frequência.) Você não poderá atravessar até que ela seja consertada.

Você pergunta quanto tempo levará; os engenheiros dizem que só precisam fazer os ajustes finais, mas alguns elefantes jovens estavam brincando nas proximidades e **roubaram todos os operadores** de suas equações de calibração! Eles poderiam terminar os ajustes se alguém determinasse quais valores de teste poderiam ser produzidos ao colocar qualquer combinação de operadores em suas equações de calibração (sua entrada de dados do desafio).

Por exemplo:
```
190: 10 19
3267: 81 40 27
83: 17 5
156: 15 6
7290: 6 8 6 15
161011: 16 10 13
192: 17 8 14
21037: 9 7 18 13
292: 11 6 16 20
```
Cada linha representa uma única equação. O valor de teste aparece antes dos dois-pontos em cada linha; seu trabalho é determinar se os números restantes podem ser combinados com operadores para produzir o valor de teste.

Os operadores **sempre são avaliados da esquerda para a direita**, **não** seguindo as regras de precedência. Além disso, os números nas equações não podem ser reorganizados. Observando pela selva, você vê elefantes segurando dois tipos diferentes de operadores: **soma** (`+`) e **multiplicação** (`*`).

### Exemplos:

Apenas três das equações acima podem ser verdadeiras ao inserir operadores:

1. **190: 10 19**  
   Há apenas uma posição que aceita um operador: entre 10 e 19. Escolhendo `+`, o resultado seria 29, mas escolhendo `*`, o valor de teste é alcançado (10 * 19 = 190).

2. **3267: 81 40 27**  
   Existem duas posições para operadores. Das quatro combinações possíveis de operadores, duas fazem o lado direito corresponder ao valor de teste:
    - `81 + 40 * 27`
    - `81 * 40 + 27`  
      Ambas resultam em 3267 (quando avaliadas da esquerda para a direita).

3. **292: 11 6 16 20**  
   Pode ser resolvida de exatamente uma maneira:
    - `11 + 6 * 16 + 20`.

### Resultado Final:

Os engenheiros só precisam do **resultado total de calibração**, que é a soma dos valores de teste das equações que podem ser verdadeiras.

No exemplo acima, a soma dos valores de teste para as três equações listadas é **3749**.

---

Determine quais equações podem ser verdadeiras. Qual é o resultado total de calibração?
