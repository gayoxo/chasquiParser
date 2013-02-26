package chasqui.parser.coleccion.objetosdigitales.recursos;

import shared.model.collection.digitalobjects.DigitalObject;
import shared.model.collection.digitalobjects.resources.DOResource;
import shared.model.collection.digitalobjects.resources.Resource;
import chasqui.parser.ChasquiParseElement;
import chasqui.parser.ResourceComparable;

public class ExtendDOResource extends DOResource implements ChasquiParseElement,ResourceComparable {

	private static final String prefixown="..";
	private int iden;
	private String tipo;
	
	


	public ExtendDOResource(DigitalObject padre, String displayName,
			String descripcion, boolean visible, DigitalObject referencia,
			int iden, String tipo) {
		super(padre, displayName, descripcion, visible, referencia);
		this.iden = iden;
		this.tipo = tipo;
	}

	@Override
	public String toString(String prefix) {
		StringBuffer SB=new StringBuffer();
		SB.append(prefix);
		SB.append("Recurso: OV"  );		
		SB.append("\n");
		SB.append(prefix+prefixown);
		SB.append("Etiqueta=" + DisplayName );
		SB.append("\n");
		SB.append(prefix+prefixown);
		SB.append("Descripcion: " + Descripcion);
		SB.append("\n");
		SB.append(prefix+prefixown);
		SB.append("Visible: " + visible);
		SB.append("\n");
		SB.append(prefix+prefixown);
		SB.append("OV Referido: " + referencia.getIdentifier());
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
	
	@Override
	public String getTipo() {
		return tipo;
	}
	

}
