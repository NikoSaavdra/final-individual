package ces.santander.ascender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

class JuegoAdivinanzaTest {

    private JuegoAdivinanza juego;

    @BeforeEach
    void setUp() {
        // Inicializamos el juego antes de cada prueba
        juego = new JuegoAdivinanza();
    }

    @Test
    void testJugadorGanaYRecibePuntos() {
        // Simulamos una partida en la que el jugador adivina correctamente
        String resultado = juego.jugarPartida("Jugador1", 30);

        // Verificamos que el resultado contiene la palabra "¡Correcto!"
        assertTrue(resultado.contains("¡Correcto!"));
    }

    @Test
    void testJugadorPierdePartidaConNumeroMayor() {
        
        String resultado = juego.jugarPartida("Jugador2", 10);

        // Verificamos que el resultado indique que el número es mayor
        assertTrue(resultado.contains("El número es mayor"));
    }

    @Test
    void testJugadorPierdePartidaConNumeroMenor() {
        
        String resultado = juego.jugarPartida("Jugador1", 80);

        // Verificamos que el resultado indique que el número es menor
        assertTrue(resultado.contains("El número es menor"));
    }

    @Test
    void testCalculoDePuntos() {

        juego.jugarPartida("Jugador2", 60);
        Map<String, Integer> puntajes = juego.obtenerPuntajes();

        // Verificamos que el jugador haya recibido puntos
        assertNotNull(puntajes.get("Jugador2"), "El jugador no tiene puntaje registrado.");
        assertTrue(puntajes.get("Jugador2") > 0, "El puntaje del jugador es incorrecto.");
    }

    @Test
    void testAcumulacionDePuntos() {
        // Jugador 1 juega dos partidas y obtiene puntos en cada una
        juego.jugarPartida("Jugador1", 25);
        juego.jugarPartida("Jugador1", 75);

        Map<String, Integer> puntajes = juego.obtenerPuntajes();

        // Verificamos que el jugador haya acumulado puntos en ambas rondas
        assertTrue(puntajes.get("Jugador1") > 0);
    }
}
