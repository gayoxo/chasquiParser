package chasqui.parser.coleccion.intanciasatributos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import chasqui.model.collection.attibuteInstance.DateAttributeInstance;
import chasqui.model.collection.attribute.DateAttribute;
import chasqui.model.collection.digitalobjects.DigitalObject;
import chasqui.parser.ChasquiParseElement;

public class ExtendDataAttributeInstance extends DateAttributeInstance
		implements ChasquiParseElement {

	public ExtendDataAttributeInstance(DateAttribute hasType, String path,
			Date fecha,DigitalObject doDigitalObject) {
		super(hasType, path, fecha, doDigitalObject);
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
