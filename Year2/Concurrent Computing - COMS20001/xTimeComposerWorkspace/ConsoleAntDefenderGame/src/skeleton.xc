/////////////////////////////////////////////////////////////////////////////////////////
//
// COMS20001
// CODE SKELETON FOR X-CORE200 EXPLORER KIT
// TITLE: "Console Ant Defender Game"
// Rudimentary game combining concurrent programming and I/O.
//
/////////////////////////////////////////////////////////////////////////////////////////

#include <stdio.h>
#include <print.h>
#include <xs1.h>
#include <platform.h>

on tile[0] : in port buttons = XS1_PORT_4E; //port to access xCore-200 buttons
on tile[0] : out port leds = XS1_PORT_4F;   //port to access xCore-200 LEDs

/////////////////////////////////////////////////////////////////////////////////////////
//
//  Helper Functions provided for you
//
/////////////////////////////////////////////////////////////////////////////////////////

//DISPLAYS an LED pattern
int showLEDs(out port p, chanend fromVisualiser) {
  int shutdown = 0;
  int pattern; //1st bit...separate green LED
               //2nd bit...blue LED
               //3rd bit...green LED
               //4th bit...red LED
  while (!shutdown) {
    fromVisualiser :> pattern;   //receive new pattern from visualiser
    if(pattern == 0xFF)
      shutdown = 1;
    else
      p <: pattern;                //send pattern to LED port
  }
  printf("Led Controller is shutting down\n");
  return 0;
}

//READ BUTTONS and send button pattern to userAnt
void buttonListener(in port b, chanend toUserAnt) {
  int shutdown = 0;
  int r;
  while (!shutdown) {
    b when pinseq(15)  :> r;
    select {
      case b when pinsneq(15) :> r:
        //b when pinseq(15)  :> r;    // check that no button is pressed
        //b when pinsneq(15) :> r;    // check if some buttons are pressed
        if ((r==13) || (r==14))     // if either button is pressed
        toUserAnt <: r;             // send button pattern to userAnt
        break;
      case toUserAnt :> shutdown:
        shutdown = 1;
        break;
    }
  }
  printf("Button listener shutting down\n");
}

//WAIT function
void waitMoment(int duration) {
  timer tmr;
  int waitTime;
  tmr :> waitTime;                       //read current timer value
  waitTime += duration;                  //set waitTime to 0.4s after value
  tmr when timerafter(waitTime) :> void; //wait until waitTime is reached
}

//PRINT WORLD TO CONSOLE
void consolePrint(unsigned int userAntToDisplay,
                  unsigned int attackerAntToDisplay) {
  char world[25]; //world of size 23, plus 1 byte for line return
                  //                  plus 1 byte for 0-termination

  //make the current world string
  for(int i=0;i<23;i++) {
    if ((i>7) && (i<15)) world[i]='-';
    else world[i]='.';
    if (userAntToDisplay==i) world[i]='X';
    if (attackerAntToDisplay==i) world[i]='O';
  }
  world[23]='\n';  //add a line break
  world[24]=0;     //add 0-termination
  printstr(world); //send off to console
}

//PROCESS THAT COORDINATES DISPLAY
void visualiser(chanend fromUserAnt, chanend fromAttackerAnt, chanend toLEDs) {
  int shutdown = 0;
  unsigned int userAntToDisplay = 11;
  unsigned int attackerAntToDisplay = 2;
  int pattern = 0;
  int round = 0;
  int distance = 0;
  int dangerzone = 0;
  while (!shutdown) {
    if (round==0) printstr("ANT DEFENDER GAME (press button to start)\n");
    round++;
    select {
      case fromUserAnt :> userAntToDisplay:
        consolePrint(userAntToDisplay,attackerAntToDisplay);
        break;
      case fromAttackerAnt :> attackerAntToDisplay:
        if(attackerAntToDisplay == 0xFF){
          shutdown = 1;
          toLEDs <: 0xFF;
        }
        else
          consolePrint(userAntToDisplay,attackerAntToDisplay);
        break;
    }
    if(!shutdown){
      distance = userAntToDisplay-attackerAntToDisplay;
      dangerzone = ((attackerAntToDisplay==7) || (attackerAntToDisplay==15));
      pattern = round%2 + 8 * dangerzone + 2 * ((distance==1) || (distance==-1));
      if ((attackerAntToDisplay>7)&&(attackerAntToDisplay<15)) pattern = 15;
      toLEDs <: pattern;
    }
  }
  printf("Visualiser is shutting down\n");
}

/////////////////////////////////////////////////////////////////////////////////////////
//
//  MOST RELEVANT PART OF CODE TO EXPAND FOR YOU
//
/////////////////////////////////////////////////////////////////////////////////////////

//Keeps an unsigned integer within two bounds
//Implemented because C99's remainder operator
//does not work exactly as desired
unsigned int keepWithinBounds(unsigned int n,unsigned int low, unsigned int high, int leftORright){
  if(n==low && leftORright<0)
    return high;
  else if(n==high && leftORright>0)
    return low;
  return n+leftORright;
}

