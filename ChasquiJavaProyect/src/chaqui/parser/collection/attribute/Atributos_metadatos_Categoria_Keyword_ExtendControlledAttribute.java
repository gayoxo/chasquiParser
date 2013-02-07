package chaqui.parser.collection.attribute;

import java.sql.ResultSet;
import java.sql.SQLException;

import chaqui.parser.ChasquiParseElement;
import chaqui.server.msqlconection.MySQLConnection;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.ControlledAttribute;
import chasqui.model.collection.attribute.controlled.Term;

public class Atributos_metadatos_Categoria_Keyword_ExtendControlledAttribute extends
ExtendControlledAttribute implements ChasquiParseElement {

	public Atributos_metadatos_Categoria_Keyword_ExtendControlledAttribute(
			String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
	}

	private void process_Vocabulary() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct contenido FROM chasqui2.metadatos Where ruta = '/manifest/metadata/lom/general/coverage/langstring' ORDER BY contenido;");
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

	@Override
	public void Process() {
		process_Vocabulary();
		
	}

	
}
