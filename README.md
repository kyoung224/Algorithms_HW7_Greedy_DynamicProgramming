[![Work in Repl.it](https://classroom.github.com/assets/work-in-replit-14baed9a392b3a25080506f3b7b6d57f295ec2978f6f33ec97e36a161684cbe9.svg)](https://classroom.github.com/online_ide?assignment_repo_id=3631831&assignment_repo_type=AssignmentRepo)
# Homework 7: Greedy and Dynamic Programming

* [Due Dates](#due-dates)
* [Read Me Before Starting](#read-me-before-starting)
* [Q1: Treasure Cave](#q1-treasure-cave)
* [Q2: Treasure Cave with Fused Bars--Value](#q2-treasure-cave-with-fused-bars--value)
* [Q3: Treasure Cave with Fused Bars--Picks (Optional)](#q3-treasure-cave-with-fused-bars--picks-optional)

## Due Dates
|   Section   |    Date    | Time (Eastern) |
|:-----------:|:----------:|:--------------:|
| Lecture 002 | 11/19/2020 | 11:59pm        |
| Lecture 004 | 11/19/2020 | 11:59pm        |
| Lecture 006 | 11/19/2020 | 11:59pm        |


## Read Me Before Starting
This coding homework covers topcs from week 10 and 11--Greedy algorithms and
Dynamic Programming. You'll be provided examples in the `main` method that you
can run manually to check for correctness. **More test cases will be posted to
Piazza, so make sure you're checking there for updates.**

## Q1: Treasure Cave
Imagine you find a hidden cave filled with with N different types of metal bars
(gold, silver, platinum, steel, etc.) Each type of metal bar has some value
v<sub>i</sub>, and there are x<sub>i</sub> bars of that metal in the cave
(for i = 0, 1, 2, 3, ... N-1). You want to bring back as many bars as of the
treasure as you can, but your bag can only fit W bars. **How do you choose how
many bars of each metal to bring home to maximize the total value?**

For example, if your bag can store 7 bars and you have gold, silver, platinum,
and steel in these quantities:

    [4, 10, 2, 4] // 4 bars of gold, 10 silver, 2 platinum, 4 steel

and these values

    [3, 1, 5, 2]  // gold worth 3 per bar, silver worth 1, platinum 5, steel 2

Then you would want to take this much of each metal

    [4, 0, 2, 1]  // all the gold, no silver, all the platinum, 1 steel bar
                  // for a total value of 24 (4*3 + 2*5 + 1*2)

Write `bestValue()` which takes in an integer W, an array of counts, and an
array of values. It should return the best value you can earn by picking the
bars optimally. **Your code should run in O(nlogn).**

* **Hint #1:** This can be done using a Greedy approach.
* **Hint #2:** Consider sorting with a [custom Comparator](https://www.geeksforgeeks.org/comparator-interface-java/)


## Q2. Treasure Cave with Fused Bars--Value
Now assume that for each type of metal, all of the bars are fused together so
that **you're forced to all the bars of a certain type, or none of them.**

This means that you sometimes should not take the metal that has the highest
value, because it either will not fit all in your bag (since you have to take
all the bars), or other metals of lesser will be worth more overall value when
combined together.

Write bestValueForFused, which takes in the arguments from above, and returns
the **value of the best picks possible.**

```
bestValueForFused(4, [], []) // 0 (the cave is empty)
bestValueForFused(4, [4, 10, 2], [3, 1, 5]) // 12 (take metal 0, even though metal 2 is worth more per bar)
bestValueForFused(4, [4, 2, 2], [3, 2, 5]) // 14 (take metal 1 and metal 2)
bestValueForFused(6, [4, 2, 1], [3, 2, 9]) // 16 (take metal 0 and metal 1)
```

* **Hint #1:** Greedy won't work here.
* **Hint #2:** Start by computing the total value of each metal (i.e. the number
of bars * value per bar).
* **Hint #3:** For each metal, you can either take it or not. If you take it, your
bag capacity decreases by the corresponding amount. How could you translate this
idea into a recursive subproblem?

## Q3. Treasure Cave with Fused Bars--Picks (Optional)
**This question is optional and worth extra credit.**
Write bestPicksForFused(), which solves Q2 but returns an array of bools, where
each element in the array describes whether we picked metal i.

```
bestPicksForFused(4, [], []) // []
bestValueForFused(4, [4, 10, 2], [3, 1, 5]) // [true, false, false]
bestValueForFused(4, [4, 2, 2], [3, 2, 5]) // [false, true, true]
bestValueForFused(6, [4, 2, 1], [3, 2, 9]) // [true, true, false]
```
