package chasqui.parser.coleccion.atributos.categoria.metadatos.taxonomias;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

import chasqui.model.collection.attribute.Attribute;
import chasqui.parser.coleccion.atributos.ExtendAttribute;
import chasqui.parser.coleccion.atributos.ExtendDigitalObject;
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
			String NodoNew=recorrio.pop();
			Atributos_metadatos_Categoria_Taxonomias_Taxonomia_Nodo_ExtendControlledAttribute ATCUnidades=new Atributos_metadatos_Categoria_Taxonomias_Taxonomia_Nodo_ExtendControlledAttribute(NodoNew, true, this,num_ruta,idov);
			ATCUnidades=(Atributos_metadatos_Categoria_Taxonomias_Taxonomia_Nodo_ExtendControlledAttribute) addAtributos(ATCUnidades);
			CreacionDeNodos(recorrio,num_ruta,idov,ATCUnidades);
			}
		
	}

	private void CreacionDeNodos(
			Stack<String> recorrio,
			String num_ruta,
			String idov2,
			Atributos_metadatos_Categoria_Taxonomias_Taxonomia_Nodo_ExtendControlledAttribute padre) {
		if (!recorrio.isEmpty())
		{
		String NodoNew=recorrio.pop();
		Atributos_metadatos_Categoria_Taxonomias_Taxonomia_Nodo_ExtendControlledAttribute ATCUnidades=new Atributos_metadatos_Categoria_Taxonomias_Taxonomia_Nodo_ExtendControlledAttribute(NodoNew, true, padre,num_ruta,idov);
		ATCUnidades=(Atributos_metadatos_Categoria_Taxonomias_Taxonomia_Nodo_ExtendControlledAttribute) padre.addAtributos(ATCUnidades);
		CreacionDeNodos(recorrio,num_ruta,idov,ATCUnidades);
		}
		else {
			padre.setIdov(idov2);
			padre.setNum_ruta(num_ruta);
			padre.Process();
		}
		
	}
	
	public void setIdov(String idov) {
		this.idov = idov;
	}
	
	@Override
	public String toString(String prefix) {
		return prefix + 
		"Attribute (Taxonomia: " + name + ")(Browseable: " + Browseable + ") \n"+processSons(prefix+"...");
		
	}
}
