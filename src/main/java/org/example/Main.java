package org.example;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerNum = new Scanner(System.in).useLocale(Locale.US);
    public static void main(String[] args) {
        boolean salir = false;
        int opcion;
        do {
            menuPrincipal();
            opcion = scannerNum.nextInt();
            try {
                switch (opcion) {
                    case 1:
                        agregarCliente();
                        break;
                    case 2:
                        listarCliente();
                        break;
                    case 3:
                        //editarAlumno();
                        break;
                    case 4:
                        //eliminarAlumno();
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        } while (!salir);
        System.out.println("Fin del programa");
    }
    private static void menuPrincipal() {
        System.out.println("Menu de Opciones");
        System.out.println("================");
        System.out.println("1. Agregar Cliente");
        System.out.println("2. Listar Cientes");
        System.out.println("3. Modifier Cliente");
        System.out.println("4. Eliminar Cliente");
        System.out.println("0. Salir");
        System.out.println();
        System.out.print("Ingrese una opcion: ");
    }
    private static void agregarCliente() throws IOException, ClassNotFoundException, SQLException, ParseException {
        System.out.print("Nombre: ");
        String nombre = scannerStr.nextLine();
        System.out.print("Apellido: ");
        String apellido = scannerStr.nextLine();
        System.out.print("Telefono: ");
        String telefono = scannerStr.nextLine();
        System.out.print("Correo: ");
        String correo = scannerStr.nextLine();
        System.out.print("Direccion: ");
        String direccion = scannerStr.nextLine();
        System.out.print("Fecha de Nacimiento: ");

        String strFechaNacimiento = scannerStr.nextLine();
        // Conversión de String a Date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaNacimiento = sdf.parse(strFechaNacimiento); // Aquí se puede lanzar una ParseException
        //System.out.println("La Fecha formato fecha " + fechaNacimiento);

        // Instanciación del cliente con la fecha convertida
        Cliente cliente = new Cliente(nombre, apellido, telefono, correo, direccion,fechaNacimiento);

        // Instanciación del cliente con la fecha convertida

        GestorClientes gestor = new GestorClientes();
        gestor.alta(cliente); // Llamada al método alta del gestor para agregar el alumno
        System.out.println("Alumno agregado exitosamente.");
    }
    private static void listarCliente() throws SQLException {
        GestorClientes gestor = new GestorClientes();
        List<Cliente> clientes = gestor.listar(); // Llamada al método listar del gestor para obtener la lista de clientes

        // Formato de cabecera
        System.out.printf("%-4s %-20s %-20s %-15s %-35s %-35s %s\n",
                "ID", "Nombre", "Apellido", "Teléfono", "Correo", "Dirección", "Fecha Nac.");

        for (Cliente cliente : clientes) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String fechaNacFormateada = (cliente.getFechaNacimiento() != null) ? sdf.format(cliente.getFechaNacimiento()) : "N/A";

            System.out.printf("%-4d %-20s %-20s %-15s %-35s %-35s %s\n",
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getTelefono(),
                    cliente.getCorreo(),
                    cliente.getDireccion(),
                    fechaNacFormateada);
        }
    }
}
