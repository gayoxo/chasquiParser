package chaqui.parser.collection.attribute;

import java.sql.ResultSet;
import java.sql.SQLException;

import chaqui.parser.collection.attribute.controlled.ImplementacionTerm;
import chaqui.server.msqlconection.MySQLConnection;
import chasqui.model.collection.attribute.Attribute;

public class Atributos_texto_Categoria_Tipo_Valor_ExtendControlledAttribute extends
		ExtendControlledAttribute {

	public Atributos_texto_Categoria_Tipo_Valor_ExtendControlledAttribute(String name,
			boolean browseable, Attribute father) {
		super(name, browseable, father);
		
	}

	private void process_units() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct valor FROM chasqui2.atributos_texto WHERE categoria='"+ Father.getFather().getName() +"' AND nom_atrib='"+Father.getName() + "' ORDER BY valor;");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					String Dato=rs.getObject("valor").toString();
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
