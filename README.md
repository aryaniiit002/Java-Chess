# Java-Chess

### Chess Engine with BDD Testing Methods and AI.
Building chess engine from scratch in the java programming language.

Objective was to develop and test a bug-free standalone game in Java, complete with a GUI and game logic components.

We can set it up as human vs human or human vs computer or even computer vs computer.

GUI inspired by: https://en.lichess.org/

## How to run ?

 1. `git clone` this repository.
 2. Compile the project.
 3. Run [java application/JChess](https://github.com/aryaniiit002/Java-Chess/blob/master/src/main/java/com/chess/JChess.java).


![JChess](https://github.com/aryaniiit002/Java-Chess/blob/master/art/JChess.png)

## Testing Methods

### Test-driven development   
[TDD](https://www.browserstack.com/guide/what-is-test-driven-development)     
Test Driven Development (TDD) is a process of modifying the code in order to pass a test designed previously. It more emphasis on production code rather than test case design.

[TDD Tests](https://github.com/aryaniiit002/Java-Chess/tree/master/src/tests/junit/test/com/chess)     

### Behaviour-Driven Development      
[BDD](https://www.froglogic.com/squish/features/bdd-behavior-driven-development-testing/)    
Behaviour Driven Development (BDD) is a synthesis and refinement of practices stemming from Test Driven Development (TDD) and Acceptance Test Driven Development (ATDD).

[BDD Tests](https://github.com/aryaniiit002/Java-Chess/tree/master/src/bddTest/java)

### TDD vs BDD
https://phoenixnap.com/blog/tdd-vs-bdd

#### In simple words
TDD is Test Driven Development means writing a test that fails because the specified functionality doesn't exist, then writing the simplest code that can make the test pass, then refactoring to remove duplication, etc. You repeat this Red-Green-Refactor loop over and over until you have a complete feature.

BDD is Behavior Driven Development means creating an executable specification that fails because the feature doesn't exist, then writing the simplest code that can make the spec pass. You repeat this until a release candidate is ready to ship.

Those seem pretty similar, right? They are. The key difference is the scope. TDD is a development practice while BDD is a team methodology. In TDD, the developers write the tests while in BDD the automated specifications are created by users or testers (with developers wiring them to the code under test.) For small, co-located, developer-centric teams, TDD and BDD are effectively the same.

## How it works?

The game state is mainly stored as a 2D list of strings, and most of the processing is thus done on a list of strings.

The GUI takes the current state and displays it on the screen. The GUI allows drag and drop movement of pieces as well as click-click movement.

***Focus of this engine is simplicity and readability. This is by no means the optimal way to build the chess engine.***

## Technology

This game is built using core Java, Java Swing GUI libraries and the jUnit test suite. It uses custom drawing for game components and self-programmed logic for checkmate detection. The code is modular, standalone and object-oriented.


### [MINIMAX](https://en.wikipedia.org/wiki/Minimax)
Basically I have built a working rule engine for chess, build a basic graphical user interface using Swing.  
We build a very simplistic that uses _Minimax_ algorithm and a simple evaluation routine and plugged that into UI.

### [Portable Game Notation](https://en.wikipedia.org/wiki/Portable_Game_Notation)
We moved into _PGN_ and fend file formats to build a database, and we can load games that are in
progress using standard chess notations.

### [FEN File Format](https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation) 
FEN , which is just a standard notation for describing a particular board position in game of chess.

This is useful when we want to analyse a game in any position and without having to play out all the moves that took place in that game. 

### AI Functioning

In brief -  
The AI that plays against the human evaluates all possible moves made by either player up to a certain level of depth.

The AI evaluates each position by giving it a score. The higher the value of the score, the more favourable a position is for white, and the lower the value of the score, the more favourable the position is for black.

Knowing that white will try to get the score to be higher and black will try to get the score to be lower, the AI assumes best play from either side as it traverses up the search tree and chooses the best move to be played.

A problem that may arise is the number of postions that need to be evaulated. Even at 3 levels of depth, thousands of positions have to be evaluatd. Several methods are used in this program to reduce positions that are searched:

In order to traverse the search tree as above, the AI needs to know how to evaluate the board at any position to decide if white or black has the advantage.
