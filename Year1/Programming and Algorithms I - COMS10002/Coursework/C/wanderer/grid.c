#include "grid.h"
#include <stdlib.h>
#include <stdio.h>

struct grid { int width, height; Entity **cells; };

// Find the x offset corresponding to given direction.
static int dx(Direction d)
{
    switch (d)
    {
    case HERE: case NORTH: case SOUTH: return 0;
    case EAST: case NORTHEAST: case SOUTHEAST: return 1;
    case WEST: case NORTHWEST: case SOUTHWEST: return -1;
    default: fail("Unknown direction"); return 0;
    }
}

// Find the y offset corresponding to a given direction.
static int dy(Direction d)
{
    switch (d)
    {
    case HERE: case EAST: case WEST: return 0;
    case SOUTH: case SOUTHEAST: case SOUTHWEST: return 1;
    case NORTH: case NORTHEAST: case NORTHWEST: return -1;
    default: fail("Unknown direction"); return 0;
    }
}

// Check if x and y are within bounds, and fail if not.
static void checkBounds(Grid g, int x, int y)
{
    if (x < 0 || x >= g->width) fail("x out of bounds");
    if (y < 0 || y >= g->height) fail("y out of bounds");
}

// Create a new dynamic array of entities
static Entity **newArray(int width, int height)
{
    Entity **cells = malloc(width * sizeof(Entity *));
    for (int x=0; x<width; x++) cells[x] = malloc(height * sizeof(Entity));
    return cells;
}

Grid newGrid(int width, int height)
{
    Grid g = malloc(sizeof(struct grid));
    *g = (struct grid) { width, height, newArray(width, height) };
    for (int x=0; x<g->width; x++)
    {
        for (int y=0; y<g->height; y++)
        {
            g->cells[x][y] = NULL;
        }
    }
    return g;
}

Entity getCell(Grid g, int x, int y)
{
    checkBounds(g, x, y);
    return g->cells[x][y];
}

void setCell(Grid g, int x, int y, Entity e)
{
    checkBounds(g, x, y);
    g->cells[x][y] = e;
}

Entity nearCell(Grid g, int x, int y, Direction d)
{
    return getCell(g, x + dx(d), y + dy(d));
}

// Define a local entity structure just for testing.
struct entity { char kind; };

// Create an entity, just for testing.
static Entity newEntity(char k)
{
    Entity e = malloc(sizeof(struct entity));
    e->kind = k;
    return e;
}

// Test setCell, getCell, nearCell.
static void testNear()
{
    Grid g = newGrid(3, 3);
    Entity player = newEntity('@');
    Entity space = newEntity('.');
    setCell(g, 1, 1, player);
    setCell(g, 2, 1, space);
    if (getCell(g, 1, 1) != player) fail("Grid getCell test failed");
    Entity next = nearCell(g, 1, 1, EAST);
    if (next != space) fail("Grid nearCell test failed");
}

void testGrid()
{
    testDisplay();
    testNear();
    puts("Grid module OK");
}

#ifdef TEST_GRID
int main() { testGrid(); }
#endif
