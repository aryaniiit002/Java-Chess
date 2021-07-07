Feature: Test Player

Scenario: Testing player move and check move status and transition
  Given Given ChessBoard using board.createStandardBoard
  When player make the move evalute it with MoveTransition
  Then check move status and evaluate board using StandardBoardEvaluator

Scenario: Testing Player move and check moveStatus and Transition
  Given Given ChessBoard using board.builder
  When player make the move and set pieces on board
  Then Check player moves status
