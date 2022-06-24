# Highway

A small town lies along a portion of a long, scenic highway. (Think "Blue Ridge Mountain Parkway"). The town benefits significantly from the tourists who pass through along this road and who frequently stop to eat at the local restaurants, make purchases at the local stores, etc.

The town has become concerned that litter along the highway may drive some of these tourists away. The town council has encouraged local organizations and civic groups to "adopt" a stretch of the highway, pledging to clean up any litter along their stretch. Groups can select the portion of the highway that they are interested in, and the council has decided that it's OK if their chosen sections overlap, as this will simply mean that they get cleaned more often.

Write a program to track how much of the nearby highway is covered by the pledges of the various groups. 

## Input

All stretches of highway are identified by a pair of floating point numbers denoting the mileage markers posted on signs alongside the highway.

The first line of input will contain two numbers, denoting the range of mileage that the town wants to keep track of.

Each subsequent line after that will contain a pair of numbers indicating a range of highway that a group has pledged to keep clean.

## Output

Print a simple report indicating the length of the portion of highway being tracked, the total length of that portion not covered by any pledges, and the percentage of the road that will be cleaned.

# Example

The input

    0.0 100.0
    20.0 30.0
    40.0 50.0

indicates that the town is tracking the highway between mileage markers 0 and 100, and that one group has pledged to clear the section from marker 20 to maker 30, and another group has pledged to clear everything from marker 40 to 50.

Output from this input would look like:

    Total distance:   100.0
    Not covered:    80.0
    Percentage covered:  20.0%
