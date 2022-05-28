/**
 * This class houses the default pictures that the AsciiArt class makes. It
 * creates an instance of AsciiArt for each picture allowing it to be called in
 * the project without the need to make a new instance of AsciiArt each time. It
 * also contains the credits of all the people who created the string images and
 * an introduction for each target.
 * 
 * @author Aaron and Raine
 *
 */
public class Pictures {
	
	/**
	 * Below, we have all class variables. They are each instances of AsciiArt,
	 * initialized with their layer on the window, whether or not they have transparent spaces,
	 * and the string that they are represented by.
	 */
	
	
	private static AsciiArt rock = new AsciiArt(0, true, " BBB\r\n" + "BBBBB\r\n" + " BBB");

	private static AsciiArt castleSprite = new AsciiArt(9, false,
			" [][][] /\"\"\\ [][][]\r\n" + "  |::| /____\\ |::|\r\n" + "  |[]|_|::::|_|[]|\r\n"
					+ "  |::::::__::::::|\r\n" + "  |:::::/||\\:::::|\r\n" + "  |:#:::||||::#::|");
	// The enemy has a small castle located <meters> meters away. With a wind speed
	// of
	// <wind speed> meters per second, send a rock that will crush their pathetic
	// stronghold!

	// https://www.asciiart.eu/buildings-and-places/castles

	private static AsciiArt ballistaIcon = new AsciiArt(0, true, "                           _.-`.\r\n"
			+ "                       _.-'__v__`.\r\n" + "                   _.-'      ^_.-'\r\n"
			+ "               _.-'       _.-'.] |\r\n" + "           _.-'       _.-' [.-'] |\r\n"
			+ "       _.-'       _.-|   |'=`-.===========.\r\n" + "   _.-'__v__  _.-'   |   | [.-'].|        .'\r\n"
			+ "  |.     ^_.-'    _.-`.  | [`-_.-'        .'\r\n" + "  | `._.-'.] |_.-'. `. `.|_.-'            .'\r\n"
			+ "  |  | [.-'='|.  `;`  `.-'               .'\r\n" + "  |  .==`-.] | `.  `;`  `.              .'\r\n"
			+ "  | '= [.-'].|_.|`.  `;`  `.           .'\r\n" + "  |'=| [`-_.-'   `|`.  `;`  `.        .'\r\n"
			+ "  '=.|_.-'         `|`.  `;`  `.     .'\r\n" + " '=               .'.`|`.  `;`  `. .'|\r\n"
			+ "'=              .'.' |\\`|`.  `;`.='.-|\r\n" + " '--------------------------.=./ |- `.`.\r\n"
			+ "          . .'.' _.-'`. \\\\ `|`.   `._.`\r\n" + "           `._.-'      `.\\\\. `|`._.'_.'\r\n"
			+ "                         `._`. `|_.'");
	// https://ascii.co.uk/art/ballista

	private static AsciiArt trebuchetIcon = new AsciiArt(0, true,
			"                             .`.\r\n" + "                            / `.`.\r\n"
					+ "     ______________________/____`_`____________________________\r\n"
					+ "    / .''.  _        _           _          _           __..--->.\r\n"
					+ "    \\ '()'       _       .''.        _       ____...---'       .'\r\n"
					+ "     |_||______.`.__  .' .'______......-----'                 /\r\n"
					+ "      .||-||-./ `.`.' .'   \\/_/  `./   /`.`.                .'\r\n"
					+ "    .'_||__.'/ (O)`.`.    \\/_/     `./   /`.`.             /\r\n"
					+ "    |_ -  _|/\\     /`.`. \\/_/        `./   /`.`.          /\r\n"
					+ "    | - _  /\\   ./   /`.`. /___________`./   /`.`._     .'\r\n"
					+ "    '-----/\\  \\/ `./   /`.`._____________`._____` .|   /\r\n"
					+ "         /\\  \\/_/  `./   /`.`.________________.'.'.' .'\r\n"
					+ "        /\\  \\/_/   .-`./   /`.`.---------.''.-----.-'\r\n"
					+ "       /\\  \\/_/  .'~ _ `./   /`.`. _ ~   '..'`._.'\r\n"
					+ "    .'/\\  \\/_/  '--------`./   /`.`.-----------' \r\n"
					+ "  .' /\\  \\/ /______________`./   /`.`..'.'.'\r\n"
					+ ".'__/____/___________________`._____` .'.'\r\n"
					+ "|____________________________________|.'    ");
	// https://ascii.co.uk/art/trebuchet

