Feature: MiniMax Algorithm Test

Scenario: Testing MiniMax
  Given Provided a chess board using board.createStandardBoard
  Then calculate move strategy
  Then Evaluate minimax move strategy

Scenario: Testing-2 MiniMax
  Given Provided a chess board using builder.setPieces
  Then white and black player make their moves
  Then minimax move strategy now to be evaluated

Scenario: Testing-3 MiniMax
  Given Provided a chess board using FenUtilities.createGameFromFEN
  Then Calculate move transition and strategy
  Then Evaluate MiniMax move strategy
