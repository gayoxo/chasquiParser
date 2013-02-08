package chasqui.parser.coleccion.atributos.categoria;

import chasqui.model.collection.attribute.Attribute;
import chasqui.parser.coleccion.atributos.ExtendTextAttribute;
import chasqui.parser.coleccion.atributos.categoria.metadatos.Atributos_metadatos_Categoria_Contexto_ExtendControlledAttribute;
import chasqui.parser.coleccion.atributos.categoria.metadatos.Atributos_metadatos_Categoria_Contribucion_ExtendTextAttribute;
import chasqui.parser.coleccion.atributos.categoria.metadatos.Atributos_metadatos_Categoria_Description_ExtendTextAttribute;
import chasqui.parser.coleccion.atributos.categoria.metadatos.Atributos_metadatos_Categoria_Keyword_ExtendControlledAttribute;
import chasqui.parser.coleccion.atributos.categoria.metadatos.catalogo.Atributos_metadatos_Categoria_Catalogos_ExtendTextAttribute;
import chasqui.parser.coleccion.atributos.categoria.metadatos.taxonomias.Atributos_metadatos_Categoria_Taxonomias_ExtendTextAttribute;

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
		Atributos_metadatos_Categoria_Contribucion_ExtendTextAttribute AMcontribucion= new Atributos_metadatos_Categoria_Contribucion_ExtendTextAttribute("Contribucion",true, this);
		Sons.add(AMcontribucion);
		AMcontribucion.Process();
		Atributos_metadatos_Categoria_Catalogos_ExtendTextAttribute AMCatalogo= new Atributos_metadatos_Categoria_Catalogos_ExtendTextAttribute("Catalogos",true, this);
		Sons.add(AMCatalogo);
		AMCatalogo.Process();
		Atributos_metadatos_Categoria_Taxonomias_ExtendTextAttribute AMTaxonimias= new Atributos_metadatos_Categoria_Taxonomias_ExtendTextAttribute("Taxonomias",true, this);
		Sons.add(AMTaxonimias);
		AMTaxonimias.Process();
		
	}
}
