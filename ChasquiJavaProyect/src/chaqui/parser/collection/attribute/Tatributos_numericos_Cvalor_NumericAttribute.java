package chaqui.parser.collection.attribute;

import chaqui.parser.ChasquiParseElement;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.NumericAttribute;

public class Tatributos_numericos_Cvalor_NumericAttribute
		extends NumericAttribute implements ChasquiParseElement{

	public Tatributos_numericos_Cvalor_NumericAttribute(
			String name, boolean browseable, Attribute father) {
		super(name, browseable, father);	
	}

	
	@Override
	public String toString(String prefix) {
		return prefix + 
				NumericAttribute.class.toString() +
				"(Name: " + name + ")(Browseable: " + Browseable + ") \n" + processSons(prefix+"...");
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
		Atributos_numericos_Categoria_Unidades_ExtendControlledAttribute ANUnits=new Atributos_numericos_Categoria_Unidades_ExtendControlledAttribute("units", true, this);
		Sons.add(ANUnits);
		ANUnits.Process();
	}

}
