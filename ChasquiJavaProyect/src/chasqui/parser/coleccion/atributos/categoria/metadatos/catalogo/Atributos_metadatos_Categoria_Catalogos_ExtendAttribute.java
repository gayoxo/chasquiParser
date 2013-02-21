package chasqui.parser.coleccion.atributos.categoria.metadatos.catalogo;

import general.server.msqlconection.MySQLConnectionChasqui;

import java.sql.ResultSet;
import java.sql.SQLException;

import shared.model.collection.attribute.Attribute;

import chasqui.parser.coleccion.atributos.ExtendAttribute;
import chasqui.parser.coleccion.objetosdigitales.ExtendDigitalObject;

public class Atributos_metadatos_Categoria_Catalogos_ExtendAttribute extends
		ExtendAttribute {

	public Atributos_metadatos_Categoria_Catalogos_ExtendAttribute(
			String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
	}

	@Override
	public void Process() {
		processAllCategorias();

	}

	private void processAllCategorias() {
		try {
			ResultSet rs=MySQLConnectionChasqui.RunQuerrySELECT("SELECT distinct contenido FROM chasqui2.metadatos WHERE ruta='/manifest/metadata/lom/general/catalogentry/catalog'  ORDER BY contenido;");
			if (rs!=null) 
			{
				while (rs.next()) {
					String Dato=rs.getObject("contenido").toString();
					if (Dato!=null&&!Dato.isEmpty())
						{
						Atributos_metadatos_Categoria_Catalogos_Catalogo_ExtendControlledAttribute ATCUnidades=new Atributos_metadatos_Categoria_Catalogos_Catalogo_ExtendControlledAttribute(Dato, true, this);
						ATCUnidades=(Atributos_metadatos_Categoria_Catalogos_Catalogo_ExtendControlledAttribute) addAtributos(ATCUnidades);
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
