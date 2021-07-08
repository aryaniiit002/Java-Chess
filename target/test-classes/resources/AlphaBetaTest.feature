Feature: Test Alpha–beta pruning

Scenario: Testing Alpha–beta pruning
  Given Given ChessBoard using Board.Builder
  When Evalute move with alphaBeta moveStrategy
  Then Test best move with evaluated alphaBeta move