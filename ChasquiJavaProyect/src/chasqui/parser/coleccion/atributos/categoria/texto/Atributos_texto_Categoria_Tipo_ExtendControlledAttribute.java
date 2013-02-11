package chasqui.parser.coleccion.atributos.categoria.texto;

import java.sql.ResultSet;
import java.sql.SQLException;

import chasqui.model.collection.attribute.Attribute;
import chasqui.parser.coleccion.atributos.ExtendControlledAttribute;
import chasqui.parser.coleccion.atributos.ExtendTerm;
import chasqui.server.msqlconection.MySQLConnection;

public class Atributos_texto_Categoria_Tipo_ExtendControlledAttribute extends
		ExtendControlledAttribute {


	public Atributos_texto_Categoria_Tipo_ExtendControlledAttribute(String name,
			boolean browseable, Attribute father) {
		super(name, browseable, father);
		
	}
	

	@Override
	public void Process() {
		process_Vocabulary();
		
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
