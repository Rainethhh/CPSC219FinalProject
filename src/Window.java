import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.ArrayList;

/**
 * This method creates a window in the console on which the animation of the
 * simulated launch of a projectile from a siege weapon will take place. It
 * allows the user to visualize the mathematical calculations being done.
 * 
 * @author Aaron and Raine
 *
 */
public class Window {
	private SiegeWeaponApp app;
	private int xCamera = 0;
	private int yCamera = 0;

	// https://stackoverflow.com/questions/2804827/create-a-string-with-n-characters
	private String emptyLine = new String(new char[Globals.FIELD_WIDTH]).replace('\0', ' ');
	private String[] blankCanvas;
	private String[] background;
	private String[] currentDrawing;
	private ArrayList<String[]> preparedFrames = new ArrayList<String[]>();

	private ArrayList<AsciiArt> stationarySprites = new ArrayList<AsciiArt>();
	private ArrayList<FlyingObject> projectiles = new ArrayList<FlyingObject>();
	private ArrayList<AsciiArt> movingSprites = new ArrayList<AsciiArt>();

	/**
	 * This constructor creates a new empty window.
	 */
	public Window(SiegeWeaponApp app) {
		this.app = app;
		blankCanvas = new String[Globals.FIELD_HEIGHT];
		for (int i = 0; i < blankCanvas.length; i++)
			blankCanvas[i] = new String(emptyLine);
	}

	/**
	 * This method pre-determines all frames based on projectile calculations, loading all frames into
	 * a list that can be used later to play the animation to the console.
	 */
	public void initialRender() {
		drawBackground();
		boolean running = true;
		
		//one loop iteration for each frame of the animation
		while (running) {
			running = evaluateGameState(); //determine if rendering is complete or not
			for (FlyingObject projectile : projectiles)
				projectile.incrementFlightPosition();
			FlyingObject launchedProjectile = projectiles.get(0);
			cameraFollowProjectile(launchedProjectile.xMovement[0], launchedProjectile.yMovement[0]);
			drawFrame();
		}
	}

	/**
	 * This method places the sprites of the animation inside the window
	 */
	public void displaySprites() {
		System.out.println("stationary sprites: ");
		for (AsciiArt sprite : stationarySprites)
			System.out.println(sprite.toString());
		System.out.println("projectiles: ");
		for (FlyingObject projectile : projectiles)
			System.out.println(projectile.getProjectileSprite().toString());
	}

	/**
	 * This method plays the full animation of the events of the simulation onto the
	 * console, giving a visual representation of the events and calculations that
	 * took place.
	 */
	public void playAnimation() {
		// measure time
		Temporal lastFrameMoment = Instant.now();
		for (String[] frame : preparedFrames) {

			// pause for required amount of time and display any errors in doing so
			Temporal currentMoment = Instant.now();
			Duration timePassed = Duration.between(lastFrameMoment, currentMoment);
			long millisecondsPassed = timePassed.toMillis();
			if (millisecondsPassed < 1000 / Globals.FRAME_RATE) {
				//waits appropriate amount of time until showing the next frame
				long waitTime = 1000 / Globals.FRAME_RATE - millisecondsPassed;
				try {
					Thread.sleep(waitTime);
				} catch (InterruptedException e) {
					System.out.println("error in pausing thread");
				}
			} else {
				 System.out.println("desired frame rate hasn't been achieved");
				 System.out.println("this frame took " + millisecondsPassed + " milliseconds "
				 		+ "to render");
			}
			
			//store current time locally for the next loop iteration to use as a reference for how much time has passed
			lastFrameMoment = Instant.now();
			displayFrame(frame);
		}
	}

	/**
	 * This method adds a new sprite to the list of stationary spites to be
	 * displayed in the animation. It also places it in the correct position
	 * in the array list so they're all sorted according to their layer.
	 * 
	 * @param sprite This is the sprite of something stationary to be displayed
	 *               during the animation
	 */
	public void addStationarySprite(AsciiArt sprite) {
		int layer = sprite.getLayer();
		boolean added = false;
		
		//empty array list
		if (stationarySprites.size() == 0) {
			stationarySprites.add(sprite);
			added = true;
		}

		//layer lower than first item in array
		if (!added && layer < stationarySprites.get(0).getLayer()) {
			stationarySprites.add(0, sprite);
			added = true;
		}
		
		//item placed somewhere in the middle.
		int i = 0;
		while (!added && i < stationarySprites.size() - 1) {
			int spriteALayer = stationarySprites.get(i).getLayer();
			int spriteBLayer = stationarySprites.get(i + 1).getLayer();
			if (spriteALayer <= layer && layer <= spriteBLayer) {
				//its layer number is somewhere between the item in front of it and the one after it,
				//inclusive.
				stationarySprites.add(i + 1, sprite);
				added = true;
			}
			i++;
		}
		
		//item placed at last spot in array
		if (!added)
			stationarySprites.add(sprite);
	}

