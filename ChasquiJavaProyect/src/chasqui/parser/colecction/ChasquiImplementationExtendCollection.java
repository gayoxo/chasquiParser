package chasqui.parser.colecction;

import java.sql.ResultSet;
import java.sql.SQLException;

import chasqui.client.main.Start;
import chasqui.model.collection.attribute.TextAttribute;
import chasqui.parser.coleccion.atributos.categoria.Atributos_metadatos_Categoria_ExtendTextAttribute;
import chasqui.parser.coleccion.atributos.categoria.Atributos_texto_y_numerico_Categoria_ExtendTextAttribute;
import chasqui.parser.coleccion.atributos.categoria.Atributos_texto_y_numerico_Categoria_ExtendTextAttribute.Tabla;
import chasqui.server.msqlconection.MySQLConnection;

public class ChasquiImplementationExtendCollection extends ExtendCollection{

	private static final String CATEGORIAS_VACIAS = " Existen filas con categorias vacias";

	public ChasquiImplementationExtendCollection() {
		super();
		
	}
@Override
public String toString() {
	return super.toString("");
}

	@Override
	public void Process() {
		process_atributos_numericos();
		process_atributos_texto();
		process_atributos_metadatos();
		
	}
	private void process_atributos_texto() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct categoria FROM chasqui2.atributos_texto ORDER BY categoria;");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					String Dato=rs.getObject("categoria").toString();
					if (Dato!=null&&!Dato.isEmpty())
						{
						Atributos_texto_y_numerico_Categoria_ExtendTextAttribute ATCategoria=new Atributos_texto_y_numerico_Categoria_ExtendTextAttribute(Dato,true,null);
						ATCategoria=(Atributos_texto_y_numerico_Categoria_ExtendTextAttribute)((TextAttribute) addAtributos(ATCategoria));
						ATCategoria.Process(Tabla.ATRIBUTOS_TEXTO);
						
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
						Atributos_texto_y_numerico_Categoria_ExtendTextAttribute ANCategoria=new Atributos_texto_y_numerico_Categoria_ExtendTextAttribute(Dato,true,null);
						ANCategoria=(Atributos_texto_y_numerico_Categoria_ExtendTextAttribute) ((TextAttribute) addAtributos(ANCategoria));
						ANCategoria.Process(Tabla.ATRIBUTOS_NUMERICOS);
						
						}
					else System.out.println(Start.WARNING + CATEGORIAS_VACIAS);
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private void process_atributos_metadatos() {
		Atributos_metadatos_Categoria_ExtendTextAttribute AMCategoria = new Atributos_metadatos_Categoria_ExtendTextAttribute("Metadatos",true,null);
		AMCategoria.Process();
		addAtributos(AMCategoria);
		
	}
}
