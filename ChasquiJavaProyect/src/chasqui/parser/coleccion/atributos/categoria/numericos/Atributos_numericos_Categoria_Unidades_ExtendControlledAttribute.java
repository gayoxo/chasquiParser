package chasqui.parser.coleccion.atributos.categoria.numericos;

import java.sql.ResultSet;
import java.sql.SQLException;

import chasqui.model.collection.attribute.Attribute;
import chasqui.parser.ChasquiParseElement;
import chasqui.parser.coleccion.atributos.ExtendControlledAttribute;
import chasqui.parser.coleccion.atributos.ExtendTerm;
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
		
	}
	
		
}