package chasqui.parser.coleccion;

import java.sql.ResultSet;
import java.sql.SQLException;

import chasqui.client.main.Escritor;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.digitalobjects.resources.ExtendExternalResource;
import chasqui.model.collection.digitalobjects.resources.Resource;
import chasqui.parser.coleccion.atributos.categoria.Atributos_metadatos_Categoria_ExtendAttribute;
import chasqui.parser.coleccion.atributos.categoria.Atributos_texto_y_numerico_Categoria_ExtendAttribute;
import chasqui.parser.coleccion.atributos.categoria.Atributos_texto_y_numerico_Categoria_ExtendAttribute.Tabla;
import chasqui.parser.coleccion.objetosdigitales.ExtendDigitalObject;
import chasqui.parser.coleccion.objetosdigitales.ObjetoVirtualExtendDigitalObject;
import chasqui.parser.coleccion.objetosdigitales.recursos.ExtendDOResource;
import chasqui.parser.coleccion.objetosdigitales.recursos.ExtendLocalResource;
import chasqui.server.msqlconection.MySQLConnection;

public class ChasquiImplementationExtendCollection extends ExtendCollection{

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
		
	}
	private void process_recursos() {
		process_recursos_propios();
		process_recursos_OV();
		process_recursos_linkeados();
		
	}
	private void process_recursos_OV() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT * FROM chasqui2.recursos WHERE tipo='OV'");
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
					String Padre=rs.getObject("idov").toString();
					String Nombre=rs.getObject("nom_rec").toString();
					String Tipo=rs.getObject("tipo").toString();
					String visible=rs.getObject("visible").toString();
					String ruta=rs.getObject("ruta").toString();
					Object nulable = rs.getObject("descripcion");
					String Descripcion="";
					if (nulable!=null)
						Descripcion=nulable.toString();
					Object nulable2 = rs.getObject("nom_rec_publico");
					String displayName="";
					if (nulable2!=null)
						displayName=nulable2.toString();
					String IdovRef=ruta.substring(0, ruta.length()-1);
					if (Padre!=null&&!Padre.isEmpty()&&Nombre!=null&&!Nombre.isEmpty()&&Tipo!=null&&!Tipo.isEmpty()&&
							visible!=null&&!visible.isEmpty()&&IdovRef!=null&&!IdovRef.isEmpty())
						{
						Integer Idov = Integer.parseInt(Padre);
						Integer Idovrefe = Integer.parseInt(IdovRef);
						ExtendDigitalObject OVEDO=getDigitalObject(Idov);
						ExtendDigitalObject Reference=getDigitalObject(Idovrefe);
						boolean visiblebol=Boolean.parseBoolean(visible);
						ExtendDOResource LR=new ExtendDOResource(OVEDO, Nombre, displayName, Descripcion, Tipo, visiblebol,Reference);
						OVEDO.getRecursos().add(LR);
						}
					else System.out.println(Escritor.WARNING + "categorias vacias");

				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private void process_recursos_linkeados() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT * FROM chasqui2.recursos WHERE tipo!='OV' AND ruta!='/'");
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
					String Padre=rs.getObject("idov").toString();
					String Nombre=rs.getObject("nom_rec").toString();
					String Tipo=rs.getObject("tipo").toString();
					String ruta=rs.getObject("ruta").toString();
					Object nulable = rs.getObject("descripcion");
					String Descripcion="";
					if (nulable!=null)
						Descripcion=nulable.toString();
					Object nulable2 = rs.getObject("nom_rec_publico");
					String displayName="";
					if (nulable2!=null)
						displayName=nulable2.toString();
					String IdovRef=ruta.substring(0, ruta.length()-1);
					Object nulable3 = rs.getObject("visible");
					String visible="false";
					if (nulable3!=null)
						visible=nulable3.toString();
					if (Padre!=null&&!Padre.isEmpty()&&Nombre!=null&&!Nombre.isEmpty()&&IdovRef!=null&&!IdovRef.isEmpty())
						{
						Integer Idov = Integer.parseInt(Padre);
						Integer Idovrefe = Integer.parseInt(IdovRef);
						ExtendDigitalObject OVEDO=getDigitalObject(Idov);
						ExtendDigitalObject Reference=getDigitalObject(Idovrefe);
						Resource target=Reference.findResource(Nombre);
						boolean visiblebol=Boolean.parseBoolean(visible);
						ExtendExternalResource LR=new ExtendExternalResource(OVEDO, Nombre, displayName, Descripcion, Tipo, visiblebol,target);
						OVEDO.getRecursos().add(LR);
						}
					else 
						System.out.println(Escritor.WARNING + "categorias vacias");

				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private void process_recursos_propios() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT * FROM chasqui2.recursos WHERE tipo!='OV' AND ruta='/';");
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
					String Padre=rs.getObject("idov").toString();
					String Nombre=rs.getObject("nom_rec").toString();
					String Tipo=rs.getObject("tipo").toString();
