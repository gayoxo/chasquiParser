package chasqui.parser.coleccion.atributos.categoria.metadatos.taxonomias;

import java.sql.ResultSet;
import java.sql.SQLException;

import chasqui.client.main.Escritor;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.ControlledAttribute;
import chasqui.model.collection.attribute.controlled.Term;
import chasqui.parser.coleccion.atributos.ExtendControlledAttribute;
import chasqui.parser.coleccion.atributos.ExtendTerm;
import chasqui.parser.coleccion.intanciasatributos.ExtendControlledAttributeInstance;
import chasqui.parser.coleccion.objetosdigitales.ExtendDigitalObject;
import chasqui.server.msqlconection.MySQLConnection;

public class Atributos_metadatos_Categoria_Taxonomias_Taxonomia_Nodo_ExtendControlledAttribute
		extends ExtendControlledAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2077419087634758554L;
	private String num_ruta;
	private String idov;

	public Atributos_metadatos_Categoria_Taxonomias_Taxonomia_Nodo_ExtendControlledAttribute(
			String name, boolean browseable, Attribute father,String num_ruta, String idov) {
		super(name, browseable, father);
		this.num_ruta=num_ruta;
		this.idov=idov;
	}

	@Override
	public void Process() {
		process_Vocabulary();		
	}

	@Override
	protected void process_Vocabulary() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct contenido,num_ruta FROM chasqui2.metadatos WHERE (ruta= '/manifest/metadata/lom/classification/taxonpath/taxon/entry/langstring' AND idov='"+idov+"') ORDER BY contenido;");
			if (rs!=null) 
			{
				while (rs.next()) {
					String contenido=rs.getObject("contenido").toString();
					String num_rutaValid=rs.getObject("num_ruta").toString();
					String[] DatosRutaSection=num_ruta.split("\\.");
					String[] DatosRutaEntry=num_rutaValid.split("\\.");
					if (contenido!=null&&num_rutaValid!=null&&!num_rutaValid.isEmpty()&&!contenido.isEmpty()&&DatosRutaSection[4].equals(DatosRutaEntry[4]))
						{
						ExtendTerm TerminoCandidato=new ExtendTerm(contenido);
//						Term T=
								addTerm(TerminoCandidato);
//						
//						//RARO MUY ABAJO
//						ExtendDigitalObject DObject= Escritor.getChasqui().getDigitalObject(Integer.parseInt(idov));
//						DObject.getSons().add(new ExtendControlledAttributeInstance(this, pathFather(),T,DObject ));
						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	


	public void setIdov(String idov) {
		this.idov = idov;
	}
	
	public void setNum_ruta(String num_ruta) {
		this.num_ruta = num_ruta;
	}
}
