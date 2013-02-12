package chasqui.parser.coleccion;

import java.util.Iterator;
import java.util.Map.Entry;

import chasqui.model.collection.Collection;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.digitalobjects.DigitalObject;
import chasqui.parser.ChasquiParseElement;

public abstract class ExtendCollection extends Collection implements ChasquiParseElement{

	@Override
	public String toString(String prefix) {
		StringBuffer SB=new StringBuffer();
		for (Attribute hijos : Atributos) {
			SB.append(((ChasquiParseElement)hijos).toString("..."));
		}
		for (Entry<Integer, DigitalObject> hijos : ObjetosDigitales.entrySet()) {
			SB.append(((ChasquiParseElement)hijos.getValue()).toString("..."));
		}
		return SB.toString();
	}
	
	public Attribute addAtributos(Attribute atribute) {
		int counter=0;
		while (counter<Atributos.size() && !Atributos.get(counter).getName().equals(atribute.getName()))
			counter++;
		if (counter==Atributos.size())
			{
			Atributos.add(atribute);
			return atribute;
			}
		else return Atributos.get(counter);
	}
	
	public DigitalObject addDigitalObjects(DigitalObject objetoDigital) {
		  DigitalObject value = ObjetosDigitales.put( objetoDigital.getIdentifier(), objetoDigital );
		    if ( value != null )
		      System.err.println("Objeto Duplicado");
		  return ObjetosDigitales.get(objetoDigital.getIdentifier());
	}
	
	public DigitalObject getDigitalObject(Integer valor)
	{
	return ObjetosDigitales.get(valor);	
	}
	
	
}
