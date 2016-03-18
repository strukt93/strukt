listSum (x:xs) = x + listSum xs
listSum [] = 0