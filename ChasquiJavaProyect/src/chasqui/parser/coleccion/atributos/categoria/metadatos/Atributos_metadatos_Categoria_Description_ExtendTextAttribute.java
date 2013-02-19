package chasqui.parser.coleccion.atributos.categoria.metadatos;

import java.sql.ResultSet;
import java.sql.SQLException;

import chasqui.client.main.Escritor;
import chasqui.model.collection.attribute.Attribute;
import chasqui.parser.ChasquiParseElement;
import chasqui.parser.coleccion.atributos.ExtendAttribute;
import chasqui.parser.coleccion.atributos.ExtendTextAttribute;
import chasqui.parser.coleccion.intanciasatributos.ExtendAttributeInstance;
import chasqui.parser.coleccion.intanciasatributos.ExtendTextAttributeInstance;
import chasqui.parser.coleccion.objetosdigitales.ExtendDigitalObject;
import chasqui.server.msqlconection.MySQLConnection;

public class Atributos_metadatos_Categoria_Description_ExtendTextAttribute extends ExtendTextAttribute implements ChasquiParseElement{


	/**
	 * 
	 */
	private static final long serialVersionUID = -5800433597359054807L;
	public Atributos_metadatos_Categoria_Description_ExtendTextAttribute(String name, boolean browseable,
			Attribute father) {
		super(name, browseable, father);

		
	}


	@Override
	public void Process() {
		process_AtributeInstances();
	}
	private void process_AtributeInstances() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT * FROM chasqui2.metadatos where ruta='/manifest/metadata/lom/general/description/langstring';");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					String idov=rs.getObject("idov").toString();
					Object temp=rs.getObject("contenido");
					String Valor="";
					if (temp!=null)
						Valor=temp.toString();
					if (idov!=null&&!idov.isEmpty())
						{
						ExtendDigitalObject DObject= Escritor.getChasqui().getDigitalObject(Integer.parseInt(idov));
						ExtendAttributeInstance EAI = new ExtendAttributeInstance(this.getFather(), ((ExtendAttribute) this.getFather()).pathFather(),DObject,null);
						EAI=(ExtendAttributeInstance) DObject.saveAtributo(EAI);
						ExtendTextAttributeInstance ETAI=new ExtendTextAttributeInstance(this, pathFather(),DObject,EAI,Valor);
						ETAI=(ExtendTextAttributeInstance) DObject.saveAtributo(ETAI);
						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
