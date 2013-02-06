package chaqui.parser.collection.attribute;

import java.sql.ResultSet;
import java.sql.SQLException;

import chaqui.server.msqlconection.MySQLConnection;
import chasqui.model.collection.attribute.Attribute;

public class Atributos_texto_y_numerico_Categoria_ExtendTextAttribute extends ExtendTextAttribute{

public enum Tabla {ATRIBUTOS_NUMERICOS,ATRIBUTOS_TEXTO};

	public Atributos_texto_y_numerico_Categoria_ExtendTextAttribute(String name, boolean browseable,
			Attribute father) {
		super(name, browseable, father);

		
	}


	private void prosessSonsTexto() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct nom_atrib FROM chasqui2.atributos_texto WHERE categoria='" + name +"' ORDER BY nom_atrib;");
			if (rs!=null) 
			{
				while (rs.next()) {
					String Dato=rs.getObject("nom_atrib").toString();
					if (Dato!=null&&!Dato.isEmpty())
						{
						Atributos_texto_Categoria_Tipo_ExtendTextAttribute ATCUnidades=new Atributos_texto_Categoria_Tipo_ExtendTextAttribute(Dato, true, this);
						ATCUnidades=(Atributos_texto_Categoria_Tipo_ExtendTextAttribute) addAtributos(ATCUnidades);
						ATCUnidades.Process();

						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void prosessSonsNumericos() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct nom_atrib FROM chasqui2.atributos_numericos WHERE categoria='" + name +"' ORDER BY nom_atrib;");
			if (rs!=null) 
			{
				while (rs.next()) {
					String Dato=rs.getObject("nom_atrib").toString();
					if (Dato!=null&&!Dato.isEmpty())
						{
						Atributos_numericos_Categoria_Tipo_ExtendNumericAttribute ATCUnidades=new Atributos_numericos_Categoria_Tipo_ExtendNumericAttribute(Dato, true, this);
						ATCUnidades=(Atributos_numericos_Categoria_Tipo_ExtendNumericAttribute) addAtributos(ATCUnidades);
						ATCUnidades.Process();

						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void Process() {
		
		System.err.println("Error, Necesario mas parametros.");
		
		
	}
	
	public void Process(Tabla tabla)
	{
		if (tabla==Tabla.ATRIBUTOS_TEXTO)
			prosessSonsTexto();
		else prosessSonsNumericos();
	}
}
