package chasqui.parser.coleccion.atributos.categoria.metadatos.taxonomias;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

import chasqui.client.main.Escritor;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.controlled.Term;
import chasqui.parser.coleccion.atributos.ExtendAttribute;
import chasqui.parser.coleccion.atributos.ExtendTerm;
import chasqui.parser.coleccion.atributos.categoria.numericos.ExtendAttributeInstance;
import chasqui.parser.coleccion.intanciasatributos.ExtendControlledAttributeInstance;
import chasqui.parser.coleccion.objetosdigitales.ExtendDigitalObject;
import chasqui.server.msqlconection.MySQLConnection;

public class Atributos_metadatos_Categoria_Taxonomias_Taxonomia_ExtendAttribute
		extends ExtendAttribute {

	private String idov;

	public Atributos_metadatos_Categoria_Taxonomias_Taxonomia_ExtendAttribute(
			String name, boolean browseable, Attribute father,String idov) {
		super(name, browseable, father);
		this.idov=idov;
	}

	@Override
	public void Process() {
		processTaxons();

	}

	private void processTaxons() {
		try {
			//TODO Reparar cada vez que lo suba
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct contenido,num_ruta FROM chasqui2.metadatos WHERE ruta='/manifest/metadata/lom/classification/taxonpath/source/langstring' AND contenido!='Sección/Sección' AND idov='"+idov+"' ORDER BY num_ruta;");
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
		String[] Nodos=contenido.split("/");
		Stack<String> Recorrio=new Stack<String>();
		//Me como el 0 que es Sección
		for (int i = Nodos.length-1; i > 0; i--)
			Recorrio.push(Nodos[i]);
		CreacionDeNodos(Recorrio,num_ruta,idov);
		
	}

	private void CreacionDeNodos(Stack<String> recorrio, String num_ruta, String idov) {
		if (!recorrio.isEmpty())
			{
			ExtendDigitalObject DObject= Escritor.getChasqui().getDigitalObject(Integer.parseInt(idov));
			ExtendAttributeInstance EAIMetadatos = new ExtendAttributeInstance(this.getFather().getFather(), ((ExtendAttribute) this.getFather().getFather()).pathFather(),DObject,null);
			EAIMetadatos=(ExtendAttributeInstance) DObject.saveAtributo(EAIMetadatos);
			ExtendAttributeInstance EAITaxonSS = new ExtendAttributeInstance(this.getFather(), ((ExtendAttribute) this.getFather()).pathFather(),DObject,EAIMetadatos);
			EAITaxonSS=(ExtendAttributeInstance) DObject.saveAtributo(EAITaxonSS);
			ExtendAttributeInstance EAITaxon = new ExtendAttributeInstance(this,pathFather(),DObject,EAITaxonSS);
			EAITaxon=(ExtendAttributeInstance) DObject.saveAtributo(EAITaxon);
			String NodoNew=recorrio.pop();
			Atributos_metadatos_Categoria_Taxonomias_Taxonomia_Nodo_ExtendControlledAttribute ATCUnidades=new Atributos_metadatos_Categoria_Taxonomias_Taxonomia_Nodo_ExtendControlledAttribute(NodoNew, true, this,num_ruta,idov);
			ATCUnidades=(Atributos_metadatos_Categoria_Taxonomias_Taxonomia_Nodo_ExtendControlledAttribute) addAtributos(ATCUnidades);
			ExtendControlledAttributeInstance ECAI=new ExtendControlledAttributeInstance(ATCUnidades, ATCUnidades.pathFather(),DObject,EAITaxon,null);
			ECAI=(ExtendControlledAttributeInstance) DObject.saveAtributo(ECAI);
			CreacionDeNodos(recorrio,num_ruta,idov,ATCUnidades,ECAI);
			}
		
		
	}

	private void CreacionDeNodos(
			Stack<String> recorrio,
			String num_ruta,
			String idov2,
			Atributos_metadatos_Categoria_Taxonomias_Taxonomia_Nodo_ExtendControlledAttribute padre, ExtendControlledAttributeInstance eCAI) {
		if (!recorrio.isEmpty())
		{
		String NodoNew=recorrio.pop();
		ExtendDigitalObject DObject= Escritor.getChasqui().getDigitalObject(Integer.parseInt(idov));
		Atributos_metadatos_Categoria_Taxonomias_Taxonomia_Nodo_ExtendControlledAttribute ATCUnidades=new Atributos_metadatos_Categoria_Taxonomias_Taxonomia_Nodo_ExtendControlledAttribute(NodoNew, true, padre,num_ruta,idov);
		ATCUnidades=(Atributos_metadatos_Categoria_Taxonomias_Taxonomia_Nodo_ExtendControlledAttribute) padre.addAtributos(ATCUnidades);
		ExtendControlledAttributeInstance ECAI=new ExtendControlledAttributeInstance(ATCUnidades, ATCUnidades.pathFather(),DObject,eCAI,null);
		ECAI=(ExtendControlledAttributeInstance) DObject.saveAtributo(ECAI);
		CreacionDeNodos(recorrio,num_ruta,idov,ATCUnidades,ECAI);
		}
		else {
			
			padre.setIdov(idov2);
			padre.setNum_ruta(num_ruta);
			padre.Process();
			process_nodo(padre,idov2,num_ruta,eCAI);
		}
		
	}
	
	public void setIdov(String idov) {
		this.idov = idov;
	}
	
	protected void process_nodo(Atributos_metadatos_Categoria_Taxonomias_Taxonomia_Nodo_ExtendControlledAttribute padre, String idov2, String num_ruta, ExtendControlledAttributeInstance eCAI) {
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
						Term T=padre.findTerm(contenido);
						ExtendControlledAttributeInstance ECAI=new ExtendControlledAttributeInstance(padre, padre.pathFather(),eCAI.getDoDigitalObject(),eCAI.getFatherAtribute(),T);
						ECAI=(ExtendControlledAttributeInstance) ((ExtendDigitalObject)eCAI.getDoDigitalObject()).saveAtributo(ECAI);
						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
