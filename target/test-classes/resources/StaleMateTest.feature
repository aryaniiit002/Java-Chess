Feature: Test StateMate situation

Scenario: Testing player StateMate situation
  Given Given ChessBoard using Board.builder
  When player make move evalute it with MoveTransition
  Then evalute inCheck, isInStaleMate, inCheckMate position