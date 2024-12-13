package ces.santander.ascender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.time.Duration;
import java.time.Instant;

public class JuegoAdivinanza {
    private static final Logger logger = LoggerFactory.getLogger(JuegoAdivinanza.class);
    private static Map<String, Integer> puntosJugadores = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        logger.info("¡Bienvenido al juego de adivinanza de números!");
        
        while (true) {
            logger.info("Introduce tu nombre (o escribe 'salir' para terminar): ");
            String nombre = scanner.next();
            
            if (nombre.equalsIgnoreCase("salir")) {
                break;
            }

            jugarRonda(nombre, scanner, random);
        }
        
        mostrarPuntajes();
        scanner.close();
    }

    private static void jugarRonda(String nombre, Scanner scanner, Random random) {
        int numeroSecreto = random.nextInt(101);
        int intentos = 0;
        logger.info("Intenta adivinar el número entre 0 y 100.");
        
        Instant inicio = Instant.now();
        
        while (true) {
            logger.info("Introduce tu adivinanza: ");
            int adivinanza = scanner.nextInt();
            intentos++;
            
            if (adivinanza == numeroSecreto) {
                Instant fin = Instant.now();
                Duration tiempo = Duration.between(inicio, fin);
                
                int puntos = calcularPuntos(intentos, tiempo);
                puntosJugadores.put(nombre, puntosJugadores.getOrDefault(nombre, 0) + puntos);

                logger.info("¡Correcto! Adivinaste el número en {} intentos y {} segundos.", intentos, tiempo.getSeconds());
                logger.info("Has ganado {} puntos. Total de puntos: {}", puntos, puntosJugadores.get(nombre));
                break;
            } else if (adivinanza < numeroSecreto) {
                logger.info("El número es mayor.");
            } else {
                logger.info("El número es menor.");
            }
        }
    }

    private static int calcularPuntos(int intentos, Duration tiempo) {
        int puntosBase = 100;
        int penalizacionIntentos = intentos * 10;
        int penalizacionTiempo = (int) tiempo.getSeconds() / 10;
        return Math.max(puntosBase - penalizacionIntentos - penalizacionTiempo, 0);
    }

    private static void mostrarPuntajes() {
        logger.info("\nPuntajes de los jugadores:");
        for (Map.Entry<String, Integer> entry : puntosJugadores.entrySet()) {
            logger.info("{}: {} puntos", entry.getKey(), entry.getValue());
        }
    }
}
