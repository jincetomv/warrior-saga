# Warrior Saga

## Description

This is a role playing game

## Levels
* Beginner - Just the starter with least complexity 
* Fighter  - Second level with complexity harder than beginner
* Invader  - Third level
* Achiever - Fourth level and most experienced level, complexity is the highest among all four

Level upgrade is based on the complexity and it lies between 0 and 1.

## Operations

* Create a character with level Beginner (Player-The protagonist) and a random complexity.
* Navigate over the provided map(Protagonist is denoted by character 'P' and enemies are denoted by 'E')
* Fight with a set of enemies with random complexity from all levels.
* Get awarded with experience points for each kill and a match victory.
* Get a health booster of 15 hit points for each kill
* Increment complexity based on fights protagonist involve.
* Save the game at any stage
* Resume the saved game even after you quit and relaunch.

## Technology stack

* Java8  - for coding
* Maven  - The build tool
* Junit4 with Mockito2 and PowerMock - For unit tests

## Design

Mostly tried to incorporate the concepts of Domain Driven Design


## How to run

This is can be built using the command

```mvn clean package```

And, can be run using the command

```java -jar target/warrior-saga-1.0-SNAPSHOT.jar```


## Extensibility

More features and experience enhancement components can be integrated to this without much effort. <br>

And here goes some of them,

* Integration of different story themes(Such as famous tv series or popular books)
* Availability of health boosters from different locations of navigation map
* More fight options such as knock out, magical powers etc. 
* Friends or supporting characters.