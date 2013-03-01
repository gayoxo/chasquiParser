package chasqui.parser.coleccion;

import general.client.main.ChasquiToFIle;
import general.server.msqlconection.MySQLConnectionChasqui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map.Entry;

import shared.model.collection.attribute.Attribute;
import shared.model.collection.digitalobjects.DigitalObject;
import shared.model.collection.digitalobjects.resources.LocalResource;
import shared.model.collection.digitalobjects.resources.Resource;

import chasqui.parser.ChasquiParseElement;
import chasqui.parser.ResourceComparable;
import chasqui.parser.coleccion.atributos.categoria.Atributos_metadatos_Categoria_ExtendAttribute;
import chasqui.parser.coleccion.atributos.categoria.Atributos_texto_y_numerico_Categoria_ExtendAttribute;
import chasqui.parser.coleccion.atributos.categoria.Atributos_texto_y_numerico_Categoria_ExtendAttribute.Tabla;
import chasqui.parser.coleccion.objetosdigitales.ExtendDigitalObject;
import chasqui.parser.coleccion.objetosdigitales.ObjetoVirtualExtendDigitalObject;
import chasqui.parser.coleccion.objetosdigitales.recursos.ExtendDOResource;
import chasqui.parser.coleccion.objetosdigitales.recursos.ExtendExternalResource;
import chasqui.parser.coleccion.objetosdigitales.recursos.ExtendLocalResource;

public class ChasquiImplementationExtendCollection extends ExtendCollection{

	/**
	 * 
	 */
	private static final long serialVersionUID = -660414354602387594L;
	private static final String CATEGORIAS_VACIAS = " Existen filas con categorias vacias";
	private static final String OBJETOSVIRTUALESERRONEOS = " Objetos virtuales erroneos";

	public ChasquiImplementationExtendCollection() {
		super();
		
	}
@Override
public String toString() {
	return super.toString("");
}

