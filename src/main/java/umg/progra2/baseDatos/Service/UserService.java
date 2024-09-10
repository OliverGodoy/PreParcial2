package umg.progra2.baseDatos.Service;

import umg.progra2.baseDatos.Conexion.Conexion;
import umg.progra2.baseDatos.Dao.UserDao;
import umg.progra2.baseDatos.Model.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {
    //private UserDao userDao = new UserDao();
    private static final UserDao userDao = new UserDao();

    public boolean deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }

    public void updateUser(User user) {
        try {
            userDao.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarUser(User user) {
        userDao.insertUser(user);

    }

    public User getUserByTelegramId(long telegramid) {
        try {
            return userDao.getUserByTelegramId(telegramid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User GetUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    //Obtener por ID
    public static User GetUserById(int id) throws SQLException {
        return userDao.getUserById(id);
    }

    public User getUserByCarne(String carne) {
        try {
            return userDao.getUserByCarne(carne);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(int idusuario) {
        try {
            return userDao.getUserById(idusuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
