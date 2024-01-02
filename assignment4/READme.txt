
//Class Structure
for this assignment i made an abstract class that implements the model i have my english european and triangle boards extend this class.
In my abstract class i have one constructor that takes in a board and a arm/dimension and initializes the board to be the output of an abstract method.
called initializeBoard which will take in the inputs and initialize a board and return it. All of my subclass constructors calls super with the three inputs if a constructor
doesnt pass in all the fields then it uses the default value to construct the board. This way i abstract out all of the constructor code and have it in one place in each subclass.
I have all of the models main functionality in the abstract class that way functionality that stays the same doesent need to be recoppied and if some of the methods need to be changed it can be overwritten in
the corresponding subclass.


//bug fixes
I changed my isInBoard method in my abstract class since the implementation was to tightly coupled to the english board and didnt work for the european board. There was still one bug that i couldnt fix which is handling
index out of bound errors when checking if a valid position can make a move or not so isGameOver doesnt work for the european board but it still works for the default and partly the triangle board.


//code refactor
I overrided the getGameState isInBoard isInRange canMakeMove makeMove and added new helpers for canMakeMove for the triangle board since it is a lot more different than the other two boards
so some of the code had to be refactored in order to make the triangle board work however there is still one bug that returns an index out of bounds error when checking if each position can make a move or not but the code does work 
when checking that the game is over on a board where you cant make anymore moves. The european board overrided canMove since its moveset is the same as the default board so i didnt need to mess with the helpers however i wasnt able to
fix the bug that throws an index out of bounds error when checking if the game is over or not however i did comprehensively check to see if it could make the same moves as the default and it does it no problem so that is the only bug in my code all other functionality works. I
also changed all of my private helper methods in the abstract class to be protected since they are part of the implementation for the functionality and if other classes can use them then they will be included in the extension of the abstract class if not then the subclass can make its own helper
methods. I also changed some things in my controller impl class based on feedback from assignment 3 I removed some redundant if statements and try catch blocks in my helper methods Quit and StringToNumber and moved my mock model class to my test file I also expanded on my javadocs for the controller
interface so it is more descriptive.
