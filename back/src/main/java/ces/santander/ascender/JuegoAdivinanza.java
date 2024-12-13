package ces.santander.ascender;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class JuegoAdivinanza {
    private Map<String, Integer> puntosJugadores = new HashMap<>();

    public String jugarPartida(String nombre, int adivinanza) {
        Random random = new Random();
        int numeroSecreto = random.nextInt(101);
        int intentos = 0;
        puntosJugadores.putIfAbsent(nombre, 0);

        while (true) {
            intentos++;

            if (adivinanza == numeroSecreto) {
                int puntos = calcularPuntos(intentos);
                puntosJugadores.put(nombre, puntosJugadores.get(nombre) + puntos);

                return "¡Correcto! Adivinaste el número en " + intentos + " intentos. Has ganado "
                        + puntos + " puntos. Total de puntos: " + puntosJugadores.get(nombre);
            } else if (adivinanza < numeroSecreto) {
                return "El número es mayor a " + adivinanza;
            } else {
                return "El número es menor a " + adivinanza;
            }
        }
    }

    private int calcularPuntos(int intentos) {
        int puntosBase = 100;
        int penalizacionIntentos = intentos * 10;
        return Math.max(puntosBase - penalizacionIntentos, 0);
    }

    public Map<String, Integer> obtenerPuntajes() {
        return puntosJugadores;
    }
}
