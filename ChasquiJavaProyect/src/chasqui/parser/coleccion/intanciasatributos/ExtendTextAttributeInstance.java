package chasqui.parser.coleccion.intanciasatributos;

import chasqui.model.collection.attibuteInstance.TextAttributeInstance;
import chasqui.model.collection.attribute.TextAttribute;
import chasqui.parser.ChasquiParseElement;

public class ExtendTextAttributeInstance extends TextAttributeInstance implements ChasquiParseElement{

	public ExtendTextAttributeInstance(TextAttribute hasType, String path,
			String valor) {
		super(hasType, path, valor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString(String prefix) {
		StringBuffer SB=new StringBuffer();
		SB.append(prefix);
		SB.append("Atributo : " + Path );
		SB.append(" =" + valor);
		SB.append("\n");
		return SB.toString();
	}

	@Override
	public void Process() {
		// TODO Auto-generated method stub
		
	}

}
