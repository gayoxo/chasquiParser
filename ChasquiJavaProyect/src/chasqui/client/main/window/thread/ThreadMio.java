package chasqui.client.main.window.thread;

import javax.swing.JTextPane;

import chasqui.client.main.Escritor;
import chasqui.client.main.window.VentanaDeInicio;
import chasqui.server.msqlconection.MySQLConnection.DB;

public class ThreadMio extends Thread {

	protected static final String PROCESANDO = "Procesando....";
	private VentanaDeInicio ventana;
	private DB dbaselocal;
	
	public ThreadMio(DB dbaselocal2, VentanaDeInicio ventana) {
		this.dbaselocal=dbaselocal2;
		this.ventana=ventana;
	}
	
	
	@Override
	public void run() {
		ventana.disableButtons();
		ventana.getSalidaJTextPane().setText(PROCESANDO);
		String S=Escritor.Ecritor(dbaselocal);
		ventana.getSalidaJTextPane().setText(S);
		ventana.setColeccion(Escritor.getChasqui());
		ventana.enabledButtons();
	}

	
	
	
}
