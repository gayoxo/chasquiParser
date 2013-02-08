package chaqui.parser.coleccion.atributos.categoria.metadatos.Taxonomias;

import java.sql.ResultSet;
import java.sql.SQLException;

import chaqui.parser.coleccion.atributos.ExtendTextAttribute;
import chaqui.parser.coleccion.atributos.categoria.metadatos.catalogo.Atributos_metadatos_Categoria_Catalogos_Catalogo_ExtendControlledAttribute;
import chaqui.server.msqlconection.MySQLConnection;
import chasqui.model.collection.attribute.Attribute;

public class Atributos_metadatos_Categoria_Taxonomias_ExtendTextAttribute
		extends ExtendTextAttribute {

	public Atributos_metadatos_Categoria_Taxonomias_ExtendTextAttribute(
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
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct idov,num_ruta FROM chasqui2.metadatos WHERE (ruta= '/manifest/metadata/lom/classification/taxonpath/source/langstring' AND contenido='sección/sección');");
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
						Atributos_metadatos_Categoria_Taxonomias_Taxonomia_ExtendTextAttribute ATCUnidades=new Atributos_metadatos_Categoria_Taxonomias_Taxonomia_ExtendTextAttribute(contenido, true, this);
						ATCUnidades=(Atributos_metadatos_Categoria_Taxonomias_Taxonomia_ExtendTextAttribute) addAtributos(ATCUnidades);
						ATCUnidades.Process(idov);

						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
