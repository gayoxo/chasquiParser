package oda.parser;

import general.server.msqlconection.MySQLConnectionOdA;

import java.util.ArrayList;
import java.util.HashMap;
import shared.model.collection.Collection;
import shared.model.collection.attribute.Attribute;
import shared.model.collection.attribute.ControlledAttribute;
import shared.model.collection.attribute.DateAttribute;
import shared.model.collection.attribute.NumericAttribute;
import shared.model.collection.attribute.TextAttribute;
import shared.model.collection.attribute.controlled.Vocabulary;
import shared.model.collection.digitalobjects.DigitalObject;


public class OdA {
	
	private Collection toOda;
	private HashMap<Attribute, Integer> ModeloOda;
	private HashMap<Vocabulary, Integer> Vocabularios;

	public OdA(Collection coleccion) {
		toOda=coleccion;
		ModeloOda=new HashMap<Attribute, Integer>();
		Vocabularios=new HashMap<Vocabulary, Integer>();
	}

	public void preocess() {
		
		PreProceso p=new PreProceso();
		ArrayList<DigitalObject> DO=p.preproccesModel(toOda);		
		processModelo(toOda.getAtributos(),1);
		processOV(DO);
		
	}

	



	private void processModelo(ArrayList<Attribute> modelo, int padre) {
	
		for (Attribute attribute : modelo) {
			String Name = attribute.getName().replaceAll("'", "\\\\'");
			String Browser;
			if (attribute.isBrowseable())
				Browser="S";
			else Browser="N";
			int Salida=2;
			if (attribute instanceof TextAttribute){
				Salida=MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`section_data` (`idpadre`, `nombre`, `visible`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+Name+"', 'S','"+Browser+"' , 'T', 'S', '0');");
			}
			else if (attribute instanceof NumericAttribute){
				Salida=MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`section_data` (`idpadre`, `nombre`, `visible`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+Name+"', 'S','"+Browser+"' , 'N', 'S', '0');");
			}
			else if (attribute instanceof DateAttribute){
				Salida=MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`section_data` (`idpadre`, `nombre`, `visible`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+Name+"', 'S','"+Browser+"' , 'D', 'S', '0');");
			}
			else if (attribute instanceof ControlledAttribute){
				Integer otro=Vocabularios.get(((ControlledAttribute) attribute).getVocabulary());
				String catalogocomp;
				if (otro!=null)
				catalogocomp=otro.toString();
				else catalogocomp="1";
				Salida=MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`section_data` (`idpadre`, `nombre`, `visible`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+Name+"', 'S','"+Browser+"' , 'C', 'S', '"+catalogocomp+"');");
				Vocabularios.put(((ControlledAttribute) attribute).getVocabulary(),Salida );
			}else{
			
				Salida=MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`section_data` (`idpadre`, `nombre`, `visible`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+Name+"', 'S','"+Browser+"' , 'X', 'S', '0');");
			}
			ModeloOda.put(attribute, Salida);
			processModelo(attribute.getSons(),Salida);
				
		} 
	}

	private void processOV(ArrayList<DigitalObject> dO) {
		for (DigitalObject digitalObject : dO) {
			saveOV(digitalObject);
		}
		
	}

	private void saveOV(DigitalObject value) {
		MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`virtual_object` (`id`, `ispublic`) VALUES ("+value.getIdentifier()+", 'S');");
		String descripcion=value.getDescription();
		descripcion=descripcion.replaceAll("'", "\\\\'");
		//111 Atributo Descripcion
		MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`text_data` (`idov`, `idseccion`, `value`) VALUES ("+value.getIdentifier()+", 111, '"+descripcion+"');");
	}

}
