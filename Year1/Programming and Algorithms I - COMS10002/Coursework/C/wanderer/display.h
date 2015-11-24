/** @file display.h
The Display object displays the game on the screen. */
struct display;
typedef struct display * Display;

/** These are the kinds of entity, with their level-file character codes. */
enum kind
{
  SPACE='.', WALL='#', ROCK='=', EARTH=':', LEFT_DEFLECTOR='/',
  RIGHT_DEFLECTOR='\\', STAR='*', CAGE='+', LANDMINE='!',
  TELEPORT='T', TIME='C', EXIT='X', ARRIVAL='A',
  BOULDER='O', LEFT_ARROW='<', RIGHT_ARROW='>', BALLOON='^',
  PLAYER='@', DEAD='?', MONSTER='M', BABY_MONSTER='S'
};
typedef enum kind Kind;

/** These are the codes for the key presses: arrow keys and space bar to move,
'Q' (either case) to quit, 'R' to restart the level, 'N' to go to the next
level, '+' to speed up, '-' to slow down.*/
enum key
{
    LEFT='<', RIGHT='>', UP='^', DOWN='v', STAY='.',
    QUIT='Q', RESTART='R', NEXT='N', FASTER='+', SLOWER='-'
};
typedef enum key Key;

/** Create a display with a given grid size. */
Display newDisplay(int width, int height);

/** Get the next key press. */
Key getKey(Display d);

/** Draw an entity of a given kind at given grid coordinates. */
void drawEntity(Display d, Kind k, int x, int y);

/** Report a bug or test failure and stop the program. */
void fail(char *message);

/** Test. */
void testDisplay();
