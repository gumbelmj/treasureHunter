===================================
Problem  - Find the treasure fast!
===================================
A treasure map was uncovered, but the map only shows the starting location. The
path from the origin to the treasure is given as series of steps.
 
"Walk north for 2 hours, then ride a horse at trot speed heading east for
15 minutes..."
 
Multiple teams of treasure hunters have departed to follow the map's
instructions.
 
Your team decided, based on the fact that there are more than 1000 instructions,
that you are better off parsing the map with a computer and then just following
a straight line towards the general area of the treasure.
 
You made some generalizations on the speed of the modes of transportation and
came up with the following approximations:
  Walk          - 3 mph
  Run           - 6 mph
  Horse trot    - 4 mph
  Horse gallop  - 15 mph
  Elephant ride - 6 mph
 
Another member on the team has already written a program to translate the map
into input which is ready for your program.
 
Write a program to parse the following input to determine the location of the
final destination.
 
Example input:
--------------
Walk, 20 mins, N
Run, 1 hour, E
Horse trot, 3 hours, NW
Elephant ride, 1 hour 30 mins, SE
Horse gallop, 20 mins, SE
Walk, 30 mins, SW
Horse trot, 1 hour 1 min, W
...
 
Example output:
---------------
2029 miles to the north, 298893 miles to the east
