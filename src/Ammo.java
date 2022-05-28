import java.util.HashMap;
import java.util.Map;

/**
 * This class handles the stats and representations specifically of a projectile
 * to be fired by a siege weapon. It is used in conjunction with the
 * FlyingObject class and is used in the Weapon class.
 * 
 * @author Aaron and Raine
 *
 */
public class Ammo extends FlyingObject {

	protected Map<String, Integer> projectileWeights = new HashMap<String, Integer>();
	protected Map<String, Integer> projectileDrags = new HashMap<String, Integer>();

	/**
	 * Constructor for Ammo class
	 * 
	 * @param projectileSprite this is the AsciiArt instance that will be used to
	 *                         Trebuchet represent the projectile in the window.
	 */
	public Ammo(String weaponType, AsciiArt projectileSprite) {
		super(projectileSprite, false);
		initializeStats(weaponType);
	}

	/**
	 * This method sets the weight of the small, medium and large ammo types. This
	 * influences how hard a projectile hits something along with how much drag it
	 * experiences.
	 * @param weaponType what the weapon is called
	 */
	public void initializeStats(String weaponType) {
		//differentd drag coefficients for different sizes
		projectileDrags.put("small", 10);
		projectileDrags.put("medium", 20);
		projectileDrags.put("large", 30);
		
		//each weapon gets a unique set of ammo weights
		if (weaponType.equals("trebuchet")) {
			projectileWeights.put("small", 120);
			projectileWeights.put("medium", 140);
			projectileWeights.put("large", 160);
		}
		if (weaponType.equals("ballista")) {
			projectileWeights.put("small", 70);
			projectileWeights.put("medium", 90);
			projectileWeights.put("large", 110);
		}
		if (weaponType.equals("cannon")) {
			projectileWeights.put("small", 20);
			projectileWeights.put("medium", 40);
			projectileWeights.put("large", 60);
		}
	}

	/**
	 * This method calculates the power the with which the projectile hits either
	 * the target or the ground. It is used to give feedback to the user.
	 * 
	 * @return kineticEnergy this is the energy with which the projectile hits
	 *         something.
	 */
	public double landingPower() {
		double speedSquared = xMovement[1] * xMovement[1] + yMovement[1] * yMovement[1];
		double kineticEnergy = weight * speedSquared;
		return kineticEnergy;
	}

	/**
	 * This method sets the weight of the projectile based on the name provided.
	 * 
	 * @param weightName This is the name of the weight which is associated with a
	 *                   number value for each different ammo type for each weapon.
	 *                   If the name given does now match the permittable names,
	 *                   nothing happens. Also updates the drag to correspond with
	 *                   the weight assigned.
	 */
	public void setWeight(String weightName) {
		if (projectileWeights.containsKey(weightName)) {
			weight = projectileWeights.get(weightName);
			updateDrag();
		}
	}

	/**
	 * This method sets the drag experienced by the projectile during its flight.
	 * This will be used in calculations pertaining to the projectile's speed and
	 * power.
	 */
	public void updateDrag() {
		windDrag = Globals.DRAG_CONSTANT / weight;
	}
}