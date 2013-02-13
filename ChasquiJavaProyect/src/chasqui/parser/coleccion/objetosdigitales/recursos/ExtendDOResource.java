package chasqui.parser.coleccion.objetosdigitales.recursos;

import chasqui.model.collection.digitalobjects.DigitalObject;
import chasqui.model.collection.digitalobjects.resources.DOResource;
import chasqui.parser.ChasquiParseElement;

public class ExtendDOResource extends DOResource implements ChasquiParseElement {

	private static final String prefixown="..";
	
	public ExtendDOResource(DigitalObject padre, String name,
			String displayName, String descripcion, String tipo,
			boolean visible, DigitalObject referencia) {
		super(padre, name, displayName, descripcion, tipo, visible, referencia);
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
		SB.append("OVReferenciado: " + referencia.getIdentifier());
		SB.append("\n");
		return SB.toString();
	}

	@Override
	public void Process() {
		// TODO Auto-generated method stub

	}

}
