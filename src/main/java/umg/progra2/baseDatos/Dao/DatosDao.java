package umg.progra2.baseDatos.Dao;
import umg.progra2.baseDatos.Conexion.Conexion;
import umg.progra2.baseDatos.Model.Datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatosDao {

    // Método para agregar un nuevo registro a la tabla tb_datos
    public void addDatos(Datos datos) {
        String query = "INSERT INTO tb_datos (nombre, apellido, departamento, fecha_nacimiento) VALUES (?, ?, ?, ?)";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, datos.getNombre());
            statement.setString(2, datos.getApellido());
            statement.setString(3, datos.getDepartamento());
            statement.setDate(4, new java.sql.Date(datos.getFechaNacimiento().getTime()));

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        datos.setCodigo(generatedKeys.getInt(1));  // Obtener el código generado
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener un registro por su codigo
    public Datos getDatos(int codigo) {
        String query = "SELECT * FROM tb_datos WHERE codigo = ?";
        Datos datos = null;

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, codigo);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    datos = new Datos();
                    datos.setCodigo(resultSet.getInt("codigo"));
                    datos.setNombre(resultSet.getString("nombre"));
                    datos.setApellido(resultSet.getString("apellido"));
                    datos.setDepartamento(resultSet.getString("departamento"));
                    datos.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }

    // Método para obtener todos los registros de la tabla tb_datos
    public List<Datos> getAllDatos() {
        List<Datos> datosList = new ArrayList<>();
        String query = "SELECT * FROM tb_datos";

        try (Connection connection = Conexion.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Datos datos = new Datos();
                datos.setCodigo(resultSet.getInt("codigo"));
                datos.setNombre(resultSet.getString("nombre"));
                datos.setApellido(resultSet.getString("apellido"));
                datos.setDepartamento(resultSet.getString("departamento"));
                datos.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
                datosList.add(datos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datosList;
    }

    // Método para actualizar un registro en la tabla tb_datos
    public void updateDatos(Datos datos) {
        String query = "UPDATE tb_datos SET nombre = ?, apellido = ?, departamento = ?, fecha_nacimiento = ? WHERE codigo = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, datos.getNombre());
            statement.setString(2, datos.getApellido());
            statement.setString(3, datos.getDepartamento());
            statement.setDate(4, new java.sql.Date(datos.getFechaNacimiento().getTime()));
            statement.setInt(5, datos.getCodigo());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un registro de la tabla tb_datos por su ID
    public void deleteDatos(int codigo) {
        String query = "DELETE FROM tb_datos WHERE codigo = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, codigo);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
