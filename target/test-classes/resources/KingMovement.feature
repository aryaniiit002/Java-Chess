Feature: King movement

Scenario:  Castling when the king hasn't moved
  When there is a chess board set up as
  |  x |  a |  b |  c |  d |  e |  f |  g |  h |
  |  1 |    |    |    | BK |    |    |    |    |
  |  2 |    |    |    |    |    |    |    |    |
  |  3 |    |    |    |    |    |    |    |    |
  |  4 |    |    |    |    |    |    |    |    |
  |  5 |    |    |    |    |    |    |    |    |
  |  6 |    |    |    |    |    |    |    |    |
  |  7 | WP | WP | WP | WP |    | WP | WP | WP |
  |  8 | WR | WN | WB | WQ | WK |    |    | WR |
  Then the WK at E8 should have the following moves
  | Start | End |
  | E8    | E7  |
  | E8    | G8  |

Scenario:  The pawn was moved up
  When there is a chess board set up as
  |  x |  a |  b |  c |  d |  e |  f |  g |  h |
  |  1 |    |    |    | BK |    |    |    |    |
  |  2 |    |    |    |    |    |    |    |    |
  |  3 |    |    |    |    |    |    |    |    |
  |  4 |    |    |    |    |    |    |    |    |
  |  5 |    |    |    |    |    |    |    |    |
  |  6 |    |    |    |    | WP |    |    |    |
  |  7 | WP | WP | WP | WP |    | WP | WP | WP |
  |  8 | WR | WN | WB | WQ | WK | WB | WN | WR |
  Then the WK at E8 should have the following moves
  | Start | End |
  | E8    | E7  |

Scenario:  A king in its beginning position
  When there is a chess board set up as
  |  x |  a |  b |  c |  d |  e |  f |  g |  h |
  |  1 |    |    |    | BK |    |    |    |    |
  |  2 |    |    |    |    |    |    |    |    |
  |  3 |    |    |    |    |    |    |    |    |
  |  4 |    |    |    |    |    |    |    |    |
  |  5 |    |    |    |    |    |    |    |    |
  |  6 |    |    |    |    |    |    |    |    |
  |  7 | WP | WP | WP | WP | WP | WP | WP | WP |
  |  8 | WR | WN | WB | WQ | WK | WB | WN | WR |
  Then the WK at E8 should have the following moves
  | Start | End |

Scenario:  Castling when the king has moved
  When there is a chess board set up as
  |  x |  a |  b |  c |  d |  e |  f |  g |  h |
  |  1 |    |    |    | BK |    |    |    |    |
  |  2 |    |    |    |    |    |    |    |    |
  |  3 |    |    |    |    |    |    |    |    |
  |  4 |    |    |    |    |    |    |    |    |
  |  5 |    |    |    |    |    |    |    |    |
  |  6 |    |    |    |    |    |    |    |    |
  |  7 | WP | WP | WP | WP |    | WP | WP | WP |
  |  8 | WR | WN | WB | WQ | WK |    |    | WR |
  And the following moves have been made
  | Start | End |
  |   E8  | E7  |
  |   E7  | E8  |
  Then the WK at E8 should have the following moves
  | Start | End |
  | E8    | E7  |