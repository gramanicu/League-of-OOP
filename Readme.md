# League of OOP

This is a project that represents the 2nd and 3rd homework for the Object-Oriented Programming Course. The problem statement can be found [here](http://elf.cs.pub.ro/poo/teme/proiect/etapa1).

## Table of Contents

- [League of OOP](#league-of-oop)
  - [Table of Contents](#table-of-contents)
  - [Project structure](#project-structure)
  - [Packages](#packages)
    - [game](#game)
    - [maps](#maps)
    - [heroes](#heroes)
    - [abilities](#abilities)
  - [Game overview](#game-overview)

## Project structure

``` bash
 - .src
    |-- Readme.md
    |-- abilities
    |   |-- Ability.java
    |   |-- Backstab.java
    |   |-- Deflect.java
    |   |-- Drain.java
    |   |-- Execute.java
    |   |-- Fireblast.java
    |   |-- Ignite.java
    |   |-- Paralysis.java
    |   |-- Slam.java
    |   |-- StatusEffect.java
    |   |-- StatusEffectType.java
    |-- heroes
    |   |-- Hero.java
    |   |-- HeroesFactory.java
    |   |-- HeroType.java
    |   |-- Knight.java
    |   |-- Pyromancer.java
    |   |-- Rogue.java
    |   |-- Wizard.java
    |-- main
    |   |-- Main.java
    |   |-- Game.java
    |-- maps
        |-- Map.java
        |-- Movement.java
        |-- Point.java
        |-- TerrainType.java
```

## Packages

### game

This package contains the game logic.

- Main - contains the main function, passes the arguments to the game driver
- Game - a driver class, manages input, output, and game logic

### maps

This package contains classes related to placement/movement on the game map

- Map - contains the map data (terrain types)
- Movement - an enum that contains all directions a player can move to
- Point - a data structure that stores the map bounds (position limits) and players position. It also manages the actual player movement
- TerrainType - an enum that contains all the terrain types

### heroes

This package contains the classes related to players/heroes

- Hero - a template class, that contains the base logic for players
- HeroesFactory - a factory used to create heroes.
- HeroType - an enum that contains all the player types
- Knight, Pyromancer, Rogue, Wizard - classes that implement functionality specific to the hero

### abilities

This package contains the classes related to heroes abilities

- Ability - a template class, that contains the base logic for abilities
- StatusEffect - a class that implements status effects (damage-over-time abilities, crowd control, etc.)
- StatusEffectTypes - an enum that contains all the status effect types
- BackStab, Deflect, etc. - classes that implement the abilities specific to each hero (each ability in its own class)

## Game overview

After the game driver loads the data into it's specific containers, the "game" is started. Each round, the player will try to move, the status effect (if he has one) will apply, and then fights can start. If a fight is possible, both players will use their abilities at the same time.

One specific exeption is the wizard, who will actively use only 1 ability, as the second is a passive. For every other ability he will get hit by (in other words, after the enemy hits him with both abilities), he will use `Deflect`.

After both heroes have used their abilities (and apply their specific status effects), their status will be checked: if only 1 of them died, the other will get xp based on the following formula `XP_Winner = XP_Winner + max(0, 200 - (Level_winner - Level_loser) * 40)` and will level up if he can do so. On level up, his `max_hp` will increase and his `hp` will be restored to maximum.

For more details, you can read the javadoc, the comments and [the problem statement](http://elf.cs.pub.ro/poo/teme/proiect/etapa1).

© 2019 Grama Nicolae, 322CA
