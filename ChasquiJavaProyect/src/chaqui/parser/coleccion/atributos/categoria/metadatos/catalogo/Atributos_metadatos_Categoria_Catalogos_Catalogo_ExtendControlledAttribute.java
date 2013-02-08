package chaqui.parser.coleccion.atributos.categoria.metadatos.catalogo;

import java.sql.ResultSet;
import java.sql.SQLException;

import chaqui.parser.coleccion.atributos.ExtendControlledAttribute;
import chaqui.parser.coleccion.atributos.ExtendTerm;
import chaqui.server.msqlconection.MySQLConnection;
import chasqui.model.collection.attribute.Attribute;

public class Atributos_metadatos_Categoria_Catalogos_Catalogo_ExtendControlledAttribute
		extends ExtendControlledAttribute {

	public Atributos_metadatos_Categoria_Catalogos_Catalogo_ExtendControlledAttribute(
			String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void Process() {
		process_Vocabulary();

	}

	@Override
	protected void process_Vocabulary() {

		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct idov,num_ruta FROM chasqui2.metadatos WHERE ruta='/manifest/metadata/lom/general/catalogentry/catalog' AND contenido='"+name+"' ORDER BY contenido;");
			if (rs!=null) 
			{
				while (rs.next()) {
					String Dato=rs.getObject("idov").toString();
					String DatoNumRuta=rs.getObject("num_ruta").toString();
					if (Dato!=null&&DatoNumRuta!=null&&!Dato.isEmpty()&&!DatoNumRuta.isEmpty())
						{
						processCatalogForIdov(Dato,DatoNumRuta);
						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	private void processCatalogForIdov(String idov, String datoNumRuta) {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct contenido,num_ruta FROM chasqui2.metadatos WHERE ruta='/manifest/metadata/lom/general/catalogentry/entry/langstring' AND idov='"+idov+"' ORDER BY num_ruta;");
			if (rs!=null) 
			{
				while (rs.next()) {
					String Dato=rs.getObject("num_ruta").toString();
					String Contenido=rs.getObject("contenido").toString();
					String[] DatosRutaCatalogo=datoNumRuta.split("\\.");
					String[] DatosRutaEntry=Dato.split("\\.");
					if (Dato!=null&&Contenido!=null&&!Contenido.isEmpty()&&!Dato.isEmpty()&&DatosRutaCatalogo[4].equals(DatosRutaEntry[4]))
						{
						ExtendTerm TerminoCandidato=new ExtendTerm(Contenido);
						addTerm(TerminoCandidato);
						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
