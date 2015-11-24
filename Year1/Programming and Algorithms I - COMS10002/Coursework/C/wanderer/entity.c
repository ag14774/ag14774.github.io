#include "entity.h"
#include <stdlib.h>
#include <stdio.h>

struct entity
{
    Kind kind; int x, y; Direction motion; Grid grid; Display display;
};

void fillGrid(Grid g, Display d, int width, int height, char *kinds[])
{
    for (int x=0; x<width; x++)
    {
        for (int y=0; y<height; y++)
        {
            Entity e = malloc(sizeof(struct entity));
            *e = (struct entity) { kinds[x][y], x, y, HERE, g, d };
            setCell(g, x, y, e);
        }
    }
}

Kind getKind(Entity e)
{
    return e->kind;
}

Direction getMotion(Entity e)
{
    return e->motion;
}

void setMotion(Entity e, Direction d)
{
    e->motion = d;
}

Entity getNear(Entity e, Direction d)
{
    return nearCell(e->grid, e->x, e->y, d);
}

// Move an entity by swapping places with a given target entity. This function
// also represents an end of frame, drawing to the screen and adding a delay.
void move(Entity entity, Entity target)
{
    Grid g = entity->grid;
    Display d = entity->display;
    int x = entity->x;
    int y = entity->y;
    entity->x = target->x;
    entity->y = target->y;
    target->x = x;
    target->y = y;
    setCell(g, target->x, target->y, target);
    setCell(g, entity->x, entity->y, entity);
    drawEntity(d, target->kind, target->x, target->y);
    drawEntity(d, entity->kind, entity->x, entity->y);
}

void mutate(Entity entity, Kind newKind)
{
    entity->kind = newKind;
}

// Create a test grid, 3 x 3 with player in the middle.
static Grid setup()
{
    Display d = newDisplay(3, 3);
    Grid g = newGrid(3, 3);
    char *level[] = { "...", ".@.", "..." };
    fillGrid(g, d, 3, 3, level);
    return g;
}

static void testMove()
{
    Grid g = setup();
    Entity player = getCell(g, 1, 1);
    if (player->kind != '@') fail("Entity fillGrid test failed");
    Entity space = getCell(g, 2, 1);
    if (space->kind != '.') fail("Entity fillGrid test 2 failed");
    move(player, space);
    if (getCell(g, 1, 1) != space) fail("Grid move test 1 failed");
    if (getCell(g, 2, 1) != player) fail("Grid move test 2 failed");
}

void testEntity()
{
    testGrid();
    testMove();
    puts("Entity module OK");
}

#ifdef TEST_ENTITY
int main() { testEntity(); }
#endif
