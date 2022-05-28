import java.util.HashMap;
import java.util.Map;

/**
 * This class controls the weapon used to fire projectiles at the targets in the
 * simulation. It contains the specific values pertaining to each specific
 * weapon type, as well as the sprite associated with the weapon. It also has
 * the ability to fire the projectile at the target.
 * 
 * @author Aaron and Raine
 *
 */
public class Weapon {
	private int minLaunchAngle;
	private int maxLaunchAngle;
	private int minLaunchPower;
	private int maxLaunchPower;

	private double xPhys;
	private double yPhys;

	private String weaponType = "";
	private AsciiArt weaponSprite;
	private Ammo projectile = null;
	private AsciiArt projectileSprite;

	/**
	 * This constructor creates an initial weapon and sets its position in the
	 * window
	 */
	public Weapon(String weaponType) {
		this.weaponType = weaponType;
		initializeStats();
	}

	/**
	 * This method checks the name of the weight given and then sets the sprite of
	 * the projectile according to the answer
	 * 
	 * @param weightName this is the name of the weight of the projectile
	 */
	public void setProjectileWeight(String weightName) {
		projectile.setWeight(weightName);
		if (weightName.equals("small"))
			projectile.setProjectileSprite(Pictures.getSmallRock());
		if (weightName.equals("medium"))
			projectile.setProjectileSprite(Pictures.getMediumRock());
		if (weightName.equals("large"))
			projectile.setProjectileSprite(Pictures.getLargeRock());
	}

	/**
	 * This method returns the projectile to be fired
	 * 
	 * @return the projetile that is currently set to be used.
	 */
	public Ammo getProjectile() {
		return projectile;
	}

	/**
	 * This method returns the horizontal position of weapon from which the
	 * projectile starts its motion
	 * 
	 * @return This is the numerical value of the horizonal position
	 */
	public double getxPhys() {
		return xPhys;
	}

	/**
	 * This method sets the horizontal position of weapon from which the projectile
	 * starts its motion
	 * 
	 * @param xPhys this is the numerical value associated with horizontal position
	 *              of where the projectile starts its motion
	 */
	public void setxPhys(double xPhys) {
		this.xPhys = xPhys;
	}

	/**
	 * This method returns the vertical position of weapon from which the projectile
	 * starts its motion
	 * 
	 * @return This is the numerical value of the vertical position
	 */
	public double getyPhys() {
		return yPhys;
	}

	/**
	 * This method sets the horizontal position of weapon from which the projectile
	 * 
	 * starts its motion
	 * 
	 * @param yPhys this is the numerical value associated with horizontal position
	 *              of where the projectile starts its motion
	 */
	public void setyPhys(double yPhys) {
		this.yPhys = yPhys;
	}

	/**
	 * This getter method gives the sprite associated with the weapon
	 * 
	 * @return The sptite corresponding to a weapon
	 */
	public AsciiArt getWeaponSprite() {
		return weaponSprite;
	}

	/**
	 * The integer minimum a weapon may fire a projectile from
	 * 
	 * @return The integer of the minimum value
	 */
	public int getMinLaunchAngle() {
		return minLaunchAngle;
	}

	/**
	 * The integer maximum a weapon may fire a projectile from
	 * 
	 * @return The integer of the maximum value
	 */
	public int getMaxLaunchAngle() {
		return maxLaunchAngle;
	}

	/**
	 * The integer minimum with which a weapon may fire a projetile
	 * 
	 * @return The numerical value associated with the minimum launch power
	 */
	public int getMinLaunchPower() {
		return minLaunchPower;
	}

	/**
	 * The integer maximum with which a weapon may fire a projetile
	 * 
	 * @return The numerical value associated with the maximum launch power
	 */
	public int getMaxLaunchPower() {
		return maxLaunchPower;
	}

	/**
	 * This method sets the stats of a the weapon to be used to the specific weapon
	 * chosen by the user. Each weapon has different intrinsic attributes and the
	 * result and restrictions of a shot will depend on which one is chosen.
	 */
	public void initializeStats() {
		
		//defining each weapon's unique stats
		if (weaponType.equals("ballista")) {
			weaponSprite = Pictures.getNewBallista();
			projectileSprite = Pictures.getRock();
			minLaunchAngle = 25;
			maxLaunchAngle = 65;
			minLaunchPower = 30;
			maxLaunchPower = 85;
		} else if (weaponType.equals("trebuchet")) {
			weaponSprite = Pictures.getNewTrebuchet();
			projectileSprite = Pictures.getRock();
			minLaunchAngle = 50;
			maxLaunchAngle = 89;
			minLaunchPower = 65;
			maxLaunchPower = 100;
		} else if (weaponType.equals("cannon")) {
			weaponSprite = Pictures.getCannon();
			projectileSprite = Pictures.getRock();
			minLaunchAngle = 0;
			maxLaunchAngle = 45;
			minLaunchPower = 10;
			maxLaunchPower = 50;
		}
	}

	/**
	 * This method creates a projectile based on the type of the weapon and the
	 * sprite given.
	 */
	public void loadProjectile() {
		projectile = new Ammo(weaponType, projectileSprite);
	}

	/**
	 * This method fires the projectile using the stats of the weapon chosen,
	 * launched from the projectile's starting position. Heavier objects are fired with less speed.
	 */
	public void fire(int launchAngle, int launchPower) {
		double angleRad = Math.toRadians(launchAngle);
		double xVel = 200 * launchPower * Math.cos(angleRad) / projectile.getWeight();
		double yVel = 200 * launchPower * Math.sin(angleRad) / projectile.getWeight();
		projectile.initializeMovement(xPhys, yPhys, xVel, yVel);
	}

}