	/**
	 * This method adds a projectile to be fired and receives the sprite associated
	 * with it
	 * 
	 * @param projectile This is a projectile that may be fired
	 */
	public void addProjectile(FlyingObject projectile) {
		projectiles.add(projectile);
		movingSprites.add(projectile.getProjectileSprite());
	}

	/**
	 * This method adds the visual representation of the target to the window
	 * 
	 * @param target This is the target which must be hit and must be represented in
	 *               the window
	 */
	public void addTarget(Target target) {
		target.updatePos();
		stationarySprites.add(target.getTargetSprite());
	}

	/**
	 * This method updates the display of the "camera" and serves as an illusion
	 * that the window is moving, not the text across the screen
	 * 
	 * @param xPhys The new horizontal position of the camera/ where the display
	 *              shows
	 * @param yPhys The new vertical position of the camera/ where the display shows
	 */
	public void updateCameraPos(double xPhys, double yPhys) {
		//preferred loation of camera
		int xPos = (int) (xPhys / Globals.LETTER_WIDTH);
		int yPos = (int) (Globals.FIELD_HEIGHT - yPhys / Globals.LETTER_HEIGHT);

		//a series of restrictions on the camera's position in case it wants to go out of bounds
		if (xPos < 0)
			xPos = 0;
		if (xPos >= Globals.FIELD_WIDTH - Globals.WINDOW_WIDTH - 1)
			xPos = Globals.FIELD_WIDTH - Globals.WINDOW_WIDTH - 1;
		if (yPos < 0)
			yPos = 0;
		if (yPos >= Globals.FIELD_HEIGHT - Globals.WINDOW_HEIGHT - 1)
			yPos = Globals.FIELD_HEIGHT - Globals.WINDOW_HEIGHT - 1;
		
		//update position based on calculations
		xCamera = xPos;
		yCamera = yPos;
	}

	/**
	 * Checks if the target or the ground has been hit, if it has not, it updates
	 * the projectile's location and continues running. If it has, it updates the
	 * feedback and stats from the launch in LaunchStats.
	 * 
	 * @return if the application is still running or not.
	 */
	private boolean evaluateGameState() {
		boolean running = true;
		
		
		//load information about target and projectile to local variables
		Target target = app.getTarget();
		Weapon weapon = app.getCurrentWeapon();
		Ammo projectile = weapon.getProjectile();
		double[] recentProjLocations = projectile.getRecentLocations();
		double xProj = projectile.getxPhys();
		double yProj = projectile.getyPhys();

		//determine outcome of game to local variables if applicable
		boolean targetMissed = yProj < 0;
		boolean outOfBounds = xProj > Globals.FIELD_WIDTH * Globals.LETTER_WIDTH || xProj < 0;
		boolean hitTarget = target.hitDetection(recentProjLocations);

		//determine if launch has ended
		if (hitTarget || targetMissed || outOfBounds)
			running = false;

		//if launch has ended, calculate a bunch of stats
		if (!running) {
			LaunchStats.damageDone = projectile.landingPower();
			LaunchStats.targetFortitude = Target.fortitudes.get(target.getTargetType());
			LaunchStats.hitTarget = hitTarget;
			LaunchStats.destroyedTarget = LaunchStats.damageDone > LaunchStats.targetFortitude;
			LaunchStats.distanceTravelled = projectile.getxPhys();
			LaunchStats.targetDistance = target.getxPhys();
			LaunchStats.missedBy = Math.abs(LaunchStats.distanceTravelled - LaunchStats.targetDistance);
			LaunchStats.windSpeed = FlyingObject.windSpeed;

			if (LaunchStats.destroyedTarget)
				LaunchStats.cumulativePowerfulHits += 1;
			else if (LaunchStats.hitTarget)
				LaunchStats.cumulativeWeakHits += 1;
			else
				LaunchStats.cumulativeMisses += 1;
			LaunchStats.cumulativeDamageDone += (int) LaunchStats.damageDone;
		}

		//these launch stats are dynamically updated every frame rather than just at the end.
		if (projectile.getyPhys() > LaunchStats.maxHeight)
			LaunchStats.maxHeight = projectile.getyPhys();
		if (projectile.getSpeed() > LaunchStats.topSpeed)
			LaunchStats.topSpeed = projectile.getSpeed();
		
		return running;
	}

