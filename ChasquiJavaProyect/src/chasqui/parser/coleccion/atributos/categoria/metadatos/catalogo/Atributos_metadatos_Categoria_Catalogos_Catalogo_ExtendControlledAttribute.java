package chasqui.parser.coleccion.atributos.categoria.metadatos.catalogo;

import general.client.main.ChasquiToFIle;
import general.server.msqlconection.MySQLConnectionChasqui;

import java.sql.ResultSet;
import java.sql.SQLException;

import shared.model.collection.attribute.Attribute;
import shared.model.collection.attribute.controlled.Term;

import chasqui.parser.coleccion.atributos.ExtendAttribute;
import chasqui.parser.coleccion.atributos.ExtendControlledAttribute;
import chasqui.parser.coleccion.atributos.ExtendTerm;
import chasqui.parser.coleccion.intanciasatributos.ExtendAttributeInstance;
import chasqui.parser.coleccion.intanciasatributos.ExtendControlledAttributeInstance;
import chasqui.parser.coleccion.intanciasatributos.ExtendNoShowAttributeInstance;
import chasqui.parser.coleccion.objetosdigitales.ExtendDigitalObject;

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
		if (vocabulary.getList().isEmpty())
			Father.getSons().remove(this);
	}

	@Override
	protected void process_Vocabulary() {

		try {
			ResultSet rs=MySQLConnectionChasqui.RunQuerrySELECT("SELECT distinct idov,num_ruta FROM chasqui2.metadatos WHERE ruta='/manifest/metadata/lom/general/catalogentry/catalog' AND contenido='"+name+"' ORDER BY contenido;");
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
		ExtendDigitalObject DObject= ChasquiToFIle.getChasqui().getDigitalObject(Integer.parseInt(idov));
		ExtendAttributeInstance EAI = new ExtendAttributeInstance(this.getFather().getFather(), ((ExtendAttribute) this.getFather().getFather()).pathFather(),DObject,null);
		EAI=(ExtendAttributeInstance) DObject.saveAtributo(EAI);
		ExtendNoShowAttributeInstance EAICategoria = new ExtendNoShowAttributeInstance(this.getFather(), ((ExtendAttribute) this.getFather()).pathFather(),DObject,EAI);
		EAICategoria=(ExtendNoShowAttributeInstance) DObject.saveAtributo(EAICategoria);
		try {
			ResultSet rs=MySQLConnectionChasqui.RunQuerrySELECT("SELECT distinct contenido,num_ruta FROM chasqui2.metadatos WHERE ruta='/manifest/metadata/lom/general/catalogentry/entry/langstring' AND idov='"+idov+"' ORDER BY num_ruta;");
			if (rs!=null) 
			{
				while (rs.next()) {
					String Dato=rs.getObject("num_ruta").toString();
					//RARO MUY ABAJO
					Object temp=rs.getObject("contenido");
					String Contenido="";
					if (temp!=null)
						Contenido=temp.toString();
					String[] DatosRutaCatalogo=datoNumRuta.split("\\.");
					String[] DatosRutaEntry=Dato.split("\\.");
					if (Dato!=null&&Contenido!=null&&!Contenido.isEmpty()&&!Dato.isEmpty()&&DatosRutaCatalogo[4].equals(DatosRutaEntry[4]))
						{
						ExtendTerm TerminoCandidato=new ExtendTerm(Contenido);
						Term T=addTerm(TerminoCandidato);
						ExtendControlledAttributeInstance ECAI=new ExtendControlledAttributeInstance(this, pathFather(),DObject,EAICategoria,T );
						ECAI=(ExtendControlledAttributeInstance) DObject.saveAtributo(ECAI);
							
						
						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
