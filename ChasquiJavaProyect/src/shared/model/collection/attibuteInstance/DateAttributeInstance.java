package shared.model.collection.attibuteInstance;


import java.util.Date;

import shared.model.collection.attribute.DateAttribute;
import shared.model.collection.digitalobjects.DigitalObject;


public class DateAttributeInstance extends AttributeInstance {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5197527830113363682L;
	protected Date fecha;

	
	
	public DateAttributeInstance(DateAttribute hasType, String path,
			DigitalObject doDigitalObject, AttributeInstance fatherAtribute,
			Date fecha) {
		super(hasType, path, doDigitalObject, fatherAtribute);
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	

}
