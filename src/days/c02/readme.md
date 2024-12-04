# Dia 2: Relatórios do Nariz Vermelho

Felizmente, o primeiro local que os Historiadores querem procurar não é uma longa caminhada do escritório do Chefe dos Historiadores.

Embora a usina de fusão/fissão de fusão nuclear do Alce do Nariz Vermelho pareça não conter nenhum sinal do Chefe dos Historiadores, os engenheiros correm até você assim que te veem. Aparentemente, eles ainda falam sobre a vez em que o Rudolph foi salvo por meio de síntese molecular a partir de um único elétron.

Eles rapidamente acrescentam que, como você já está aqui, eles realmente apreciariam sua ajuda para analisar alguns dados incomuns da usina do Alce do Nariz Vermelho. Você se vira para verificar se os Historiadores estão esperando por você, mas eles parecem já ter se dividido em grupos que estão atualmente procurando por todos os cantos da instalação. Você oferece sua ajuda com os dados incomuns.

Os dados incomuns (seu input de quebra-cabeça) consistem em muitos **relatórios**, um relatório por linha. Cada relatório é uma lista de números chamados **níveis**, separados por espaços. Por exemplo:
```
7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9
```
Esses dados de exemplo contêm seis relatórios, cada um com cinco níveis.

Os engenheiros estão tentando descobrir quais relatórios são **seguros**. Os sistemas de segurança da usina de fusão do Alce do Nariz Vermelho só podem tolerar níveis que estão aumentando ou diminuindo gradualmente. Portanto, um relatório conta como seguro se ambos os seguintes critérios forem verdadeiros:

1. Os níveis estão todos ou **aumentando** ou **diminuindo**.
2. Qualquer dois níveis adjacentes devem diferir por **pelo menos 1** e **no máximo 3**.

No exemplo acima, os relatórios podem ser considerados seguros ou inseguros verificando essas regras:

- ``7 6 4 2 1``: **Seguro**, porque os níveis estão todos diminuindo de 1 ou 2.
- ``1 2 7 8 9``: **Inseguro**, porque ``2 7`` é um aumento de 5.
- ``9 7 6 2 1``: **Inseguro**, porque ``6 2`` é uma diminuição de 4.
- ``1 3 2 4 5``: **Inseguro**, porque ``1 3`` está aumentando, mas ``3 2`` está diminuindo.
- ``8 6 4 4 1``: **Inseguro**, porque ``4 4`` não é nem aumento nem diminuição.
- ``1 3 6 7 9``: **Seguro**, porque os níveis estão todos aumentando de 1, 2 ou 3.

Portanto, neste exemplo, ``2`` relatórios são seguros.

Analise os dados incomuns dos engenheiros. Quantos relatórios são seguros?

Para começar, obtenha seu input de quebra-cabeça.

--- Parte Dois ---

Os engenheiros ficaram surpresos com o baixo número de relatórios seguros até perceberem que esqueceram de mencionar o **Problem Dampener**.

O **Problem Dampener** é um módulo montado no reator que permite que os sistemas de segurança do reator tolerem um único nível ruim em um relatório que, de outra forma, seria seguro. É como se o nível ruim nunca tivesse acontecido!

Agora, as mesmas regras se aplicam, exceto que, se remover um único nível de um relatório inseguro o tornasse seguro, o relatório será contado como seguro.

Mais relatórios do exemplo acima agora são seguros:

- **7 6 4 2 1**: Seguro sem remover nenhum nível.
- **1 2 7 8 9**: Inseguro, independentemente de qual nível seja removido.
- **9 7 6 2 1**: Inseguro, independentemente de qual nível seja removido.
- **1 3 2 4 5**: Seguro ao remover o segundo nível, 3.
- **8 6 4 4 1**: Seguro ao remover o terceiro nível, 4.
- **1 3 6 7 9**: Seguro sem remover nenhum nível.

Graças ao **Problem Dampener**, 4 relatórios são realmente seguros!

Atualize sua análise para lidar com situações onde o **Problem Dampener** pode remover um único nível de relatórios inseguros. Quantos relatórios são agora seguros?
