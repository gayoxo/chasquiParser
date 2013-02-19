package chasqui.parser.coleccion.atributos.categoria.numericos;

import java.sql.ResultSet;
import java.sql.SQLException;

import chasqui.client.main.Escritor;
import chasqui.model.collection.attribute.NumericAttribute;
import chasqui.parser.ChasquiParseElement;
import chasqui.parser.coleccion.atributos.ExtendAttribute;
import chasqui.parser.coleccion.atributos.ExtendControlledAttribute;
import chasqui.parser.coleccion.atributos.ExtendNumericAttribute;
import chasqui.parser.coleccion.atributos.ExtendTerm;
import chasqui.parser.coleccion.intanciasatributos.ExtendAttributeInstance;
import chasqui.parser.coleccion.intanciasatributos.ExtendControlledAttributeInstance;
import chasqui.parser.coleccion.intanciasatributos.ExtendNumericAttributeInstance;
import chasqui.parser.coleccion.objetosdigitales.ExtendDigitalObject;
import chasqui.server.msqlconection.MySQLConnection;

public class Atributos_numericos_Categoria_Unidades_ExtendControlledAttribute extends
		ExtendControlledAttribute implements ChasquiParseElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6251627006544684372L;

	public Atributos_numericos_Categoria_Unidades_ExtendControlledAttribute(String name,
			boolean browseable, NumericAttribute father) {
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
		if (vocabulary.getList().isEmpty())
			Father.getSons().remove(this);
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
					Object temp2=rs.getObject("valor");
					String Valor="";
					if (temp2!=null)
						Valor=temp2.toString();
					if (idov!=null&&!idov.isEmpty()&&!Unidades.isEmpty()&&!Valor.isEmpty())
						{
						ExtendDigitalObject DObject= Escritor.getChasqui().getDigitalObject(Integer.parseInt(idov));
						ExtendAttributeInstance EAI = new ExtendAttributeInstance(this.getFather().getFather(), ((ExtendAttribute) this.getFather().getFather()).pathFather(),DObject,null);
						EAI=(ExtendAttributeInstance) DObject.saveAtributo(EAI);
						ExtendNumericAttributeInstance ENAI = new ExtendNumericAttributeInstance((NumericAttribute) this.getFather(), ((ExtendNumericAttribute) this.getFather()).pathFather(),DObject,EAI,Float.parseFloat(Valor));
						ENAI=(ExtendNumericAttributeInstance) DObject.saveAtributo(ENAI);
						ExtendControlledAttributeInstance ECAI=new ExtendControlledAttributeInstance(this, pathFather(),DObject,ENAI,findTerm(Unidades));
						ECAI=(ExtendControlledAttributeInstance) DObject.saveAtributo(ECAI);
						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
		
}
