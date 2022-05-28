import java.util.ArrayList;

/**
 * This class creates sets of global variables during the application for the
 * calculations (gravity, max and min wind speed) as well as how the animation
 * in the window is run (frame rate, physics updates, how many meters a
 * character represents, window dimensions). This class determines the
 * conditions under which the application will be run as well as setting the
 * acceptable user responses that may be given during the running of the application.
 * 
 * @author Aaron and Raine
 *
 */
public class Globals {
	public static final int WINDOW_WIDTH = 240;
	public static final int WINDOW_HEIGHT = 40;
	public static final int FIELD_WIDTH = 2500;
	public static final int FIELD_HEIGHT = 600;
	public static final int LINES_BETWEEN_WINDOWS = 10;

	public static ArrayList<String> weaponTypes = new ArrayList<String>();
	public static ArrayList<String> sizeNames = new ArrayList<String>();
	public static final double GRAVITY = -30;
	public static final double DRAG_CONSTANT = 15;
	public static final long FRAME_RATE = 100;
	public static final int PHYSICS_UPDATES_PER_FRAME = 100;
	public static final double LETTER_HEIGHT = 2.5; // should be the width x2 to x2.5
	public static final double LETTER_WIDTH = 1.0;
	public static final int MIN_WIND_SPEED = -200;
	public static final int MAX_WIND_SPEED = 200;

	private static final Globals INSTANCE = new Globals();

	/**
	 * Default constructor that automatically sets the allowed weapon and size
	 * names.
	 */
	public Globals() {
		setWeaponTypes();
		setSizeNames();
	}

	/**
	 * This method sets the names of the weapons that the user may choose from in a
	 * String array.
	 */
	private void setWeaponTypes() {
		weaponTypes.add("trebuchet");
		weaponTypes.add("ballista");
		weaponTypes.add("cannon");
	}

	/**
	 * This method adds the names of the sizes the user may choose from.
	 */
	private void setSizeNames() {
		sizeNames.add("small");
		sizeNames.add("medium");
		sizeNames.add("large");
	}
}