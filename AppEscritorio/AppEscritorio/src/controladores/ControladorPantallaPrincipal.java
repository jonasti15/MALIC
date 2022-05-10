
package controladores;

        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;

        import javax.swing.*;

        import basedatos.LoginBD;
        import dialogo.DialogoLogin;
        import elementos.Permisos;
        import elementos.User;
        import interfaz.MUsker;
        import paneles.PanelMostrarAnimales;
        import paneles.PanelPrincipal;

public class ControladorPantallaPrincipal implements ActionListener {
    PreparedStatement ps = null;
    ResultSet rs = null;

    PanelPrincipal panelActual;

    public ControladorPantallaPrincipal(PanelPrincipal panel) {
        this.panelActual = panel;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String accion = evt.getActionCommand();
        Permisos permiso = null;

        switch (accion) {
            case "mostrarAnimales": {
                System.out.println("Mostrar animales");
                PanelMostrarAnimales panelMostrarAnimales=new PanelMostrarAnimales();
                panelActual.setViewportView(panelMostrarAnimales);

            }break;
            case "alertas": {
                System.out.println("Alertas");
            }break;
            case "mostrarVisitas": {
                System.out.println("Mostrar visitas");
            }break;


        }

    }
}
