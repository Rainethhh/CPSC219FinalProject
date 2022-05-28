import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class controls the targets to be fired upon in the simulation. It has
 * introductions for them and what they look like, what their stats are and what
 * their location is. It also has a way to check if the target has been hit.
 * 
 * @author Aaron and Raine
 *
 */
public class Target {

	public static ArrayList<String> targets = new ArrayList<String>();
	public static Map<String, String> intros = new HashMap<String, String>();
	public static Map<String, AsciiArt> sprites = new HashMap<String, AsciiArt>();
	public static Map<String, Integer> fortitudes = new HashMap<String, Integer>();
	private int xMin = 200;
	private int xMax = 800;
	private double xPhys;
	private double yPhys;
	private String targetType;
	private AsciiArt targetSprite;
	private String defaultIntro;
	private String intro;
	private double fortitude;

	/**
	 * This is the constructor for the target. It sets up the randomizes the
	 * target's variables, sets up the target, sets its fortitude as well as its
	 * visual components like the sprite and its introduction.
	 */
	public Target() {
		setupTargets();
		setupIntros();
		setupSprites();
		setupFortitudes();
		randomizeTargetVars(0);
	}

	/**
	 * This method replaces the placeholder values in the introduction with their
	 * actual values during the simulation
	 * 
	 * @param windSpeed This is the numerical value of the wind speed present in the
	 *                  application
	 */
	public void placeStringNumbers(double windSpeed) {
		String intro = new String(defaultIntro.replace("<wind speed>", Integer.toString((int) windSpeed)));
		intro = new String(intro.replace("<meters>", Integer.toString((int) xPhys)));
		this.intro = intro;
	}

	/**
	 * This getter method returns the name of the type of the target in use
	 * 
	 * @return the name of the target as a string
	 */
	public String getTargetType() {
		return targetType;
	}

	/**
	 * This getter method returns the sprite of the target on which it is called.
	 */
	public AsciiArt getTargetSprite() {
		return new AsciiArt(targetSprite);
	}

	/**
	 * This getter method returns the physical position of the target on the ground
	 * (horizontal)
	 * 
	 * @return the numerical value of where horizontally the target is located
	 */
	public double getxPhys() {
		return xPhys;
	}

	/**
	 * This method returns the specific introduction for a target (string)
	 * 
	 * @return This is the string introduction linked with the specific target type
	 *         it is called on.
	 */
	public String getIntro() {
		return intro;
	}

	/**
	 * This method randomizes the target to be fired on.
	 */
	public void randomizeTargetVars(double windSpeed) {
		Random r = new Random();
		targetType = targets.get(r.nextInt(8));
		targetSprite = sprites.get(targetType);
		defaultIntro = new String(intros.get(targetType));
		fortitude = fortitudes.get(targetType);
		updatePos();
		placeStringNumbers(windSpeed);
	}

	/**
	 * This method randomizes the position of the target in the simulation to keep
	 * the application interesting and changing.
	 */
	public void updatePos() {
		Random r = new Random();
		xPhys = r.nextInt(xMax - xMin) + xMin;
		yPhys = 5;
		targetSprite.updateWindowPos(xPhys, yPhys, "bc");
	}

	/**
	 * This method checks if the projectile fired has hit the target
	 * 
	 * @param recentProjLocations This is a list of the most recent locations the
	 *                            projectile has been
	 * @return the answer to if the projectile has hit the target or not
	 */
	public boolean hitDetection(double[] recentProjLocations) {
		//determines relative position of target's art relative to declared location
		//i.e. top left, bottom centre, etc.
		String spriteRef = targetSprite.getReferenceFrame(); 
		double xRel = AsciiArt.refConversions.get(spriteRef.charAt(1)) * targetSprite.getWidth() * Globals.LETTER_WIDTH;
		double yRel = AsciiArt.refConversions.get(spriteRef.charAt(0)) * targetSprite.getHeight()
				* Globals.LETTER_HEIGHT;
		
		//max and min x and y positions defining the target's rectangular hitbox
		double xMin = xPhys - xRel;
		double xMax = xPhys + targetSprite.getWidth() * Globals.LETTER_WIDTH - xRel;
		double yMin = yPhys + yRel - targetSprite.getHeight() * Globals.LETTER_HEIGHT;
		double yMax = yPhys + yRel;

		boolean hit = false;
		for (int i = 0; i < Globals.PHYSICS_UPDATES_PER_FRAME; i++) {
			//determines where the tareget has been in between the last frame and the current one, and if any of these are inside the hitbox, it's a hit
			double x = recentProjLocations[2 * i];
			double y = recentProjLocations[2 * i + 1];
			if (x < xMax && x > xMin & y < yMax && y > yMin) {
				hit = true;
			}
		}
		return hit;
	}

