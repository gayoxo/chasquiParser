package chasqui.parser.coleccion.atributos.categoria.metadatos;

import general.client.main.ChasquiToFIle;
import general.server.msqlconection.MySQLConnectionChasqui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import shared.model.collection.attribute.Attribute;

import chasqui.parser.coleccion.atributos.ExtendDateAttribute;
import chasqui.parser.coleccion.intanciasatributos.ExtendDataAttributeInstance;
import chasqui.parser.coleccion.intanciasatributos.ExtendTextAttributeInstance;
import chasqui.parser.coleccion.objetosdigitales.ExtendDigitalObject;

public class Atributos_metadatos_Categoria_Contribucion_Fecha_ExtendDateAttribute
		extends ExtendDateAttribute {

	public Atributos_metadatos_Categoria_Contribucion_Fecha_ExtendDateAttribute(
			String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
	}

	@Override
	public void Process() {
//		process_AtributeInstances();
		
	}

//	private void process_AtributeInstances() {
//		try {
//			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT * FROM chasqui2.metadatos WHERE ruta='/manifest/metadata/lom/lifecycle/contribute/date/datetime';");
//			if (rs!=null) 
//			{
//				while (rs.next()) {
//					
//					String idov=rs.getObject("idov").toString();
//					Object temp=rs.getObject("contenido");
//					String Valor="";
//					if (temp!=null)
//						Valor=temp.toString();
//					if (idov!=null&&!idov.isEmpty())
//						{
//						ExtendDigitalObject DObject= Escritor.getChasqui().getDigitalObject(Integer.parseInt(idov));
//						 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//						if (!Valor.isEmpty())
//							{
//							Date D= formatter.parse(Valor);
//							DObject.getSons().add(new ExtendDataAttributeInstance(this, pathFather(),D,DObject  ));
//						
//						}
//						}
//					
//				}
//			rs.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			System.err.println("Fecha incorrecta");
//			e.printStackTrace();
//		}
//		
//	}
}
