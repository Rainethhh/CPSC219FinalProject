import java.util.HashMap;
import java.util.Map;

/**
 * This class takes strings and transforms them into sprites which can be
 * displayed in the console. It works with the Pictures class to take already
 * set strings and turn them into sprites which can then be placed using the
 * class. It also finds the sprite's data like the width and height.
 * 
 * @author Aaron and Raine
 *
 */
public class AsciiArt {
	public static Map<Character, Double> refConversions = new HashMap<Character, Double>();

	private String[] model;
	private int layer = 0;
	private boolean transparentSpaces = true;
	private int width = 0;
	private int height = 0;

	private int xPos = 0;
	private int yPos = 0;
	private String referenceFrame;

	/**
	 * Constructor that creates an instance of AsciiArt.
	 * 
	 * @param layer             determines which layer the sprite is on, higher
	 *                          numbers drawn over top of those with lower numbers.
	 * @param transparentSpaces a boolean indicating if the sprite has transparent
	 *                          spaces.
	 * @param artString         the actual sprite in the form of a string
	 */
	public AsciiArt(int layer, boolean transparentSpaces, String artString) {
		this.layer = layer;
		this.transparentSpaces = transparentSpaces;
		modelAndDimensions(artString);
		initializeRefConversions();
	}

	/**
	 * This is a copy constructor meant to duplicate another instance of AsciiArt.
	 * 
	 * @param sprite This is another instance of AsciiArt which is to be copied.
	 */
	public AsciiArt(AsciiArt sprite) {
		model = new String[sprite.model.length];
		for (int i = 0; i < sprite.model.length; i++)
			model[i] = sprite.model[i];
		width = sprite.width;
		height = sprite.height;
		xPos = sprite.xPos;
		yPos = sprite.yPos;
		layer = sprite.layer;
		transparentSpaces = sprite.transparentSpaces;
		initializeRefConversions();
	}

	/**
	 * gives the string representation of the art
	 * @return modelString string representation
	 */
	public String toString() {
		String modelString = "";
		for (int i = 0; i < height; i++) {
			modelString += model[i] + "\n";
		}
		return modelString;
	}

	/**
	 * This method takes the white space found in some string art and removes as
	 * much as possible while maintaining the string image.
	 * 
	 * @param n this is how many white spaces can be safely removed from the string
	 *          art.
	 */
	public void trimLeadingSpaces(int n) {
		for (int i = 0; i < height; i++) {
			model[i] = model[i].substring(n);
		}
		System.out.println(this.toString());
	}

	/**
	 * This getter method returns the model of what it is called on.
	 * 
	 * @return This is the specific model to what the getter method was called on
	 */
	public String[] getModel() {
		return model;
	}

	/**
	 * This is the setter method meant to link something to a specific model
	 * 
	 * @param model This is the model what this method was called on will be linked
	 *              to
	 */
	public void setModel(String[] model) {
		this.model = model;
	}

	/**
	 * This getter method returns the integer width of what it was called on,
	 * assuming it has a width
	 * 
	 * @return this is the integer representation of the width of what this was
	 *         called on
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * This setter method sets the width of what it was called on to an integer
	 * specified
	 * 
	 * @param width This is the integer the width will be set to
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * This getter method returns the integer height of what it was called on,
	 * assuming it has a height
	 * 
	 * @return this is the integer representation of the height of what this was
	 *         called on
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * This setter method sets the height of what it was called on to an integer
	 * specified
	 * 
	 * @param height This is the integer the height will be set to
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * This getter method returns an object's horizontal position in the plane
	 * 
	 * @return The integer representation of an objects horizontal position in the
	 *         plane
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * This setter method sets an objects horizontal position in the plane
	 * 
	 * @param xPos This is the integer horizontal position the object will be set to
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * This getter method returns an object's vertical position in the plane
	 * 
	 * @return The integer representation of an objects vertical position in the
	 *         plane
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * This setter method sets an objects vertical position in the plane
	 * 
	 * @param yPos This is the integer vertical position the object will be set to
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	/**
	 * This method returns the boolean value of if a sprite has transparent spaces
	 * where there are " " or if they remain white. true if they are transparent,
	 * false otherwise.
	 * 
	 * @return The answer to if the spaces in a sprite are transparent
	 */
	public boolean getTransparentSpaces() {
		return transparentSpaces;
	}

	/**
	 * This method returns the number value of the layer a sprite will be displayed
	 * on
	 * 
	 * @return This is the value, the higher the integer, the higher priority it
	 *         will be displayed with in the foreground.
	 */
	public int getLayer() {
		return layer;
	}

	/**
	 * This method returns the reference frame being used
	 * 
	 * @return The string representation of the reference frame
	 */
	public String getReferenceFrame() {
		return referenceFrame;
	}

	/**
	 * This method updates the position of the sprite in the window being displayed.
	 * 
	 * @param xPhys          This is the physical horizontal position of the sprite
	 *                       in virtual space.
	 * @param yPhys          This is the physical vertical position of the sprite in
	 *                       virtual space.
	 * @param referenceFrame This is where the sprite's art is placed relative to its physical location
	 * 						 i.e. bottom centre = bc, top right = "tr"
	 * @return the data of the AsciiArt instance has been updated and returns the
	 *         new version.
	 */
	public AsciiArt updateWindowPos(double xPhys, double yPhys, String referenceFrame) {
		this.referenceFrame = referenceFrame;
		//xFactor and yFactor are how many meters wide and tall each letter is in the game.
		double yFactor = Globals.LETTER_HEIGHT;
		double xFactor = Globals.LETTER_WIDTH;

		char[] refs = referenceFrame.toCharArray();

		//offsets calculated with refernce frame
		int xOff = (int) (refConversions.get(refs[1]) * width);
		int yOff = (int) (refConversions.get(refs[0]) * height);
		
		//conversion from physical location in meters to physical location in terms of amount of characters
		xPos = (int) (xPhys / xFactor - xOff); 
		yPos = (int) (Globals.FIELD_HEIGHT - yPhys / yFactor - yOff);
		return this;
	}

	/**
	 * This method makes it simpler to place the sprites in the desired positions
	 * later in the program. t = top, c = center, b = bottom, l = left, r = right.
	 */
	private void initializeRefConversions() {
		refConversions.put('t', 0.0); 
		refConversions.put('c', 0.5);
		refConversions.put('b', 1.0);
		refConversions.put('l', 0.0);
		refConversions.put('r', 1.0);
	}

	/**
	 * This method removes the "\r\n" from string art while making it easy to print
	 * by setting it to the String[] instance variable model. It also finds the
	 * width and height of the string image.
	 * 
	 * @param modString This is the actual string form of the string image.
	 */
	private void modelAndDimensions(String modString) {
		model = modString.split("\r\n");
		height = model.length;
		width = calculateWidth();
	}

	/**
	 * This method finds how wide the widest point of the string image is.
	 * 
	 * @return the integer width of the widest point of the string image.
	 */
	private int calculateWidth() {
		int width = 0;
		for (String string : model) {
			if (string.length() > width) {
				width = string.length();
			}
		}
		return width;
	}
}