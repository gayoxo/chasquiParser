package chasqui.parser.coleccion.intanciasatributos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import shared.model.collection.attibuteInstance.AttributeInstance;
import shared.model.collection.attibuteInstance.DateAttributeInstance;
import shared.model.collection.attribute.DateAttribute;
import shared.model.collection.digitalobjects.DigitalObject;

import chasqui.parser.ChasquiParseElement;
import chasqui.parser.InstanceAttribute;

public class ExtendDateAttributeInstance extends DateAttributeInstance
		implements ChasquiParseElement,InstanceAttribute {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7995692529122651680L;

	public ExtendDateAttributeInstance(DateAttribute hasType, String path,
			DigitalObject doDigitalObject, AttributeInstance fatherAtribute,
			Date fecha) {
		super(hasType, path, doDigitalObject, fatherAtribute, fecha);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString(String prefix) {
		StringBuffer SB=new StringBuffer();
//		SB.append(prefix);
//		SB.append("Atributo : " + Path );
//		DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
//			SB.append(" =" + df.format(fecha));
//		SB.append("\n");
		return SB.toString();
	}

	@Override
	public void Process() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toStringinterno(String prefix) {
		StringBuffer SB=new StringBuffer();
		SB.append(prefix);
		SB.append("Atributo : " + hasType.getName() );
		DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
			SB.append(" =" + df.format(fecha));
		SB.append("\n");
		SB.append(processSons(prefix+".."));
		return SB.toString();
	}

	private Object processSons(String prefixheredado) {
		StringBuffer SB=new StringBuffer();
		for (AttributeInstance element : sons) {
			SB.append(((InstanceAttribute)element).toStringinterno(prefixheredado));
		} 
		return SB.toString();
	}
}