	/**
	 * This method adds the names of the possible targets to a list from which a
	 * target will be randomly selected
	 */
	private void setupTargets() {
		targets.add("castle");
		targets.add("windmill");
		targets.add("tower");
		targets.add("cow");
		targets.add("bat");
		targets.add("skeleton");
		targets.add("dragon");
		targets.add("napoleon");
	}

	/**
	 * This method creates a specific intro for each target and sets them to
	 * correspond with said specific target.
	 */
	private void setupIntros() {
		intros.put("castle", "The enemy has a small castle located <meters> meters away. With a wind speed of\r\n"
				+ "<wind speed> meters per second, send a rock that will crush their pathetic stronghold!");
		intros.put("windmill",
				"We have received word that the enemy is sheltering in a windmill <meters> meters from here.\r\n"
						+ "Send them packing with a rock they'll never see coming, but keep in mind that there is a \r\n"
						+ "wind blowing <wind speed> meters per second.");
		intros.put("tower",
				"There's no princess in that castle that's <meters> meters away, but enemy reinforcements!\r\n"
						+ "From the flag on top we can tell that there is a wind blowing <wind speed> meters per second.\r\n"
						+ "Bring them back down to earth!");
		intros.put("cow",
				"The lone cow that's <meters> meters away just insulted your mother! \r\n"
						+ "Make it pay!! The wind may be blowing <wind speed> meters per second, but don't let that\r\n"
						+ "stop you from defending your mother's honor!");
		intros.put("bat",
				"The troops spotted a bat <meters> meters away. It hasn't moved even though\r\n"
						+ "there is a gust blowing <wind speed> meters per second. The troops fear it may be\r\n"
						+ "a vampire, put them at ease!");
		intros.put("skeleton",
				"It seems the enemy has found an undead ally <meters> meters from here.\r\n"
						+ "Prove to them that neither a wind blowing <wind speed> meters per second nor\r\n"
						+ "the super natural can stop us. Make sure this thing wishes it were dead! Again!");
		intros.put("dragon",
				"Fancy yourself a dragonslayer? Well there's one <meters> meters from here and\r\n"
						+ "with a wind blowing <wind speed> meters per second, there's never been a better\r\n"
						+ "opportunity to add to your resume!");
		intros.put("napoleon",
				"It seems Napoleon has finally gotten as big as his ego! Show that French tart\r\n"
						+ "what you're made of. He's standing <meters> meters from here and the wind is blowing\r\n"
						+ "<wind speed> meters per second. Hit him hard!");
	}

	/**
	 * This method links specific sprites to their target type
	 */
	private void setupSprites() {
		sprites.put("castle", Pictures.getCastleSprite());
		sprites.put("windmill", Pictures.getWindMill());
		sprites.put("tower", Pictures.getTower());
		sprites.put("cow", Pictures.getCow());
		sprites.put("bat", Pictures.getBat());
		sprites.put("skeleton", Pictures.getSkeleton());
		sprites.put("dragon", Pictures.getDragon());
		sprites.put("napoleon", Pictures.getNapoleon());
	}

	/**
	 * This method sets the fortitude or "health" of each target to a its specified
	 * value
	 */
	private void setupFortitudes() {
		fortitudes.put("castle", 10);
		fortitudes.put("windmill", 4);
		fortitudes.put("tower", 10);
		fortitudes.put("cow", 1);
		fortitudes.put("bat", 10);
		fortitudes.put("skeleton", 10);
		fortitudes.put("dragon", 10);
		fortitudes.put("napoleon", 10);
	}

}