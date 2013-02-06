package chaqui.parser.collection.attribute;

import chaqui.parser.ChasquiParseElement;
import chasqui.model.collection.attribute.Attribute;

public class Atributos_texto_Categoria_Tipo_ExtendTextAttribute extends
ExtendTextAttribute implements ChasquiParseElement {

	public Atributos_texto_Categoria_Tipo_ExtendTextAttribute(
			String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
		
	}

	@Override
	public void Process() {
		Atributos_texto_Categoria_Tipo_Valor_ExtendControlledAttribute ATCTU=new Atributos_texto_Categoria_Tipo_Valor_ExtendControlledAttribute("Valor", false, this);
		ATCTU=(Atributos_texto_Categoria_Tipo_Valor_ExtendControlledAttribute) addAtributos(ATCTU);
		ATCTU.Process();
	}

	public String toString(String prefix) {
		return prefix + 
		"TextAttribute (Tipo: " + name + ")(Browseable: " + Browseable + ") \n"+processSons(prefix+"...");
		
	}
	
}
