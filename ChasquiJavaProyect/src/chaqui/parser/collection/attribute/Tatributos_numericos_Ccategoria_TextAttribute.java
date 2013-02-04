package chaqui.parser.collection.attribute;

import java.sql.ResultSet;
import java.sql.SQLException;

import chaqui.parser.ChasquiParseElement;
import chaqui.server.msqlconection.MySQLConnection;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.TextAttribute;
import chasqui.model.collection.attribute.controlled.Term;

public class Tatributos_numericos_Ccategoria_TextAttribute extends TextAttribute implements ChasquiParseElement{


	public Tatributos_numericos_Ccategoria_TextAttribute(String name, boolean browseable,
			Attribute father) {
		super(name, browseable, father);
	}


	@Override
	public String toString(String prefix) {
		return prefix + 
		TextAttribute.class.toString()
		+ "(Name: " + name + ")(Browseable: " + Browseable + ") \n"+processSons(prefix+"...");
		
	}

	private String processSons(String string) {
		StringBuffer SB=new StringBuffer();
		for (Attribute son : Sons) {
			SB.append(((ChasquiParseElement)son).toString(string));
		}
		return SB.toString();
	}


	@Override
	public void Process() {
		Tatributos_numericos_Cnom_atrib_ControlledAttribute ANNom_atrib=new Tatributos_numericos_Cnom_atrib_ControlledAttribute("nom_atrib", true, this);
		Sons.add(ANNom_atrib);
		ANNom_atrib.Process();
		Tatributos_numericos_Cvalor_NumericAttribute ANValor=new Tatributos_numericos_Cvalor_NumericAttribute("valor", true, this);
		Sons.add(ANValor);
		ANValor.Process();
	}
}
