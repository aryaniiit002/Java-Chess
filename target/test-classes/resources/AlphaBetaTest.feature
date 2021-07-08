Feature: Test Alpha–beta pruning and IterativeDeepening

Scenario: Testing Alpha–beta pruning
  Given Given ChessBoard using Board.Builder
  When Evalute move with alphaBeta moveStrategy
  Then Test best move with evaluated alphaBeta move

Scenario: Testing IterativeDeepening
  Given ChessBoard is given using Board.Builder
  When Evalute dept with iterativeDeepening moveStrategy
  Then Test best move with evaluated iterativeDeepening move