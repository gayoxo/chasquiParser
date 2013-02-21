package chasqui.parser.coleccion.atributos.categoria.metadatos;

import general.client.main.ChasquiToFIle;
import general.server.msqlconection.MySQLConnectionChasqui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import shared.model.collection.attribute.Attribute;
import shared.model.collection.attribute.controlled.Term;

import chasqui.parser.ChasquiParseElement;
import chasqui.parser.coleccion.atributos.ExtendAttribute;
import chasqui.parser.coleccion.atributos.ExtendTerm;
import chasqui.parser.coleccion.atributos.ExtendTextAttribute;
import chasqui.parser.coleccion.intanciasatributos.ExtendAttributeInstance;
import chasqui.parser.coleccion.intanciasatributos.ExtendControlledAttributeInstance;
import chasqui.parser.coleccion.intanciasatributos.ExtendDataAttributeInstance;
import chasqui.parser.coleccion.intanciasatributos.ExtendTextAttributeInstance;
import chasqui.parser.coleccion.objetosdigitales.ExtendDigitalObject;

public class Atributos_metadatos_Categoria_Contribucion_ExtendAttribute
		extends ExtendAttribute {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4682508839089329463L;
	private Atributos_metadatos_Categoria_Contribucion_Fecha_ExtendDateAttribute AMFecha;
	private Atributos_metadatos_Categoria_Contribucion_Autor_ExtendTextAttribute AMAutor;
	private Atributos_metadatos_Categoria_Contribucion_Papel_ExtendControlledAttribute AMpapel;



	public Atributos_metadatos_Categoria_Contribucion_ExtendAttribute(
			String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
	}

	
	
	@Override
	public void Process() {

		AMAutor=new Atributos_metadatos_Categoria_Contribucion_Autor_ExtendTextAttribute("Autor", true, this);
		Sons.add(AMAutor);
		AMAutor.Process();
		AMpapel=new Atributos_metadatos_Categoria_Contribucion_Papel_ExtendControlledAttribute("Papel", true, this);
		Sons.add(AMpapel);
		AMpapel.Process();
		AMFecha=new Atributos_metadatos_Categoria_Contribucion_Fecha_ExtendDateAttribute("Fecha", true, this);
		Sons.add(AMFecha);
		AMFecha.Process();
		process_AtributeInstances();
		}
	
	private void process_AtributeInstances() {
		try {
			ResultSet rs=MySQLConnectionChasqui.RunQuerrySELECT("SELECT distinct idov,num_ruta FROM chasqui2.metadatos WHERE ruta='/manifest/metadata/lom/lifecycle/contribute/role/source/langstring';");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					String idov=rs.getObject("idov").toString();
					String num_ruta=rs.getObject("num_ruta").toString();
					if (idov!=null&&!idov.isEmpty())
						{
						procesaContributor(idov,num_ruta);
						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}



	private void procesaContributor(String idov, String num_ruta) {
		
		ExtendDigitalObject DObject= ChasquiToFIle.getChasqui().getDigitalObject(Integer.parseInt(idov));
		ExtendAttributeInstance EAI = new ExtendAttributeInstance(this.getFather(), ((ExtendAttribute) this.getFather()).pathFather(),DObject,null);
		EAI=(ExtendAttributeInstance) DObject.saveAtributo(EAI);
		ExtendAttributeInstance EAIContributor = new ExtendAttributeInstance(this, pathFather(),DObject,EAI);
		//Le añado a pelo todos son iguales y distintas contribuciones
		DObject.getSons().add((EAIContributor));
		try {
			ResultSet rs=MySQLConnectionChasqui.RunQuerrySELECT("SELECT * FROM chasqui2.metadatos WHERE ("+
					"ruta='/manifest/metadata/lom/lifecycle/contribute/role/value/langstring' OR "+
					"ruta='/manifest/metadata/lom/lifecycle/contribute/centity/vcard' OR "+
					"ruta='/manifest/metadata/lom/lifecycle/contribute/date/datetime') AND idov="+idov );
			if (rs!=null) 
			{
				while (rs.next()) {
					String contenido=rs.getObject("contenido").toString();
					String num_rutaValid=rs.getObject("num_ruta").toString();
					String ruta=rs.getObject("ruta").toString();
					String[] DatosRutaSection=num_ruta.split("\\.");
					String[] DatosRutaEntry=num_rutaValid.split("\\.");
					if (contenido!=null&&num_rutaValid!=null&&!num_rutaValid.isEmpty()&&!contenido.isEmpty()&&DatosRutaSection[4].equals(DatosRutaEntry[4]))
						{
						if (ruta.equals("/manifest/metadata/lom/lifecycle/contribute/centity/vcard"))
							{
							ExtendTextAttributeInstance ETAI=new ExtendTextAttributeInstance(AMAutor, AMAutor.pathFather(),DObject,EAIContributor,contenido);
							ETAI=(ExtendTextAttributeInstance) DObject.saveAtributo(ETAI);
							}
						if (ruta.equals("/manifest/metadata/lom/lifecycle/contribute/role/value/langstring"))
							{
							ExtendControlledAttributeInstance ECAI=new ExtendControlledAttributeInstance(AMpapel, AMpapel.pathFather(),DObject,EAIContributor,AMpapel.findTerm(contenido));
							ECAI=(ExtendControlledAttributeInstance) DObject.saveAtributo(ECAI);
							}
						if (ruta.equals("/manifest/metadata/lom/lifecycle/contribute/date/datetime"))
							{
							 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							if (!contenido.isEmpty())
								{
								Date D= formatter.parse(contenido);
								ExtendDataAttributeInstance EDAI=new ExtendDataAttributeInstance(AMFecha, AMFecha.pathFather(),DObject,EAIContributor,D);
								EDAI=(ExtendDataAttributeInstance) DObject.saveAtributo(EDAI);
							}
							}
						}
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println("Fecha incorrecta");
			e.printStackTrace();
		}
		
	}
	
	

}