//					String ruta=rs.getObject("ruta").toString();
					Object nulable = rs.getObject("descripcion");
					String Descripcion="";
					if (nulable!=null)
						Descripcion=nulable.toString();
					Object nulable2 = rs.getObject("nom_rec_publico");
					String displayName="";
					if (nulable2!=null)
						displayName=nulable2.toString();
					Object nulable3 = rs.getObject("visible");
					String visible="false";
					if (nulable3!=null)
						visible=nulable3.toString();
					if (Padre!=null&&!Padre.isEmpty()&&Nombre!=null&&!Nombre.isEmpty())
						{
						Integer Idov = Integer.parseInt(Padre);
						ExtendDigitalObject OVEDO=getDigitalObject(Idov);
						boolean visiblebol=Boolean.parseBoolean(visible);
						ExtendLocalResource LR=new ExtendLocalResource(OVEDO, Nombre, displayName, Descripcion, Tipo, visiblebol);
						OVEDO.getRecursos().add(LR);
						RecursosGeneral.add(LR);
						}
					else System.out.println(Escritor.WARNING + "categorias vacias");

				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private void genera_Objetos_Digitales() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT * FROM chasqui2.objeto_virtual ORDER BY idov;");
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
					else System.out.println(Escritor.WARNING + OBJETOSVIRTUALESERRONEOS);
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private void process_atributos_texto() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct categoria FROM chasqui2.atributos_texto ORDER BY categoria;");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					String Dato=rs.getObject("categoria").toString();
					if (Dato!=null&&!Dato.isEmpty())
						{
						Atributos_texto_y_numerico_Categoria_ExtendAttribute ATCategoria=new Atributos_texto_y_numerico_Categoria_ExtendAttribute(Dato,true,null);
						ATCategoria=(Atributos_texto_y_numerico_Categoria_ExtendAttribute)((Attribute) addAtributos(ATCategoria));
						ATCategoria.Process(Tabla.ATRIBUTOS_TEXTO);
						
						}
					else System.out.println(Escritor.WARNING + CATEGORIAS_VACIAS);
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}



	private void process_atributos_numericos() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct categoria FROM chasqui2.atributos_numericos;");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					//Proceso categoria
					String Dato=rs.getObject("categoria").toString();
					if (Dato!=null&&!Dato.isEmpty())
						{
						Atributos_texto_y_numerico_Categoria_ExtendAttribute ANCategoria=new Atributos_texto_y_numerico_Categoria_ExtendAttribute(Dato,true,null);
						ANCategoria=(Atributos_texto_y_numerico_Categoria_ExtendAttribute) ((Attribute) addAtributos(ANCategoria));
						ANCategoria.Process(Tabla.ATRIBUTOS_NUMERICOS);
						
						}
					else System.out.println(Escritor.WARNING + CATEGORIAS_VACIAS);
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private void process_atributos_metadatos() {
		Atributos_metadatos_Categoria_ExtendAttribute AMCategoria = new Atributos_metadatos_Categoria_ExtendAttribute("Metadatos",true,null);
		AMCategoria.Process();
		addAtributos(AMCategoria);
		
	}
}
