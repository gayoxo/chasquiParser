package chaqui.parser.collection.attribute;

import java.sql.ResultSet;
import java.sql.SQLException;

import chaqui.parser.ChasquiParseElement;
import chaqui.parser.collection.attribute.controlled.ImplementacionTerm;
import chaqui.server.msqlconection.MySQLConnection;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.ControlledAttribute;
import chasqui.model.collection.attribute.controlled.Term;

public class Tatributos_metadatos_Ccontexto_ControlledAttribute extends
ExtendControlledAttribute implements ChasquiParseElement {

	public Tatributos_metadatos_Ccontexto_ControlledAttribute(
			String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
	}

	private void process_Vocabulary() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct contenido FROM chasqui2.metadatos Where ruta = '/manifest/metadata/lom/general/keyword/langstring' ORDER BY contenido;");
			if (rs!=null) 
			{
				while (rs.next()) {
					String Dato=rs.getObject("contenido").toString();
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
	public String toString(String prefix) {
		return prefix + 
		ControlledAttribute.class.toString() +
		"(Name: " + name + ")(Browseable: " + Browseable + ") \n" + processVocabulary(prefix+"...");
		
	}


	@Override
	public void Process() {
		process_Vocabulary();
		
	}

	
}
