public class CannonAmmo extends FlyingObject {
	
	/**
	 * This method sets the sprite for the projectile that will be fired by the Cannon.
	 * @param projectileSprite this is the AsciiArt instance that will be used to 
	 * represent the projectile in the window.
	 */
	public CannonAmmo(AsciiArt projectileSprite) {
		super(projectileSprite);
	}

	/**
	 * This method sets the weight of the small, medium and large projectile types for the Cannon.
	 */
	public void initializeStats() {
		projectileWeights.put("small", 20);
		projectileWeights.put("medium", 40);
		projectileWeights.put("large", 60);
	}
	
	/**
	 * This method calculates the wind drag the Cannon projectile will face.
	 */
	protected void updateDrag() {
		int dragConstant = 100;
		windDrag = dragConstant/weight;
	}
}