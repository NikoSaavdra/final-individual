# final-grupo

# Juego de Adivinanza

Este es un juego de adivinanza en Java donde dos jugadores intentan adivinar un número secreto generado aleatoriamente en 10 partidas. Cada jugador tiene hasta 10 intentos por partida para adivinar el número.

# Descripción

El juego consta de 10 partidas en las que dos jugadores se alternan para adivinar un número secreto. En cada partida, el número secreto es generado aleatoriamente entre 0 y 100. Los jugadores tienen un máximo de 10 intentos para adivinarlo.

- Si un jugador adivina el número: El jugador recibe puntos según la cantidad de intentos realizados (menos intentos, más puntos).
- Si nadie adivina en 10 intentos: La partida termina sin puntos para los jugadores.

# Funcionamiento

1. Inicio del Juego:
   - El juego comienza con la llamada al método `jugarJuego(nombreJugador1, nombreJugador2, adivinanzaJugador1, adivinanzaJugador2)`.
   - Este método inicia un ciclo de 10 partidas, llamando a `jugarPartida` para cada una.

2. Juego por Partidas:
   - En cada partida, se genera un número secreto aleatorio.
   - Los jugadores se alternan en sus intentos de adivinar el número, hasta que se alcanzan 10 intentos o uno de los jugadores adivina correctamente.
   - Si un jugador adivina, recibe puntos según el número de intentos utilizados y al menor número de intentos significa más puntos.

3. Puntajes:
   - Después de cada partida, se actualiza el puntaje de los jugadores.
   - Al final de las 10 partidas, el puntaje total de cada jugador se muestra.

4. Finalización:
   - Al finalizar las 10 partidas, el juego muestra un resumen con los puntajes finales de ambos jugadores.
   - El jugador con más puntos gana el juego.

# Sistema de Puntaje

El puntaje se calcula en función de la cantidad de intentos que le toma a un jugador adivinar el número secreto. El sistema de puntaje funciona de la siguiente manera:

1. Puntos por Intentos:
     - Si un jugador adivina el número: Los puntos dependen de cuántos intentos le haya tomado adivinar el número.
     - Si el jugador adivina en el primer intento, obtiene 10 puntos.
     - Si lo hace en el segundo intento, obtiene 9 puntos, y así sucesivamente.
     - Si el jugador necesita 10 intentos para adivinar, obtiene 1 punto.
   - Los puntos para un jugador se calculan con la fórmula `11 - intentos`, donde `intentos` es la cantidad de intentos realizados para adivinar el número secreto.
   
2. Si Ningún Jugador Adivina:
   - Si ninguno de los dos jugadores adivina el número secreto en los 10 intentos, no se otorgan puntos para esa partida.

3. Acumulación de Puntajes:
   - Los puntos obtenidos por un jugador en cada partida se suman a su puntaje total a medida que avanzan las partidas.

Ejemplo:
- Si un jugador adivina en el 3er intento, recibirá 8 puntos (11 - 3 = 8).
- Si otro jugador adivina en el 6to intento, recibirá 5 puntos (11 - 6 = 5).
- Si ninguno adivina después de 10 intentos, no se suman puntos.


