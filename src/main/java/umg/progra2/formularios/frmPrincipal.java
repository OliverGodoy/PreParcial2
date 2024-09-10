package umg.progra2.formularios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmPrincipal {
    private JPanel JframePrincipal;
    private JLabel lblTitulo;
    private JLabel lblEjercicio1;
    private JButton buttonEjercicio1;
    private JLabel lblEjercicio2;
    private JButton ingresarAFormularioButton;
    private JLabel lblEjercicio3;
    private JButton ingresarAFormularioButton1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("frmPrincipal");
        frame.setContentPane(new frmPrincipal().JframePrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public frmPrincipal() {
        buttonEjercicio1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmDatos frmDatos = new frmDatos();
                frmDatos.setVisible(true);

            }
        });
        ingresarAFormularioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmUsuarios frmUsuarios = new frmUsuarios();
                frmUsuarios.setVisible(true);
            }
        });
        ingresarAFormularioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmChampions frmChampions = new frmChampions();
                frmChampions.setVisible(true);
            }
        });
    }
}
