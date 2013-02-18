package chasqui.parser.coleccion.intanciasatributos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import chasqui.model.collection.attibuteInstance.AttributeInstance;
import chasqui.model.collection.attibuteInstance.DateAttributeInstance;
import chasqui.model.collection.attribute.DateAttribute;
import chasqui.model.collection.digitalobjects.DigitalObject;
import chasqui.parser.ChasquiParseElement;

public class ExtendDataAttributeInstance extends DateAttributeInstance
		implements ChasquiParseElement {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7995692529122651680L;

	public ExtendDataAttributeInstance(DateAttribute hasType, String path,
			DigitalObject doDigitalObject, AttributeInstance fatherAtribute,
			Date fecha) {
		super(hasType, path, doDigitalObject, fatherAtribute, fecha);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString(String prefix) {
		StringBuffer SB=new StringBuffer();
		SB.append(prefix);
		SB.append("Atributo : " + Path );
		DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
			SB.append(" =" + df.format(fecha));
		SB.append("\n");
		return SB.toString();
	}

	@Override
	public void Process() {
		// TODO Auto-generated method stub

	}

}
