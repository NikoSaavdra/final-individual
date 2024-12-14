package ces.santander.ascender;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class JuegoAdivinanza {
    private Map<String, Integer> puntosJugadores = new HashMap<>();
    private static final int MAX_INTENTOS = 10;

    public String jugarPartida(String nombreJugador1, String nombreJugador2, int adivinanzaJugador1, int adivinanzaJugador2) {
        Random random = new Random();
        int numeroSecreto = random.nextInt(101);  
        int intentosJugador1 = 0, intentosJugador2 = 0;
        
        // Inicializar los puntajes de los jugadores si no existen
        puntosJugadores.putIfAbsent(nombreJugador1, 0);
        puntosJugadores.putIfAbsent(nombreJugador2, 0);

        for (int i = 0; i < MAX_INTENTOS; ) {
            i++;
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

        // Si ninguno de los dos jugadores acertó después de 10 intentos
        return "Ningún jugador adivinó el número secreto en " + MAX_INTENTOS + " intentos.";
    }

    int calcularPuntos(int intentos) {
        
        if (intentos <= 10) {
            return 11 - intentos;  
        }
        return 0;  
    }

    public Map<String, Integer> obtenerPuntajes() {
        return puntosJugadores;
    }
}
