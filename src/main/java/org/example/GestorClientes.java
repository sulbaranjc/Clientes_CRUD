package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GestorClientes {
    Conexion c = new Conexion();
    public void alta(Cliente p) throws SQLException {
        Statement consulta = c.conectar().createStatement();
        // Conversión de Date a String. tratamiento de la fecha para que sea aceptada por MySQL
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strFechaFormateada = sdf.format(p.getFechaNacimiento());
        // estring de inserción
        String cadena = "INSERT INTO cliente(nombre, apellido, telefono, correo, direccion, fecha_nacimiento) VALUES ('"
                + p.getNombre() + "','"
                + p.getApellido() + "','"
                + p.getTelefono() + "','"
                + p.getCorreo() + "','"
                + p.getDireccion() + "','"
                + strFechaFormateada + "');";
        // System.out.println(cadena);
        consulta.executeUpdate(cadena);
        consulta.close();
    }
    public List<Cliente> listar() throws SQLException {
        Statement consulta = c.conectar().createStatement();
        ResultSet rs = consulta.executeQuery("SELECT * FROM cliente");
        List<Cliente> lista = new ArrayList<>();
        while (rs.next()) {
            Cliente p = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getString("direccion"),
                    rs.getDate("fecha_nacimiento")
            );
            lista.add(p);
        }
        rs.close();
        consulta.close();
        return lista;
    }



}
