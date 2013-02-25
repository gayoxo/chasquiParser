package oda.parser;

import general.client.main.ChasquiToFIle;
import general.server.msqlconection.MySQLConnectionChasqui;
import general.server.msqlconection.MySQLConnectionOdA;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import chasqui.parser.coleccion.objetosdigitales.ObjetoVirtualExtendDigitalObject;
import shared.model.collection.Collection;
import shared.model.collection.attibuteInstance.AttributeInstance;
import shared.model.collection.attibuteInstance.ControlledAttributeInstance;
import shared.model.collection.attibuteInstance.DateAttributeInstance;
import shared.model.collection.attibuteInstance.NumericAttributeInstance;
import shared.model.collection.attibuteInstance.TextAttributeInstance;
import shared.model.collection.attribute.Attribute;
import shared.model.collection.attribute.ControlledAttribute;
import shared.model.collection.attribute.DateAttribute;
import shared.model.collection.attribute.NumericAttribute;
import shared.model.collection.attribute.TextAttribute;
import shared.model.collection.attribute.controlled.Term;
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
		processModeloIniciales(toOda.getAtributos(),1);
		processOV(DO);
		
	}

	



	private void processModeloIniciales(ArrayList<Attribute> modelo, int padre) {
		for (Attribute attribute : modelo) {
			String Name = attribute.getName().replaceAll("'", "\\\\'");
			String Browser;
			if (attribute.isBrowseable())
				Browser="S";
			else Browser="N";
			int Salida=2;
			if (attribute instanceof TextAttribute){
				Salida=insertIntoFather(padre,Name,Browser,'T',"0");
			}
			else if (attribute instanceof NumericAttribute){
				Salida=insertIntoFather(padre,Name,Browser,'N',"0");
				//Salida=MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`section_data` (`idpadre`, `nombre`, `visible`,`orden`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+Name+"', 'S','1','"+Browser+"' , 'N', 'S', '0');");
			}
			else if (attribute instanceof DateAttribute){
				Salida=insertIntoFather(padre,Name,Browser,'D',"0");
				//Salida=MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`section_data` (`idpadre`, `nombre`, `visible`,`orden`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+Name+"', 'S','1','"+Browser+"' , 'D', 'S', '0');");
			}
			else if (attribute instanceof ControlledAttribute){
				Integer otro=Vocabularios.get(((ControlledAttribute) attribute).getVocabulary());
				String catalogocomp;
				if (otro!=null)
				catalogocomp=otro.toString();
				else catalogocomp="1";
				Salida=insertIntoFather(padre,Name,Browser,'C',catalogocomp);
				//Salida=MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`section_data` (`idpadre`, `nombre`, `visible`,`orden`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+Name+"', 'S','1','"+Browser+"' , 'C', 'S', '"+catalogocomp+"');");
				Vocabularios.put(((ControlledAttribute) attribute).getVocabulary(),Salida );
			}else{
				Salida=insertIntoFather(padre,Name,Browser,'X',"0");
				//Salida=MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`section_data` (`idpadre`, `nombre`, `visible`,`orden`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+Name+"', 'S','1','"+Browser+"' , 'X', 'S', '0');");
			}
			ModeloOda.put(attribute, Salida);
			processModelo(attribute.getSons(),Salida);
				
		}
	}

	private void processModelo(ArrayList<Attribute> modelo, int padre) {
	
		int pos =1;
		for (Attribute attribute : modelo) {
			String Name = attribute.getName().replaceAll("'", "\\\\'");
			String Browser;
			if (attribute.isBrowseable())
				Browser="S";
			else Browser="N";
			int Salida=2;
			if (attribute instanceof TextAttribute){
				Salida=MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`section_data` (`idpadre`, `nombre`, `visible`,`orden`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+Name+"', 'S','"+pos+"','"+Browser+"' , 'T', 'S', '0');");
			}
			else if (attribute instanceof NumericAttribute){
				Salida=MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`section_data` (`idpadre`, `nombre`, `visible`,`orden`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+Name+"', 'S','"+pos+"','"+Browser+"' , 'N', 'S', '0');");
			}
			else if (attribute instanceof DateAttribute){
				Salida=MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`section_data` (`idpadre`, `nombre`, `visible`,`orden`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+Name+"', 'S','"+pos+"','"+Browser+"' , 'F', 'S', '0');");
			}
			else if (attribute instanceof ControlledAttribute){
				Integer otro=Vocabularios.get(((ControlledAttribute) attribute).getVocabulary());
				String catalogocomp;
				if (otro!=null)
				catalogocomp=otro.toString();
				else catalogocomp="1";
				Salida=MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`section_data` (`idpadre`, `nombre`, `visible`,`orden`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+Name+"', 'S','"+pos+"','"+Browser+"' , 'C', 'S', '"+catalogocomp+"');");
				Vocabularios.put(((ControlledAttribute) attribute).getVocabulary(),Salida );
			}else{
			
				Salida=MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`section_data` (`idpadre`, `nombre`, `visible`,`orden`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+Name+"', 'S','"+pos+"','"+Browser+"' , 'X', 'S', '0');");
			}
			ModeloOda.put(attribute, Salida);
			processModelo(attribute.getSons(),Salida);
			pos++;	
		} 
	}

	private int insertIntoFather(int padre, String name, String browser, char Tipo, String catalogo) {
		try {
			ResultSet rs=MySQLConnectionOdA.RunQuerrySELECT("SELECT MAX(orden) FROM oda2011.section_data WHERE idpadre="+padre+";");
			if (rs!=null) 
			{
				rs.next();
					
					String Dato=rs.getObject("MAX(orden)").toString();
					int orden=Integer.parseInt(Dato);
					orden++;
					int Salida= MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`section_data` (`idpadre`, `nombre`, `visible`,`orden`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+name+"', 'S','"+orden+"','"+browser+"' , '"+Tipo+"', 'S', '"+catalogo+"');");
				
			rs.close();
			return Salida;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
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
		procesa_Atributos(value.getSons(),value);
		
	}

	private void procesa_Atributos(ArrayList<AttributeInstance> sons, DigitalObject DO) {
		int idov=DO.getIdentifier();
		for (AttributeInstance attributeInstance : sons) {
			int seccion=ModeloOda.get(attributeInstance.getHasType());
			if (attributeInstance instanceof TextAttributeInstance){
				String value = ((TextAttributeInstance) attributeInstance).getValor();
				MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`text_data` (`idov`, `idseccion`, `value`) VALUES ('"+idov+"', '"+seccion+"', '"+value+"');");
			}
			else if (attributeInstance instanceof NumericAttributeInstance){
				
				float value=((NumericAttributeInstance) attributeInstance).getValor();
				MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`numeric_data` (`idov`, `idseccion`, `value`) VALUES ('"+idov+"', '"+seccion+"', '"+value+"');");
			}
			else if (attributeInstance instanceof DateAttributeInstance){
				Date valueD = ((DateAttributeInstance) attributeInstance).getFecha();
				DateFormat df = new SimpleDateFormat ("yyyyMMdd");
				String value=df.format(valueD);
				//INSERT INTO `oda2011`.`date_data` (`idov`, `idseccion`, `value`) VALUES (1, 1, 12-10-2020);
				MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`date_data` (`idov`, `idseccion`, `value`) VALUES ('"+idov+"', '"+seccion+"', '"+value+"');");
			}
			else if (attributeInstance instanceof ControlledAttributeInstance){
				Term term = ((ControlledAttributeInstance) attributeInstance).getTermino();
				if (term!=null)
					{String value = term.getTerm();
				MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`controlled_data` (`idov`, `idseccion`, `value`) VALUES ('"+idov+"', '"+seccion+"', '"+value+"');");
					}

			}
		}
		
	}

}
