package chaqui.parser.coleccion.atributos.categoria.texto;

import java.sql.ResultSet;
import java.sql.SQLException;

import chaqui.parser.coleccion.atributos.ExtendControlledAttribute;
import chaqui.parser.coleccion.atributos.ExtendTerm;
import chaqui.server.msqlconection.MySQLConnection;
import chasqui.model.collection.attribute.Attribute;

public class Atributos_texto_Categoria_Tipo_Valor_ExtendControlledAttribute extends
		ExtendControlledAttribute {

	public Atributos_texto_Categoria_Tipo_Valor_ExtendControlledAttribute(String name,
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
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct valor FROM chasqui2.atributos_texto WHERE categoria='"+ Father.getFather().getName() +"' AND nom_atrib='"+Father.getName() + "' ORDER BY valor;");
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
