package chasqui.parser.coleccion.atributos.categoria.metadatos;

import java.sql.ResultSet;
import java.sql.SQLException;

import chasqui.client.main.Escritor;
import chasqui.model.collection.attribute.Attribute;
import chasqui.parser.ChasquiParseElement;
import chasqui.parser.coleccion.atributos.ExtendAttribute;
import chasqui.parser.coleccion.atributos.ExtendTextAttribute;
import chasqui.parser.coleccion.intanciasatributos.ExtendTextAttributeInstance;
import chasqui.parser.coleccion.objetosdigitales.ExtendDigitalObject;
import chasqui.server.msqlconection.MySQLConnection;

public class Atributos_metadatos_Categoria_Contribucion_ExtendAttribute
		extends ExtendAttribute {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4682508839089329463L;
	private Atributos_metadatos_Categoria_Contribucion_Fecha_ExtendDateAttribute AMFecha;
	private Atributos_metadatos_Categoria_Contribucion_Autor_ExtendTextAttribute AMAutor;
	private Atributos_metadatos_Categoria_Contribucion_Papel_ExtendControlledAttribute AMpapel;



	public Atributos_metadatos_Categoria_Contribucion_ExtendAttribute(
			String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
	}

	
	
	@Override
	public void Process() {

		AMAutor=new Atributos_metadatos_Categoria_Contribucion_Autor_ExtendTextAttribute("Autor", true, this);
		Sons.add(AMAutor);
		AMAutor.Process();
		AMpapel=new Atributos_metadatos_Categoria_Contribucion_Papel_ExtendControlledAttribute("Papel", true, this);
		Sons.add(AMpapel);
		AMpapel.Process();
		AMFecha=new Atributos_metadatos_Categoria_Contribucion_Fecha_ExtendDateAttribute("Fecha", true, this);
		Sons.add(AMFecha);
		AMFecha.Process();
		process_AtributeInstances();
		}
	
	private void process_AtributeInstances() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT * FROM chasqui2.metadatos where ruta='/manifest/metadata/lom/lifecycle/contribute/centity/vcard';");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					String idov=rs.getObject("idov").toString();
					Object temp=rs.getObject("contenido");
					String Valor="";
					if (temp!=null)
						Valor=temp.toString();
					if (idov!=null&&!idov.isEmpty())
						{
						ExtendDigitalObject DObject= Escritor.getChasqui().getDigitalObject(Integer.parseInt(idov));
						DObject.getSons().add(new ExtendTextAttributeInstance(this, pathFather(),Valor,DObject  ));
						//
						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
