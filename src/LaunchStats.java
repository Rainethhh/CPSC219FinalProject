/**
 * This class handles the feedback given to the player after the shot has
 * concluded in the application. It gives a series of statistics, congratulates
 * the player if they were successful and offers feedback and further options if
 * they were not.
 * 
 * @author Aaron and Raine
 *
 */
public class LaunchStats {
	public static boolean hitTarget;
	public static boolean destroyedTarget;
	public static double targetFortitude;
	public static double damageDone;

	public static double maxHeight;
	public static double distanceTravelled;
	public static double targetDistance;
	public static double missedBy;
	public static double windSpeed;
	public static double topSpeed;

	public static double cumulativePowerfulHits = 0;
	public static double cumulativeWeakHits = 0;
	public static double cumulativeMisses = 0;
	public static double cumulativeDamageDone = 0;

	/**
	 * This method resets the statistics of the application to prepare for the next
	 * shot should the user decide to play again.
	 */
	public static void resetStats() {
		hitTarget = false;
		destroyedTarget = false;
		targetFortitude = 0;
		damageDone = 0;

		maxHeight = 0;
		distanceTravelled = 0;
		targetDistance = 0;
		missedBy = 0;
		windSpeed = 0;
		topSpeed = 0;
	}

	/**
	 * This method gives either a "you win" or "you lose" message along with
	 * statistics of the shot fired by the siege weapon to either congratulate or
	 * give feedback to the player.
	 */
	public static void convertToString() {
		String outputString = "";

		if (hitTarget) {
			//stats unique to when  target is hit
			if (destroyedTarget) {
				//stats unique to when taregt is hit and destroyed
				outputString += Pictures.getTrophy().toString();
				outputString += "A powerful hit on the target, destroying it completely!\n\n";
			} else
				//stats unique to when target is hit but not destroyed
				outputString += "A measly hit on the target, even a fly could hit it harder! "
						+ "Use heavier ammo or shoot with more power and you're in!\n\n";
			outputString += "target fortitude: " + (int) targetFortitude + "\n";
			outputString += "damage done: " + (int) damageDone + "\n";
		} else 
			//stats unique to when target is missed
			outputString += "Missed by " + (int) missedBy + " meters!\n\n";
		
		
		//these stats are always shown
		outputString += "max Height: " + (int) maxHeight + " meters\n";
		outputString += "horizontal distance travelled: " + (int) distanceTravelled + " meters\n";
		outputString += "top speed: " + (int) topSpeed + " meters per second\n";
		outputString += "target distance: " + (int) targetDistance + " meters\n";
		outputString += "wind speed: " + (int) windSpeed + " meters per second\n";

		outputString += "\n";

		outputString += "cumulative powerful hits: " + (int) cumulativePowerfulHits + "\n";
		outputString += "cumulative measly hits: " + (int) cumulativeWeakHits + "\n";
		outputString += "cumulative misses: " + (int) cumulativeMisses + "\n";
		outputString += "cumulative damage done: " + (int) cumulativeDamageDone + "\n";

		System.out.println(outputString);
	}
}