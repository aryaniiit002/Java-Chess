Feature: Pawn movement

Scenario: Describing a particular board position using FEN
  Given Given Chess Board
  When Calculate String fenString
  Then Compare the fenString with the correct/user provided string

Scenario: Analysing board for Pawn Structure or Movements
  Given create a particular board position
  When setting up board with white pieces
  And setting up board with black pieces
  Then build board
  Then compare the analysed board for White Player
  Then compare the analysed board for Black Player
