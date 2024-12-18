package ces.santander.ascender;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductoMenu {

    private int id;
    private String nombre;
    private int cantidad;
    private float precio;

    public ProductoMenu(int id, String nombre, int cantidad, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void elegirMenu(ArrayList<ProductoMenu> productos) throws Exception {
        Scanner valorEntrada = new Scanner(System.in);
        boolean sigue = true;

        while (sigue) {

            System.out.println(
                    Colores.CELESTE + "=======================================" + Colores.RESET);
            System.out.println(
                    Colores.MAGENTA + "         PROYECTO PRODUCTO            " + Colores.RESET);
            System.out.println(
                    Colores.CELESTE + "=======================================" + Colores.RESET);
            System.out.println("         Seleccione una opción:      ");
            System.out.println(
                    Colores.CELESTE + "=======================================" + Colores.RESET);
            System.out.println(Colores.AMARILLO + "1. Ver productos disponibles" + Colores.RESET);
            System.out.println(Colores.AMARILLO + "2. Comprar producto" + Colores.RESET);
            System.out.println(Colores.AMARILLO + "3. Crear producto" + Colores.RESET);
            System.out.println(Colores.AMARILLO + "4. Modificar producto" + Colores.RESET);
            System.out.println(Colores.AMARILLO + "5. Salir" + Colores.RESET);
            System.out.println(Colores.CELESTE + "=======================================" + Colores.RESET);

            int opcion = valorEntrada.nextInt();
            valorEntrada.nextLine();

            if (opcion < 1 || opcion > 5) {
                throw new Exception("Opción no válida, por favor intente nuevamente.");
            }

            switch (opcion) {
                case 1:
                    mostrarProductosDisponibles(productos);
                    break;

                case 2:
                    comprarProducto(productos, valorEntrada);
                    break;

                case 3:
                    crearProducto(productos, valorEntrada);
                    break;

                case 4:
                    modificarProducto(productos, valorEntrada);
                    break;

                case 5:
                    System.out.println("Ha salido del menú.");
                    sigue = false;
                    break;
            }

            System.out.println(Colores.GRIS + "****************************************" + Colores.RESET);
            System.out.println("Presione cualquier tecla para volver al Menú o 'S' para salir.");
            String respuesta = valorEntrada.nextLine();
            if (respuesta.equalsIgnoreCase("S")) {
                sigue = false;
            }
        }

        valorEntrada.close();
    }

    private void mostrarProductosDisponibles(ArrayList<ProductoMenu> productos) {
        
        System.out.println(Colores.VERDE + "Ha seleccionado la opcion 1" + Colores.RESET);
        System.out.println(Colores.BLANCO + "Los productos disponibles son:" + Colores.RESET);

        for (ProductoMenu producto : productos) {
            System.out.println(Colores.CELESTE + "****************************************" + Colores.RESET);
            System.out.println(Colores.ROJO + "Nombre: " + Colores.RESET + Colores.NEGRITA + producto.getNombre() + Colores.RESET);
            System.out.println(Colores.ROJO + "Id: " + Colores.RESET + Colores.NEGRITA + producto.getId() + Colores.RESET);
            System.out.println(Colores.ROJO + "Cantidad: " + Colores.RESET + Colores.NEGRITA + producto.getCantidad() + Colores.RESET);
            System.out.println(Colores.ROJO + "Precio: " + Colores.RESET + Colores.NEGRITA + producto.getPrecio() + Monedas.euro + Colores.RESET);
            System.out.println(Colores.CELESTE + "****************************************" + Colores.RESET);
        }
    }

    private void comprarProducto(ArrayList<ProductoMenu> productos, Scanner valorEntrada) {
        System.out.println(Colores.VERDE + "Ha seleccionado la opcion 2" + Colores.RESET);
        System.out.println("Ingrese el nombre del producto:");
        String nombreBuscado = valorEntrada.nextLine();
        boolean encontrado = false;
        float total = 0.0f;

        for (ProductoMenu producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombreBuscado)) {
                System.out.println("Ingrese la cantidad a comprar:");
                int cantidadComprar = valorEntrada.nextInt();
                valorEntrada.nextLine(); 

                if (producto.getCantidad() >= cantidadComprar) {
                    producto.cantidad -= cantidadComprar;
                    total = producto.precio * cantidadComprar;
                    System.out.println("Ha comprado " + cantidadComprar + " unidades de " + nombreBuscado + "   PRECIO TOTAL: " + total + Monedas.euro);
                } else {
                    System.out.println("La cantidad solicitada es mayor al stock disponible.");
                }
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Producto no encontrado.");
        }
    }

    private void crearProducto(ArrayList<ProductoMenu> productos, Scanner scanner) {
        System.out.println(Colores.VERDE + "Ha seleccionado la opcion 3" + Colores.RESET);
        System.out.println(Colores.NEGRITA + "Ingrese el nombre del nuevo producto:" + Colores.RESET);
        String nombre = scanner.nextLine();

        for (ProductoMenu producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println(Colores.NEGRITA + "Error: Ya existe un producto con el mismo nombre." + Colores.RESET);

                return; 
            }
        }

        System.out.println(Colores.NEGRITA + "Ingrese la cantidad del nuevo producto:" + Colores.RESET);
        int cantidad = scanner.nextInt();
        System.out.println(Colores.NEGRITA + "Ingrese el precio del nuevo producto:" + Colores.RESET);
        float precio = scanner.nextFloat();
        scanner.nextLine(); 

        int nuevoId = productos.size() + 1;
        ProductoMenu nuevoProducto = new ProductoMenu(nuevoId, nombre, cantidad, precio);
        productos.add(nuevoProducto);
        System.out.println(Colores.AMARILLO + "Producto creado exitosamente." + Colores.RESET);

    }

    private void modificarProducto(ArrayList<ProductoMenu> productos, Scanner valorEntrada) {
        System.out.println(Colores.NEGRITA + Colores.VERDE + "Ha seleccionado la opcion 4" + Colores.RESET);
        System.out.println(Colores.NEGRITA + "Ingrese el nombre del producto a modificar:" + Colores.RESET);
        String nombreBuscado = valorEntrada.nextLine();
        boolean encontrado = false;
        

        for (ProductoMenu producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombreBuscado)) {
                System.out.println(Colores.NEGRITA + "Ingrese la cantidad:" + Colores.RESET);
                int nuevaCantidad = valorEntrada.nextInt();
                valorEntrada.nextLine();
                

                producto.cantidad += nuevaCantidad; 
                System.out.println(Colores.AMARILLO + "Cantidad del producto " + nombreBuscado + " modificada a " + producto.cantidad + 
                           " unidades." + Colores.RESET);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Producto no encontrado.");
        }
    }
}
