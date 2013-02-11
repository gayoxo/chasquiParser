package chasqui.parser.coleccion.atributos.categoria;

import java.sql.ResultSet;
import java.sql.SQLException;

import chasqui.model.collection.attribute.Attribute;
import chasqui.parser.coleccion.atributos.ExtendAttribute;
import chasqui.parser.coleccion.atributos.categoria.numericos.Atributos_numericos_Categoria_Tipo_ExtendNumericAttribute;
import chasqui.parser.coleccion.atributos.categoria.texto.Atributos_texto_Categoria_Tipo_ExtendControlledAttribute;
import chasqui.server.msqlconection.MySQLConnection;

public class Atributos_texto_y_numerico_Categoria_ExtendAttribute extends ExtendAttribute{

public enum Tabla {ATRIBUTOS_NUMERICOS,ATRIBUTOS_TEXTO};

	public Atributos_texto_y_numerico_Categoria_ExtendAttribute(String name, boolean browseable,
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
						Atributos_texto_Categoria_Tipo_ExtendControlledAttribute ATCUnidades=new Atributos_texto_Categoria_Tipo_ExtendControlledAttribute(Dato, true, this);
						Sons.add(ATCUnidades);
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
						Sons.add(ATCUnidades);
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
	
	public String toString(String prefix) {
		return prefix + 
		"TextAttribute (Categoria: " + name + ")(Browseable: " + Browseable + ") \n"+processSons(prefix+"...");
		
	}
}
