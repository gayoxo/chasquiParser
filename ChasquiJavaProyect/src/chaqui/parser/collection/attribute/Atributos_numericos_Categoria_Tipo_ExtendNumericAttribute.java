package chaqui.parser.collection.attribute;

import chasqui.model.collection.attribute.Attribute;

public class Atributos_numericos_Categoria_Tipo_ExtendNumericAttribute extends
ExtendNumericAttribute {

	public Atributos_numericos_Categoria_Tipo_ExtendNumericAttribute(
			String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
	}



	@Override
	public void Process() {
		Atributos_numericos_Categoria_Unidades_ExtendControlledAttribute ATCTU=new Atributos_numericos_Categoria_Unidades_ExtendControlledAttribute("Unidades", false, this);
		ATCTU=(Atributos_numericos_Categoria_Unidades_ExtendControlledAttribute) addAtributos(ATCTU);
		ATCTU.Process();
		
	}

	public String toString(String prefix) {
		return prefix + 
		"NumericAttribute (Tipo: " + name + ")(Browseable: " + Browseable + ") \n"+processSons(prefix+"...");
		
	}
	
}