	private static AsciiArt cannon = new AsciiArt(9, false,
			"     .-._______\r\n" + "  .={ . }..--\"\"\r\n" + " [/\"`._.'    ");
	// https://ascii.co.uk/art/canon fsc

	private static AsciiArt sun = new AsciiArt(1, false, "     .\r\n" + "   \\ | /\r\n" + " '-.;;;.-'\r\n"
			+ "-==;;;;;==-\r\n" + " .-';;;'-.\r\n" + "   / | \\\r\n" + "     '");
	// https://www.asciiart.eu/nature/sun jgs

	private static AsciiArt tree = new AsciiArt(4, true, "       ###\r\n" + "      #o###\r\n" + "    #####o###\r\n"
			+ "   #o#\\#|#/###\r\n" + "    ###\\|/#o#\r\n" + "     # }|{  #\r\n" + "       }|{");
	// https://ascii.co.uk/art/tree ejm97
	private static AsciiArt windMill = new AsciiArt(9, false,
			"                                             __\r\n"
					+ "                      ,-_                  (`  ).\r\n"
					+ "                      |-_'-,              (     ).\r\n"
					+ "                      |-_'-'           _(        '`.\r\n"
					+ "             _        |-_'/        .=(`(      .     )\r\n"
					+ "            /;-,_     |-_'        (     (.__.:-`-_.'\r\n"
					+ "           /-.-;,-,___|'          `(       ) )\r\n"
					+ "          /;-;-;-;_;_/|\\_ _ _ _ _   ` __.:'   )\r\n"
					+ "             x_( __`|_P_|`-;-;-;,|        `--'\r\n" + "             |\\ \\    _||   `-;-;-'\r\n"
					+ "             | \\`   -_|.      '-'\r\n" + "             | /   /-_| `\r\n"
					+ "             |/   ,'-_|  \\\r\n" + "             /____|'-_|___\\\r\n"
					+ "      _..,____]__|_\\-_'|_[___,.._\r\n" + "     '                          ``'--,..,.         ");
	// We have received word that the enemy is sheltering in a windmill <meters>
	// meters from here.
	// Send them packing with a rock they'll never see coming, but keep in mind that
	// there is a
	// wind blowing <wind speed> meters per second.

	// https://ascii.co.uk/art/windmill mic

	private static AsciiArt trophy = new AsciiArt(0, false,
			"             ___________\r\n" + "            '._==_==_=_.'\r\n" + "            .-\\:      /-.\r\n"
					+ "           | (|:.     |) |\r\n" + "            '-|:.     |-'\r\n"
					+ "              \\::.    /\r\n" + "               '::. .'\r\n" + "                 ) (\r\n"
					+ "               _.' '._\r\n" + "              `\"\"\"\"\"\"\"`");
	// https://ascii.co.uk/art/trophy jgs

	private static AsciiArt siegeWord = new AsciiArt(0, false,
			"     _                 \r\n" + "    (_)                \r\n" + " ___ _  ___  __ _  ___ \r\n"
					+ "/ __| |/ _ \\/ _` |/ _ \\\r\n" + "\\__ \\ |  __/ (_| |  __/\r\n"
					+ "|___/_|\\___|\\__, |\\___|\r\n" + "             __/ |     \r\n" + "            |___/      ");
	// https://ascii.co.uk/art/siege

