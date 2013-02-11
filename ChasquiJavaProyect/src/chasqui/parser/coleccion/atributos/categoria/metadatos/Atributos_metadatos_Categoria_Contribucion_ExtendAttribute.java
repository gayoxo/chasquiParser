package chasqui.parser.coleccion.atributos.categoria.metadatos;

import chasqui.model.collection.attribute.Attribute;
import chasqui.parser.coleccion.atributos.ExtendAttribute;
import chasqui.parser.coleccion.atributos.ExtendTextAttribute;

public class Atributos_metadatos_Categoria_Contribucion_ExtendAttribute
		extends ExtendAttribute {

	public Atributos_metadatos_Categoria_Contribucion_ExtendAttribute(
			String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
	}

	
	
	@Override
	public void Process() {
		Atributos_metadatos_Categoria_Contribucion_Status_ExtendControlledAttribute AMstatus=new Atributos_metadatos_Categoria_Contribucion_Status_ExtendControlledAttribute("Estatus", true, this);
		Sons.add(AMstatus);
		AMstatus.Process();
		Atributos_metadatos_Categoria_Contribucion_Papel_ExtendControlledAttribute AMpapel=new Atributos_metadatos_Categoria_Contribucion_Papel_ExtendControlledAttribute("Papel", true, this);
		Sons.add(AMpapel);
		AMpapel.Process();
		Atributos_metadatos_Categoria_Contribucion_Fecha_ExtendDateAttribute AMFecha=new Atributos_metadatos_Categoria_Contribucion_Fecha_ExtendDateAttribute("Fecha", true, this);
		Sons.add(AMFecha);
		AMFecha.Process();
		Atributos_metadatos_Categoria_Contribucion_Autor_ExtendTextAttribute AMAutor=new Atributos_metadatos_Categoria_Contribucion_Autor_ExtendTextAttribute("Autor", true, this);
		Sons.add(AMAutor);
		AMAutor.Process();
		}

}
