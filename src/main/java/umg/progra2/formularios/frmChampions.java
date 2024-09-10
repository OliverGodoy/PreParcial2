package umg.progra2.formularios;

import umg.progra2.baseDatos.Model.EquipoChampions;
import umg.progra2.baseDatos.Model.User;
import umg.progra2.baseDatos.Service.DatosService;
import umg.progra2.baseDatos.Service.EquipoChampionsService;
import umg.progra2.baseDatos.Service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

public class frmChampions extends JFrame{
    private JPanel JframeChampions;
    private JLabel lblTitulo;
    private JLabel lblIdEquipo;
    private JLabel lblNombre;
    private JLabel lblPais;
    private JLabel lblEstadio;
    private JLabel lblFundación;
    private JLabel lblEntrenador;
    private JTextField textFieldEntrenador;
    private JLabel lblWebOficial;
    private JLabel lblFacebook;
    private JTextField textFieldFacebook;
    private JLabel lblTwitter;
    private JTextField textFieldTwitter;
    private JLabel lblInstragram;
    private JTextField textFieldInstagram;
    private JTextField textFieldWebOficial;
    private JLabel lblPatrocinador;
    private JTextField textFieldPatrocinador;
    private JTextField textFieldCreadoEn;
    private JTextField textFieldIdEquipo;
    private JTextField textFieldNombre;
    private JTextField textFieldPais;
    private JTextField textFieldEstadio;
    private JTextField textFieldFundacion;
    private JButton buttonIngresar;
    private JButton buttonActualizar;
    private JButton buttonEliminar;
    private JButton buttonBuscar;
    private JLabel lblCiudad;
    private JTextField textFieldCiudad;
    private JLabel formato;

    public static void main(String[] args) {
        JFrame frame = new JFrame("frmChampions");
        frame.setContentPane(new frmChampions().JframeChampions);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public frmChampions() {
        // Configura el contenido del JFrame agregando el JPanel
        setContentPane(JframeChampions); // Aquí agregas el JPanel JframeDatos al JFrame actual
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana
        setTitle("Formulario de Datos");
        pack(); // Ajusta el tamaño de la ventana al contenido
        setLocationRelativeTo(null); // Centra la ventana en la pantalla


        buttonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EquipoChampions equipo = new EquipoChampions();

                equipo.setIdEquipo(Integer.parseInt(textFieldIdEquipo.getText()));
                equipo.setNombre(textFieldNombre.getText());
                equipo.setPais(textFieldPais.getText());
                equipo.setCiudad(textFieldCiudad.getText());
                equipo.setEstadio(textFieldEstadio.getText());
                equipo.setFundacion(Integer.parseInt(textFieldFundacion.getText()));
                equipo.setEntrenador(textFieldEntrenador.getText());
                equipo.setWebOficial(textFieldWebOficial.getText());
                equipo.setFacebook(textFieldFacebook.getText());
                equipo.setTwitter(textFieldTwitter.getText());
                equipo.setInstagram(textFieldInstagram.getText());
                equipo.setPatrocinadorPrincipal(textFieldPatrocinador.getText());

                try{
                    new EquipoChampionsService().agregarEquipo(equipo);
                    JOptionPane.showMessageDialog(null, "Usuario creado exitosamente");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Ups hay clavo con la db:" +ex.getMessage());
                }

            }
        });
        buttonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EquipoChampions equipo = new EquipoChampions();

                equipo.setIdEquipo(Integer.parseInt(textFieldIdEquipo.getText()));
                equipo.setNombre(textFieldNombre.getText());
                equipo.setPais(textFieldPais.getText());
                equipo.setCiudad(textFieldCiudad.getText());
                equipo.setEstadio(textFieldEstadio.getText());
                equipo.setFundacion(Integer.parseInt(textFieldFundacion.getText()));
                equipo.setEntrenador(textFieldEntrenador.getText());
                equipo.setWebOficial(textFieldWebOficial.getText());
                equipo.setFacebook(textFieldFacebook.getText());
                equipo.setTwitter(textFieldTwitter.getText());
                equipo.setInstagram(textFieldInstagram.getText());
                equipo.setPatrocinadorPrincipal(textFieldPatrocinador.getText());

                try{
                    new EquipoChampionsService().actualizarEquipo(equipo);
                    JOptionPane.showMessageDialog(null, "Equipo Actualizado exitosamente");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Ups hay clavo con la db:" +ex.getMessage());
                }

            }
        });
        buttonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idEquipo = textFieldIdEquipo.getText().isEmpty() ? 0 : Integer.parseInt(textFieldIdEquipo.getText());
                try {
                    // Llamar al servicio para eliminar los datos
                    boolean eliminado = new EquipoChampionsService().eliminarEquipo(idEquipo);

                    if (eliminado) {
                        JOptionPane.showMessageDialog(null, "Equipo eliminado exitosamente");

                        textFieldIdEquipo.setText("");
                        textFieldNombre.setText("");
                        textFieldPais.setText("");
                        textFieldCiudad.setText("");
                        textFieldEstadio.setText("");
                        textFieldFundacion.setText("");
                        textFieldEntrenador.setText("");
                        textFieldWebOficial.setText("");
                        textFieldFacebook.setText("");
                        textFieldTwitter.setText("");
                        textFieldInstagram.setText("");
                        textFieldPatrocinador.setText("");

                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el equipo para eliminar");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el equipo: " + ex.getMessage());
                }

            }
        });
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Buscar por id
                EquipoChampions equipo = new EquipoChampions();
                int idEquipo = textFieldIdEquipo.getText().isEmpty() ? 0 : Integer.parseInt(textFieldIdEquipo.getText());

                try{
                    EquipoChampions equipoEncontrado = new EquipoChampionsService().obtenerEquipoPorId(idEquipo);
                    if(equipoEncontrado != null){
                        textFieldNombre.setText(equipoEncontrado.getNombre());
                        textFieldPais.setText(equipoEncontrado.getPais());
                        textFieldCiudad.setText(equipoEncontrado.getCiudad());
                        textFieldEstadio.setText(equipoEncontrado.getEstadio());
                        textFieldFundacion.setText(String.valueOf(equipoEncontrado.getFundacion()));
                        textFieldEntrenador.setText(equipoEncontrado.getEntrenador());
                        textFieldWebOficial.setText(equipoEncontrado.getWebOficial());
                        textFieldFacebook.setText(equipoEncontrado.getFacebook());
                        textFieldTwitter.setText(equipoEncontrado.getTwitter());
                        textFieldInstagram.setText(equipoEncontrado.getInstagram());
                        textFieldPatrocinador.setText(equipoEncontrado.getPatrocinadorPrincipal());

                    }else{
                        JOptionPane.showMessageDialog(null, "No se encontro el Equipo");
                    }
                } catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Hay erro en db:" +ex.getMessage());
                }
            }
        });
    }
}
