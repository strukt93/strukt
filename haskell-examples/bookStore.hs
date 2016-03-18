type Id = Int
type Name = String
type Authors = [String]
type ReviewBody = String
type BookId = Id
type MagazineId = Id
type CustomerId = Id
data ReviewId = BookId | MagazineId deriving (Show)
data Book = Book Id Name Authors deriving (Show)
data Magazine = Magazine Id Name Authors deriving (Show)
data Customer = Customer Id Name deriving (Show)
data Review = Review  ReviewId CustomerId ReviewBody deriving (Show)
getBookData (Book id name authors) x = if(x == 1) then id else if (x == 2) then name else authors