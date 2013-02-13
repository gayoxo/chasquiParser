package chasqui.parser.coleccion.atributos.categoria.numericos;

import java.sql.ResultSet;
import java.sql.SQLException;

import chasqui.client.main.Escritor;
import chasqui.model.collection.attribute.Attribute;
import chasqui.parser.coleccion.atributos.ExtendNumericAttribute;
import chasqui.parser.coleccion.atributos.categoria.texto.Atributos_texto_Categoria_Tipo_ExtendControlledAttribute;
import chasqui.parser.coleccion.intanciasatributos.ExtendNumericAttributeInstance;
import chasqui.parser.coleccion.intanciasatributos.ExtendTextAttributeInstance;
import chasqui.parser.coleccion.objetosdigitales.ExtendDigitalObject;
import chasqui.server.msqlconection.MySQLConnection;

public class Atributos_numericos_Categoria_Tipo_ExtendNumericAttribute extends
ExtendNumericAttribute {

	public Atributos_numericos_Categoria_Tipo_ExtendNumericAttribute(
			String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
	}



	@Override
	public void Process() {
		Atributos_numericos_Categoria_Unidades_ExtendControlledAttribute ATCTU=new Atributos_numericos_Categoria_Unidades_ExtendControlledAttribute("Unidades", false, this);
		ATCTU=(Atributos_numericos_Categoria_Unidades_ExtendControlledAttribute) addAtributos(ATCTU);
		process_AtributeInstances();
		ATCTU.Process();
		
		
	}



	private void process_AtributeInstances() {
		try {
			
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT * FROM chasqui2.atributos_numericos WHERE categoria='" + Father.getName() +"' AND nom_atrib='" + name + "' ORDER BY idov;");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					String idov=rs.getObject("idov").toString();
					Object temp=rs.getObject("valor");
					String Valor="";
					if (temp!=null)
						Valor=temp.toString();
					if (idov!=null&&!idov.isEmpty())
						{
						ExtendDigitalObject DObject= Escritor.getChasqui().getDigitalObject(Integer.parseInt(idov));
						DObject.getSons().add(new ExtendNumericAttributeInstance(this, pathFather(),Float.parseFloat(Valor),DObject ));
						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
}