	private static AsciiArt moon = new AsciiArt(1, false, "        _..._\r\n" + "      .' .::::.\r\n"
			+ "     :  ::::::::\r\n" + "     :  ::::::::\r\n" + "     `. '::::::'\r\n" + "       `-.::''");
	// ascii.co.uk/art/moon jgs
	private static AsciiArt catapult = new AsciiArt(0, false,
			"                             .---.\r\n" + "                            |     |\r\n"
					+ "                .  /  '  ,  |     |\r\n" + "              .\" \".          `---'\r\n"
					+ "             .\"   \".  -\r\n" + "             \".     \".\r\n" + "               \".  .\"\\\r\n"
					+ "                 \"\"\\  \\\r\n" + "                    \\  \\     .-.\r\n"
					+ "                     %  \\    |\".\".\r\n" + "                  .~\" \\  \\   | |\".\".\r\n"
					+ "               .--.____\\  \\__| |_\".\".___.|___________\r\n"
					+ "              |\". \".----\\  \\------\".\".---\".o . \".    `\".\r\n"
					+ "              | \".o\".  \".\\  \\      \"-\" |   $\\ .|\".     \".\r\n"
					+ "              |  \".o\".    \\  \\=====| |=`--   #.'.\".     \".===========._\r\n"
					+ "              |   \". \".    \". \".   | | .   bIt| \".\".     \".=========='\r\n"
					+ "              |    \". \".___________| |_______/|____\".______.\r\n"
					+ "              \".   |\"--\"                                   \".\r\n"
					+ "               \".  |                                         |\r\n"
					+ "                \". |    _________                _________   |\r\n"
					+ "                 \".|___/..=====. \\______________/..=====. \\__|\r\n"
					+ "                        .\"\\ | /\".                .\"\\ | /\".\r\n"
					+ "                        |-- O --|                |-- O --|\r\n"
					+ "                        \"./ | \\.\"                \"./ | \\.\"\r\n"
					+ "                         \"=====\"                  \"=====\"\r\n" + "");
	// https://ascii.co.uk/art/catapult Peter Rijks

	private static AsciiArt clouds = new AsciiArt(5, true,
			"                       .')             _\r\n" + "                      (_  )        .+(`  ) )      \r\n"
					+ "            _                     :(    ) )\r\n"
					+ "        .:(`  )  ) --        .--  `.  (    ) )  - --\r\n"
					+ "       :(      )           .(   )   ` __.:'\r\n" + "       `(       ) )       (      )\r\n"
					+ "         ` __.:'   ))--- (       )) ----      _\r\n"
					+ "                --'  _    `- __.'         .=(`  )\r\n"
					+ "                   (`  ).                :(      )\r\n"
					+ "                 (       '`. .  --       `(       ) ) ) ----\r\n"
					+ "                 (         ) ) ---         ` __.:'\r\n" + "                  ` __.:'-'");
	// https://ascii.co.uk/art/clouds -a:f-

	/**
	 * This method creates ground with a specified width
	 */
	private static AsciiArt ground = new AsciiArt(6, false,
			new String(new char[Globals.FIELD_WIDTH]).replace('\0', '_'));
	// https://stackoverflow.com/questions/2804827/create-a-string-with-n-characters

	// "_________________________^-,;-,___^|/-____________-;,-.,.-._____________-.-,;'//,.;,_________________________________________________________________");

	private static AsciiArt tower = new AsciiArt(7, false,
			"     |>>>\r\n" + "     |\r\n" + " _  _|_  _\r\n" + "|;|_|;|_|;|\r\n" + "\\\\.    .  /\r\n"
					+ " \\\\:  .  /\r\n" + "  ||:   |\r\n" + "  ||:.  |\r\n" + "  ||:  .|\r\n" + "  ||:   |\r\n"
					+ "  ||: , |\r\n" + "  ||:   |\r\n" + "  ||: . |\r\n" + "  ||:   |");
	// There's no princess in that castle that's <meters> meters away, but enemy
	// reinforcements!
	// From the flag on top we can tell that there is a wind blowing <wind speed>
	// meters per second.
	// Bring them back down to earth!

	// https://www.asciiart.eu/buildings-and-places/castles

