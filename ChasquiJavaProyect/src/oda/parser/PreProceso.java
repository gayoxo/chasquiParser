package oda.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import chasqui.parser.ChasquiParseElement;
import shared.model.collection.Collection;
import shared.model.collection.attibuteInstance.AttributeInstance;
import shared.model.collection.attribute.Attribute;
import shared.model.collection.digitalobjects.DigitalObject;

public class PreProceso {

	
	
	private HashMap<Attribute, Integer> tabla; 
	private HashMap<Attribute, Integer> tablaOV; 
	private Set<Attribute> nuevoModelSet;
	private ArrayList<Attribute> NuevoModelo;

	
	public PreProceso() {
		tabla=new HashMap<Attribute, Integer>();
		NuevoModelo=new ArrayList<Attribute>();
		nuevoModelSet=new HashSet<Attribute>();
	}
	
	
	public ArrayList<DigitalObject> preproccesModel(Collection toOda) {
		ArrayList<DigitalObject> DO=new ArrayList<DigitalObject>();
		
		for (Entry<Integer, DigitalObject> par:toOda.getObjetosDigitales().entrySet())
			{
			DigitalObject Actual=par.getValue();
			DO.add(Actual);
			ArrayList<AttributeInstance> Atributos = cloneList(Actual.getSons());
			processAtributos(Atributos);
			}
		for (DigitalObject digitalObject : DO) {
			procesa(digitalObject);
		}
		
		
		debugpaint();
		debugpaint2(toOda);
		return DO;
	}
	
	
	
	private void debugpaint2(Collection toOda) {
		StringBuffer SB=new StringBuffer();
		for (Attribute iterable_element : toOda.getAtributos()) {
			SB.append(((ChasquiParseElement)iterable_element).toString("..."));
		}
		System.out.println(SB.toString());
		
		
	}


	private void procesa(DigitalObject digitalObject) {
		clonetablerep();
		ArrayList<AttributeInstance> Atributos=digitalObject.getSons();
		for (AttributeInstance attributeInstance : Atributos)
			processAtribute(attributeInstance);

		
	}


	


	private void processAtribute(AttributeInstance attributeInstance) {
		Integer quedan= tablaOV.get(attributeInstance.getHasType());
		if (quedan==null)
			{
			insertaOBuscaElemento(attributeInstance);
			}
		else {
			Integer total=tabla.get(attributeInstance.getHasType());
			if (total-quedan>0)
				{
				insertaOBuscaElementoMultiple(attributeInstance,total-quedan);
				}
			else 
			{
				insertaOBuscaElemento(attributeInstance);
				tablaOV.put(attributeInstance.getHasType(), quedan-1);
			}
			}
		
	}


	private void insertaOBuscaElementoMultiple(
			AttributeInstance attributeInstance, int i) {
		// TODO Auto-generated method stub
		
	}


	private void insertaOBuscaElemento(AttributeInstance attributeInstance) {
		if (attributeInstance.getHasType().getFather()==null)
		{
			if (!nuevoModelSet.contains(attributeInstance.getHasType()))
			{
				attributeInstance.getHasType().setSons(new ArrayList<Attribute>());
				NuevoModelo.add(attributeInstance.getHasType());
				nuevoModelSet.add(attributeInstance.getHasType());
			}
		}
	else {
			if (!nuevoModelSet.contains(attributeInstance.getHasType()))
			{
				if (!nuevoModelSet.contains(attributeInstance.getFatherAtribute().getHasType()))
					processAtribute(attributeInstance.getFatherAtribute());
				
				attributeInstance.getHasType().setSons(new ArrayList<Attribute>());
				attributeInstance.getFatherAtribute().getHasType().getSons().add(attributeInstance.getHasType());
				NuevoModelo.add(attributeInstance.getHasType());
				nuevoModelSet.add(attributeInstance.getHasType());
				
					
			}
		}
		
	}


	private void clonetablerep() {
		tablaOV=new HashMap<Attribute, Integer>();
		for (Entry<Attribute, Integer> iterable_element : tabla.entrySet()) {
			tablaOV.put(iterable_element.getKey(), iterable_element.getValue());
		}
		
	}


	public void debugpaint() {
		for (Entry<Attribute, Integer> iterable_element : tabla.entrySet()) {
				System.out.println(iterable_element.getKey().getName()+" :" + iterable_element.getValue());
		}
		
	}


	private void processAtributos(ArrayList<AttributeInstance> atributos) {
		while(!atributos.isEmpty())
		{
			AttributeInstance Atributo=atributos.get(0);
			int cantidad=findAndRemove(Atributo,atributos);
			evaluaSobreTabla(Atributo.getHasType(),cantidad);
		}
		
	}

	private void evaluaSobreTabla(Attribute attribute, int cantidad) {
		Integer entrada=tabla.get(attribute);
		if ((cantidad>1)&&(entrada==null||(entrada<cantidad)))
			{
			tabla.put(attribute, cantidad);
			}			
	}


	private int findAndRemove(AttributeInstance atributo,
			ArrayList<AttributeInstance> atributos) {
		int apariciones=0;
		ArrayList<AttributeInstance> ABorrar=new ArrayList<AttributeInstance>();
		for (AttributeInstance attributeInstance : atributos) {
			if (atributo.getFatherAtribute()==attributeInstance.getFatherAtribute()
					&& atributo.getHasType()==attributeInstance.getHasType())
				{
				apariciones++;
				ABorrar.add(attributeInstance);
				}
		}
		atributos.remove(atributo);
		for (AttributeInstance attributeInstance : ABorrar) {
			atributos.remove(attributeInstance);
		}
		return apariciones;
	}

	private ArrayList<AttributeInstance> cloneList(
			ArrayList<AttributeInstance> sons) {
		ArrayList<AttributeInstance> salida=new ArrayList<AttributeInstance>();
		for (AttributeInstance attributeInstance : sons) {
			salida.add(attributeInstance);
		}
		return salida;
	}
}
