package chasqui.parser.coleccion.atributos.categoria;

import shared.model.collection.attribute.Attribute;
import chasqui.parser.coleccion.atributos.ExtendAttribute;
import chasqui.parser.coleccion.atributos.ExtendTextAttribute;
import chasqui.parser.coleccion.atributos.categoria.metadatos.Atributos_metadatos_Categoria_Contexto_ExtendControlledAttribute;
import chasqui.parser.coleccion.atributos.categoria.metadatos.Atributos_metadatos_Categoria_Contribucion_ExtendAttribute;
import chasqui.parser.coleccion.atributos.categoria.metadatos.Atributos_metadatos_Categoria_Status_ExtendControlledAttribute;
import chasqui.parser.coleccion.atributos.categoria.metadatos.Atributos_metadatos_Categoria_Description_ExtendTextAttribute;
import chasqui.parser.coleccion.atributos.categoria.metadatos.Atributos_metadatos_Categoria_Keyword_ExtendControlledAttribute;
import chasqui.parser.coleccion.atributos.categoria.metadatos.catalogo.Atributos_metadatos_Categoria_Catalogos_ExtendAttribute;
import chasqui.parser.coleccion.atributos.categoria.metadatos.taxonomias.Atributos_metadatos_Categoria_Taxonomias_ExtendAttribute;

public class Atributos_metadatos_Categoria_ExtendAttribute extends ExtendAttribute{


	/**
	 * 
	 */
	private static final long serialVersionUID = 6201865586344180458L;

	public Atributos_metadatos_Categoria_ExtendAttribute(String name, boolean browseable,
			Attribute father) {
		super(name, browseable, father);

		
	}
	
	//SELECT idov,count(*) FROM chasqui2.metadatos where ruta='/manifest/metadata/lom/lifecycle/contribute/centity/vcard' group by idov;

	
	@Override
	public void Process() {
		
		Atributos_metadatos_Categoria_Contexto_ExtendControlledAttribute AMcontenido=new Atributos_metadatos_Categoria_Contexto_ExtendControlledAttribute("Contexto", false, this);
		Sons.add(AMcontenido);
		AMcontenido.Process();
		Atributos_metadatos_Categoria_Keyword_ExtendControlledAttribute AMkeyword= new Atributos_metadatos_Categoria_Keyword_ExtendControlledAttribute("Palablas Clave", false, this);
		Sons.add(AMkeyword);
		AMkeyword.Process();
		Atributos_metadatos_Categoria_Description_ExtendTextAttribute AMdescripcion= new Atributos_metadatos_Categoria_Description_ExtendTextAttribute("Descripcion", false, this);
		Sons.add(AMdescripcion);
		AMdescripcion.Process();
		Atributos_metadatos_Categoria_Status_ExtendControlledAttribute AMstatus=new Atributos_metadatos_Categoria_Status_ExtendControlledAttribute("Estatus", false, this);
		Sons.add(AMstatus);
		AMstatus.Process();
		Atributos_metadatos_Categoria_Contribucion_ExtendAttribute AMcontribucion= new Atributos_metadatos_Categoria_Contribucion_ExtendAttribute("Contribucion",false, this);
		Sons.add(AMcontribucion);
		AMcontribucion.Process();
		Atributos_metadatos_Categoria_Catalogos_ExtendAttribute AMCatalogo= new Atributos_metadatos_Categoria_Catalogos_ExtendAttribute("Catalogos",false, this);
		Sons.add(AMCatalogo);
		AMCatalogo.Process();
		Atributos_metadatos_Categoria_Taxonomias_ExtendAttribute AMTaxonimias= new Atributos_metadatos_Categoria_Taxonomias_ExtendAttribute("Taxonomias",false, this);
		Sons.add(AMTaxonimias);
		AMTaxonimias.Process();
		
	}
}