	private static AsciiArt newTrebuchet = new AsciiArt(9, false, "________\r\n" + "|      \\\r\n" + "|       \\\r\n"
			+ "@     _--\\--_\r\n" + "    //|   #  |\r\n" + "   // |______|");

	private static AsciiArt newBallista = new AsciiArt(9, false,
			"       ______/ /____\r\n" + "   ~---|    / /    |---~\r\n" + "    \\__|___/@/_____|__/\r\n"
					+ "      /   / /       /\r\n" + "     +___/_/_______+");

	private static AsciiArt smallRock = new AsciiArt(10, true, "O");
	private static AsciiArt mediumRock = new AsciiArt(10, true, " DDD\r\n" + "DDDDD\r\n" + " DDD");
	private static AsciiArt largeRock = new AsciiArt(10, true,
			"  BBBB\r\n" + " BBBBBB\r\n" + "BBBBBBBB\r\n" + " BBBBBB \r\n" + "  BBBB");

	private static AsciiArt singleCloud = new AsciiArt(5, false,
			"   __   _\r\n" + " _(  )_( )_\r\n" + "(_   _    _)\r\n" + "  (_) (__)");
//https://www.asciiart.eu/nature/clouds

	private static AsciiArt cow = new AsciiArt(9, false, "         __n__n__\r\n" + "  .------`-\00/-'\r\n"
			+ " /  ##  ## (oo)\r\n" + "/ \\## __   ./\r\n" + "   |//YY \\|/\r\n" + "   |||   |||");
//The lone cow that's <meters> meters away just insulted your mother! 
//Make it pay!! The wind may be blowing <wind speed> meters per second, but don't let that
//stop you from defending your mother's honor!

//https://www.asciiart.eu/animals/cows snd Art by Shanaka Dias

	private static AsciiArt bat = new AsciiArt(9, false,
			"  _   ,_,   _\r\n" + " / `'=) (='` \\\r\n" + "/.-.-.\\ /.-.-.\\ \r\n" + "      `\"      `");
//The troops spotted a bat <meters> meters away. It hasn't moved even though
//there is a gust blowing <wind speed> meters per second. The troops fear it may be
//a vampire, put them at ease!

//https://www.asciiart.eu/animals/bats jgs

	private static AsciiArt skeleton = new AsciiArt(9, false,
			"    .-.\r\n" + "   (o.o)\r\n" + "    |=|\r\n" + "   __|__\r\n" + " //.=|=.\\\\\r\n" + "// .=|=. \\\\\r\n"
					+ "\\\\ .=|=. //\r\n" + " \\\\(_=_)//\r\n" + "  (:| |:)\r\n" + "   || ||\r\n" + "   () ()\r\n"
					+ "   || ||\r\n" + "   || ||\r\n" + "  ==' '==");
	// It seems the enemy has found an undead ally <meters> meters from here.
	// Prove to them that neither a wind blowing <wind speed> meters per second nor
	// the super natural can stop us. Make sure this thing wishes it were dead!
	// Again!

	// https://www.asciiart.eu/mythology/skeletons 142

	private static AsciiArt dragon = new AsciiArt(9, false,
			"         __        _      \r\n" + "       _/  \\    _(\\(o     \r\n" + "      /     \\  /  _  ^^^o \r\n"
					+ "     /   !   \\/  ! '!!!v' \r\n" + "    !  !  \\ _' ( \\____    \r\n"
					+ "    ! . \\ _!\\   \\===^\\)   \r\n" + "     \\ \\_!  / __!         \r\n"
					+ "      \\!   /    \\         \r\n" + "(\\_      _/   _\\ )        \r\n"
					+ " \\ ^^--^^ __-^ /(__       \r\n" + "  ^^----^^    \"^--v'");
	// Fancy yourself a dragonslayer? Well there's one <meters> meters from here and
	// with a wind blowing <wind speed> meters per second, there's never been a
	// better
	// opportunity to add to your resume!

