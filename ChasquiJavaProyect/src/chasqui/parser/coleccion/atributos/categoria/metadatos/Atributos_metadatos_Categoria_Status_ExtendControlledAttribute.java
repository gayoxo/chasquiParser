package chasqui.parser.coleccion.atributos.categoria.metadatos;

import java.sql.ResultSet;
import java.sql.SQLException;

import chasqui.client.main.Escritor;
import chasqui.model.collection.attribute.Attribute;
import chasqui.parser.coleccion.atributos.ExtendAttribute;
import chasqui.parser.coleccion.atributos.ExtendControlledAttribute;
import chasqui.parser.coleccion.atributos.ExtendTerm;
import chasqui.parser.coleccion.atributos.categoria.numericos.ExtendAttributeInstance;
import chasqui.parser.coleccion.intanciasatributos.ExtendControlledAttributeInstance;
import chasqui.parser.coleccion.objetosdigitales.ExtendDigitalObject;
import chasqui.server.msqlconection.MySQLConnection;

public class Atributos_metadatos_Categoria_Status_ExtendControlledAttribute
		extends ExtendControlledAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = -888323507305495887L;

	public Atributos_metadatos_Categoria_Status_ExtendControlledAttribute(
			String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
	}

	@Override
	public void Process() {
		process_Vocabulary();
		if (vocabulary.getList().isEmpty())
			Father.getSons().remove(this);
		process_AtributeInstances();
	}

	@Override
	protected void process_Vocabulary() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT distinct contenido FROM chasqui2.metadatos WHERE ruta='/manifest/metadata/lom/lifecycle/status/value/langstring' ORDER BY contenido;");
			if (rs!=null) 
			{
				while (rs.next()) {
					String Dato=rs.getObject("contenido").toString();
					if (Dato!=null&&!Dato.isEmpty())
						{
						ExtendTerm TerminoCandidato=new ExtendTerm(Dato);
						addTerm(TerminoCandidato);
						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void process_AtributeInstances() {
		try {
			ResultSet rs=MySQLConnection.RunQuerrySELECT("SELECT * FROM chasqui2.metadatos WHERE ruta='/manifest/metadata/lom/lifecycle/status/value/langstring' ORDER BY contenido;");
			if (rs!=null) 
			{
				while (rs.next()) {
					
					String idov=rs.getObject("idov").toString();
					Object temp=rs.getObject("contenido");
					String Valor="";
					if (temp!=null)
						Valor=temp.toString();
					if (idov!=null&&!idov.isEmpty()&&!Valor.isEmpty())
						{
						ExtendDigitalObject DObject= Escritor.getChasqui().getDigitalObject(Integer.parseInt(idov));
						ExtendAttributeInstance EAI = new ExtendAttributeInstance(this.getFather(), ((ExtendAttribute) this.getFather()).pathFather(),DObject,null);
						EAI=(ExtendAttributeInstance) DObject.saveAtributo(EAI);
						ExtendControlledAttributeInstance ECAI=new ExtendControlledAttributeInstance(this, pathFather(),DObject,EAI,findTerm(Valor));
						ECAI=(ExtendControlledAttributeInstance) DObject.saveAtributo(ECAI);
						}
					
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
