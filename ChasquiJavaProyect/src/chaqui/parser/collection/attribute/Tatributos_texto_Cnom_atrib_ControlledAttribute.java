package chaqui.parser.collection.attribute;

import java.sql.ResultSet;
import java.sql.SQLException;

import chaqui.parser.ChasquiParseElement;
import chaqui.parser.collection.attribute.controlled.ImplementacionTerm;
import chaqui.server.msqlconection.MySQLConnection;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.ControlledAttribute;
import chasqui.model.collection.attribute.controlled.Term;

public class Tatributos_texto_Cnom_atrib_ControlledAttribute extends
ControlledAttribute implements ChasquiParseElement {

	public Tatributos_texto_Cnom_atrib_ControlledAttribute(
			String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
		
	}

	private void process_Vocabulary() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct nom_atrib FROM chasqui2.atributos_texto WHERE categoria='" + Father.getName() +"';");
			if (rs!=null) 
			{
				while (rs.next()) {
					String Dato=rs.getObject("nom_atrib").toString();
					if (Dato!=null&&!Dato.isEmpty())
						{
						ImplementacionTerm TerminoCandidato=new ImplementacionTerm(Dato);
						vocabulary.addTerm(TerminoCandidato);
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

	private String processVocabulary(String string) {
		StringBuffer SB=new StringBuffer();
		for (Term term : vocabulary.getList()) {
			SB.append(((ImplementacionTerm)term).toString(string));
		}
		return SB.toString();
	}

	@Override
	public void Process() {
		process_Vocabulary();
		
	}

	
}
