package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorClientes {
    Conexion c = new Conexion();

    // Crear un nuevo cliente
    public void alta(Cliente p) throws SQLException {
        String sql = "INSERT INTO cliente (nombre, apellido, telefono, correo, direccion) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = c.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getApellido());
            stmt.setString(3, p.getTelefono());
            stmt.setString(4, p.getCorreo());
            stmt.setString(5, p.getDireccion());
            stmt.executeUpdate();
        }
    }

    // Listar todos los clientes
    public List<Cliente> listar() throws SQLException {
        String sql = "SELECT * FROM cliente";
        List<Cliente> lista = new ArrayList<>();

        try (Connection conn = c.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente p = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("direccion")
                );
                lista.add(p);
            }
        }
        return lista;
    }

    // Buscar cliente por ID
    public Cliente buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        try (Connection conn = c.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("telefono"),
                            rs.getString("correo"),
                            rs.getString("direccion")
                    );
                }
            }
        }
        return null; // Cliente no encontrado
    }

    // Modificar cliente
    public boolean modificarCliente(int id, String nombre, String apellido, String telefono, String correo, String direccion) throws SQLException {
        String sql = "UPDATE cliente SET nombre = ?, apellido = ?, telefono = ?, correo = ?, direccion = ? WHERE id = ?";

        try (Connection conn = c.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, telefono);
            stmt.setString(4, correo);
            stmt.setString(5, direccion);
            stmt.setInt(6, id);

            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;
        }
    }

    // Eliminar cliente
    public boolean eliminarCliente(int id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id = ?";

        try (Connection conn = c.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int filasEliminadas = stmt.executeUpdate();
            return filasEliminadas > 0;
        }
    }
}
