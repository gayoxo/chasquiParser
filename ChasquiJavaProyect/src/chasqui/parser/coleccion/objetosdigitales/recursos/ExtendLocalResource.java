package chasqui.parser.coleccion.objetosdigitales.recursos;

import shared.model.collection.digitalobjects.DigitalObject;
import shared.model.collection.digitalobjects.resources.LocalResource;
import shared.model.collection.digitalobjects.resources.Resource;
import chasqui.parser.ChasquiParseElement;
import chasqui.parser.ResourceComparable;

public class ExtendLocalResource extends LocalResource implements
		ChasquiParseElement,ResourceComparable {

	private static final String prefixown="..";
	private int iden;
	private String tipo;
	
	

	

	public ExtendLocalResource(DigitalObject padre, String name,
			String displayName, String descripcion, boolean visible, int iden,
			String tipo) {
		super(padre, name, displayName, descripcion, visible);
		this.iden = iden;
		this.tipo = tipo;
	}

	@Override
	public String toString(String prefix) {
		StringBuffer SB=new StringBuffer();
		SB.append(prefix);
		SB.append("Recurso: Propio");		
		SB.append("\n");
		SB.append(prefix+prefixown);
		SB.append("Etiqueta=" + DisplayName );
		SB.append("\n");
		SB.append(prefix+prefixown);
		SB.append("Visible: " + visible);
		SB.append("\n");
		SB.append(prefix+prefixown);
		SB.append("Ruta: " + Padre.getIdentifier() + "/" + Name);
		SB.append("\n");
		return SB.toString();
	}

	@Override
	public void Process() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public Resource compareMenor(Resource candidato) {
		if (iden<((ResourceComparable)candidato).getiden()) return this;
		return candidato;
	}
	
	@Override
	public int getiden() {
		return iden;
	}
	
	public String getRuta()
	{
		return Padre.getIdentifier() + "/" + Name;
	}
	
	@Override
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
