package chasqui.parser.coleccion.objetosdigitales.recursos;

import shared.model.collection.digitalobjects.DigitalObject;
import shared.model.collection.digitalobjects.resources.LocalResource;
import chasqui.parser.ChasquiParseElement;

public class ExtendLocalResource extends LocalResource implements
		ChasquiParseElement {

	private static final String prefixown="..";
	
	public ExtendLocalResource(DigitalObject padre, String name,
			String displayName, String descripcion,  boolean visible) {
		super(padre, name, displayName, descripcion, visible);
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
	
	
	

}
