Feature: Test Engine

Scenario: Testing chess engine
  Given Given Chess Board using createGameFromFEN
  When Calculate minimax from move strategy
  Then execute minimax and evalute board
