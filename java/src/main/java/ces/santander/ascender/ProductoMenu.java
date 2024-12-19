package ces.santander.ascender;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

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
                    Colores.MAGENTA + "         PROYECTO PRODUCTOS            " + Colores.RESET);
            System.out.println(
                    Colores.CELESTE + "=======================================" + Colores.RESET);
            System.out.println("         Seleccione una opción:      ");
            System.out.println(
                    Colores.CELESTE + "=======================================" + Colores.RESET);
            System.out.println(Colores.AMARILLO + "1. Ver productos disponibles" + Colores.RESET);
            System.out.println(Colores.AMARILLO + "2. Comprar producto" + Colores.RESET);
            System.out.println(Colores.AMARILLO + "3. Crear producto" + Colores.RESET);
            System.out.println(Colores.AMARILLO + "4. Modificar producto" + Colores.RESET);
            System.out.println(Colores.AMARILLO + "5. Eliminar producto" + Colores.RESET);
            System.out.println(Colores.AMARILLO + "6. Salir" + Colores.RESET);
            System.out.println(Colores.CELESTE + "=======================================" + Colores.RESET);

            int opcion = valorEntrada.nextInt();
            valorEntrada.nextLine();

            if (opcion < 1 || opcion > 6) {
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
                    eliminarProducto(productos, valorEntrada);
                    break;

                case 6:
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
            System.out.println(
                    Colores.ROJO + "Nombre: " + Colores.RESET + Colores.NEGRITA + producto.getNombre() + Colores.RESET);
            System.out.println(
                    Colores.ROJO + "Id: " + Colores.RESET + Colores.NEGRITA + producto.getId() + Colores.RESET);
            System.out.println(Colores.ROJO + "Cantidad: " + Colores.RESET + Colores.NEGRITA + producto.getCantidad()
                    + Colores.RESET);
            System.out.println(Colores.ROJO + "Precio: " + Colores.RESET + Colores.NEGRITA + producto.getPrecio()
                    + Monedas.euro + Colores.RESET);
            System.out.println(Colores.CELESTE + "****************************************" + Colores.RESET);
        }
    }

    private void comprarProducto(ArrayList<ProductoMenu> productos, Scanner valorEntrada) {
        System.out.println(Colores.VERDE + "Ha seleccionado la opcion 2" + Colores.RESET);
        System.out.println("Ingrese la Id del producto:");
        int IdBuscado = valorEntrada.nextInt();
        boolean encontrado = false;
        float total = 0.0f;

        for (Iterator<ProductoMenu> iterator = productos.iterator(); iterator.hasNext();) {
            ProductoMenu producto = iterator.next();
            if (producto.getId() == IdBuscado) {
                String nombreProducto = producto.nombre;
                System.out.println("Ingrese la cantidad a comprar de: " + nombreProducto);
                int cantidadComprar = valorEntrada.nextInt();
                valorEntrada.nextLine();

                while (cantidadComprar < 0) {
                    System.out.println(
                            Colores.ROJO + "La cantidad no puede ser negativa. Intente nuevamente:" + Colores.RESET);
                    cantidadComprar = valorEntrada.nextInt();
                    valorEntrada.nextLine();
                }

                if (producto.getCantidad() >= cantidadComprar) {
                    producto.cantidad -= cantidadComprar;
                    total = producto.precio * cantidadComprar;
                    System.out.println("Ha comprado " + cantidadComprar + " unidades de " + nombreProducto
                            + "   PRECIO TOTAL: " + total + Monedas.euro);

                    // Si la cantidad llega a 0, eliminar el producto de la lista
                    if (producto.getCantidad() == 0) {
                        iterator.remove();
                        System.out.println(Colores.ROJO + "El producto " + nombreProducto
                                + " ha sido eliminado de los productos disponibles." + Colores.RESET);
                    }
                } else {
                    System.out.println("La cantidad solicitada es mayor al stock disponible.");
                }
                encontrado = true;
                break; // Producto encontrado y procesado, salir del bucle
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
                System.out
                        .println(Colores.NEGRITA + "Error: Ya existe un producto con el mismo nombre." + Colores.RESET);

                return;
            }
        }

        System.out.println(Colores.NEGRITA + "Ingrese la cantidad del nuevo producto:" + Colores.RESET);
        int cantidad = scanner.nextInt();
        while (cantidad < 0) {
            System.out.println(Colores.ROJO + "La cantidad no puede ser negativa. Intente nuevamente:" + Colores.RESET);
            cantidad = scanner.nextInt();
        }
        System.out.println(Colores.NEGRITA + "Ingrese el precio del nuevo producto:" + Colores.RESET);
        float precio = scanner.nextFloat();
        scanner.nextLine();

        // Obtener el mayor ID y sumarle 1
        int nuevoId = 1; // Por defecto 1, si no hay productos
        for (ProductoMenu producto : productos) {
            if (producto.getId() > nuevoId) {
                nuevoId = producto.getId(); // Obtener el ID más alto
            }
        }
        nuevoId++;

        ProductoMenu nuevoProducto = new ProductoMenu(nuevoId, nombre, cantidad, precio);
        productos.add(nuevoProducto);
        System.out.println(Colores.AMARILLO + "Producto creado exitosamente." + Colores.RESET);

    }

    private void modificarProducto(ArrayList<ProductoMenu> productos, Scanner valorEntrada) {
        System.out.println(Colores.NEGRITA + Colores.VERDE + "Ha seleccionado la opcion 4" + Colores.RESET);
        System.out.println(Colores.NEGRITA + "Ingrese la Id del producto a modificar:" + Colores.RESET);
        int idBuscado = valorEntrada.nextInt();
        boolean encontrado = false;

        for (ProductoMenu producto : productos) {

            if (producto.getId() == idBuscado) {
                String nombreProducto = producto.nombre;
                System.out.println(Colores.NEGRITA + "Producto: " + nombreProducto + " .Ingrese la cantidad a añadir:"
                        + Colores.RESET);
                int nuevaCantidad = valorEntrada.nextInt();          
                valorEntrada.nextLine();

                while ( nuevaCantidad < 0) {
                    System.out.println(Colores.ROJO + "La cantidad no puede ser negativa. Intente nuevamente:" + Colores.RESET);
                    nuevaCantidad = valorEntrada.nextInt();
                }

                producto.cantidad += nuevaCantidad;
                System.out.println(Colores.AMARILLO + "Cantidad del producto " + nombreProducto + " modificada a "
                        + producto.cantidad +
                        " unidades." + Colores.RESET);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Producto no encontrado.");
        }
    }

    private void eliminarProducto(ArrayList<ProductoMenu> productos, Scanner valorEntrada) {
        System.out.println(Colores.NEGRITA + Colores.VERDE + "Ha seleccionado la opcion 5" + Colores.RESET);
        System.out.println(Colores.NEGRITA + "Ingrese la Id producto a eliminar" + Colores.RESET);
        int numeroId = valorEntrada.nextInt();
        boolean encontrado = false;

        for (Iterator<ProductoMenu> iterator = productos.iterator(); iterator.hasNext();) {
            ProductoMenu producto = iterator.next();
            if (producto.getId() == numeroId) {
                String nombreProducto = producto.getNombre();
                System.out.println(Colores.NEGRITA + "Producto encontrado: " + nombreProducto + Colores.RESET);

                // Preguntar si realmente quiere eliminarlo
                System.out.println(
                        Colores.NEGRITA + "¿Está seguro que desea eliminar el producto? (s/n)" + Colores.RESET);
                String confirmacion = valorEntrada.next();

                if (confirmacion.equalsIgnoreCase("s")) {

                    iterator.remove();
                    System.out.println(
                            Colores.ROJO + "El producto " + nombreProducto + " ha sido eliminado." + Colores.RESET);
                    encontrado = true;
                } else {
                    System.out.println(Colores.AMARILLO + "Eliminación cancelada." + Colores.RESET);
                }
                break;
            }
            if (!encontrado) {
                System.out.println("Producto no encontrado");
            }
        }   
    }
}
