Feature: Test FENParser

Scenario: Describing a particular board position using FEN
  Given Given Chess Board
  When Calculate String fenString
  Then Compare the fenString with the correct/user provided string

Scenario: Describing a particular board position
  Given Chess Board is given
  Then Calculate MoveTransition
  And Apply assertTrue to calculated MoveTransition
  Then calculate String fenString
  Then compare the fenString