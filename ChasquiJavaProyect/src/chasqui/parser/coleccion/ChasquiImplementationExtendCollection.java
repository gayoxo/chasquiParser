package chasqui.parser.coleccion;

import java.sql.ResultSet;
import java.sql.SQLException;

import chasqui.client.main.Escritor;
import chasqui.model.collection.attribute.Attribute;
import chasqui.parser.coleccion.atributos.categoria.Atributos_metadatos_Categoria_ExtendAttribute;
import chasqui.parser.coleccion.atributos.categoria.Atributos_texto_y_numerico_Categoria_ExtendAttribute;
import chasqui.parser.coleccion.atributos.categoria.Atributos_texto_y_numerico_Categoria_ExtendAttribute.Tabla;
import chasqui.server.msqlconection.MySQLConnection;

public class ChasquiImplementationExtendCollection extends ExtendCollection{

	private static final String CATEGORIAS_VACIAS = " Existen filas con categorias vacias";
	private static final String OBJETOSVIRTUALESERRONEOS = " Objetos virtuales erroneos";

	public ChasquiImplementationExtendCollection() {
		super();
		
	}
@Override
public String toString() {
	return super.toString("");
}

	@Override
	public void Process() {
		genera_Objetos_Digitales();
		process_atributos_numericos();
		process_atributos_texto();
		process_atributos_metadatos();
		
	}
	private void genera_Objetos_Digitales() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT * FROM chasqui2.objeto_virtual ORDER BY idov;");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					String Dato=rs.getObject("idov").toString();
					String Descripcion="";
					//rs.getObject("descripcion").toString();
					//String Usuario=rs.getObject("idov").toString();
					if (Dato!=null&&!Dato.isEmpty()&&Descripcion!=null)
						{
						ObjetoVirtualExtendDigitalObject OVEDO=new ObjetoVirtualExtendDigitalObject();
						
						
						}
					else System.out.println(Escritor.WARNING + OBJETOSVIRTUALESERRONEOS);
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
						Atributos_texto_y_numerico_Categoria_ExtendAttribute ATCategoria=new Atributos_texto_y_numerico_Categoria_ExtendAttribute(Dato,true,null);
						ATCategoria=(Atributos_texto_y_numerico_Categoria_ExtendAttribute)((Attribute) addAtributos(ATCategoria));
						ATCategoria.Process(Tabla.ATRIBUTOS_TEXTO);
						
						}
					else System.out.println(Escritor.WARNING + CATEGORIAS_VACIAS);
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
						Atributos_texto_y_numerico_Categoria_ExtendAttribute ANCategoria=new Atributos_texto_y_numerico_Categoria_ExtendAttribute(Dato,true,null);
						ANCategoria=(Atributos_texto_y_numerico_Categoria_ExtendAttribute) ((Attribute) addAtributos(ANCategoria));
						ANCategoria.Process(Tabla.ATRIBUTOS_NUMERICOS);
						
						}
					else System.out.println(Escritor.WARNING + CATEGORIAS_VACIAS);
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private void process_atributos_metadatos() {
		Atributos_metadatos_Categoria_ExtendAttribute AMCategoria = new Atributos_metadatos_Categoria_ExtendAttribute("Metadatos",true,null);
		AMCategoria.Process();
		addAtributos(AMCategoria);
		
	}
}
