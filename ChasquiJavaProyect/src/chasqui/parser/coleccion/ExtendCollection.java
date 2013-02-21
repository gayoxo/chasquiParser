package chasqui.parser.coleccion;

import java.util.Map.Entry;

import shared.model.collection.Collection;
import shared.model.collection.attribute.Attribute;
import shared.model.collection.digitalobjects.DigitalObject;

import chasqui.parser.ChasquiParseElement;
import chasqui.parser.coleccion.objetosdigitales.ExtendDigitalObject;

public abstract class ExtendCollection extends Collection implements ChasquiParseElement{

	/**
	 * 
	 */
	private static final long serialVersionUID = 924575690968035690L;

	@Override
	public String toString(String prefix) {
		StringBuffer SB=new StringBuffer();
		SB.append("#MODELO \n");
		for (Attribute hijos : Atributos) {
			SB.append(((ChasquiParseElement)hijos).toString("..."));
		}
		SB.append("#OV \n");
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
	
	public ExtendDigitalObject getDigitalObject(Integer valor)
	{
	return (ExtendDigitalObject) ObjetosDigitales.get(valor);	
	}
	
	
}
