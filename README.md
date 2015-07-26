# KnightmareChess
An implementation of Knightmare Chess by Steve Jackson Games.

    "Knightmare Chess" refers to a set of cards that compliment traditional 
chess that is published by Steve Jackson Games. As printed on the box "Move 
a piece. Play a card. Chess will never be the same." As the explanation 
continues, "The cards break the rules in wild and unpredictable ways. Some 
affect a single move, and some change the entire game." For example, one card 
reads "Move one of your knights to any square whose color is different from 
the one it currently occupies. You cannot capture a piece with this move." 
Of course, you always have the option of making a regulaar chess moves, but 
you also always have the option of playing a card and making a move completely 
out of left field or even completely changing the nature of a piece for the 
remainder of the game. For instance, one card let's you choose a pawn and turn 
it into a "crab." Until it leaves play, a crab's regular move is forward 
diagonally(no capture), and captures straight forward only. And if you are 
lucky enough to get the card, you can even make your king move twice every 
turn, allowing him to put up quite a fight and be fiendishly difficult to 
capture on his own.
    The current version of this game, simply allows two people to play a 
rudimentary version of regular chess using an ascii representation of a board 
and does not implement any of the "Knightmare Chess" rules. In fact it doesn't 
even implement rules that resolve around the king being in check. It does 
however implement all other restrictions on piece movements and allows special 
moves(like castle). This of course will be changed in future versions of the 
code. Over the next few iterations, I will complete the implementation of 
standard chesss, add an undo feature, and improve the user interface. I will 
also be doing research on the card game and begin providing some of the 
additional back-end support needed to make a good implemention of a subset of 
the Knightmare chess rules. However, the code, as it stands now, was developed 
from the ground up with the intention of having to support the needs of 
Knightmare chess. In particular, the object representation of a single move 
can include anything the Knightmare rules can cook up, while still restricting 
the standard moves to standard rules.
    The other primary goal of this code is to allow me to explore the uses and 
implementations of common design patterns. I used the decorator pattern to 
construct a Move object, so that complicated moves can be built out of the 
standard ones. I used the factory pattern in a couple of places, both to create 
Piece objects, each with their own list of regularly allowable moves and a 
subsequent list of constraints that must be met for each move to occur, and also 
to create the Move objects themselves. Furthermore, I intend to use the observer 
pattern to facilitate to prevent a King being in check. All pieces of the 
opposite color, will be alerted when the king tries to move and will alert the 
King if they can check the King in the proposed move. A similar mechanism will 
be used when pieces other than the king move.
    As for the main design of the program, the first thing to be noticed is that 
there is a large number of classes seperated into a fairly large number of 
packages. Part of this is because of the use of the decorator pattern to 
construct moves and the use of seperate classes to define each "constraint" that 
test differnt aspects of a particular moves validity, but there are quite a few 
enums, and simple utility classes for moving data around between objects and for 
constructing other objects. The design is quite complex, but the result is a 
versitile program that can easily be extended to implement the KnightMare chess 
rules without having to edit large sections of existing code. What follows is a 
brief discription of what you will find in each of the packages in the src file.
    
    components - Contains the basic object definitions that will be used to manipulate the board state.
    constraints - Contains the interface and classes that defines the various constraints that can be 
        used to restrict a given move. 
    definitions - Contains various enums used throughout the code, such as Color, Rank, File, and PieceType.
    factory - Contains two factory classes, PieceFactory and MoveFactory, that encapsulate the code to create
        an object of the specified type.
    game - Contains the code used to run a chess game between two players
    moveDecorators - Contains a slew of moveDecorators that represent the various possible moves, from simply
        moving forward one space, to the L-shape move of a knight.
    moves - Contains the base move types that moveDecorators decorate. A little un-intuitively, the only base
        move in the file currently is "Touch" which is a non-move. Other potential base moves include "Place,"
        which places a piece anywhere on the board, and "MoveOffBoard," which completely removes a piece from
        the board, but not from the game.
    setup - Contains a single class, Setup, which should probaly be moved to the game package. The intention
        in creating this package was for it to contain the code that gets players from the opening menus and
        options to the actual game itself.
    test - Contains all the testing code for the game.
    utility - Contains various utility classes that don't have another home at the present time.
