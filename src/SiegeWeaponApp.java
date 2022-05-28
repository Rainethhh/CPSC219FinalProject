import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class assembles the other classes and runs the application. It uses the
 * other classes to take information from the user and display an animation of a
 * siege weapon throwing a projectile at a target.
 * 
 * @author Aaron and Raine
 *
 */
public class SiegeWeaponApp {
	private UserInputManager uim = new UserInputManager();
	private Weapon currentWeapon;
	private Target target = new Target();
	private double windSpeed;

	/**
	 * This is the main method, it runs the application.
	 */
	public static void main(String[] args) {
		SiegeWeaponApp siege = new SiegeWeaponApp();
		siege.run();
	}

	/**
	 * This method returns the weapon currently in use.
	 * 
	 * @return currentWeapon This is the weapon being used currently in the
	 *         application.
	 */
	public Weapon getCurrentWeapon() {
		return currentWeapon;
	}

	/**
	 * This method returns the target that is to be hit during the application
	 * 
	 * @return The target created that must be destroyed to win
	 */
	public Target getTarget() {
		return target;
	}

	/**
	 * This method runs the entire application start to finish.
	 */
	private void run() {
		introduction();

		//a loop for creating new random conditions, firing projectile and viewing stats
		boolean running = true;
		while (running) {
			LaunchStats.resetStats();
			FlyingObject.windSpeed = randomizeWindSpeed();
			target.randomizeTargetVars(windSpeed);
			siegeCreator();

			running = launchCycle();
		}

		System.out.println("Thanks for playing!");
	}
	
	
	/**
	 * This method prints out an introduction message for the game and waits until the user types "ok"
	 * to continue
	 */
	private void introduction() {
		System.out.println("welcome to...");

		System.out.println(Pictures.getSiegeWord().toString() + "\n");
		System.out.println("WELCOME to Siege! In this application your goal is to destroy a target"
				+ " using a siege weapon.\n You may choose either a cannon, ballista or trebuchet"
				+ " to accomplish this task,\n along with the size of the projectile you will fire."
				+ " But beware, your choice of weapon will affect its\n range and accuracy, some"
				+ " sacrifice accuracy for heavier hits!\n The projectile size you choose will also"
				+ "affect wind resistance and launch speed.\n" + "Good luck, and GO DESTROY SOME STUFF!\n");

		System.out.print("type \"ok\" to continue.");
		Scanner reader = new Scanner(System.in);
		if (!reader.next().equals("ok"))
			System.out.println("\nthat wasn't an \"ok\", but I suppose that works too...");
	}

	/**
	 * This method generates and displays images on the console and in the window.
	 * 
	 * @param window This is the window in which the images will be displayed.
	 */
	private void loadInitialSprites(Window window) {
		
		//place the weapon
		currentWeapon.setxPhys(5);
		currentWeapon.setyPhys(5);
		window.addStationarySprite(currentWeapon.getWeaponSprite().updateWindowPos(currentWeapon.getxPhys(),
				currentWeapon.getyPhys(), "bl"));
		
		//place the ground
		window.addStationarySprite(Pictures.getGround().updateWindowPos(0, 7.5, "cl"));
		
		//place the target
		window.addTarget(target);
		window.addProjectile(currentWeapon.getProjectile());

		//place singular clouds
		for (int i = 0; i < 1000; i++) {
			Random r = new Random();
			int xPos = r.nextInt(1200);
			int yPos = r.nextInt(750 - 25) + 25;
			FlyingObject cloudA = new FlyingObject(Pictures.getSingleCloud(), true);
			window.addProjectile(cloudA);
			cloudA.initializeMovement(xPos, yPos, windSpeed, 0);
		}

		//place cloud clusters
		for (int i = 0; i < 40; i++) {
			Random r = new Random();
			int xPos = r.nextInt(Globals.FIELD_WIDTH);
			int yPos = r.nextInt(Globals.FIELD_HEIGHT);
			FlyingObject cloudA = new FlyingObject(Pictures.getClouds(), true);
			window.addProjectile(cloudA);
			cloudA.initializeMovement(xPos, yPos, windSpeed, 0);
		}

	}