	@Override
	public void Process() {
		genera_Objetos_Digitales();
		process_atributos_numericos();
		process_atributos_texto();
		process_atributos_metadatos();
		process_recursos();
		procesa_imagen_OVs();
		//debugPintaiconos();
		
	}
	public String debugPintaiconos() {
		StringBuffer SB=new StringBuffer();
		for (Entry<Integer, DigitalObject> OVindividual : getObjetosDigitales().entrySet()) {
			if (OVindividual.getValue().getIcono()!=null)
			{
				if (OVindividual.getValue().getIcono() instanceof ExtendLocalResource)
					{
					SB.append("sudo cp");
					SB.append(" ");
					SB.append("\"");
					SB.append(((ExtendLocalResource) OVindividual.getValue().getIcono()).getRuta());
					SB.append("\"");
					SB.append(" ");
					SB.append("\"");
					SB.append("iconos/");
					SB.append(OVindividual.getValue().getIdentifier());
					SB.append(".");
					SB.append(((ExtendLocalResource) OVindividual.getValue().getIcono()).getTipo());
					SB.append("\"");
					SB.append("\n");
										
					}
				else if (OVindividual.getValue().getIcono() instanceof ExtendExternalResource)
				{
					SB.append("sudo cp");
					SB.append(" ");
					SB.append("\"");
				SB.append(((ExtendExternalResource) OVindividual.getValue().getIcono()).getRuta());
				SB.append("\"");
				SB.append(" ");
				SB.append("\"");
				SB.append("iconos\\");
				SB.append(OVindividual.getValue().getIdentifier());
				SB.append(".");
				SB.append(((ExtendExternalResource) OVindividual.getValue().getIcono()).getTipo());
				SB.append("\"");
				SB.append("\n");
				}
				else {
					System.err.println("OV como icono: "+ OVindividual.getValue().getIdentifier() );
				}
				
			}
			
		}
		return SB.toString();
		
	}
	private void procesa_imagen_OVs() {
		for (Entry<Integer, DigitalObject> OVindividual : getObjetosDigitales().entrySet()) {
			calculasuIcono(OVindividual.getValue());
		}
		
	}
	private void calculasuIcono(DigitalObject digitalObject) {
		Resource Final=null;
		for (Resource recurso : digitalObject.getRecursos()) {
			if (((ResourceComparable)recurso).getTipo().toLowerCase().equals("jpg"))
				if (Final==null)
					Final=recurso;
				else
					Final=((ResourceComparable)Final).compareMenor(recurso);
		}
		digitalObject.setIcono(Final);
		
	}
	private void process_recursos() {
		process_recursos_propios();
		process_recursos_OV();
		process_recursos_linkeados();
		
	}
	private void process_recursos_OV() {
		try {
			ResultSet rs=MySQLConnectionChasqui.RunQuerrySELECT("SELECT * FROM chasqui2.recursos WHERE tipo='OV'");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					
					/*
					 * Padre = padre;
		Name = name;
		DisplayName = displayName;
		Descripcion = descripcion;
		Tipo = tipo;
		this.visible = visible;
		this.ruta = ruta;
					 */
					String IdS=rs.getObject("id").toString();
					String Padre=rs.getObject("idov").toString();
					String Nombre=rs.getObject("nom_rec").toString();
					String ruta=rs.getObject("ruta").toString();
					Object nulable = rs.getObject("descripcion");
					String Descripcion="";
					if (nulable!=null)
						Descripcion=nulable.toString();
					Object nulable2 = rs.getObject("nom_rec_publico");
					String displayName=Nombre;
					if (nulable2!=null)
						displayName=nulable2.toString();
					Object nulable3 = rs.getObject("visible");
					String visible="NO";
					if (nulable3!=null)
						visible=nulable3.toString();
					String IdovRef=ruta.substring(0, ruta.length()-1);
					
					//
					Object typonull = rs.getObject("tipo");
					String typoString="OV";
					if (typonull!=null)
						typoString=typonull.toString();
					//
					
					if (Padre!=null&&!Padre.isEmpty()&&Nombre!=null&&!Nombre.isEmpty()&&
							visible!=null&&!visible.isEmpty()&&IdovRef!=null&&!IdovRef.isEmpty())
						{
						Integer Id = Integer.parseInt(IdS);
						Integer Idov = Integer.parseInt(Padre);
						Integer Idovrefe = Integer.parseInt(IdovRef);
						ExtendDigitalObject OVEDO=getDigitalObject(Idov);
						ExtendDigitalObject Reference=getDigitalObject(Idovrefe);
						boolean visiblebol;
						if (visible.toLowerCase().equals("SI".toLowerCase()))
							visiblebol=true;
					else visiblebol=false;
						if (displayName.isEmpty()) displayName=Nombre;
						ExtendDOResource LR=new ExtendDOResource(OVEDO, displayName, Descripcion, visiblebol,Reference,Id,typoString);
						OVEDO.getRecursos().add(LR);
						}
					else System.out.println(ChasquiToFIle.WARNING + "categorias vacias");

				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private void process_recursos_linkeados() {
		try {
			ResultSet rs=MySQLConnectionChasqui.RunQuerrySELECT("SELECT * FROM chasqui2.recursos WHERE tipo!='OV' AND ruta!='/'");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					
					/*
					 * Padre = padre;
		Name = name;
		DisplayName = displayName;
		Descripcion = descripcion;
		Tipo = tipo;
		this.visible = visible;
		this.ruta = ruta;
					 */
					String IdS=rs.getObject("id").toString();
					String Padre=rs.getObject("idov").toString();
					String Nombre=rs.getObject("nom_rec").toString();
					String ruta=rs.getObject("ruta").toString();
					Object nulable = rs.getObject("descripcion");
					String Descripcion="";
					if (nulable!=null)
						Descripcion=nulable.toString();
					Object nulable2 = rs.getObject("nom_rec_publico");
					String displayName=Nombre;
					if (nulable2!=null)
						displayName=nulable2.toString();
					String IdovRef=ruta.substring(0, ruta.length()-1);
					Object nulable3 = rs.getObject("visible");
					String visible="NO";
					if (nulable3!=null)
						visible=nulable3.toString();
					
					//
//					Object typonull = rs.getObject("tipo");
					String typoString="OV";
//					if (typonull!=null)
//						typoString=typonull.toString();
					//
					
					
					if (Padre!=null&&!Padre.isEmpty()&&Nombre!=null&&!Nombre.isEmpty()&&IdovRef!=null&&!IdovRef.isEmpty())
						{
						Integer Id = Integer.parseInt(IdS);
						Integer Idov = Integer.parseInt(Padre);
						Integer Idovrefe = Integer.parseInt(IdovRef);
						ExtendDigitalObject OVEDO=getDigitalObject(Idov);
						ExtendDigitalObject Reference=getDigitalObject(Idovrefe);
						Resource target=Reference.findResource(Nombre);
						boolean visiblebol;
						if (visible.toLowerCase().equals("SI".toLowerCase()))
							visiblebol=true;
					else visiblebol=false;
						if (displayName.isEmpty()) displayName=Nombre;
						ExtendExternalResource LR=new ExtendExternalResource(OVEDO, displayName, Descripcion, visiblebol,target,Id,typoString);
						OVEDO.getRecursos().add(LR);
						}
					else 
						System.out.println(ChasquiToFIle.WARNING + "categorias vacias");

				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private void process_recursos_propios() {
		try {
			ResultSet rs=MySQLConnectionChasqui.RunQuerrySELECT("SELECT * FROM chasqui2.recursos WHERE tipo!='OV' AND ruta='/';");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					
					/*
					 * Padre = padre;
		Name = name;
		DisplayName = displayName;
		Descripcion = descripcion;
		Tipo = tipo;
		this.visible = visible;
		this.ruta = ruta;
					 */
					String IdS=rs.getObject("id").toString();
					String Padre=rs.getObject("idov").toString();
					String Nombre=rs.getObject("nom_rec").toString();
//					String ruta=rs.getObject("ruta").toString();
					Object nulable = rs.getObject("descripcion");
					String Descripcion="";
					if (nulable!=null)
						Descripcion=nulable.toString();
					Object nulable2 = rs.getObject("nom_rec_publico");
					String displayName=Nombre;
					if (nulable2!=null)
						displayName=nulable2.toString();
					Object nulable3 = rs.getObject("visible");
					String visible="NO";
					if (nulable3!=null)
						visible=nulable3.toString();
					
					//
					Object typonull = rs.getObject("tipo");
					String typoString="OV";
					if (typonull!=null)
						typoString=typonull.toString();
					//
					
					
					if (Padre!=null&&!Padre.isEmpty()&&Nombre!=null&&!Nombre.isEmpty())
						{
						Integer Id = Integer.parseInt(IdS);
						Integer Idov = Integer.parseInt(Padre);
						ExtendDigitalObject OVEDO=getDigitalObject(Idov);
						boolean visiblebol;
						if (visible.toLowerCase().equals("SI".toLowerCase()))
								visiblebol=true;
						else visiblebol=false;
						if (displayName.isEmpty()) displayName=Nombre;
						ExtendLocalResource LR=new ExtendLocalResource(OVEDO, Nombre.toLowerCase(), displayName, Descripcion, visiblebol,Id,typoString);
						OVEDO.getRecursos().add(LR);
						RecursosGeneral.add(LR);
						}
					else System.out.println(ChasquiToFIle.WARNING + "categorias vacias");

				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private void genera_Objetos_Digitales() {
		try {
			ResultSet rs=MySQLConnectionChasqui.RunQuerrySELECT("SELECT * FROM chasqui2.objeto_virtual ORDER BY idov;");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					String Dato=rs.getObject("idov").toString();
					Object nulable = rs.getObject("descripcion");
					String Descripcion;
					if (nulable!=null)
						Descripcion=nulable.toString();
					else Descripcion="";
					//rs.getObject("descripcion").toString();
					//String Usuario=rs.getObject("idov").toString();
					if (Dato!=null&&!Dato.isEmpty()&&Descripcion!=null)
						{
						Integer Idov = Integer.parseInt(Dato);
						ObjetoVirtualExtendDigitalObject OVEDO=new ObjetoVirtualExtendDigitalObject(Idov,Descripcion);
						addDigitalObjects(OVEDO);					
						}
					else System.out.println(ChasquiToFIle.WARNING + OBJETOSVIRTUALESERRONEOS);
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private void process_atributos_texto() {
		try {
			ResultSet rs=MySQLConnectionChasqui.RunQuerrySELECT("SELECT distinct categoria FROM chasqui2.atributos_texto ORDER BY categoria;");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					String Dato=rs.getObject("categoria").toString();
					if (Dato!=null&&!Dato.isEmpty())
						{
						Atributos_texto_y_numerico_Categoria_ExtendAttribute ATCategoria=new Atributos_texto_y_numerico_Categoria_ExtendAttribute(Dato,false,null);
						ATCategoria=(Atributos_texto_y_numerico_Categoria_ExtendAttribute)((Attribute) addAtributos(ATCategoria));
						ATCategoria.Process(Tabla.ATRIBUTOS_TEXTO);
						
						}
					else System.out.println(ChasquiToFIle.WARNING + CATEGORIAS_VACIAS);
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}



	private void process_atributos_numericos() {
		try {
			ResultSet rs=MySQLConnectionChasqui.RunQuerrySELECT("SELECT distinct categoria FROM chasqui2.atributos_numericos;");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					//Proceso categoria
					String Dato=rs.getObject("categoria").toString();
					if (Dato!=null&&!Dato.isEmpty())
						{
						Atributos_texto_y_numerico_Categoria_ExtendAttribute ANCategoria=new Atributos_texto_y_numerico_Categoria_ExtendAttribute(Dato,false,null);
						ANCategoria=(Atributos_texto_y_numerico_Categoria_ExtendAttribute) ((Attribute) addAtributos(ANCategoria));
						ANCategoria.Process(Tabla.ATRIBUTOS_NUMERICOS);
						
						}
					else System.out.println(ChasquiToFIle.WARNING + CATEGORIAS_VACIAS);
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private void process_atributos_metadatos() {
		Atributos_metadatos_Categoria_ExtendAttribute AMCategoria = new Atributos_metadatos_Categoria_ExtendAttribute("Metadatos",false,null);
		AMCategoria.Process();
		addAtributos(AMCategoria);
		
	}
}
