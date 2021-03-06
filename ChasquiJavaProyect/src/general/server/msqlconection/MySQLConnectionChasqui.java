package general.server.msqlconection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQLConnectionChasqui {
	
	
	public enum DB {DBaseServer,DBaseLocal};
	
	private static MySQLConnectionChasqui instance;
	private Connection conexion;
	private static final String DBaseServer="jdbc:mysql://a-note.fdi.ucm.es:3306/chasqui2";
	private static final String DBaseLocal="jdbc:mysql://localhost:3306/chasqui2";
	
	private static String DBSelected;
	
	private static final String DriverDatabase="com.mysql.jdbc.Driver";
	private static final String ErrorMySQLConnection="Error en driver de conexion al mySQL";
	private static final String ErrorCOnexionDB="Error en conexion a base de datos";
	private static final String QInsertaDatabase="Inserta la DB a conectarte para origen de datos chasqui:";
	private static final String QOption1="1: DBServer horchata.fdi.ucm.es";
	private static final String QOption2="2: DBLocal  localhost";
	private static final String ErrorUpdate="Error ejecutando Update Querry: ";
	private static final String ErrorSelect="Error ejecutando Querry: ";
	
	private MySQLConnectionChasqui(){
		try {
			Class.forName(DriverDatabase);
			paticionDeDatos(); 
		} catch (ClassNotFoundException e) {
			System.err.println(ErrorMySQLConnection);
			e.printStackTrace();
			System.exit(1);
		} catch (SQLException e) {
			System.err.println(ErrorCOnexionDB);
			e.printStackTrace();
			System.exit(1);
		}
		
	}

	public MySQLConnectionChasqui(DB db) {
		try {
			Class.forName(DriverDatabase);
			InicializacionSinPeticionDedatos(db); 
		} catch (ClassNotFoundException e) {
			System.err.println(ErrorMySQLConnection);
			e.printStackTrace();
			System.exit(1);
		} catch (SQLException e) {
			System.err.println(ErrorCOnexionDB);
			e.printStackTrace();
			System.exit(1);
		}
	}

	public MySQLConnectionChasqui(String dbNameIP,int Port, String user, String password) {
		try {
			Class.forName(DriverDatabase);
			InicializacionAnonima(dbNameIP,Port,user,password); 
		} catch (ClassNotFoundException e) {
			System.err.println(ErrorMySQLConnection);
			e.printStackTrace();
			System.exit(1);
		} catch (SQLException e) {
			System.err.println(ErrorCOnexionDB);
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void InicializacionAnonima(String dbNameIP, int port, String user, String password) throws SQLException {
		String[] Separacion= dbNameIP.split("////");
		if (Separacion.length<2) throw new SQLException("dbName Incorrect");
		StringBuffer SB=new StringBuffer();
		for (int i = 0; i < Separacion.length-1; i++) {
			SB.append(Separacion[i]);
		}
		conexion = DriverManager.getConnection("jdbc:mysql://"+SB.toString()+":"+port+"/"+Separacion[Separacion.length-1], user, password);	
		DBSelected=DBaseServer;
		
	}

	private void InicializacionSinPeticionDedatos(DB db) throws SQLException {
			switch (db) {
			case DBaseServer:
				conexion = DriverManager.getConnection(DBaseServer, "ilsaserver", "platano");	
				DBSelected=DBaseServer;
				break;
			case DBaseLocal:
				DBSelected=DBaseLocal;
				conexion = DriverManager.getConnection(DBaseLocal, "ilsaserver", "platano");	
				break;
			}
			
		
	}

	private void paticionDeDatos() throws SQLException {
		try {
		System.out.println(QInsertaDatabase);
		System.out.println(QOption1);
		System.out.println(QOption2);
		BufferedReader leerEntrada = new BufferedReader(new InputStreamReader(System.in));
		String salida = leerEntrada.readLine();
		switch (salida) {
		case "1":
			conexion = DriverManager.getConnection(DBaseServer, "ilsaserver", "platano");	
			DBSelected=DBaseServer;
			break;
		case "2":
			DBSelected=DBaseLocal;
			conexion = DriverManager.getConnection(DBaseLocal, "ilsaserver", "platano");	
			break;
		default:
			System.err.println("Error Procesando Seleccion, pruebe de nuevo");
			paticionDeDatos();
			break;
		}
		} catch (IOException e) {
			System.err.println("Error Procesando Seleccion, pruebe de nuevo");
			paticionDeDatos();
		}
	}
	
	
	public static MySQLConnectionChasqui getInstance()
	{
		if (instance==null)
			instance=new MySQLConnectionChasqui();
		return instance;
	}
	
	public static MySQLConnectionChasqui getInstance(DB db)
	{
		if (instance==null)
			instance=new MySQLConnectionChasqui(db);
		return instance;
	}
	
	public static MySQLConnectionChasqui getInstance(String dbNameIP,int port, String user, String password) {
		if (instance==null)
			instance=new MySQLConnectionChasqui(dbNameIP,port,user,password);
		return instance;	
	}
	
	public static void RunQuerryUPDATE(String querry)
	{		
		try {
			Statement st = instance.conexion.createStatement();
			st.executeUpdate(querry);
		} catch (SQLException e) {
			System.err.println(ErrorUpdate + querry);
			e.printStackTrace();
		}
	}
	
	public static ResultSet RunQuerrySELECT(String querry)
	{		
		try {
			Statement st = instance.conexion.createStatement();
			ResultSet rs = st.executeQuery(querry);
			return rs;
		} catch (SQLException e) {
			System.err.println(ErrorSelect + querry);
			e.printStackTrace();
			return null;
		}
	}
	 
public static String getDBSelected() {
	return DBSelected;
}


}
