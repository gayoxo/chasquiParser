package chasqui.model.collection.digitalobjects.resources;

import chasqui.model.collection.digitalobjects.DigitalObject;
import chasqui.parser.ChasquiParseElement;
import chasqui.parser.coleccion.objetosdigitales.recursos.ExternalResource;

public class ExtendExternalResource extends ExternalResource implements
		ChasquiParseElement {

	private static final String prefixown="..";
	
	public ExtendExternalResource(DigitalObject padre, String name,
			String displayName, String descripcion, String tipo,
			boolean visible, Resource target) {
		super(padre, name, displayName, descripcion, tipo, visible, target);
		
		if (target==null || target.getPadre()==null)
		{
		System.err.println("error grave");	
		}
	}

	@Override
	public String toString(String prefix) {
		StringBuffer SB=new StringBuffer();
		SB.append(prefix);
		SB.append("Recurso : Name=" + Name );
			SB.append(" DisplayNane=" + DisplayName );
		SB.append("\n");
		SB.append(prefix+prefixown);
		SB.append("Descripcion: " + Descripcion);
		SB.append("\n");
		SB.append(prefix+prefixown);
		SB.append("Tipo: " + Tipo);
		SB.append("\n");
		SB.append(prefix+prefixown);
		SB.append("Visible: " + visible);
		SB.append("\n");
		SB.append(prefix+prefixown);
		SB.append("Referencia: " + Target.getPadre().getIdentifier());
		SB.append("\n");
		return SB.toString();
	}

	@Override
	public void Process() {
		// TODO Auto-generated method stub

	}

}
