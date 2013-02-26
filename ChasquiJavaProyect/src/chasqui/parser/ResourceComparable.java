package chasqui.parser;

import shared.model.collection.digitalobjects.resources.Resource;

public interface ResourceComparable {

	public Resource compareMenor(Resource candidato);
	
	public int getiden();
	
	public String getTipo();
}
