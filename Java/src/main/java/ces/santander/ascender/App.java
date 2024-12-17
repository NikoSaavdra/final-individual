package ces.santander.ascender;

public class App {

    public static void main(String[] args) {

        ProductoMenu menu = new ProductoMenu(0, "", 0, 0.0);

        ProductoMenu[] productos = {
                new ProductoMenu(1, "Pizza", 4, 10.99),
                new ProductoMenu(2, "Hamburguesa", 3, 5.99),
                new ProductoMenu(3, "Pasta", 4, 8.50)
        };

        try {
            // Llamar al método elegirMenu que maneja el menú
            char resultado = menu.elegirMenu(productos);
            System.out.println("Resultado del menú: " + resultado);
        } catch (Exception e) {
            // Capturar cualquier excepción y mostrar el mensaje de error
            System.err.println("Se ha producido un error en el menú: " + e.getMessage());
        }
    }
}
