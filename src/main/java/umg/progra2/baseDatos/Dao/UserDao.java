package umg.progra2.baseDatos.Dao;

import umg.progra2.baseDatos.Conexion.Conexion;
import umg.progra2.baseDatos.Model.User;

import java.sql.*;

public class UserDao {


    public void deleteUserByEmail(String email) throws SQLException {
        String query = "DELETE FROM  tb_usuarios WHERE correo = ?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.executeUpdate();
        }
    }

    public void updateUser(User user) throws SQLException {
        String query = "UPDATE tb_usuarios SET carne = ?, nombre = ?, correo = ?, seccion = ?, telegramid = ?, activo = ? WHERE idusuario = ?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getCarne());
            statement.setString(2, user.getNombre());
            statement.setString(3, user.getCorreo());
            statement.setString(4, user.getSeccion());
            statement.setLong(5, user.getTelegramid());
            statement.setString(6, user.getActivo());
            statement.setInt(7, user.getId());
            statement.executeUpdate();
        }
    }

    public boolean deleteUserById(int id){
        String query = "DELETE FROM  tb_usuarios WHERE idusuario = ?";
        try(Connection connection = Conexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1,id);
            int rowsAffected = statement.executeUpdate(); //ejcuta las consultas y obtiene las filas
            return rowsAffected > 0; //retorna true si se elimino un registro
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void insertUser(User user){
        String query = "INSERT INTO tb_usuarios (carne, nombre, correo, seccion, telegramid, activo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getCarne());
            statement.setString(2, user.getNombre());
            statement.setString(3, user.getCorreo());
            statement.setString(4, user.getSeccion());
            statement.setLong(5, user.getTelegramid());
            statement.setString(6, user.getActivo());
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public User getUserByTelegramId(long telegramid) throws SQLException {
        String query = "SELECT * FROM tb_usuarios WHERE telegramid = ?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, telegramid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("idusuario"));
                user.setCarne(resultSet.getString("carne"));
                user.setNombre(resultSet.getString("nombre"));
                user.setCorreo(resultSet.getString("correo"));
                user.setSeccion(resultSet.getString("seccion"));
                user.setTelegramid(resultSet.getLong("telegramid"));
                user.setActivo(resultSet.getString("activo"));
                return user;
            }
        }
        return null;
    }

    public User getUserByEmail(String Email){
        String query = "SELECT * FROM tb_usuarios WHERE correo = ?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, Email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("idusuario"));
                user.setCarne(resultSet.getString("carne"));
                user.setNombre(resultSet.getString("nombre"));
                user.setCorreo(resultSet.getString("correo"));
                user.setSeccion(resultSet.getString("seccion"));
                user.setTelegramid(resultSet.getLong("telegramid"));
                user.setActivo(resultSet.getString("activo"));
                return user;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByCarne(String carne) throws SQLException {
        String query = "SELECT * FROM tb_usuarios WHERE carne = ?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, carne);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("idusuario"));
                user.setNombre(resultSet.getString("nombre"));
                user.setCarne(resultSet.getString("carne"));
                user.setCorreo(resultSet.getString("correo"));
                user.setSeccion(resultSet.getString("seccion"));
                user.setTelegramid(resultSet.getLong("telegramid"));
                user.setActivo(resultSet.getString("activo"));
                return user;
            }
        }
        return null;
    }

    public User getUserById(int idusuario) throws SQLException {
        String query = "SELECT * FROM tb_usuarios WHERE idusuario = ?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idusuario);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("idusuario"));
                user.setCarne(resultSet.getString("carne"));
                user.setNombre(resultSet.getString("nombre"));
                user.setCorreo(resultSet.getString("correo"));
                user.setSeccion(resultSet.getString("seccion"));
                user.setTelegramid(resultSet.getLong("telegramid"));
                user.setActivo(resultSet.getString("activo"));
                return user;
            }
        }
        return null;
    }


}
