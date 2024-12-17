package ces.santander.ascender;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {

        ArrayList<ProductoMenu> productos = new ArrayList<>();
        productos.add(new ProductoMenu(1, "Pan", 8, 1.99));
        productos.add(new ProductoMenu(2, "Sal", 3, 2.50));
        productos.add(new ProductoMenu(3, "Harina", 4, 5.50));

        ProductoMenu menu = new ProductoMenu(0, "", 0, 0.0);

        try {
                menu.elegirMenu(productos);
                
        } catch (Exception e) {
        
            System.err.println("Se ha producido un error en el menú: " + e.getMessage());
        }
    }
}
