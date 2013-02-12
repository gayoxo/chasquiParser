package chasqui.parser.coleccion.atributos.categoria.metadatos.taxonomias;

import java.sql.ResultSet;
import java.sql.SQLException;

import chasqui.model.collection.attribute.Attribute;
import chasqui.parser.coleccion.atributos.ExtendAttribute;
import chasqui.parser.coleccion.atributos.ExtendDigitalObject;
import chasqui.parser.coleccion.atributos.categoria.metadatos.catalogo.Atributos_metadatos_Categoria_Catalogos_Catalogo_ExtendControlledAttribute;
import chasqui.server.msqlconection.MySQLConnection;

public class Atributos_metadatos_Categoria_Taxonomias_ExtendAttribute
		extends ExtendAttribute {

	public Atributos_metadatos_Categoria_Taxonomias_ExtendAttribute(
			String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Process() {
		processAllCategorias();

	}

	private void processAllCategorias() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct idov,num_ruta FROM chasqui2.metadatos WHERE (ruta= '/manifest/metadata/lom/classification/taxonpath/source/langstring' AND contenido='Sección/Sección');");
			if (rs!=null) 
			{
				while (rs.next()) {
					String idov=rs.getObject("idov").toString();
					String num_ruta=rs.getObject("num_ruta").toString();
					if (idov!=null&&num_ruta!=null&&!num_ruta.isEmpty()&&!idov.isEmpty())
						{
						findTaxonomy(idov,num_ruta);

						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void findTaxonomy(String idov, String num_ruta) {
		//SELECT distinct idov,ruta,contenido,num_ruta FROM chasqui2.metadatos WHERE (ruta= '/manifest/metadata/lom/classification/taxonpath/taxon/entry/langstring' AND idov='836');
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct contenido,num_ruta FROM chasqui2.metadatos WHERE (ruta= '/manifest/metadata/lom/classification/taxonpath/taxon/entry/langstring' AND idov='"+idov+"');");
			if (rs!=null) 
			{
				while (rs.next()) {
					String contenido=rs.getObject("contenido").toString();
					String num_rutaValid=rs.getObject("num_ruta").toString();
					String[] DatosRutaSection=num_ruta.split("\\.");
					String[] DatosRutaEntry=num_rutaValid.split("\\.");
					if (contenido!=null&&num_rutaValid!=null&&!num_rutaValid.isEmpty()&&!contenido.isEmpty()&&DatosRutaSection[4].equals(DatosRutaEntry[4]))
						{
						Atributos_metadatos_Categoria_Taxonomias_Taxonomia_ExtendAttribute ATCUnidades=new Atributos_metadatos_Categoria_Taxonomias_Taxonomia_ExtendAttribute(contenido, true, this,idov);
						ATCUnidades=(Atributos_metadatos_Categoria_Taxonomias_Taxonomia_ExtendAttribute) addAtributos(ATCUnidades);
						ATCUnidades.setIdov(idov);
						ATCUnidades.Process();
						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
