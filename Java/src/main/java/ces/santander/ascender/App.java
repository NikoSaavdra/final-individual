package ces.santander.ascender;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {

        ArrayList<ProductoMenu> productos = new ArrayList<>();
        productos.add(new ProductoMenu(1, "Pan", 8, 1.99f));
        productos.add(new ProductoMenu(2, "Sal", 5, 2.5f));
        productos.add(new ProductoMenu(3, "Harina", 4, 5.9f));

        ProductoMenu menu = new ProductoMenu(0, "", 0, 0.0f);

        try {
                menu.elegirMenu(productos);
                
        } catch (Exception e) {
        
            System.err.println("Se ha producido un error en el menú: " + e.getMessage());
        }
    }
}
