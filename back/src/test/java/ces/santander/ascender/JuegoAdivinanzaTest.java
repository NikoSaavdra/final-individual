package ces.santander.ascender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JuegoAdivinanzaTest {

    private JuegoAdivinanza juego;

    @BeforeEach
    public void setup() {
        juego = new JuegoAdivinanza();
    }

    @Test
    public void testInicializacionPuntajes() {
        String resultado = juego.jugarJuego("Jugador1", "Jugador2", 50, 60);
        // Verificar que los puntajes se inicializan a 0 para ambos jugadores
        assertTrue(resultado.contains("Jugador1=0"));
        assertTrue(resultado.contains("Jugador2=0"));
    }

    @Test
    public void testJugador1Gana() {
        String resultado = juego.jugarPartida("Jugador1", "Jugador2", 50, 40, 1);
        assertTrue(resultado.contains("¡Numero menor!"));
        assertTrue(resultado.contains("¡Jugador1 ha ganado!"));
        assertTrue(resultado.contains("Total de puntos:"));
    }

    @Test
    public void testJugador2Gana() { 
        String resultado = juego.jugarPartida("Jugador1", "Jugador2", 40, 50, 1);
        assertTrue(resultado.contains("¡Jugador2 ha ganado!"));
        assertTrue(resultado.contains("Total de puntos:"));
    }

    @Test
    public void testNingunJugadorAdivina() {      
        String resultado = juego.jugarPartida("Jugador1", "Jugador2", 100, 1, 1);
        assertTrue(resultado.contains("Ningún jugador adivinó el número secreto"));
    }

    @Test
    public void testCalculoPuntos() {
    
        int puntos = juego.calcularPuntos(5);
        assertEquals(6, puntos); // 11 - intentos = 11 - 5 = 6

        puntos = juego.calcularPuntos(10);
        assertEquals(1, puntos); // 11 - 10 = 1
    }
}

