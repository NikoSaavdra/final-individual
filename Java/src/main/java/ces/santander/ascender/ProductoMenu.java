package ces.santander.ascender;

import java.util.Scanner;

/*
 * Crea un programa en Java que simule un menú de opciones para una tienda. El programa debe mostrar un menú con las siguientes opciones:

    Ver productos disponibles
    Realizar una compra
    Consultar saldo
    Salir

El programa debe utilizar una estructura switch para gestionar la selección del usuario. Dependiendo de la opción seleccionada, el programa debe 
   realizar las siguientes acciones:

    Opción 1: Mostrar un mensaje que simule la lista de productos disponibles (por ejemplo, "Producto A", "Producto B", "Producto C").
    Opción 2: Solicitar al usuario el nombre de un producto y su precio, y mostrar un mensaje simulando que la compra se ha realizado con éxito.
    Opción 3: Mostrar un mensaje que indique el saldo actual (puedes simular un saldo inicial de, por ejemplo, 1000 unidades monetarias).
    Opción 4: Salir del programa.

Utiliza un ciclo while para seguir mostrando el menú hasta que el usuario elija la opción de salir (opción 4).
 */

public class ProductoMenu {

    private int id;
    private String nombre;
    private int cantidad;
    private double precio;

    // Constructor de la clase
    public ProductoMenu(int id, String nombre, int cantidad, double precio) {
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

    public double getPrecio() {
        return precio;
    }

    public char elegirMenu(ProductoMenu[] productos) throws Exception {
        Scanner valorEntrada = new Scanner(System.in);
        Scanner valor = new Scanner(System.in);
        int cant = valor.nextInt();

        boolean sigue = true;

        while (sigue) {
            System.out.println("Seleccione una opción: ");
            System.out.println("1. Ver productos disponibles");
            System.out.println("2. Comprar producto");
            System.out.println("3. Crear producto");
            System.out.println("4. Modificar producto");
            System.out.println("5. Salir");

            int opcion = valorEntrada.nextInt();
            valorEntrada.nextLine(); // Consumir la nueva línea después de leer el número
           

            if (opcion < 1 || opcion > 5) {
                throw new Exception("Opción no válida, por favor intente nuevamente.");
            }

            switch (opcion) {
                case 1:
                    // Mostrar productos disponibles
                    for (int i = 0; i < productos.length; i++) {
                        System.out.println("Id: " + productos[i].getId());
                        System.out.println("Nombre: " + productos[i].getNombre());
                        System.out.println("Cantidad: " + productos[i].getCantidad());
                    }
                    // Pedir al usuario que elija si desea volver o salir
                    char respuesta = elegir(valorEntrada);
                    if (respuesta == 'S' || respuesta == 's') {
                        sigue = false;
                    }
                    break;

                case 2:
                    // Comprar producto
                    System.out.println("Ingrese el nombre del producto:");
                    String nombreBuscado = valorEntrada.nextLine();
                    boolean encontrado = false;

                    for (int i = 0; i < productos.length; i++) {
                        if (productos[i].getNombre().equals(nombreBuscado)) {
                            System.out.println("Ingrese la cantidad a comprar");
                             int stock = productos[i].getCantidad();
                             if (stock > cant) {
                                 stock = stock - cant;
                             } else {
                                System.out.println("La cantidad ingresa es mayor al stock");
                               }
                            System.out.println("Ha comprado " + stock + " unidades de " + nombreBuscado);
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("Producto no encontrado.");
                    }

                    // Pedir al usuario que elija si desea volver o salir
                    char respuestaBusqueda = elegir(valorEntrada);
                    if (respuestaBusqueda == 'S' || respuestaBusqueda == 's') {
                        sigue = false;
                    }
                    break;

                case 3: // crea Producto

                case 4: // Modificar producto

                case 5:
                    // Salir del menú
                    System.out.println("Ha salido del menú.");
                    sigue = false;
                    break;
            }
        }
        
        valorEntrada.close();
        return 'S';
        
    }

    // Método para elegir si volver o salir
    public char elegir(Scanner input) {
        System.out.println("Presione cualquier tecla para volver al Menú o 'S' para salir.");

        String respuesta = input.nextLine();
        if (respuesta.length() > 0) {
            return respuesta.charAt(0);
        } else {
            return 'S'; // Si no se ingresa nada, por defecto consideramos que se desea salir
        }
    }
}