	/**
	 * 
	 * This method randomizes the initial conditions of the target, then 
	 * prompts the user for the weapon to be used, the size of the projectile
	 * to be fired, the angle from which it will be fired and with what power.
	 */
	private void siegeCreator() {
		target.placeStringNumbers(windSpeed);
		System.out.println("\n" + target.getIntro() + "\n");
		boolean confirm = false;
		
		//a loop to confirm changes in case user accidentally input a value they didn't want to actually use
		while (!confirm) {
			selectWeapon();
			selectProjectileSize();
			int launchAngle = selectLaunchAngle();
			int launchPower = selectLaunchPower();
			currentWeapon.fire(launchAngle, launchPower);
			confirm = confirmOrCancel();
		}
	}

	/**
	 * Play the animation.
	 * Gives the user the option of if they want a replay, to play again or to quit.
	 * Returns the boolean of if the application should be run again.
	 * 
	 * @return false if they want to stop playing, true otherwise
	 */
	private boolean launchCycle() {
		boolean running = true;
		Window window = new Window(this);
		loadInitialSprites(window);
		window.initialRender();
		
		//loop of asking to replay
		boolean replay = true;
		while (replay) {
			window.playAnimation();
			LaunchStats.convertToString();

			ArrayList<String> nextOptions = new ArrayList<String>();
			nextOptions.add("again");
			nextOptions.add("replay");
			nextOptions.add("quit");
			String nextStep = uim.stringInput("Play again, replay, or quit?", "Please enter a valid input",
					nextOptions);

			running = !nextStep.equals("quit");
			replay = nextStep.equals("replay");
		}
		
		return running;
	}

	/**
	 * This method returns a random number between two numbers which will correspond
	 * to the wind speed during the simulation
	 * 
	 * @return the double that will represent the wind speed in this running of the
	 *         application
	 */
	private double randomizeWindSpeed() {
		Random r = new Random();
		// Selfish Salamader's answer for "random between two numbers java" as a Grepper
		// search result (Don't know how to link it as it was found using the Grepper browser extension)
		windSpeed = r.nextInt(Globals.MAX_WIND_SPEED - Globals.MIN_WIND_SPEED) + Globals.MIN_WIND_SPEED;
		return windSpeed;
	}

	/**
	 * This method checks if the user wants to confirm their choices during the
	 * application setup
	 * 
	 * @return the true or false answer of if they want to use the values they gave
	 *         previously for the simulation
	 */
	private boolean confirmOrCancel() {
		boolean confirm = uim.booleanInput("Would you like to confirm or deny these selections?",
				"Please enter \"c\" or \"d\".", "c", "d");
		return confirm;
	}

	/**
	 * This method prompts the user for which weapon they will use and if the answer
	 * is the name of an allowed weapon, it sets the current weapon's name to it.
	 */
	private void selectWeapon() {
		String weaponName = uim.stringInput("Which weapon will you use?", "Please select a valid weapon",
				Globals.weaponTypes);
		setCurrentWeapon(weaponName);
	}

	/**
	 * This method prompts the user for a projectile size from a set list and sets
	 * the launch power if it is in that range. It makes an instance of the weapon's
	 * ammo type to load it and sets the projectile weight name.
	 */
	private void selectProjectileSize() {
		String weightName = uim.stringInput("What is the size of the projectile?", "Please select a valid size",
				Globals.sizeNames);
		currentWeapon.loadProjectile();
		currentWeapon.setProjectileWeight(weightName);
	}

	/**
	 * This method prompts the user for a launch angle in a certain range pertaining
	 * to a siege weapon and then sets the launch angle if it is in that range.
	 */
	private int selectLaunchAngle() {
		int launchAngle = uim.intInput("How steep of an angle do you want to launch the projectile at?",
				"Please select a valid integer", currentWeapon.getMinLaunchAngle(), currentWeapon.getMaxLaunchAngle());
		return launchAngle;
	}

	/**
	 * This method prompts the user for a launch power in a certain range pertaining
	 * to a siege weapon and then sets the launch power if it is in that range.
	 * 
	 * @param launchAngle angle the projectile will be launched
	 */
	private int selectLaunchPower() {
		int launchPower = uim.intInput("How much power do you want to launch the projectile with?",
				"Please select a valid integer", currentWeapon.getMinLaunchPower(), currentWeapon.getMaxLaunchPower());
		return launchPower;
	}

	/**
	 * This method sets the current weapon, if it is not one of the allowed weapon
	 * names, it displays an error
	 * 
	 * @param weaponName This is the string name of the weapon to be used
	 */
	private void setCurrentWeapon(String weaponName) {
		currentWeapon = new Weapon(weaponName);
	}

}