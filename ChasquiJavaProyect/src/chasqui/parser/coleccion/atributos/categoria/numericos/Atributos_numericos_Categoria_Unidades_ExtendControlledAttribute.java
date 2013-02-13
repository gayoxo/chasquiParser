package chasqui.parser.coleccion.atributos.categoria.numericos;

import java.sql.ResultSet;
import java.sql.SQLException;

import chasqui.client.main.Escritor;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.controlled.Term;
import chasqui.parser.ChasquiParseElement;
import chasqui.parser.coleccion.atributos.ExtendControlledAttribute;
import chasqui.parser.coleccion.atributos.ExtendTerm;
import chasqui.parser.coleccion.intanciasatributos.ExtendControlledAttributeInstance;
import chasqui.parser.coleccion.intanciasatributos.ExtendNumericAttributeInstance;
import chasqui.parser.coleccion.objetosdigitales.ExtendDigitalObject;
import chasqui.server.msqlconection.MySQLConnection;

public class Atributos_numericos_Categoria_Unidades_ExtendControlledAttribute extends
		ExtendControlledAttribute implements ChasquiParseElement {

	public Atributos_numericos_Categoria_Unidades_ExtendControlledAttribute(String name,
			boolean browseable, Attribute father) {
		super(name, browseable, father);
		
	}

	protected void process_Vocabulary() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct unidades FROM chasqui2.atributos_numericos WHERE categoria='"+ Father.getFather().getName() +"' AND nom_atrib='"+Father.getName()+"' ORDER BY unidades;");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					String Dato=rs.getObject("unidades").toString();
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
	

	@Override
	public void Process() {
		process_Vocabulary();
		process_AtributeInstances(); 
		
	}
	
	private void process_AtributeInstances() {
		try {
			
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT * FROM chasqui2.atributos_numericos WHERE categoria='" +  Father.getFather().getName() +"' AND nom_atrib='" + Father.getName() + "' ORDER BY idov;");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					String idov=rs.getObject("idov").toString();
					Object temp=rs.getObject("unidades");
					String Unidades="";
					if (temp!=null)
						Unidades=temp.toString();
					if (idov!=null&&!idov.isEmpty()&&!Unidades.isEmpty())
						{
						ExtendDigitalObject DObject= Escritor.getChasqui().getDigitalObject(Integer.parseInt(idov));
						DObject.getSons().add(new ExtendControlledAttributeInstance(this, pathFather(),findTerm(Unidades),DObject ));
						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
		
}
