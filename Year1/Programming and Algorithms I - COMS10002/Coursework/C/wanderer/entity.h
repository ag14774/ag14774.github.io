#include "grid.h"

/** @file entity.h
An entity is anything that can appear in the grid.  This module provides
generic operations that all entities use. A forward reference to the Entity
type is in the grid header. */
struct entity;

/** Fill a grid with new entities, e.g. from a level file. */
void fillGrid(Grid g, Display d, int width, int height, char *kinds[]);

/** Get the kind of an entity. */
Kind getKind(Entity e);

/** Get the direction of motion. */
Direction getMotion(Entity e);

/** Set the direction of motion. */
void setMotion(Entity e, Direction d);

/** Get an entity's neighbour in the given direction. It is assumed that there
is a suitable border round the grid so the request is never out of bounds. */
Entity getNear(Entity entity, Direction d);

/** Move an entity by swapping places with a given target entity. This function
also represents an end of frame, drawing to the screen and adding a delay. */
void move(Entity entity, Entity target);

/** Change the kind of an entity. This should always be followed by a move
involving the mutated entity, even if it is just move(player,player). */
void mutate(Entity entity, Kind newKind);

/** Test. */
void testEntity();
