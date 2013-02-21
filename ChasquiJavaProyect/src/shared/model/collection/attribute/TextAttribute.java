package shared.model.collection.attribute;


public abstract class TextAttribute  extends Attribute{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4609921347620938010L;

	public TextAttribute(String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
	}

}
