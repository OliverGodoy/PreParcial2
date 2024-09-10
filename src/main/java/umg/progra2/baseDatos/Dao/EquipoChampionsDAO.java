package umg.progra2.baseDatos.Dao;

import umg.progra2.baseDatos.Conexion.Conexion;
import umg.progra2.baseDatos.Model.EquipoChampions;

import java.sql.*;

public class EquipoChampionsDAO {


    public void agregarEquipo(EquipoChampions equipo){
        String query = "INSERT INTO equipos_champions (nombre, pais, ciudad, estadio, fundacion, entrenador, web_oficial, facebook, twitter, instagram, patrocinador_principal) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, equipo.getNombre());
            statement.setString(2, equipo.getPais());
            statement.setString(3, equipo.getCiudad());
            statement.setString(4, equipo.getEstadio());
            statement.setInt(5, equipo.getFundacion());
            statement.setString(6, equipo.getEntrenador());
            statement.setString(7, equipo.getWebOficial());
            statement.setString(8, equipo.getFacebook());
            statement.setString(9, equipo.getTwitter());
            statement.setString(10, equipo.getInstagram());
            statement.setString(11, equipo.getPatrocinadorPrincipal());
            statement.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public EquipoChampions obtenerEquipoPorId(int id) {
        String query = "SELECT * FROM equipos_champions WHERE id_equipo = ?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    EquipoChampions equipo = new EquipoChampions();
                    equipo.setIdEquipo(rs.getInt("id_equipo"));
                    equipo.setNombre(rs.getString("nombre"));
                    equipo.setPais(rs.getString("pais"));
                    equipo.setCiudad(rs.getString("ciudad"));
                    equipo.setEstadio(rs.getString("estadio"));
                    equipo.setFundacion(rs.getInt("fundacion"));
                    equipo.setEntrenador(rs.getString("entrenador"));
                    equipo.setWebOficial(rs.getString("web_oficial"));
                    equipo.setFacebook(rs.getString("facebook"));
                    equipo.setTwitter(rs.getString("twitter"));
                    equipo.setInstagram(rs.getString("instagram"));
                    equipo.setPatrocinadorPrincipal(rs.getString("patrocinador_principal"));
                    equipo.setCreadoEn(rs.getTimestamp("creado_en"));
                    return equipo;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void actualizarEquipo(EquipoChampions equipo) {
        String query = "UPDATE equipos_champions SET nombre = ?, pais = ?, ciudad = ?, estadio = ?, fundacion = ?, entrenador = ?, web_oficial = ?, facebook = ?, twitter = ?, instagram = ?, patrocinador_principal = ? WHERE id_equipo = ?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, equipo.getNombre());
            ps.setString(2, equipo.getPais());
            ps.setString(3, equipo.getCiudad());
            ps.setString(4, equipo.getEstadio());
            ps.setInt(5, equipo.getFundacion());
            ps.setString(6, equipo.getEntrenador());
            ps.setString(7, equipo.getWebOficial());
            ps.setString(8, equipo.getFacebook());
            ps.setString(9, equipo.getTwitter());
            ps.setString(10, equipo.getInstagram());
            ps.setString(11, equipo.getPatrocinadorPrincipal());
            ps.setInt(12, equipo.getIdEquipo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public boolean eliminarEquipo(int id) {
        String query = "DELETE FROM equipos_champions WHERE id_equipo = ?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
