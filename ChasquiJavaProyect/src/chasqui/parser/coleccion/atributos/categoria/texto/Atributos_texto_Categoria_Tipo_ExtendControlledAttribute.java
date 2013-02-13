package chasqui.parser.coleccion.atributos.categoria.texto;

import java.sql.ResultSet;
import java.sql.SQLException;

import chasqui.client.main.Escritor;
import chasqui.model.collection.attribute.Attribute;
import chasqui.parser.coleccion.atributos.ExtendControlledAttribute;
import chasqui.parser.coleccion.atributos.ExtendTerm;
import chasqui.parser.coleccion.intanciasatributos.ExtendControlledAttributeInstance;
import chasqui.parser.coleccion.objetosdigitales.ExtendDigitalObject;
import chasqui.server.msqlconection.MySQLConnection;

public class Atributos_texto_Categoria_Tipo_ExtendControlledAttribute extends
		ExtendControlledAttribute {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5834014899460862094L;


	public Atributos_texto_Categoria_Tipo_ExtendControlledAttribute(String name,
			boolean browseable, Attribute father) {
		super(name, browseable, father);
		
	}
	

	@Override
	public void Process() {
		process_Vocabulary();
		process_AtributeInstances();
		
	}

	private void process_AtributeInstances() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT * FROM chasqui2.atributos_texto WHERE categoria='"
					+ 
					//Father.getFather().getName()
						Father.getName()
					+"' AND nom_atrib='"
					+
//					Father.getName()
						name
					+ "' ORDER BY idov;");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					String idov=rs.getObject("idov").toString();
					Object temp=rs.getObject("valor");
					String Valor="";
					if (temp!=null)
						Valor=rs.getObject("valor").toString();
					if (idov!=null&&!idov.isEmpty())
						{
						ExtendDigitalObject DObject= Escritor.getChasqui().getDigitalObject(Integer.parseInt(idov));
						DObject.getSons().add(new ExtendControlledAttributeInstance(this, pathFather(),findTerm(Valor),DObject  ));
						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void process_Vocabulary() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct valor FROM chasqui2.atributos_texto WHERE categoria='"
					+ 
					//Father.getFather().getName()
						Father.getName()
					+"' AND nom_atrib='"
					+
//					Father.getName()
						name
					+ "' ORDER BY valor;");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					String Dato=rs.getObject("valor").toString();
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
	
	
}
