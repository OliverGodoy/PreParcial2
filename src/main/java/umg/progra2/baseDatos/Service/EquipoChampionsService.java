package umg.progra2.baseDatos.Service;

import umg.progra2.baseDatos.Conexion.Conexion;
import umg.progra2.baseDatos.Dao.EquipoChampionsDAO;
import umg.progra2.baseDatos.Model.EquipoChampions;

import java.sql.SQLException;
import java.util.List;

public class EquipoChampionsService {

   private final EquipoChampionsDAO equipoChampionsDAO = new EquipoChampionsDAO();


    public void agregarEquipo(EquipoChampions equipo){
        equipoChampionsDAO.agregarEquipo(equipo);
    }

    public EquipoChampions obtenerEquipoPorId(int id){
        return equipoChampionsDAO.obtenerEquipoPorId(id);
    }

    public void actualizarEquipo(EquipoChampions equipo) {
        equipoChampionsDAO.actualizarEquipo(equipo);
    }

    public boolean eliminarEquipo(int id) {
        return equipoChampionsDAO.eliminarEquipo(id);
    }
}