	// https://www.asciiart.eu/mythology/dragons Art by Gunnar Z.
	private static AsciiArt napoleon = new AsciiArt(9, false,
			"       .-\"\"\"\"\"-.\r\n" + "    .-\"         \"-.\r\n" + " __/               \\__\r\n"
					+ " \\     .-\"\"\"\"\"-.     /\r\n" + "  '----//o   o\\\\----'\r\n" + "      (    _\\   )\r\n"
					+ "  ,____`\\  =  /`____,\r\n" + "// \\   `;'---' `   / \\\r\n" + "\\\\/     |-o        \\//\r\n"
					+ "|    |  |      |     |\r\n" + "|    |  |-o    |\\    |\r\n" + "\\    `--|      |/    /\r\n"
					+ " '._    |-o    |    /\r\n" + "    '|\"\"|      |  .'\r\n" + "     |  |-o    |-`\r\n"
					+ "     |  |      |\r\n" + "     |_/ \\_____|\r\n" + "      |   |   |\r\n" + "      |   |   |\r\n"
					+ "      \\'-.|.-'/\r\n" + "      ]  _|_  [\r\n" + "     /    |    \\\r\n"
					+ "    /    / \\    \\\r\n" + "   (___/`   `\\___)");
//It seems Napoleon has finally gotten as big as his ego! Show that French tart
//what you're made of. He's standing <meters> meters from here and the wind is blowing
//<wind speed> meters per second. Hit him hard!

//https://www.asciiart.eu/people/famous/napoleon jgs Art by Joan G. Stark

	/**
	 * This like a the other getter methods in this class returns an instance of
	 * AsciiArt based on the name specified
	 * 
	 * @return The instance of AsciiArt pertaining to a rock, but in the wider class
	 *         it returns an instance of whatever was called. Example:
	 *         getCastleSprite() returns an instance of the AsciiArt(castleSprite).
	 */
	public static AsciiArt getRock() {
		return new AsciiArt(rock);
	}

	public static AsciiArt getCastleSprite() {
		return new AsciiArt(castleSprite);
	}

	public static AsciiArt getBallistaIcon() {
		return new AsciiArt(ballistaIcon);
	}

	public static AsciiArt getTrebuchetIcon() {
		return new AsciiArt(trebuchetIcon);
	}

	public static AsciiArt getCannon() {
		return new AsciiArt(cannon);
	}

	public static AsciiArt getSun() {
		return new AsciiArt(sun);
	}

	public static AsciiArt getTree() {
		return new AsciiArt(tree);
	}

	public static AsciiArt getWindMill() {
		return new AsciiArt(windMill);
	}

	public static AsciiArt getTrophy() {
		return new AsciiArt(trophy);
	}

	public static AsciiArt getSiegeWord() {
		return new AsciiArt(siegeWord);
	}

	public static AsciiArt getMoon() {
		return new AsciiArt(moon);
	}

	public static AsciiArt getCatapult() {
		return new AsciiArt(catapult);
	}

	public static AsciiArt getClouds() {
		return new AsciiArt(clouds);
	}

	public static AsciiArt getGround() {
		return new AsciiArt(ground);
	}

	public static AsciiArt getTower() {
		return new AsciiArt(tower);
	}

	public static AsciiArt getNewTrebuchet() {
		return new AsciiArt(newTrebuchet);
	}

	public static AsciiArt getNewBallista() {
		return new AsciiArt(newBallista);
	}

	public static AsciiArt getSmallRock() {
		return new AsciiArt(smallRock);
	}

	public static AsciiArt getMediumRock() {
		return new AsciiArt(mediumRock);
	}

	public static AsciiArt getLargeRock() {
		return new AsciiArt(largeRock);
	}

	public static AsciiArt getSingleCloud() {
		return new AsciiArt(singleCloud);
	}

	public static AsciiArt getCow() {
		return new AsciiArt(cow);
	}

	public static AsciiArt getBat() {
		return new AsciiArt(bat);
	}

	public static AsciiArt getSkeleton() {
		return new AsciiArt(skeleton);
	}

	public static AsciiArt getDragon() {
		return new AsciiArt(dragon);
	}

	public static AsciiArt getNapoleon() {
		return new AsciiArt(napoleon);
	}

}