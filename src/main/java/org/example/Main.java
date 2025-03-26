package org.example;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerNum = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String[] args) {
        boolean salir = false;
        int opcion;

        do {
            menuPrincipal();
            System.out.print("Ingrese una opción: ");
            String input = scannerStr.nextLine(); // Leer como String

            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ingresar un número.");
                continue;
            }

            try {
                switch (opcion) {
                    case 1:
                        agregarCliente();
                        break;
                    case 2:
                        listarCliente();
                        break;
                    case 3:
                        editarCliente();
                        break;
                    case 4:
                        eliminarCliente();
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        } while (!salir);
        System.out.println("Fin del programa.");
    }

    private static void menuPrincipal() {
        System.out.println("\nMenú de Opciones");
        System.out.println("================");
        System.out.println("1. Agregar Cliente");
        System.out.println("2. Listar Clientes");
        System.out.println("3. Modificar Cliente");
        System.out.println("4. Eliminar Cliente");
        System.out.println("0. Salir");
        System.out.println();
        System.out.print("Ingrese una opción: ");
    }

    private static void agregarCliente() throws SQLException {
        System.out.print("Nombre: ");
        String nombre = scannerStr.nextLine();
        System.out.print("Apellido: ");
        String apellido = scannerStr.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scannerStr.nextLine();
        System.out.print("Correo: ");
        String correo = scannerStr.nextLine();
        System.out.print("Dirección: ");
        String direccion = scannerStr.nextLine();

        Cliente cliente = new Cliente(nombre, apellido, telefono, correo, direccion);
        GestorClientes gestor = new GestorClientes();
        gestor.alta(cliente);
        System.out.println("Cliente agregado exitosamente.");

        presioneEnterParaContinuar();
    }

    private static void listarCliente() throws SQLException {
        GestorClientes gestor = new GestorClientes();
        List<Cliente> clientes = gestor.listar();

        System.out.printf("\n%-4s %-20s %-20s %-15s %-35s %-35s\n",
                "ID", "Nombre", "Apellido", "Teléfono", "Correo", "Dirección");
        System.out.println("----------------------------------------------------------------------------------------");

        for (Cliente cliente : clientes) {
            System.out.printf("%-4d %-20s %-20s %-15s %-35s %-35s\n",
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getTelefono(),
                    cliente.getCorreo(),
                    cliente.getDireccion());
        }

        presioneEnterParaContinuar();
    }

    private static void editarCliente() throws SQLException {
        GestorClientes gestor = new GestorClientes();

        System.out.print("Ingrese el ID del cliente a modificar: ");
        String input = scannerStr.nextLine();
        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Debe ingresar un número.");
            presioneEnterParaContinuar();
            return;
        }

        Cliente cliente = gestor.buscarPorId(id);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            presioneEnterParaContinuar();
            return;
        }

        System.out.print("Nuevo Nombre (" + cliente.getNombre() + "): ");
        String nuevoNombre = scannerStr.nextLine();
        System.out.print("Nuevo Apellido (" + cliente.getApellido() + "): ");
        String nuevoApellido = scannerStr.nextLine();
        System.out.print("Nuevo Teléfono (" + cliente.getTelefono() + "): ");
        String nuevoTelefono = scannerStr.nextLine();
        System.out.print("Nuevo Correo (" + cliente.getCorreo() + "): ");
        String nuevoCorreo = scannerStr.nextLine();
        System.out.print("Nueva Dirección (" + cliente.getDireccion() + "): ");
        String nuevaDireccion = scannerStr.nextLine();

        boolean actualizado = gestor.modificarCliente(id,
                nuevoNombre.isEmpty() ? cliente.getNombre() : nuevoNombre,
                nuevoApellido.isEmpty() ? cliente.getApellido() : nuevoApellido,
                nuevoTelefono.isEmpty() ? cliente.getTelefono() : nuevoTelefono,
                nuevoCorreo.isEmpty() ? cliente.getCorreo() : nuevoCorreo,
                nuevaDireccion.isEmpty() ? cliente.getDireccion() : nuevaDireccion);

        if (actualizado) {
            System.out.println("Cliente actualizado exitosamente.");
        } else {
            System.out.println("No se pudo actualizar el cliente.");
        }

        presioneEnterParaContinuar();
    }

    private static void eliminarCliente() throws SQLException {
        GestorClientes gestor = new GestorClientes();

        System.out.print("Ingrese el ID del cliente a eliminar: ");
        String input = scannerStr.nextLine();
        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Debe ingresar un número.");
            presioneEnterParaContinuar();
            return;
        }

        boolean eliminado = gestor.eliminarCliente(id);
        if (eliminado) {
            System.out.println("Cliente eliminado exitosamente.");
        } else {
            System.out.println("No se encontró un cliente con ese ID.");
        }

        presioneEnterParaContinuar();
    }

    private static void presioneEnterParaContinuar() {
        System.out.println("\nPresione Enter para continuar...");
        scannerStr.nextLine(); // Espera a que el usuario presione Enter
    }
}
