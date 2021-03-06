package chasqui.parser.coleccion.atributos.categoria.texto;

import general.client.main.ChasquiToFIle;
import general.server.msqlconection.MySQLConnectionChasqui;

import java.sql.ResultSet;
import java.sql.SQLException;

import shared.model.collection.attribute.Attribute;

import chasqui.parser.coleccion.atributos.ExtendAttribute;
import chasqui.parser.coleccion.atributos.ExtendControlledAttribute;
import chasqui.parser.coleccion.atributos.ExtendTerm;
import chasqui.parser.coleccion.intanciasatributos.ExtendAttributeInstance;
import chasqui.parser.coleccion.intanciasatributos.ExtendControlledAttributeInstance;
import chasqui.parser.coleccion.objetosdigitales.ExtendDigitalObject;

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
		if (vocabulary.getList().isEmpty())
			Father.getSons().remove(this);
		process_AtributeInstances();
		
	}

	private void process_AtributeInstances() {
		try {
			ResultSet rs=MySQLConnectionChasqui.RunQuerrySELECT("SELECT * FROM chasqui2.atributos_texto WHERE categoria='"
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
						ExtendDigitalObject DObject= ChasquiToFIle.getChasqui().getDigitalObject(Integer.parseInt(idov));
						ExtendAttributeInstance EAI = new ExtendAttributeInstance(this.getFather(), ((ExtendAttribute) this.getFather()).pathFather(),DObject,null);
						EAI=(ExtendAttributeInstance) DObject.saveAtributo(EAI);
						ExtendControlledAttributeInstance ECAI=new ExtendControlledAttributeInstance(this, pathFather(),DObject,EAI,findTerm(Valor));
						ECAI=(ExtendControlledAttributeInstance) DObject.saveAtributo(ECAI);
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
			ResultSet rs=MySQLConnectionChasqui.RunQuerrySELECT("SELECT distinct valor FROM chasqui2.atributos_texto WHERE categoria='"
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
