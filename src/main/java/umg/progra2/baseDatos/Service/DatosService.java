package umg.progra2.baseDatos.Service;
import umg.progra2.baseDatos.Dao.DatosDao;
import umg.progra2.baseDatos.Model.Datos;

import java.util.List;

public class DatosService {
    //private DatosDao datosDAO;
    private final DatosDao datosDao = new DatosDao();

    public void guardarDatos(Datos datos) {
        datosDao.addDatos(datos);
    }

    public Datos obtenerDatosPorCodigo(int codigo){
        return datosDao.getDatos(codigo);
    }

    public List<Datos> obtenerTodosLosDatos(){
        return datosDao.getAllDatos();
    }

    public void actualizarDatos(Datos datos) {
        datosDao.updateDatos(datos);
    }

    public boolean eliminarDato(int codigo) {
        datosDao.deleteDatos(codigo);
        return true;
    }
}
