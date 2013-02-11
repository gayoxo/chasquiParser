package chasqui.client.main.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JLabel;

import chasqui.client.main.window.thread.ThreadMio;
import chasqui.parser.coleccion.ChasquiImplementationExtendCollection;
import chasqui.server.msqlconection.MySQLConnection.DB;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Label;
import java.io.File;

public class VentanaDeInicio {

	
	private JFrame frmChasquiparser;
	private JTextField NameIPjTextField;
	private JTextPane SalidaJTextPane;
	private JTextField PortJTextField;
	private JTextField UserJTextField;
	private JTextField PasswordJTextField_1;
	private VentanaDeInicio Yo;
	private JButton dBLocalJButton;
	private JButton dBHorchataJButton;
	private JButton OtraDB;
	private JMenu menuAcciones;
	private File archivo_Abierto;
	private ChasquiImplementationExtendCollection Coleccion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaDeInicio window = new VentanaDeInicio();
					window.frmChasquiparser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaDeInicio() {
		initialize();
		Yo=this;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChasquiparser = new JFrame();
		frmChasquiparser.setTitle("ChasquiParser");
		frmChasquiparser.setBounds(100, 100, 924, 590);
		frmChasquiparser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChasquiparser.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JSplitPane GeneralJSplitPane = new JSplitPane();
		GeneralJSplitPane.setEnabled(false);
		frmChasquiparser.getContentPane().add(GeneralJSplitPane, BorderLayout.CENTER);
		
		JPanel DBSelectJPanel = new JPanel();
		GeneralJSplitPane.setLeftComponent(DBSelectJPanel);
		DBSelectJPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel InternoDBSelectJPanel = new JPanel();
		DBSelectJPanel.add(InternoDBSelectJPanel, BorderLayout.NORTH);
		InternoDBSelectJPanel.setLayout(new BoxLayout(InternoDBSelectJPanel, BoxLayout.Y_AXIS));
		
		dBLocalJButton = new JButton("localHost");
		dBLocalJButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ThreadMio T2=new ThreadMio(DB.DBaseLocal,Yo);
				T2.start();
				
			}
		});
		dBLocalJButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		InternoDBSelectJPanel.add(dBLocalJButton);
		
		dBHorchataJButton = new JButton("horchata.fdi.ucm.es");
		dBHorchataJButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ThreadMio T2=new ThreadMio(DB.DBaseServer,Yo);
				T2.start();
			}
		});
		dBHorchataJButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		InternoDBSelectJPanel.add(dBHorchataJButton);
		
		JPanel otrosJPanel = new JPanel();
		InternoDBSelectJPanel.add(otrosJPanel);
		otrosJPanel.setLayout(new BoxLayout(otrosJPanel, BoxLayout.Y_AXIS));
		
		JLabel otroLabel = new JLabel("Otra BD");
		otrosJPanel.add(otroLabel);
		
		JPanel NameIPPanelJPanel = new JPanel();
		otrosJPanel.add(NameIPPanelJPanel);
		NameIPPanelJPanel.setLayout(new BoxLayout(NameIPPanelJPanel, BoxLayout.X_AXIS));
		
		Label NameIPLabel = new Label("Name/IP");
		NameIPPanelJPanel.add(NameIPLabel);
		
		NameIPjTextField = new JTextField();
		NameIPjTextField.setText("Server/CHASQUIDB");
		NameIPPanelJPanel.add(NameIPjTextField);
		NameIPjTextField.setColumns(30);
		
		JPanel PortJPanel = new JPanel();
		otrosJPanel.add(PortJPanel);
		
		Label PortLabel = new Label("Port");
		PortJPanel.add(PortLabel);
		
		PortJTextField = new JTextField();
		PortJTextField.setText("3306");
		PortJPanel.add(PortJTextField);
		PortJTextField.setColumns(10);
		
		JPanel UserPasswordJPanel = new JPanel();
		otrosJPanel.add(UserPasswordJPanel);
		
		UserJTextField = new JTextField();
		UserJTextField.setText("User");
		UserPasswordJPanel.add(UserJTextField);
		UserJTextField.setColumns(10);
		
		PasswordJTextField_1 = new JTextField();
		PasswordJTextField_1.setText("Password");
		UserPasswordJPanel.add(PasswordJTextField_1);
		PasswordJTextField_1.setColumns(10);
		
		JPanel panel = new JPanel();
		otrosJPanel.add(panel);
		
		OtraDB = new JButton("Otra BD");
		panel.add(OtraDB);
		
		JScrollPane SalidaContenedorJScrollPane = new JScrollPane();
		GeneralJSplitPane.setRightComponent(SalidaContenedorJScrollPane);
		
		SalidaJTextPane = new JTextPane();
		SalidaJTextPane.setEditable(false);
		SalidaContenedorJScrollPane.setViewportView(SalidaJTextPane);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		frmChasquiparser.setJMenuBar(menuBar);
		
		menuAcciones = new JMenu("Acciones");
		menuBar.add(menuAcciones);
		
		JMenuItem itemCargar = new JMenuItem("Cargar");
		menuAcciones.add(itemCargar);
		
		JMenuItem itemSalvar = new JMenuItem("Salvar");
		itemSalvar.setEnabled(false);
		menuAcciones.add(itemSalvar);
		
		JMenu menuSalvarComo = new JMenu("Salvar como...");
		menuAcciones.add(menuSalvarComo);
		
		JMenuItem ItemDat = new JMenuItem(".chasqui");
		menuSalvarComo.add(ItemDat);
		
		JMenuItem mntmNewMenuItem = new JMenuItem(".txt");
		menuSalvarComo.add(mntmNewMenuItem);
	}
	
	public void disableButtons() {
		menuAcciones.setEnabled(false);
		dBHorchataJButton.setEnabled(false);
		dBLocalJButton.setEnabled(false);
		OtraDB.setEnabled(false);
		SalidaJTextPane.setEnabled(false);
	}
	
	public void enabledButtons() {
		menuAcciones.setEnabled(true);
		dBHorchataJButton.setEnabled(true);
		dBLocalJButton.setEnabled(true);
		OtraDB.setEnabled(true);
		SalidaJTextPane.setEnabled(true);
	}
	
	public JTextPane getSalidaJTextPane() {
		return SalidaJTextPane;
	}
	
public ChasquiImplementationExtendCollection getColeccion() {
	return Coleccion;
}

public void setColeccion(ChasquiImplementationExtendCollection coleccion) {
	Coleccion = coleccion;
}


}
