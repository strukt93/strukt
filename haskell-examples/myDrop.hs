-- File: myDrop.hs
-- Reimplementing the built-in function drop.
myDrop n xs = if n<=0 || null xs then xs else myDrop (n-1) (tail xs)
