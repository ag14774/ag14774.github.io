#include "display.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

struct display { int width, height; };

// Report a failure in a script-friendly way, and stop the program.
void fail(char *message)
{
    fputs(message, stderr);
    fputs("\n", stderr);
    exit(1);
}

Display newDisplay(int width, int height)
{
    Display d = malloc(sizeof(struct display));
    *d = (struct display) { width, height };
    return d;
}

void drawEntity(Display d, Kind k, int x, int y)
{
    printf("%d,%d %c\n", x, y, k);
}

Key getKey(Display d)
{
    static char *keys = "^v<>.";
    static int n = 0;
    if (n >= strlen(keys)) n = 0;
    return keys[n++];
}

void testDisplay() { puts("Display module OK"); }

#ifdef TEST_DISPLAY
int main() { testDisplay(); }
#endif
