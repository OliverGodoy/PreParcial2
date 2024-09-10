package umg.progra2.formularios;

import umg.progra2.baseDatos.Model.Datos;
import umg.progra2.baseDatos.Service.DatosService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class frmDatos extends JFrame{
    private JPanel JframeDatos;
    private JLabel lblTitulo;
    private JLabel lblCodigo;
    private JTextField textFieldCodigo;
    private JLabel lblNombre;
    private JTextField textFieldNombre;
    private JLabel lblApellido;
    private JTextField textFieldApellido;
    private JLabel lblDepartamento;
    private JComboBox comboBoxDepartamento;
    private JLabel lblFechaNacimiento;
    private JButton buttonBuscar;
    private JButton buttonActualizar;
    private JButton buttonGrabar;
    private JTextField textFieldFechaNacimiento;
    private JButton buttonEliminar;

    public static void main(String[] args) {
        JFrame frame = new JFrame("frmDatos");
        frame.setContentPane(new frmDatos().JframeDatos);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public frmDatos() {
        // Configura el contenido del JFrame agregando el JPanel
        setContentPane(JframeDatos); // Aquí agregas el JPanel JframeDatos al JFrame actual
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana
        setTitle("Formulario de Datos");
        pack(); // Ajusta el tamaño de la ventana al contenido
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        comboBoxDepartamento.addItem("Alta Verapaz");
        comboBoxDepartamento.addItem("Baja Verapaz");
        comboBoxDepartamento.addItem("Chimaltenango");
        comboBoxDepartamento.addItem("Chiquimula");
        comboBoxDepartamento.addItem("Guatemala");
        comboBoxDepartamento.addItem("El Progreso");
        comboBoxDepartamento.addItem("Escuintla");
        comboBoxDepartamento.addItem("Huehuetenango");
        comboBoxDepartamento.addItem("Izabal");
        comboBoxDepartamento.addItem("Jalapa");
        comboBoxDepartamento.addItem("Jutiapa");
        comboBoxDepartamento.addItem("Petén");
        comboBoxDepartamento.addItem("Quetzaltenango");
        comboBoxDepartamento.addItem("Quiché");
        comboBoxDepartamento.addItem("Retalhuleu");
        comboBoxDepartamento.addItem("Sacatepéquez");
        comboBoxDepartamento.addItem("San Marcos");
        comboBoxDepartamento.addItem("Santa Rosa");
        comboBoxDepartamento.addItem("Sololá");
        comboBoxDepartamento.addItem("Suchitepéquez");
        comboBoxDepartamento.addItem("Totonicapán");
        comboBoxDepartamento.addItem("Zacapa");



        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //Buscar por codigo
                int codigo = textFieldCodigo.getText().isEmpty() ? 0 : Integer.parseInt(textFieldCodigo.getText());
                try{
                    Datos datosEncontrados = new DatosService().obtenerDatosPorCodigo(codigo);
                    if(datosEncontrados != null){
                        textFieldNombre.setText(datosEncontrados.getNombre());
                        textFieldApellido.setText(datosEncontrados.getApellido());
                        textFieldFechaNacimiento.setText(datosEncontrados.getFechaNacimiento().toString());
                        comboBoxDepartamento.setSelectedItem(datosEncontrados.getDepartamento());
                    }else{
                        JOptionPane.showMessageDialog(null, "No se encontro el Usuario");
                    }
                } catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Hay erro en db:" +ex.getMessage());
                }
            }
        });
        buttonGrabar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Datos datos = new Datos();
                datos.setNombre(textFieldNombre.getText());
                datos.setApellido(textFieldApellido.getText());
                datos.setDepartamento(comboBoxDepartamento.getSelectedItem().toString());
                datos.setCodigo(Integer.parseInt(textFieldCodigo.getText()));

                String fechaTexto = textFieldFechaNacimiento.getText();
                try{
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date fechaUtil = formato.parse(fechaTexto);
                    java.sql.Date fechaSQL = new java.sql.Date(fechaUtil.getTime());
                    datos.setFechaNacimiento(fechaSQL);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Formato de fecha invalido:" +ex.getMessage());
                    return;
                }
                try{
                    new DatosService().guardarDatos(datos);
                    JOptionPane.showMessageDialog(null, "Usuario creado exitosamente");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Ups hay clavo con la db:" +ex.getMessage());
                }

            }
        });
        buttonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Datos datos = new Datos();

                datos.setNombre(textFieldNombre.getText());
                datos.setApellido(textFieldApellido.getText());
                datos.setDepartamento(comboBoxDepartamento.getSelectedItem().toString());
                datos.setCodigo(Integer.parseInt(textFieldCodigo.getText()));
                String fechaTexto = textFieldFechaNacimiento.getText();
                try{
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date fechaUtil = formato.parse(fechaTexto);
                    java.sql.Date fechaSQL = new java.sql.Date(fechaUtil.getTime());
                    datos.setFechaNacimiento(fechaSQL);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Formato de fecha invalido:" +ex.getMessage());
                    return;
                }
                try{
                    new DatosService().actualizarDatos(datos);
                    JOptionPane.showMessageDialog(null, "Usuario actulizado exitosamente");
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Ups hay clavo con la db:" +ex.getMessage());
                }
            }
        });
        buttonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el código del usuario a eliminar
                int codigo = textFieldCodigo.getText().isEmpty() ? 0 : Integer.parseInt(textFieldCodigo.getText());

                try {
                    // Llamar al servicio para eliminar los datos
                    boolean eliminado = new DatosService().eliminarDato(codigo);

                    if (eliminado) {
                        JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");

                        // Limpiar los campos después de la eliminación
                        textFieldNombre.setText("");
                        textFieldApellido.setText("");
                        textFieldFechaNacimiento.setText("");
                        comboBoxDepartamento.setSelectedIndex(-1); // Deseleccionar el ComboBox
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el usuario para eliminar");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: " + ex.getMessage());
                }
            }
        });
    }



}
