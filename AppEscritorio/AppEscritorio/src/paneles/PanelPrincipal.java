package paneles;

import controladores.ControladorAnimales;
import controladores.ControladorPantallaPrincipal;
import controladores.ControladorVisitas;
import interfaz.MUsker;

import java.awt.*;

import javax.swing.*;

public class PanelPrincipal extends JScrollPane{
	private static final Color COLOROSCURO = new Color(118,185,71);
	private static final Color COLORFONDO = new Color(177,216,183);
	ControladorPantallaPrincipal controladorPantallaPrincipal;
	PanelMenu menu;
	ControladorAnimales controlador;
	ControladorVisitas controladorVisitas;
	public PanelPrincipal(MUsker mUsker) {
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setBackground(Color.white);
		this.setBorder(null);
		this.getVerticalScrollBar().setUnitIncrement(20);
		this.controlador=new ControladorAnimales(mUsker);
		this.controladorVisitas=new ControladorVisitas(mUsker);
		this.controladorPantallaPrincipal =new ControladorPantallaPrincipal(this, mUsker, controlador, controladorVisitas);
		this.menu=new PanelMenu(controladorPantallaPrincipal);

		this.setViewportView(menu);
		this.setBackground(Color.white);
	}




}
