package umg.progra2.formularios;

import umg.progra2.baseDatos.Dao.UserDao;
import umg.progra2.baseDatos.Model.Datos;
import umg.progra2.baseDatos.Model.User;
import umg.progra2.baseDatos.Service.DatosService;
import umg.progra2.baseDatos.Service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmUsuarios extends JFrame{
    private JLabel lblTitulo;
    private JPanel JframaUsuarios;
    private JLabel lblIdUsuario;
    private JTextField textFieldIdUsuario;
    private JLabel lblCarne;
    private JLabel lblNombre;
    private JLabel lblCorreo;
    private JLabel lblSeccion;
    private JLabel lblTelegramId;
    private JLabel lblActivo;
    private JTextField textFieldCarne;
    private JTextField textFieldNombre;
    private JTextField textFieldCorreo;
    private JTextField textFieldSeccion;
    private JTextField textFieldTelegramId;
    private JCheckBox checkBoxActivo;
    private JButton buttonInsertar;
    private JButton buttonActualizar;
    private JButton buttonEliminar;
    private JButton buttonBuscar;
    private JTextField textFieldActivo;

    public static void main(String[] args) {
        JFrame frame = new JFrame("frmUsuarios");
        frame.setContentPane(new frmUsuarios().JframaUsuarios);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public frmUsuarios() {

        // Configura el contenido del JFrame agregando el JPanel
        setContentPane(JframaUsuarios); // Aquí agregas el JPanel JframeDatos al JFrame actual
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana
        setTitle("Formulario de Datos");
        pack(); // Ajusta el tamaño de la ventana al contenido
        setLocationRelativeTo(null); // Centra la ventana en la pantalla


        buttonInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String email = textFieldCorreo.getText();
                User usuarioExistente = UserService.GetUserByEmail(email);

                if (usuarioExistente != null) {
                    JOptionPane.showMessageDialog(null, "El correo electrónico ya está registrado.");
                } else {
                    User user = new User();
                    user.setId(Integer.parseInt(textFieldIdUsuario.getText()));
                    user.setNombre(textFieldNombre.getText());
                    user.setCarne(textFieldCarne.getText());
                    user.setCorreo(textFieldCorreo.getText());
                    user.setSeccion(textFieldSeccion.getText());
                    user.setActivo(textFieldActivo.getText());

                    String telegramIdString = textFieldTelegramId.getText(); // Obtener el texto del JTextField

                    try {
                        long telegramId = Long.parseLong(telegramIdString); // Convertir a long
                        user.setTelegramid(telegramId); // Establecer el valor en el objeto User
                        JOptionPane.showMessageDialog(null, "ID de Telegram establecido exitosamente.");
                    } catch (NumberFormatException ex) {
                        // Manejar el caso en que la conversión falla
                        JOptionPane.showMessageDialog(null, "El ID de Telegram no es válido. Debe ser un número.");
                    }
                    try {
                        new UserService().insertarUser(user);
                        JOptionPane.showMessageDialog(null, "Usuario creado exitosamente");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Ups hay clavo con la db:" + ex.getMessage());
                    }

                }
            }
        });
        buttonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gmail = textFieldCorreo.getText(); // Asegúrate de que este sea el campo correcto para el correo
                User correoExistente = UserService.GetUserByEmail(gmail);

                if (correoExistente != null) { // Cambia la condición aquí
                    JOptionPane.showMessageDialog(null, "El correo electrónico ya está registrado.");
                } else {
                    User user = new User();
                    user.setId(Integer.parseInt(textFieldIdUsuario.getText()));
                    user.setNombre(textFieldNombre.getText());
                    user.setCarne(textFieldCarne.getText());
                    user.setCorreo(textFieldCorreo.getText());
                    user.setSeccion(textFieldSeccion.getText());
                    user.setActivo(textFieldActivo.getText());
                    String telegramIdString = textFieldTelegramId.getText();
                    try {
                        long telegramId = Long.parseLong(telegramIdString); // Convertir a long
                        user.setTelegramid(telegramId);
                        //JOptionPane.showMessageDialog(null, "ID de Telegram Actualizado exitosamente.");
                    } catch (NumberFormatException ex) {
                        // Manejar el caso en que la conversión falla
                        JOptionPane.showMessageDialog(null, "El ID de Telegram no es válido. Debe ser un número.");
                    }
                    try {
                        new UserService().updateUser(user);
                        JOptionPane.showMessageDialog(null, "Usuario Actualizado exitosamente");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Ups hay clavo con la db:" + ex.getMessage());
                    }
                }
            }
        });
        buttonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = textFieldIdUsuario.getText().isEmpty() ? 0 : Integer.parseInt(textFieldIdUsuario.getText());

                try{
                    boolean eliminar = new UserService().deleteUserById(id);
                    if(eliminar){
                        JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");

                        textFieldIdUsuario.setText("");
                        textFieldCarne.setText("");
                        textFieldNombre.setText("");
                        textFieldCorreo.setText("");
                        textFieldSeccion.setText("");
                        textFieldTelegramId.setText("");
                        textFieldActivo.setText("");
                    }else {
                        JOptionPane.showMessageDialog(null, "No se encontró el usuario para eliminar");
                    }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: " + ex.getMessage());
                }
            }
        });
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Buscar por id
                User user = new User();
                int id = textFieldIdUsuario.getText().isEmpty() ? 0 : Integer.parseInt(textFieldIdUsuario.getText());
                try{
                    User userEncontrado = new UserService().getUserById(id);
                    if(userEncontrado != null){
                        textFieldCarne.setText(userEncontrado.getCarne());
                        textFieldNombre.setText(userEncontrado.getNombre());
                        textFieldCorreo.setText(userEncontrado.getCorreo());
                        textFieldSeccion.setText(userEncontrado.getSeccion());
                        textFieldActivo.setText(userEncontrado.getActivo());
                        // Obtener el ID de Telegram del usuario encontrado y establecerlo en el JTextField
                        long telegramId = userEncontrado.getTelegramid(); // Obtén el ID de Telegram
                        textFieldTelegramId.setText(String.valueOf(telegramId)); // Establece el ID de Telegram en el JTextField
                    }else{
                        JOptionPane.showMessageDialog(null, "No se encontro el Usuario");
                    }
                } catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Hay erro en db:" +ex.getMessage());
                }
            }
        });
    }
}
