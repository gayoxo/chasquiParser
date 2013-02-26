package chasqui.parser.coleccion.objetosdigitales.recursos;

import shared.model.collection.digitalobjects.DigitalObject;
import shared.model.collection.digitalobjects.resources.ExternalResource;
import shared.model.collection.digitalobjects.resources.Resource;
import chasqui.parser.ChasquiParseElement;
import chasqui.parser.ResourceComparable;

public class ExtendExternalResource extends ExternalResource implements
		ChasquiParseElement,ResourceComparable {

	private static final String prefixown="..";
	private int iden;
	private String tipo;
	




	public ExtendExternalResource(DigitalObject padre, String displayName,
			String descripcion, boolean visible, Resource target, int iden,
			String tipo) {
		super(padre, displayName, descripcion, visible, target);
		this.iden = iden;
		this.tipo = tipo;
	}

	@Override
	public String toString(String prefix) {
		StringBuffer SB=new StringBuffer();
		SB.append(prefix);
		SB.append("Recurso: Externo");		
		SB.append("\n");
		SB.append(prefix+prefixown);
		SB.append("Etiqueta=" + DisplayName );
		SB.append("\n");
		SB.append(prefix+prefixown);
		SB.append("Visible: " + visible);
		SB.append("\n");
		SB.append(prefix+prefixown);
		SB.append("OV.Propietario: " + Target.getPadre().getIdentifier());
		SB.append("\n");
		SB.append(prefix+prefixown);
		ExtendLocalResource ELR=(ExtendLocalResource)Target;
		SB.append("Recurso referido " + ELR.getName());
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
		ExtendLocalResource ELR=(ExtendLocalResource)Target;
		return ELR.getRuta();
	}

	@Override
	public String getTipo() {
		return tipo;
	}
	
}
