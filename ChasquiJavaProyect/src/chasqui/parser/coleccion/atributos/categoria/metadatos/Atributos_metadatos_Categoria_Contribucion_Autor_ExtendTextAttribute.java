package chasqui.parser.coleccion.atributos.categoria.metadatos;

import java.sql.ResultSet;
import java.sql.SQLException;

import chasqui.client.main.Escritor;
import chasqui.model.collection.attribute.Attribute;
import chasqui.parser.coleccion.atributos.ExtendTextAttribute;
import chasqui.parser.coleccion.intanciasatributos.ExtendTextAttributeInstance;
import chasqui.parser.coleccion.objetosdigitales.ExtendDigitalObject;
import chasqui.server.msqlconection.MySQLConnection;

public class Atributos_metadatos_Categoria_Contribucion_Autor_ExtendTextAttribute
		extends ExtendTextAttribute {

	public Atributos_metadatos_Categoria_Contribucion_Autor_ExtendTextAttribute(
			String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
	}

	@Override
	public void Process() {
		process_AtributeInstances();
		
	}
	private void process_AtributeInstances() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT * FROM chasqui2.metadatos where ruta='/manifest/metadata/lom/lifecycle/contribute/centity/vcard';");
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
						DObject.getSons().add(new ExtendTextAttributeInstance(this, pathFather(),Valor,DObject  ));
						//
						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
}
