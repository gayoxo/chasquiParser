package chaqui.parser.collection;

import java.sql.ResultSet;
import java.sql.SQLException;

import chaqui.client.main.Start;
import chaqui.parser.ChasquiParseElement;
import chaqui.parser.collection.attribute.Tatributos_numericos_Ccategoria_TextAttribute;
import chaqui.parser.collection.attribute.Tatributos_texto_Ccategoria_TextAttribute;
import chaqui.server.msqlconection.MySQLConnection;
import chasqui.model.collection.Collection;
import chasqui.model.collection.attribute.Attribute;

public class ChasquiImplementationCollection extends Collection implements ChasquiParseElement{

	private static final String CATEGORIAS_VACIAS = " Existen filas con categorias vacias";

	public ChasquiImplementationCollection() {
		super();
		
	}
	
	private void process_atributos_texto() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct categoria FROM chasqui2.atributos_texto;");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					//Proceso categoria
					String Dato=rs.getObject("categoria").toString();
					if (Dato!=null&&!Dato.isEmpty())
						{
						Tatributos_texto_Ccategoria_TextAttribute ANCategoria=new Tatributos_texto_Ccategoria_TextAttribute(Dato,true,null);
						ANCategoria.Process();
						Atributos.add(ANCategoria);
						}
					else System.out.println(Start.WARNING + CATEGORIAS_VACIAS);
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void process_atributos_numericos() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct categoria FROM chasqui2.atributos_numericos;");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					//Proceso categoria
					String Dato=rs.getObject("categoria").toString();
					if (Dato!=null&&!Dato.isEmpty())
						{
						Tatributos_numericos_Ccategoria_TextAttribute ANCategoria=new Tatributos_numericos_Ccategoria_TextAttribute(Dato,true,null);
						ANCategoria.Process();
						Atributos.add(ANCategoria);
						}
					else System.out.println(Start.WARNING + CATEGORIAS_VACIAS);
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public String toString() {
		return toString("");
		
	}

	@Override
	public String toString(String prefix) {
		StringBuffer SB=new StringBuffer();
		for (Attribute hijos : Atributos) {
			SB.append(((ChasquiParseElement)hijos).toString("..."));
		}
		return SB.toString();
	}

	@Override
	public void Process() {
		process_atributos_numericos();
		process_atributos_texto();
		
	}
}
