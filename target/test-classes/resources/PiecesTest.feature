Feature: Test Pieces

Scenario: Testing White Pieces on board
  Given Given Chess Board using board.builder
  When set white pieces on board
  Then calculate moves and execute board evaluation
  And evalute legal moves of board white pieces

Scenario: Testing black Pieces on board
  Given given Chess Board using board.builder
  When set black pieces on board
  Then calculate black moves and execute board evaluation
  And evalute legal moves of board black pieces
