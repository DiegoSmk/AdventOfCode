# Desafio: Patrulha do Guarda

## Objetivo
Este desafio envolve simular o movimento de um guarda em um laboratório, com base em um protocolo de patrulha e uma série de regras para determinar a direção e os movimentos do guarda. O objetivo é prever o caminho do guarda e contar o número de posições distintas que ele visitará antes de sair da área mapeada. Aqui está o passo a passo para resolver o problema:

## 1. **Entender o Mapa Inicial**
- O mapa é dado como uma matriz de caracteres. Cada célula pode ser:
   - `.` (uma área livre),
   - `#` (um obstáculo),
   - `^`, `v`, `<`, `>` (direções do guarda: para cima, para baixo, para a esquerda ou para a direita).
- O guarda começa em uma posição marcada com um desses símbolos de direção.

## 2. **Definir as Regras de Movimento**
- O guarda segue as seguintes regras de movimento:
   1. **Se houver um obstáculo diretamente à frente**, o guarda vira 90 graus para a direita (ou seja, gira de `^` para `>`, de `>` para `v`, de `v` para `<`, e de `<` para `^`).
   2. **Caso contrário**, o guarda avança uma posição na direção em que está olhando.

## 3. **Inicializar a Posição do Guarda e o Mapa**
- Identifique a posição inicial do guarda no mapa (a posição marcada com `^`, `v`, `<` ou `>`).
- Determine a direção inicial do guarda a partir do símbolo (exemplo: `^` significa que o guarda está olhando para cima).

## 4. **Simular o Movimento do Guarda**
- O guarda continuará se movendo até sair da área mapeada.
- Crie uma lista ou conjunto para rastrear as posições visitadas.
- A cada movimento, registre a posição do guarda (adicionando à lista ou conjunto) e ajuste sua posição conforme as regras de movimento:
   - Se não houver obstáculo, mova o guarda uma célula na direção que está olhando.
   - Se houver um obstáculo, vire o guarda para a direita (modificando sua direção) e, em seguida, mova-o.

## 5. **Verificar se o Guarda Saiu da Área Mapeada**
- A cada movimento, verifique se a nova posição do guarda está fora do mapa.
- Se o guarda sair do mapa, pare a simulação.

## 6. **Contar as Posições Distintas**
- O número de posições distintas visitadas pelo guarda é o tamanho do conjunto ou lista de posições que foram registradas durante a simulação.
- Cada posição é única, mesmo que o guarda passe várias vezes pela mesma célula (o que não é relevante, pois estamos interessados nas posições distintas).

## 7. **Exibir a Resposta**
- A resposta final é o número de posições distintas visitadas pelo guarda antes de ele sair da área mapeada.

## 8. **Considerações Finais**
- A simulação deve ser feita de forma eficiente, para garantir que o programa funcione dentro do tempo limite para mapas grandes.
- O movimento do guarda e a detecção de obstáculos devem ser cuidadosamente implementados para garantir que as direções e movimentos sejam seguidos corretamente.

### Exemplo de Fluxo do Programa:
1. Iniciar no mapa e localizar a posição inicial do guarda.
2. Definir a direção inicial do guarda.
3. Criar um conjunto para armazenar as posições visitadas.
4. Simular o movimento do guarda conforme as regras.
5. Parar quando o guarda sair do mapa.
6. Contar o número de posições distintas visitadas.
7. Exibir o resultado final.

--- Parte Dois ---

# Passos para Solucionar o Desafio - Parte Dois

## **Simular o Movimento do Guarda**
- Para cada posição candidata onde um obstáculo poderia ser colocado, simule o movimento do guarda começando da posição inicial.
- Durante a simulação:
  1. O guarda se move de acordo com as regras mencionadas.
  2. Verifique se, em algum ponto, ele volta à mesma posição onde já esteve. Se isso ocorrer, significa que ele entrou em um loop.

- O obstáculo não pode ser colocado na posição inicial do guarda, pois ele estaria lá e perceberia.

## **Contar as Posições**
- O número de posições válidas para colocar o obstáculo é a resposta para o problema. Isso deve incluir todas as posições onde o guarda ficaria preso em um loop.
