Feature: Castling Test

Scenario: Testing White Player sides Castling
  Given Chess Board is given with white pieces
  When Player make move
  Then that move status now to be checked if successful or not
  Then check white player castling
  And check white player's king side castling
  And check white player's queen side castling

Scenario: Testing no caslting out of check
  Given Given FEN Chess Board
  When Calculate illegal castle moves and moveTransition
  Then apply assertTrue on calculated moveTransition