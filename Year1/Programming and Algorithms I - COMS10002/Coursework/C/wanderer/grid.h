#include "display.h"

/** @file grid.h
A Grid is a 2D array of entities. */
struct grid;
typedef struct grid * Grid;

/** An entity is anything that can appear in the grid.  This is a forward
reference to the Entity type defined in the entity module.  This module makes
no assumptions about the type and calls no functions on it. */
struct entity;
typedef struct entity * Entity;

/** There are eight directions, plus HERE for the current position. */
enum direction
{ HERE, NORTH, SOUTH, EAST, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST };
typedef enum direction Direction;

/** Create a grid object, with NULL entities. */
Grid newGrid(int width, int height);

/** Get an entity out of the grid. */
Entity getCell(Grid g, int x, int y);

/** Put an entity into the grid. */
void setCell(Grid g, int x, int y, Entity e);

/** Get an entity's neighbour in the given direction. It is assumed that there
is a suitable border round the grid so the request is never out of bounds. */
Entity nearCell(Grid g, int x, int y, Direction d);

/** Test. */
void testGrid();