	/**
	 * This methods draws the ground and places all non-moving background
	 * sprites in the window.
	 */
	private void drawBackground() {
		currentDrawing = newDrawing(blankCanvas);
		renderSprites(stationarySprites);
		background = currentDrawing;
	}

	/**
	 * This method draws a new frame during rendering and adds it to the prepared frames.
	 */
	private void drawFrame() {
		currentDrawing = newDrawing(background);
		renderSprites(movingSprites);
		currentDrawing = cropDrawing(currentDrawing);
		preparedFrames.add(currentDrawing);
	}

	/**
	 * This method takes in a full visualization of the field, but we only need to see
	 * what the camera sees so we crop it down to only contain what's inside the camera.
	 * 
	 * @param drawing This is the drawing to be cropped
	 * @return The newly cropped drawing
	 */
	private String[] cropDrawing(String[] drawing) {
		//define the rectangular area that represents the camera's view
		int xMin = xCamera;
		int xMax = xCamera + Globals.WINDOW_WIDTH;
		int yMin = yCamera;
		int yMax = yCamera + Globals.WINDOW_HEIGHT;
		
		//the actual cropping process
		String[] croppedDrawing = new String[yMax - yMin];
		for (int i = yMin; i < yMax; i++) {
			String line = currentDrawing[i];
			String newLine = line.substring(xMin, xMax);
			croppedDrawing[i - yMin] = newLine;
		}
		
		return croppedDrawing;
	}

	/**
	 * This method displays the next frame of the animation on the window in the
	 * console
	 * 
	 * @param frame This is the new frame that is to be displayed
	 */
	private void displayFrame(String[] frame) {
		for (int i = 0; i < Globals.LINES_BETWEEN_WINDOWS; i++)
			System.out.println();
		for (String line : frame)
			System.out.println(line);
	}

	/**
	 * Creates a deep copy of the background frame.
	 * 
	 * @param backdrop This is the background which the window will display
	 * @return the new and updates drawing to be displayed on the display
	 */
	private String[] newDrawing(String[] backdrop) {
		String[] drawing = new String[Globals.FIELD_HEIGHT];
		for (int i = 0; i < Globals.FIELD_HEIGHT; i++) {
			String newLine = new String(backdrop[i]);
			drawing[i] = newLine;
		}
		return drawing;
	}

	/**
	 * This method places all the sprites in the frame in the window to be displayed
	 * 
	 * @param sprites
	 */
	private void renderSprites(ArrayList<AsciiArt> sprites) {
		for (AsciiArt sprite : sprites)
			placeSprite(sprite);
	}

	/**
	 * This method places a sprite in the window. If they should be partially off
	 * screen, it trims in accordingly
	 * 
	 * @param sprite
	 */
	private void placeSprite(AsciiArt sprite) {
		if (partiallyOffscreen(sprite))
			sprite = cutSprite(sprite);

		for (int i = 0; i < sprite.getHeight(); i++) {
			currentDrawing[i + sprite.getyPos()] = overwriteLine(currentDrawing[i + sprite.getyPos()],
					sprite.getModel()[i], sprite.getxPos(), sprite.getTransparentSpaces());
		}
	}

	/**
	 * This method places one line of a sprite over the current drawing, and checks if there are
	 * transparent spaces on the line being drawn that is now on top. Creates the illusion of
	 * depth during the running of the application.
	 * 
	 * @param line              the line to be overridden
	 * @param addedItem         the characters that are now on top
	 * @param index             how many characters are on top of the other sprite
	 * @param transparentSpaces if there are transparent spaces or not on the sprite
	 *                          that is now in front
	 * @return the new string representation with one sprite on top of another
	 */
	private String overwriteLine(String line, String addedItem, int index, boolean transparentSpaces) {
		char[] characters = addedItem.toCharArray();
		char[] charLine = line.toCharArray();
		for (int i = 0; i < characters.length; i++) {
			if (characters[i] != ' ' || !transparentSpaces)
				charLine[index + i] = characters[i];
		}
		return new String(charLine);
	}

	/**
	 * This method checks is a sprite in the animation is partially off screen in
	 * the animation
	 * 
	 * @param sprite This is the sprite to be checked
	 * @return the answer of if it is or is not partially off screen
	 */
	private boolean partiallyOffscreen(AsciiArt sprite) {
		boolean off = false;
		if (sprite.getxPos() + sprite.getWidth() > Globals.FIELD_WIDTH)
			off = true;
		if (sprite.getyPos() + sprite.getHeight() > Globals.FIELD_HEIGHT)
			off = true;
		if (sprite.getxPos() < 0)
			off = true;
		if (sprite.getyPos() < 0)
			off = true;
		return off;
	}

