Feature: CheckMate Test

Scenario: Testing checkkMate move
  Given Chess board is given
  When player make move
  Then that move status now to be checked if done or not
  Then check checkmate situation