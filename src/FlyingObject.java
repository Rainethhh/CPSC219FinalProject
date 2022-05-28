
/**
 * This class handles things pertaining to a simulated object that flies through the air.
 * It contains the variables necessary to know its position, velocity and acceleration.
 * 
 * @author Aaron and Raine
 *
 */
public class FlyingObject {
	public static double windSpeed;
	protected AsciiArt projectileSprite;
	protected int weight;
	protected double windDrag;
	protected boolean linearFlyer = false;
	protected double[] xMovement = new double[3];
	protected double[] yMovement = new double[3];
	protected double[] recentLocations = new double[2 * Globals.PHYSICS_UPDATES_PER_FRAME];

	/**
	 * This constructor sets the specific stats of the projectile being fired for a
	 * specific weapon of a specific size. It also takes an instance of AsciiArt and
	 * sets the sprite to of the projectile to it.
	 * 
	 * @param projectileSprite This is the AsciiArt that will serve as the
	 *                         representation of the projectile bring fired.
	 */
	public FlyingObject(AsciiArt projectileSprite, boolean linearFlyer) {
		this.linearFlyer = linearFlyer;
		this.projectileSprite = projectileSprite;
	}

	/**
	 * This method gives the starting movement of the projectile when it is
	 * initially fired from the siege weapon.
	 * 
	 * @param xPhys       This is the horizontal position of the projectile
	 * @param yPhys       This is the vertical position of the projectile
	 * @param launchAngle This is the angle off the ground the projectile is fired
	 *                    from.
	 * @param launchPower This is the power that the projectile is fired with.
	 */
	public void initializeMovement(double xPhys, double yPhys, double xSpeed, double ySpeed) {
		//position
		xMovement[0] = xPhys;
		yMovement[0] = yPhys;
		//velocity
		xMovement[1] = xSpeed;
		yMovement[1] = ySpeed;
		//acceleration
		xMovement[2] = 0;
		yMovement[2] = 0;
	}

	/**
	 * This method updates the position of the projectile sprite in the window based
	 * on how the calculated projectile moves with regards to wind speed, drag and
	 * gravity.It also stores all increments of physical location in an array that will be used fot hit detection.
	 */
	public void incrementFlightPosition() {
		int freq = Globals.PHYSICS_UPDATES_PER_FRAME;
		long fr = Globals.FRAME_RATE;
		long denom = freq * fr; 

		for (int updateN = 0; updateN < freq; updateN++) {
			for (int i = 0; i < 2; i++) {
				//change positions according to velocity and change velocity according to acceleration
				xMovement[i] += xMovement[i + 1] / denom;
				yMovement[i] += yMovement[i + 1] / denom;
			}
			
			//recent locations used for hit detection later in the target
			recentLocations[2 * updateN] = xMovement[0];
			recentLocations[2 * updateN + 1] = yMovement[0];
			
			if (!linearFlyer) {
				//non-linear flyers get a non-zero acceleration here
				xMovement[2] = (windSpeed - xMovement[1]) * windDrag;
				yMovement[2] = (0 - yMovement[1]) * windDrag + Globals.GRAVITY;
			}
		}
		updateSpritePos(xMovement[0], yMovement[0]);
	}

	/**
	 * This method returns the AsciiArt instance of the projectile's sprite.
	 */
	public AsciiArt getProjectileSprite() {
		return projectileSprite;
	}

	/**
	 * This method sets the sprite of the projectile using an instance of AsciiArt
	 * 
	 * @param projectileSprite This is the AsciiArt that will represent the
	 *                         projectile fired.
	 */
	public void setProjectileSprite(AsciiArt projectileSprite) {
		this.projectileSprite = projectileSprite;
	}

	/**
	 * This method returns the weight of the projectile that is fired.
	 * 
	 * @return weight This is the weight of the projectile as an integer.
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * This getter method returns the answer to a calculation of how fast an object
	 * is flying
	 * 
	 * @return This is the double value of the speed of the object
	 */
	public double getSpeed() {
		return Math.sqrt(xMovement[1] * xMovement[1] + yMovement[1] * yMovement[1]);
	}

	/**
	 * This returns the most recent locations of the object
	 * 
	 * @return a list of doubles of the most recent location the object has Traveled
	 *         through
	 */
	public double[] getRecentLocations() {
		return recentLocations;
	}

	/**
	 * This getter method returns the horizontal physical position of the object in
	 * space
	 * 
	 * @return the double representation of where the object is horizontally in
	 *         space
	 */
	public double getxPhys() {
		return xMovement[0];
	}

	/**
	 * This getter method returns the vertical physical position of the object in
	 * space
	 * 
	 * @return the double representation of where the object is vertically in space
	 */
	public double getyPhys() {
		return yMovement[0];
	}

	/**
	 * This method updates the position of the sprite of the projectile.
	 * 
	 * @param xPhys This is the new horizontal position of the sprite in the window
	 * @param yPhys This is the new vertical position of the sprite in the window
	 */
	protected void updateSpritePos(double xPhys, double yPhys) {
		projectileSprite.updateWindowPos(xPhys, yPhys, "cc");
	}
}