package chaqui.parser.collection.attribute;

import chasqui.model.collection.attribute.Attribute;

public class Atributos_metadatos_Categoria_ExtendTextAttribute extends ExtendTextAttribute{


	public Atributos_metadatos_Categoria_ExtendTextAttribute(String name, boolean browseable,
			Attribute father) {
		super(name, browseable, father);

		
	}


	@Override
	public String toString(String prefix) {
		return prefix + 
		"TextAttribute (Categoria: " + name + ")(Browseable: " + Browseable + ") \n"+processSons(prefix+"...");
		
	}

	
	@Override
	public void Process() {
		Atributos_metadatos_Categoria_Contexto_ExtendControlledAttribute AMcontenido=new Atributos_metadatos_Categoria_Contexto_ExtendControlledAttribute("Contexto", true, this);
		Sons.add(AMcontenido);
		AMcontenido.Process();
		Atributos_metadatos_Categoria_Keyword_ExtendControlledAttribute AMkeyword= new Atributos_metadatos_Categoria_Keyword_ExtendControlledAttribute("Palablas Clave", true, this);
		Sons.add(AMkeyword);
		AMkeyword.Process();
		Atributos_metadatos_Categoria_Description_ExtendTextAttribute AMdescripcion= new Atributos_metadatos_Categoria_Description_ExtendTextAttribute("Descripcion", true, this);
		Sons.add(AMdescripcion);
		AMdescripcion.Process();
		
	}
}
