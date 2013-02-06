package chaqui.parser.collection.attribute;

import java.sql.ResultSet;
import java.sql.SQLException;

import chaqui.parser.ChasquiParseElement;
import chaqui.parser.collection.attribute.controlled.ImplementacionTerm;
import chaqui.server.msqlconection.MySQLConnection;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.ControlledAttribute;
import chasqui.model.collection.attribute.controlled.Term;

public class Atributos_numericos_Categoria_Unidades_ExtendControlledAttribute extends
		ExtendControlledAttribute implements ChasquiParseElement {

	public Atributos_numericos_Categoria_Unidades_ExtendControlledAttribute(String name,
			boolean browseable, Attribute father) {
		super(name, browseable, father);
		
	}

	private void process_units() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct unidades FROM chasqui2.atributos_numericos WHERE categoria='"+ Father.getFather().getName() +"' AND nom_atrib='"+Father.getName()+"' ORDER BY unidades;");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					String Dato=rs.getObject("unidades").toString();
					if (Dato!=null&&!Dato.isEmpty())
						{
						ImplementacionTerm TerminoCandidato=new ImplementacionTerm(Dato);
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
		process_units();
		
	}
	
		
}
