-- File: myLast.hs
-- Reimplementing the built-in last function.

myLast xs = if(length xs == 1) then head xs else myLast (tail xs)