//DEFENDER PROCESS... The defender is controlled by this process userAnt,
//                    which has channels to a buttonListener, visualiser and controller
void userAnt(chanend fromButtons, chanend toVisualiser, chanend toController) {
  int shutdown = 0;
  unsigned int userAntPosition = 11;       //the current defender position
  int buttonInput;                         //the input pattern from the buttonListener
  unsigned int attemptedAntPosition = 0;            //the next attempted defender position after considering button
  int moveForbidden;                       //the verdict of the controller if move is allowed
  toVisualiser <: userAntPosition;         //show initial position
  while (!shutdown) {
    select {
      case toController :> int control:
        if(control == 0xFF) {
          shutdown = 1;
          fromButtons <: control;
        }
        break;
      case fromButtons :> buttonInput: //expect values 13 and 14
        //added code starts here
        if (buttonInput == 14)
          attemptedAntPosition = keepWithinBounds(userAntPosition,0,22,-1); //if SW1 pressed, go left
        else
          attemptedAntPosition = keepWithinBounds(userAntPosition,0,22,+1); //if SW2 pressed, go right

        toController <: attemptedAntPosition; //Ask controller for permission
        toController :> moveForbidden;
        if(!moveForbidden)
          userAntPosition = attemptedAntPosition;

        //added code ends here
        toVisualiser <: userAntPosition;
        break;
    }
  }
  printf("User ant is shutting down\n");
}

int toggleDirection(int current){
  if(current==1)
    return -1;
  else
    return 1;
}

//ATTACKER PROCESS... The attacker is controlled by this process attackerAnt,
//                    which has channels to the visualiser and controller
void attackerAnt(chanend toVisualiser, chanend toController) {
  int moveCounter = 0;                       //moves of attacker so far
  unsigned int attackerAntPosition = 2;      //the current attacker position
  unsigned int attemptedAntPosition;         //the next attempted  position after considering move direction
  int currentDirection = 1;                  //the current direction the attacker is moving
  int moveForbidden = 0;                     //the verdict of the controller if move is allowed
  int shutdown = 0;                           //indicating the attacker process is alive
  int duration = 40000000;
  const int LOWER_DURATION = 10000000;
  const int STEP_DURATION = 1000000;
  const int DECREASE_INTERVAL = 5;
  toVisualiser <: attackerAntPosition;       //show initial position

  while (!shutdown) {
  //added code starts here
    moveCounter++;
    if(moveCounter%31==0 || moveCounter%37==0) //toggle direction according to specs
      currentDirection = toggleDirection(currentDirection);
    attemptedAntPosition = keepWithinBounds(attackerAntPosition,0,22,currentDirection);

    toController <: attemptedAntPosition;
    toController :> moveForbidden;

    if(!moveForbidden)
      attackerAntPosition = attemptedAntPosition;
    else
      currentDirection = toggleDirection(currentDirection);

    toVisualiser <: attackerAntPosition;

    if(attackerAntPosition>7 && attackerAntPosition<15){
      shutdown = 1;
      toVisualiser <: 0xFF;
    }
  //added code ends here
    if(moveCounter%DECREASE_INTERVAL==0 && duration>=LOWER_DURATION)
      duration-=STEP_DURATION;
    waitMoment(duration);
  }
  printf("Attacker ant is shutting down\n");
}

//COLLISION DETECTOR... the controller process responds to �permission-to-move� requests
//                      from attackerAnt and userAnt. The process also checks if an attackerAnt
//                      has moved to winning positions.
void controller(chanend fromAttacker, chanend fromUser) {
  unsigned int lastReportedUserAntPosition = 11;      //position last reported by userAnt
  unsigned int lastReportedAttackerAntPosition = 2;   //position last reported by attackerAnt
  unsigned int attempt = 0;                           //incoming data from ants
  int gameEnded = 0;                                  //indicates if game is over
  fromUser :> attempt;                                //start game when user moves
  fromUser <: 1;                                      //forbid first move
  while (!gameEnded) {
    select {
      case fromAttacker :> attempt:
      //added code starts here
        if (attempt == lastReportedUserAntPosition)
          fromAttacker <: 1;
        else {
          fromAttacker <: 0;
          lastReportedAttackerAntPosition = attempt;
        }

        if(lastReportedAttackerAntPosition>7 && lastReportedAttackerAntPosition<15){
          gameEnded = 1;
          fromUser <: 0xFF;
        }
      //added code ends here
        break;
      case fromUser :> attempt:
      //added code starts here
        if (attempt == lastReportedAttackerAntPosition)
          fromUser <: 1;
        else {
          fromUser <: 0;
          lastReportedUserAntPosition = attempt;
        }
      //added code ends here
        break;
    }
  }
  printf("Controller is shutting down\n");
}

//MAIN PROCESS defining channels, orchestrating and starting the processes
int main(void) {
  chan buttonsToUserAnt,         //channel from buttonListener to userAnt
       userAntToVisualiser,      //channel from userAnt to Visualiser
       attackerAntToVisualiser,  //channel from attackerAnt to Visualiser
       visualiserToLEDs,         //channel from Visualiser to showLEDs
       attackerAntToController,  //channel from attackerAnt to Controller
       userAntToController;      //channel from userAnt to Controller

  par {
    //PROCESSES FOR YOU TO EXPAND
    on tile[1]: userAnt(buttonsToUserAnt,userAntToVisualiser,userAntToController);
    on tile[1]: attackerAnt(attackerAntToVisualiser,attackerAntToController);
    on tile[1]: controller(attackerAntToController, userAntToController);

    //HELPER PROCESSES USING BASIC I/O ON X-CORE200 EXPLORER
    on tile[0]: buttonListener(buttons, buttonsToUserAnt);
    on tile[0]: visualiser(userAntToVisualiser,attackerAntToVisualiser,visualiserToLEDs);
    on tile[0]: showLEDs(leds,visualiserToLEDs);
  }
  return 0;
}