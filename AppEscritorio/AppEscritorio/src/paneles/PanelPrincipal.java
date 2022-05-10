package paneles;

import controladores.ControladorPantallaPrincipal;
import gestionpantallas.RoundedBorder;

import java.awt.*;

import javax.swing.*;

public class PanelPrincipal extends JScrollPane{
	private static final Color COLOROSCURO = new Color(118,185,71);
	private static final Color COLORFONDO = new Color(177,216,183);
	ControladorPantallaPrincipal controladorPantallaPrincipal;
	public PanelPrincipal() {
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.setBackground(Color.white);
		this.setBorder(null);
		
		this.getVerticalScrollBar().setUnitIncrement(20);
		this.controladorPantallaPrincipal =new ControladorPantallaPrincipal(this);
		this.setViewportView(crearPanelVentana());
		this.setBackground(Color.white);
	}
	
	public Component crearPanelVentana() {
		JPanel panel = new JPanel();
		panel.setBackground(COLORFONDO);

		JLabel label = new JLabel("MUsker");
		label.setFont(new Font("Times new Roman + Trutype font", Font.PLAIN, 36));
		label.setPreferredSize(new Dimension(200, 40));
		label.setForeground(new Color(60, 112, 112));
		label.setHorizontalAlignment(JLabel.CENTER);
		panel.add(crearpanelbotones());
		panel.setBorder(BorderFactory.createEmptyBorder(30,10,10,10));
		
		return panel;
	}

	private Component crearpanelbotones() {
		JPanel panel = new JPanel(new GridLayout(4,1,30,30));
		panel.setBackground(COLORFONDO);
		JLabel logo =new JLabel(new ImageIcon("images/MUskerGrande.png"));
		panel.add(logo);
		panel.add(crearBoton("mostrarAnimales"));
		panel.add(crearBoton("alertas"));
		panel.add(crearBoton("mostrarVisitas"));


		return panel;
	}

	private Component crearBoton(String nombre) {
		ImageIcon mostrarAnimalesImg=new ImageIcon("images/"+nombre+".png");
		JButton boton = new JButton(mostrarAnimalesImg);
		boton.setActionCommand(nombre);
		boton.addActionListener(controladorPantallaPrincipal);
		boton.setPreferredSize(new Dimension(mostrarAnimalesImg.getIconWidth(),mostrarAnimalesImg.getIconHeight()));
		boton.setHorizontalAlignment(JLabel.CENTER);

		return boton;
	}

}
