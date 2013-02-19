package chasqui.parser.coleccion.objetosdigitales;

import java.util.ArrayList;

import chasqui.model.collection.attibuteInstance.AttributeInstance;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.TextAttribute;
import chasqui.model.collection.digitalobjects.DigitalObject;
import chasqui.model.collection.digitalobjects.resources.Resource;
import chasqui.parser.AtributeElement;
import chasqui.parser.ChasquiParseElement;
import chasqui.parser.coleccion.atributos.categoria.numericos.ExtendAttributeInstance;
import chasqui.parser.coleccion.intanciasatributos.ExtendNumericAttributeInstance;
import chasqui.parser.coleccion.objetosdigitales.recursos.ExtendLocalResource;

public abstract class ExtendDigitalObject extends DigitalObject implements ChasquiParseElement{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7082722970187677052L;
	private static final String internalprefix="..";
	
	public ExtendDigitalObject(Integer identifier, String description) {
		super(identifier, description);
	}


	protected String processSons(String string) {
		StringBuffer SB=new StringBuffer();
		for (AttributeInstance son : Atributos) {
			SB.append(((ChasquiParseElement)son).toString(string));
		}
		for (Resource rec:Recursos)	
		{
		SB.append(((ChasquiParseElement)rec).toString(string));	
		}
		return SB.toString();
	}
	
	@Override
	public String toString(String prefix) {
		StringBuffer SB=new StringBuffer();
		SB.append(prefix);
		SB.append("OV: " + identifier + "\n");
		SB.append(prefix+internalprefix);
		SB.append("Descripcion: " + description + " \n");
		SB.append(processSons(prefix+internalprefix));
		return SB.toString();
	}


	public Resource findResource(String nombre) {
		for (Resource targetpos : Recursos) {
			if (targetpos instanceof ExtendLocalResource){
				ExtendLocalResource RecursoLocalOV = (ExtendLocalResource)targetpos;
			
			if (remove1(RecursoLocalOV.getName().toLowerCase()).equals(remove1(nombre.toLowerCase())))
				return targetpos;
			}
		}
		return null;
	}
	
	public static String remove1(String input) {
	    String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
	    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
	    String output = input;
	    for (int i=0; i<original.length(); i++) {

	        output = output.replace(original.charAt(i), ascii.charAt(i));
	    }
	    return output;
	}
	
	public AttributeInstance saveAtributo(AttributeInstance Atribute) {
		for (AttributeInstance targetpos : Atributos) {
			if (targetpos.getPath().equals(Atribute.getPath())&&Atribute instanceof ExtendAttributeInstance)
				return targetpos;
				
		}
		getSons().add(Atribute);
		return Atribute;
	}
	
	
}
