package oda.parser;

import general.server.msqlconection.MySQLConnectionChasqui;

import java.io.FileWriter;
import java.io.PrintWriter;
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
	private HashMap<Attribute,ArrayList<Attribute>> nuevos_elementos;

	
	public PreProceso() {
		tabla=new HashMap<Attribute, Integer>();
		NuevoModelo=new ArrayList<Attribute>();
		nuevoModelSet=new HashSet<Attribute>();
		nuevos_elementos=new HashMap<Attribute,ArrayList<Attribute>>();
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
		
		
	//	debugpaint();
	//	debugpaint2(toOda);
		
		
		toOda.setAtributos(NuevoModelo);
toOda.ordena();
debugpaint3(toOda);
		return DO;
		
	}
	
	
	
	private void debugpaint3(Collection toOda) {
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {

        	
            fichero = new FileWriter("debug.txt");
            pw = new PrintWriter(fichero);
            pw.println("Collection: " + MySQLConnectionChasqui.getDBSelected());
                pw.println(toOda.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
		
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
				tablaOV.put(attributeInstance.getHasType(), new Integer(quedan-1));
				insertaOBuscaElementoMultiple(attributeInstance,total-quedan+1);
				}
			else 
			{
				insertaOBuscaElemento(attributeInstance);
				inserteEnAtributosNuevos(attributeInstance);
				tablaOV.put(attributeInstance.getHasType(), new Integer(quedan-1));
			}
			}
		
	}


	private void inserteEnAtributosNuevos(AttributeInstance attributeInstance) {
		ArrayList<Attribute> lista=nuevos_elementos.get(attributeInstance.getHasType());
		if (lista==null)
			{
			lista=new ArrayList<Attribute>();
			lista.add(attributeInstance.getHasType());
			nuevos_elementos.put(attributeInstance.getHasType(), lista);
			}
		
	}


	private void insertaOBuscaElementoMultiple(
			AttributeInstance attributeInstance, int i) {
		if (attributeInstance.getHasType().getFather()==null)
		{
			Attribute nuevo=findinLista(attributeInstance,i);
			if (nuevo==null)
				nuevo=generaNuevo(attributeInstance,i);
				attributeInstance.setHasType(nuevo);
				NuevoModelo.add(attributeInstance.getHasType());
				nuevoModelSet.add(attributeInstance.getHasType());
			
		}
	else {
		Attribute nuevo=findinLista(attributeInstance,i);
		if (nuevo==null)
			{
			nuevo=generaNuevo(attributeInstance,i);
			attributeInstance.setHasType(nuevo);
			attributeInstance.getHasType().setSons(new ArrayList<Attribute>());
			attributeInstance.getFatherAtribute().getHasType().getSons().add(attributeInstance.getHasType());
			nuevoModelSet.add(attributeInstance.getHasType());
			}
		else {
			attributeInstance.setHasType(nuevo);
			if (!nuevoModelSet.contains(attributeInstance.getFatherAtribute().getHasType()))
				processAtribute(attributeInstance.getFatherAtribute());	
		}
				

		}
		
	}


	private Attribute generaNuevo(AttributeInstance attributeInstance, int i) {
		Attribute A=null;
		try {
			A=(Attribute) attributeInstance.getHasType().clone();
			A.setName(A.getName()+i);
			ArrayList<Attribute> lista=nuevos_elementos.get(attributeInstance.getHasType());
			lista.add(A);
			nuevos_elementos.put(attributeInstance.getHasType(), lista);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return A;
	}


	private Attribute findinLista(AttributeInstance attributeInstance, int i) {
		ArrayList<Attribute> lista=nuevos_elementos.get(attributeInstance.getHasType());
		if (lista.size()>i-1)
			return lista.get(i-1);
		else return null;
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
			if (!nuevoModelSet.contains(attributeInstance.getFatherAtribute().getHasType()))
				processAtribute(attributeInstance.getFatherAtribute());
			 if (!nuevoModelSet.contains(attributeInstance.getHasType()))
			{	
				attributeInstance.getHasType().setSons(new ArrayList<Attribute>());
				attributeInstance.getFatherAtribute().getHasType().getSons().add(attributeInstance.getHasType());
				nuevoModelSet.add(attributeInstance.getHasType());
				
					
			}else if (attributeInstance.getFatherAtribute().getHasType()!=attributeInstance.getHasType().getFather())
			{
				
				Attribute A=findInMyFather(attributeInstance.getFatherAtribute().getHasType(),attributeInstance.getHasType());
				if (A==null)
				{
					try {
						A=(Attribute) attributeInstance.getHasType().clone();
						attributeInstance.setHasType(A);
						attributeInstance.getHasType().setSons(new ArrayList<Attribute>());
						attributeInstance.getFatherAtribute().getHasType().getSons().add(attributeInstance.getHasType());
						nuevoModelSet.add(attributeInstance.getHasType());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else 
					attributeInstance.setHasType(A);
				
				
			}	

			
		}
		
	}


	private Attribute findInMyFather(Attribute padre, Attribute Yo) {
		for (Attribute candidato : padre.getSons()) {
			if (candidato.getName().equals(Yo.getName()))
				return candidato;
		}
		return null;
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
