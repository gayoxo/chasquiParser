package chasqui.parser.coleccion.objetosdigitales.recursos;

import shared.model.collection.digitalobjects.DigitalObject;
import shared.model.collection.digitalobjects.resources.ExternalResource;
import shared.model.collection.digitalobjects.resources.Resource;
import chasqui.parser.ChasquiParseElement;

public class ExtendExternalResource extends ExternalResource implements
		ChasquiParseElement {

	private static final String prefixown="..";
	
	public ExtendExternalResource(DigitalObject padre,
			String displayName, String descripcion, 
			boolean visible, Resource target) {
		super(padre, displayName, descripcion,visible, target);
		
		if (target==null || target.getPadre()==null)
		{
		System.err.println("error grave");	
		}
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

}
