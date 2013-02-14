package chasqui.parser.coleccion.objetosdigitales.recursos;

import chasqui.model.collection.digitalobjects.DigitalObject;
import chasqui.model.collection.digitalobjects.resources.DOResource;
import chasqui.parser.ChasquiParseElement;

public class ExtendDOResource extends DOResource implements ChasquiParseElement {

	private static final String prefixown="..";
	
	public ExtendDOResource(DigitalObject padre,
			String displayName, String descripcion, 
			boolean visible, DigitalObject referencia) {
		super(padre, displayName, descripcion, visible, referencia);
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

}
