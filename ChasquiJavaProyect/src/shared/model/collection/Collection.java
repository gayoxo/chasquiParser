package shared.model.collection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import shared.model.collection.attribute.Attribute;
import shared.model.collection.digitalobjects.DigitalObject;
import shared.model.collection.digitalobjects.resources.Resource;


public abstract class Collection implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2024710713540254451L;
	protected ArrayList<Attribute> Atributos;
	protected HashMap<Integer,DigitalObject> ObjetosDigitales;
	protected ArrayList<Resource> RecursosGeneral;
	
	
	protected Collection() {
		Atributos=new ArrayList<Attribute>();
		ObjetosDigitales=new HashMap<Integer,DigitalObject>();
		RecursosGeneral=new ArrayList<Resource>();
	}

	public ArrayList<Attribute> getAtributos() {
		return Atributos;
	}
	
	public ArrayList<Resource> getRecursosGeneral() {
		return RecursosGeneral;
	}
	
	public HashMap<Integer, DigitalObject> getObjetosDigitales() {
		return ObjetosDigitales;
	}
	
	public void setAtributos(ArrayList<Attribute> atributos) {
		Atributos = atributos;
	}
	
	public void setObjetosDigitales(
			HashMap<Integer, DigitalObject> objetosDigitales) {
		ObjetosDigitales = objetosDigitales;
	}
	
	public void setRecursosGeneral(ArrayList<Resource> recursosGeneral) {
		RecursosGeneral = recursosGeneral;
	}

	public void ordena() {
		int in;
		for (int i = 1 ; i < Atributos.size() ; i++) {
				    Attribute comp = Atributos.get(i);
				    comp.ordena();
				    in = i;
				 
				    while (in > 0 && Atributos.get(in - 1).getName().compareTo(comp.getName()) > 0){
				    	Atributos.set(in, Atributos.get(in-1));
				    	in--;
				 }
				 
				    Atributos.set(in, comp);
				  

		} 
		
	}
	
}
