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

#### Test-driven development
#### Behaviour-Driven Development  
{work In Progress}

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

### Directory Structure of Source Code `./src`.

<pre>
./src
├── main
│   └── java
│       └── com
│           └── chess
│               ├── engine
│               │   └── classic
│               │       ├── Alliance.java
│               │       ├── board
│               │       │   ├── Board.java
│               │       │   ├── BoardUtils.java
│               │       │   ├── Move.java
│               │       │   ├── MoveTransition.java
│               │       │   └── MoveUtils.java
│               │       ├── pieces
│               │       │   ├── Bishop.java
│               │       │   ├── King.java
│               │       │   ├── Knight.java
│               │       │   ├── Pawn.java
│               │       │   ├── Piece.java
│               │       │   ├── PieceUtils.java
│               │       │   ├── Queen.java
│               │       │   └── Rook.java
│               │       └── player
│               │           ├── ai
│               │           │   ├── AlphaBetaWithMoveOrdering.java
│               │           │   ├── BoardEvaluator.java
│               │           │   ├── IterativeDeepening.java
│               │           │   ├── KingSafetyAnalyzer.java
│               │           │   ├── MiniMax.java
│               │           │   ├── MoveOrdering.java
│               │           │   ├── MoveStrategy.java
│               │           │   ├── PawnStructureAnalyzer.java
│               │           │   ├── RookStructureAnalyzer.java
│               │           │   ├── StandardBoardEvaluator.java
│               │           │   └── StockAlphaBeta.java
│               │           ├── BlackPlayer.java
│               │           ├── Player.java
│               │           └── WhitePlayer.java
│               ├── gui
│               │   ├── DebugPanel.java
│               │   ├── GameHistoryPanel.java
│               │   ├── GameSetup.java
│               │   ├── Table.java
│               │   └── TakenPiecesPanel.java
│               ├── JChess.java
│               └── pgn
│                   ├── FenUtilities.java
│                   ├── GameFactory.java
│                   ├── Game.java
│                   ├── InvalidGame.java
│                   ├── MySqlGamePersistence.java
│                   ├── ParsePGNException.java
│                   ├── PGNGameTags.java
│                   ├── PGNPersistence.java
│                   ├── PGNUtilities.java
│                   ├── Playable.java
│                   ├── PlayPGNException.java
│                   └── ValidGame.java
└── tests
    └── java
        └── com
            └── chess
                ├── ChessTestSuite.java
                ├── pgn
                │   ├── queenPromotion.pgn
                │   ├── smallerTest.pgn
                │   ├── t10.pgn
                │   ├── t1.pgn
                │   ├── t2.pgn
                │   ├── t3.pgn
                │   ├── t4.pgn
                │   ├── t5.pgn
                │   ├── t6.pgn
                │   ├── t7.pgn
                │   ├── t8.pgn
                │   └── t9.pgn
                ├── TestAlphaBeta.java
                ├── TestBoard.java
                ├── TestCastling.java
                ├── TestCheckmate.java
                ├── TestEngine.java
                ├── TestFENParser.java
                ├── TestIterativeDeepening.java
                ├── TestKingSafety.java
                ├── TestMiniMax.java
                ├── TestPawnStructure.java
                ├── TestPGNParser.java
                ├── TestPieces.java
                ├── TestPlayer.java
                ├── TestRookStructure.java
                └── TestStaleMate.java

17 directories, 74 files

</pre>


