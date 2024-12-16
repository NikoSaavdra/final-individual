package ces.santander.ascender;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class JuegoAdivinanza {
    private Map<String, Integer> puntosJugadores = new HashMap<>();
    private static final int MAX_INTENTOS = 10;
    private static final int NUM_PARTIDAS = 10;  

    public String jugarJuego(String nombreJugador1, String nombreJugador2, int adivinanzaJugador1, int adivinanzaJugador2) {
    
        puntosJugadores.put(nombreJugador1, 0);
        puntosJugadores.put(nombreJugador2, 0);
        
        for (int partida = 1; partida <= NUM_PARTIDAS; partida++) {
            jugarPartida(nombreJugador1, nombreJugador2, adivinanzaJugador1, adivinanzaJugador2, partida);
        }
        return "Juego terminado. Puntajes finales: " + puntosJugadores.toString();
    }

    public String jugarPartida(String nombreJugador1, String nombreJugador2, int adivinanzaJugador1, int adivinanzaJugador2, int partida) {
        Random random = new Random();
        int numeroSecreto = random.nextInt(100) + 1;  
        int intentosJugador1 = 0;
        int intentosJugador2 = 0;
        
<<<<<<< HEAD
        for (int i = 0; i <MAX_INTENTOS; i++ ) {
=======
        for (int i = 0; i < MAX_INTENTOS;i++) {
>>>>>>> origin/main
            String jugadorActual = (i % 2 == 0) ? nombreJugador1 : nombreJugador2;  // Alternamos entre jugador1 y jugador2
            int intentoActual = (jugadorActual.equals(nombreJugador1)) ? adivinanzaJugador1 : adivinanzaJugador2;
            int intentos = (jugadorActual.equals(nombreJugador1)) ? ++intentosJugador1 : ++intentosJugador2;

            if (intentoActual == numeroSecreto) {
                int puntos = calcularPuntos(intentos);
                puntosJugadores.put(jugadorActual, puntosJugadores.get(jugadorActual) + puntos);
                return "¡" + jugadorActual + " ha ganado! Adivinaste el número en " + intentos + " intentos. Has ganado "
                        + puntos + " puntos. Total de puntos: " + puntosJugadores.get(jugadorActual);
            } else if (intentoActual < numeroSecreto) {
                return jugadorActual + ": El número es mayor a " + intentoActual;
            } else {
                return jugadorActual + ": El número es menor a " + intentoActual;
            }
            
        }
        return "Ningún jugador adivinó el número secreto en " + MAX_INTENTOS + " intentos.";
    }

    int calcularPuntos(int intentos) {
        if (intentos <= 10) {
            return 11 - intentos;  
        }
        return 0;  
    } 
}
