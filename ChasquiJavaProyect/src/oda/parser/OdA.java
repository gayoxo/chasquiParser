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
import shared.model.collection.digitalobjects.resources.DOResource;
import shared.model.collection.digitalobjects.resources.ExternalResource;
import shared.model.collection.digitalobjects.resources.LocalResource;
import shared.model.collection.digitalobjects.resources.Resource;


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
		processModeloIniciales(toOda.getAtributos());
		processOV(DO);
		
	}

	



	private void processModeloIniciales(ArrayList<Attribute> modelo) {
		for (Attribute attribute : modelo) {
			int Salida=3;
			if (attribute.getName().equals("Metadatos"))
				{
				Salida=2;
				ModeloOda.put(attribute, Salida);
				processModelo(attribute.getSons(),Salida);
				}
			else{ 
			int padre=1;
			String Name = attribute.getName().replaceAll("'", "\\\\'");
			String Browser;
			if (attribute.isBrowseable())
				Browser="S";
			else Browser="N";
			if (attribute instanceof TextAttribute){
				Salida=insertIntoFather(padre,Name,Browser,'T',"0");
			}
			else if (attribute instanceof NumericAttribute){
				Salida=insertIntoFather(padre,Name,Browser,'N',"0");
			}
			else if (attribute instanceof DateAttribute){
				Salida=insertIntoFather(padre,Name,Browser,'D',"0");
			}
			else if (attribute instanceof ControlledAttribute){
				Integer otro=Vocabularios.get(((ControlledAttribute) attribute).getVocabulary());
				String catalogocomp;
				if (otro!=null)
				catalogocomp=otro.toString();
				else catalogocomp="1";
				Salida=insertIntoFather(padre,Name,Browser,'C',catalogocomp);
				Vocabularios.put(((ControlledAttribute) attribute).getVocabulary(),Salida );
			}else{
				Salida=insertIntoFather(padre,Name,Browser,'X',"0");
			}
			ModeloOda.put(attribute, Salida);
			processModelo(attribute.getSons(),Salida);
			}
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
				Salida=MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `section_data` (`idpadre`, `nombre`, `visible`,`orden`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+Name+"', 'S','"+pos+"','"+Browser+"' , 'T', 'S', '0');");
			}
			else if (attribute instanceof NumericAttribute){
				Salida=MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `section_data` (`idpadre`, `nombre`, `visible`,`orden`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+Name+"', 'S','"+pos+"','"+Browser+"' , 'N', 'S', '0');");
			}
			else if (attribute instanceof DateAttribute){
				Salida=MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `section_data` (`idpadre`, `nombre`, `visible`,`orden`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+Name+"', 'S','"+pos+"','"+Browser+"' , 'F', 'S', '0');");
			}
			else if (attribute instanceof ControlledAttribute){
				Integer otro=Vocabularios.get(((ControlledAttribute) attribute).getVocabulary());
				String catalogocomp;
				if (otro!=null)
				catalogocomp=otro.toString();
				else catalogocomp="1";
				Salida=MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `section_data` (`idpadre`, `nombre`, `visible`,`orden`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+Name+"', 'S','"+pos+"','"+Browser+"' , 'C', 'S', '"+catalogocomp+"');");
				Vocabularios.put(((ControlledAttribute) attribute).getVocabulary(),Salida );
			}else{
			
				Salida=MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `section_data` (`idpadre`, `nombre`, `visible`,`orden`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+Name+"', 'S','"+pos+"','"+Browser+"' , 'X', 'S', '0');");
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
					
					Object datoO= rs.getObject("MAX(orden)");
					String Dato="0";
					if (datoO!=null)
						Dato=datoO.toString();
					int orden=Integer.parseInt(Dato);
					orden++;
					int Salida= MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `section_data` (`idpadre`, `nombre`, `visible`,`orden`, `browseable`, `tipo_valores`, `extensible`, `vocabulario`) VALUES ('"+padre+"','"+name+"', 'S','"+orden+"','"+browser+"' , '"+Tipo+"', 'S', '"+catalogo+"');");
				
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
		MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `virtual_object` (`id`, `ispublic`) VALUES ("+value.getIdentifier()+", 'S');");
		String descripcion=value.getDescription();
		descripcion=descripcion.replaceAll("'", "\\\\'");
		//111 Atributo Descripcion
		MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `text_data` (`idov`, `idseccion`, `value`) VALUES ("+value.getIdentifier()+", 111, '"+descripcion+"');");
		procesa_Atributos(value.getSons(),value);
		procesa_recursos(value.getRecursos(),value);
		procesa_icono();
	}

	private void procesa_icono() {
		// TODO icono del OV
		
	}

	private void procesa_recursos(ArrayList<Resource> recursos,
			DigitalObject DO) {
		int idov=DO.getIdentifier();
		for (Resource resource : recursos) {
			String iconoov;
			if (resource.equals(DO.getIcono()))
				iconoov="S";
			else iconoov="N";
			boolean visBool=resource.isVisible();
			String VisString;
			if (visBool) 
					VisString="S";
			else 
				VisString="N";
			if (resource instanceof LocalResource)
			{
			//MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `resources` (`idov`, `visible`, `name`, `idov_refered`, `idresource_refered`, `type`) VALUES ('"+idov+"', '"+VisString+"', '"+Name+"', 1, 1,P)");
				String Name=((LocalResource) resource).getName();
				
				MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `resources` (`idov`, `visible`,`iconoov`, `name`, `type`) VALUES ('"+idov+"', '"+VisString+"','"+iconoov+"', '"+Name+"', 'P' )");
				
			}
			else if (resource instanceof DOResource)
			{
				Integer idovref = ((DOResource) resource).getReferencia().getIdentifier();
				MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `resources` (`idov`, `visible`,`iconoov`, `idov_refered`, `type`) VALUES ('"+idov+"', '"+VisString+"','"+iconoov+"', '"+idovref+"','OV')");	
			}
			else if (resource instanceof ExternalResource)
			{
				String Name=((LocalResource)((ExternalResource) resource).getTarget()).getName();
				Integer idresource_refered=((LocalResource)((ExternalResource) resource).getTarget()).getPadre().getIdentifier();
				MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `resources` (`idov`, `visible`, `name`,`idresource_refered`, `type`) VALUES ('"+idov+"', '"+VisString+"', '"+Name+"', '"+idresource_refered+"','F')");
			}

		}
		
	}

	private void procesa_Atributos(ArrayList<AttributeInstance> sons, DigitalObject DO) {
		int idov=DO.getIdentifier();
		for (AttributeInstance attributeInstance : sons) {
			int seccion=ModeloOda.get(attributeInstance.getHasType());
			if (attributeInstance instanceof TextAttributeInstance){
				String value = ((TextAttributeInstance) attributeInstance).getValor();
				MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `text_data` (`idov`, `idseccion`, `value`) VALUES ('"+idov+"', '"+seccion+"', '"+value+"');");
			}
			else if (attributeInstance instanceof NumericAttributeInstance){
				
				float value=((NumericAttributeInstance) attributeInstance).getValor();
				MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `numeric_data` (`idov`, `idseccion`, `value`) VALUES ('"+idov+"', '"+seccion+"', '"+value+"');");
			}
			else if (attributeInstance instanceof DateAttributeInstance){
				Date valueD = ((DateAttributeInstance) attributeInstance).getFecha();
				DateFormat df = new SimpleDateFormat ("yyyyMMdd");
				String value=df.format(valueD);
				//INSERT INTO `date_data` (`idov`, `idseccion`, `value`) VALUES (1, 1, 12-10-2020);
				MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `date_data` (`idov`, `idseccion`, `value`) VALUES ('"+idov+"', '"+seccion+"', '"+value+"');");
			}
			else if (attributeInstance instanceof ControlledAttributeInstance){
				Term term = ((ControlledAttributeInstance) attributeInstance).getTermino();
				if (term!=null)
					{String value = term.getTerm();
				MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `controlled_data` (`idov`, `idseccion`, `value`) VALUES ('"+idov+"', '"+seccion+"', '"+value+"');");
					}

			}
		}
		
	}

}
