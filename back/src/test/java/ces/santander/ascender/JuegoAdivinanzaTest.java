package ces.santander.ascender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JuegoAdivinanzaTest {

    private JuegoAdivinanza juego;

    @BeforeEach
    void setUp() {
        // Inicializamos el juego antes de cada prueba
        juego = new JuegoAdivinanza();
    }

    @Test
    void testNingunJugadorAcierta() {

        // Supongamos que el número secreto es 45 y ambos jugadores fallan durante 10 intentos
        String resultado = juego.jugarPartida("Juan", "Pedro", 30, 50);

        assertTrue(resultado.contains("Ningún jugador adivinó el número secreto en 10 intentos"));
    }

    @Test
    void testPuntajeCalculadoCorrectamente() {

        // Supongamos que el número secreto es 45 y el jugador 2 adivina correctamente
        String resultado = juego.jugarPartida("Juan", "Pedro", 30, 45);

        assertTrue(resultado.contains("¡Pedro ha ganado!"));
        assertTrue(resultado.contains("Adivinaste el número en 1 intentos"));
        assertTrue(resultado.contains("Total de puntos: 10"));
    }

}
