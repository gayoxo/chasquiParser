package chasqui.parser.coleccion.atributos.categoria.texto;

import chasqui.model.collection.attribute.Attribute;
import chasqui.parser.ChasquiParseElement;
import chasqui.parser.coleccion.atributos.ExtendTextAttribute;

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

	
}