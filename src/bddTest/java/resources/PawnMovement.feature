Feature: Pawn movement

Scenario: A pawn in its beginning position
  When there is a chess board set up as
  |  x |  a |  b |  c |  d |  e |  f |  g |  h |
  |  1 |    |    |    | BK |    |    |    |    |
  |  2 |    |    |    |    |    |    |    |    |
  |  3 |    |    |    |    |    |    |    |    |
  |  4 |    |    |    |    |    |    |    |    |
  |  5 |    |    |    |    |    |    |    |    |
  |  6 |    |    |    |    |    |    |    |    |
  |  7 |    |    |    | WP |    |    |    |    |
  |  8 |    |    |    | WK |    |    |    |    |
  Then the WP at D7 should have the following moves
  | Start | End |
  | D7    | D6  |
  | D7    | D5  |

Scenario: A pawn has moved before
  When there is a chess board set up as
  |  x |  a |  b |  c |  d |  e |  f |  g |  h |
  |  1 |    |    |    | BK |    |    |    |    |
  |  2 |    |    |    |    |    |    |    |    |
  |  3 |    |    |    |    |    |    |    |    |
  |  4 |    |    |    |    |    |    |    |    |
  |  5 |    |    |    |    |    |    |    |    |
  |  6 |    |    |    | WP |    |    |    |    |
  |  7 |    |    |    |    |    |    |    |    |
  |  8 |    |    |    | WK |    |    |    |    |
  And the following moves have been made
  | Start | End |
  |   D5  | D6  |
  Then the WP at D6 should have the following moves
  | Start | End |
  | D6    | D5  |

Scenario: A pawn has an enemy on the left attacking position
  When there is a chess board set up as
  |  x |  a |  b |  c |  d |  e |  f |  g |  h |
  |  1 |    |    |    | BK |    |    |    |    |
  |  2 |    |    |    |    |    |    |    |    |
  |  3 |    |    |    |    |    |    |    |    |
  |  4 |    |    |    |    |    |    |    |    |
  |  5 |    |    | BP |    |    |    |    |    |
  |  6 |    |    |    | WP |    |    |    |    |
  |  7 |    |    |    |    |    |    |    |    |
  |  8 |    |    |    | WK |    |    |    |    |
  Then the WP at D6 should have the following moves
  | Start | End |
  | D6    | D5  |
  | D6    | C5  |
