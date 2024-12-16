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
    public void testNumeroEsMayor() {
        String resultado = juego.jugarPartida("Jugador1", "Jugador2", 1, 2, 1);
        assertTrue(resultado.contains("El número es mayor"));

    }

    @Test
    public void testNumeroEsMenor() { 
        String resultado = juego.jugarPartida("Jugador1", "Jugador2", 100, 99, 1);
        assertTrue(resultado.contains("El número es menor"));      
    }

    @Test
    public void testNumeroInvalido() {      
        String resultado = juego.jugarJuego("Jugador1", "Jugador2", 110, 102);
        assertFalse(resultado.contains("110"));
        assertFalse(resultado.contains("102"));
    }

    @Test
    public void testCalculoPuntos() {  
        int puntos = juego.calcularPuntos(5);
        assertEquals(6, puntos); // 11 - intentos = 11 - 5 = 6

        puntos = juego.calcularPuntos(10);
        assertEquals(1, puntos); // 11 - 10 = 1
    }
}