	/**
	 * If the sprite is at least partially off screen, this method trims the sprite
	 * to look as if it has moved and is partially off screen.
	 * 
	 * @param sprite This is the sprite to be trimmed/cut
	 * @return the new trimmed sprite
	 */
	private AsciiArt cutSprite(AsciiArt sprite) {
		AsciiArt spriteCopy = new AsciiArt(sprite);

		int spriteHeight = spriteCopy.getHeight();
		int spriteWidth = spriteCopy.getWidth();
		int xSprite = spriteCopy.getxPos();
		int ySprite = spriteCopy.getyPos();

		if (ySprite >= Globals.FIELD_HEIGHT || xSprite >= Globals.FIELD_WIDTH || ySprite + spriteHeight <= 2
				|| xSprite + spriteWidth <= 2) {
			spriteCopy = new AsciiArt(0, true, " ");
		} else {
			//spriteCopy = spriteOnscreen(spriteCopy);
			spriteCopy = new AsciiArt(0, true, " ");
		}

		return spriteCopy;
	}

	/**
	 * This method cuts a sprite now that it's been determined to be partially on screen.
	 * This method is never used on a sprite that is fully on screen.
	 * @param spriteCopy The sprite that is to be moved
	 * @return The newly moved sprite
	 */
	private AsciiArt spriteOnscreen(AsciiArt spriteCopy) {
		//store sprite variables locally
		int spriteHeight = spriteCopy.getHeight();
		int spriteWidth = spriteCopy.getWidth();
		int xSprite = spriteCopy.getxPos();
		int ySprite = spriteCopy.getyPos();

		//initialize amount of characters to cut from each side of the sprite
		int bottomCut = 0;
		int topCut = 0;
		int rightCut = 0;
		int leftCut = 0;

		//calculate actual values for variables above if applicable
		if (ySprite + spriteHeight + 1 > Globals.FIELD_HEIGHT)
			bottomCut = ySprite + spriteHeight + 1 - Globals.FIELD_HEIGHT;
		if (ySprite < 0)
			topCut = -ySprite;
		if (xSprite + spriteWidth + 1 > Globals.FIELD_WIDTH)
			rightCut = xSprite + spriteWidth + 1 - Globals.FIELD_WIDTH;
		if (xSprite < 0)
			leftCut = -xSprite;
			
		//create a new model for the sprite based on calculations
		String[] model = new String[spriteHeight - bottomCut - topCut];
		
		for (int y = topCut; y < spriteHeight - bottomCut; y++) {
			String lineString = spriteCopy.getModel()[y];
			int lineLength = lineString.length();
			int lineEnd = spriteWidth - rightCut;
			if (lineEnd > lineLength)
				lineEnd = lineLength;
			model[y - topCut] = lineString.substring(leftCut, lineEnd);
		}
		
		//model is now updated, all variables are calculated. Now, we store the local variables
		//back into the sprite instance.
		spriteCopy.setyPos(spriteCopy.getyPos() + topCut);
		spriteCopy.setxPos(spriteCopy.getxPos() + leftCut);
		spriteCopy.setWidth(spriteCopy.getWidth() - rightCut - leftCut);
		spriteCopy.setHeight(spriteCopy.getHeight() - topCut - bottomCut);
		spriteCopy.setModel(model);

		return spriteCopy;
	}

	/**
	 * This method makes the camera follow the projectile across the simulation by
	 * updating the camera position when the projectile moves
	 * 
	 * @param xPhys This is the horizontal position in physical space
	 * @param yPhys This is the vertical position in physical space
	 */
	private void cameraFollowProjectile(double xPhys, double yPhys) {
		//a relative location of where the projectile is on the field
		double xProgress = xPhys / (Globals.FIELD_WIDTH * Globals.WINDOW_WIDTH);
		double yProgress = yPhys / (Globals.FIELD_HEIGHT * Globals.WINDOW_HEIGHT);

		//adjustable offsets for camera location relative to projectile
		double xOff = -10;
		double yOff = 0;

		//adjustable linear equations for camera's position relative to projectile, depending on where the projectile is located physically
		double xRel = xProgress * 0.0 + 0.0; 
		double yRel = yProgress * 0.0 + 0.5;

		//calculate final position of camera using all the calculations above
		double x = xOff + xPhys + xRel * Globals.WINDOW_WIDTH * Globals.LETTER_WIDTH;
		double y = yOff + yPhys + yRel * Globals.WINDOW_HEIGHT * Globals.LETTER_HEIGHT;
		
		//update camera position
		updateCameraPos(x, y);
	}

}