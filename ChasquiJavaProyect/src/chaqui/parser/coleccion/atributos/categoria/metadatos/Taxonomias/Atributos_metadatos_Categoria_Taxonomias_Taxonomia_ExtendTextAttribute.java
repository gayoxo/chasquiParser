package chaqui.parser.coleccion.atributos.categoria.metadatos.Taxonomias;

import java.sql.ResultSet;
import java.sql.SQLException;

import chaqui.parser.coleccion.atributos.ExtendTextAttribute;
import chaqui.parser.coleccion.atributos.categoria.metadatos.catalogo.Atributos_metadatos_Categoria_Catalogos_Catalogo_ExtendControlledAttribute;
import chaqui.server.msqlconection.MySQLConnection;
import chasqui.model.collection.attribute.Attribute;

public class Atributos_metadatos_Categoria_Taxonomias_Taxonomia_ExtendTextAttribute
		extends ExtendTextAttribute {

	public Atributos_metadatos_Categoria_Taxonomias_Taxonomia_ExtendTextAttribute(
			String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Process() {
		System.err.println("Error Entrada de parametros necesaria.");

	}

	public void Process(String idov) {
		processTaxons(idov);
		
	}

	private void processTaxons(String idov) {
		// SELECT distinct * FROM chasqui2.metadatos WHERE ruta='/manifest/metadata/lom/classification/taxonpath/source/langstring' AND contenido!='sección/sección' AND idov=1 ORDER BY num_ruta;
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct contenido,num_ruta FROM chasqui2.metadatos WHERE ruta='/manifest/metadata/lom/classification/taxonpath/source/langstring' AND contenido!='sección/sección' AND idov='"+idov+"' ORDER BY num_ruta;");
			if (rs!=null) 
			{
				while (rs.next()) {
					String Contenido=rs.getObject("contenido").toString();
					String num_ruta=rs.getObject("num_ruta").toString();
					if (Contenido!=null&&num_ruta!=null&&!num_ruta.isEmpty()&&!Contenido.isEmpty())
						{
						processContenido(Contenido,num_ruta,idov);
						

						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void processContenido(String contenido, String num_ruta, String idov) {
		//TODO necesito crear los nodos y luego que el nodo busque el valor con la ruta y el identificador
//		Atributos_metadatos_Categoria_Taxonomias_Taxonomia_Nodo_ExtendControlledAttribute ATCUnidades=new Atributos_metadatos_Categoria_Catalogos_Catalogo_ExtendControlledAttribute(Dato, true, this);
//		ATCUnidades=(Atributos_metadatos_Categoria_Catalogos_Catalogo_ExtendControlledAttribute) addAtributos(ATCUnidades);
//		ATCUnidades.Process();
		
	}
}
