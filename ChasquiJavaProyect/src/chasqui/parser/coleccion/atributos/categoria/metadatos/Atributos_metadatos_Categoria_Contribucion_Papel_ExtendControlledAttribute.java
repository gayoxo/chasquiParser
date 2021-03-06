package chasqui.parser.coleccion.atributos.categoria.metadatos;

import general.client.main.ChasquiToFIle;
import general.server.msqlconection.MySQLConnectionChasqui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import shared.model.collection.attribute.Attribute;

import chasqui.parser.coleccion.atributos.ExtendControlledAttribute;
import chasqui.parser.coleccion.atributos.ExtendTerm;
import chasqui.parser.coleccion.intanciasatributos.ExtendControlledAttributeInstance;
import chasqui.parser.coleccion.intanciasatributos.ExtendDateAttributeInstance;
import chasqui.parser.coleccion.objetosdigitales.ExtendDigitalObject;

public class Atributos_metadatos_Categoria_Contribucion_Papel_ExtendControlledAttribute
		extends ExtendControlledAttribute {

	public Atributos_metadatos_Categoria_Contribucion_Papel_ExtendControlledAttribute(
			String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
	}

	@Override
	public void Process() {
		process_Vocabulary();
		if (vocabulary.getList().isEmpty())
			Father.getSons().remove(this);
	//	process_AtributeInstances();
	}

	@Override
	protected void process_Vocabulary() {
		try {
			ResultSet rs=MySQLConnectionChasqui.RunQuerrySELECT("SELECT distinct contenido FROM chasqui2.metadatos WHERE ruta='/manifest/metadata/lom/lifecycle/contribute/role/value/langstring' ORDER BY contenido;");
			if (rs!=null) 
			{
				while (rs.next()) {
					String Dato=rs.getObject("contenido").toString();
					if (Dato!=null&&!Dato.isEmpty())
						{
						ExtendTerm TerminoCandidato=new ExtendTerm(Dato);
						addTerm(TerminoCandidato);
						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
//	private void process_AtributeInstances() {
//		try {
//			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT * FROM chasqui2.metadatos WHERE ruta='/manifest/metadata/lom/lifecycle/contribute/role/value/langstring' ORDER BY contenido;");
//			if (rs!=null) 
//			{
//				while (rs.next()) {
//					
//					String idov=rs.getObject("idov").toString();
//					Object temp=rs.getObject("contenido");
//					String Valor="";
//					if (temp!=null)
//						Valor=temp.toString();
//					if (idov!=null&&!idov.isEmpty()&&!Valor.isEmpty())
//						{
//						ExtendDigitalObject DObject= Escritor.getChasqui().getDigitalObject(Integer.parseInt(idov));
//						DObject.getSons().add(new ExtendControlledAttributeInstance(this, pathFather(),findTerm(Valor),DObject  ));
//						}
//					
//				}
//			rs.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
//	}

}
