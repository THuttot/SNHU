Reflections on this software - Talan Hutto 6/23/2025
-Summarize the project and what problem it was solving.
This code looks at a text file from produce sales at a grocery store and counts how many time a given item was sold and stores it in memory. The user can then
take this information and see the quantity of times and individual item is selling and use that information to re arrage the store in a more efficient manner.
-What did you do particularly well?
The error handling is simple in this code, yet effective. It will catch whether or not a file has been opened before it executes and then tells the user about the error that occured before exiting the application. 
The code is well structured with a .h file and .cpp file, which makes it easy to read and understand. When printing frequencies to the terminal, it sets a maximum width for the quantity and name of the produce item
and ensure that each line is printed within these bounds. This makes the prints much cleaner and easier to read.
-Where could you enhance your code? How would these improvements make your code more efficient, secure, and so on?
I could have the code organize the list from highest to lowest, or visa verse depending on user desire and input. This would work with both the number and histogram function.
I would want to add the ability for the code to track and store a list of "Biggest hitters". This list would only be updated if a produce item sold higher than it ever has before, which could show trends.
I would also want to add a date stamp on each created document so we could track seasonal trends.
-Which pieces of the code did you find most challenging to write, and how did you overcome this? What tools or resources are you adding to your support network?
The hardest part was the error handling for normalizing the case sensitivity for each word it counts. Making "APPLE" or "ApPlE" read as just apple. To solve this problem I went to stack overlow and read a few articles on how error handling works for 
strings. There were a few libraries I could inlcude in my file that would have solveed the problem very simply, but I wanted to ensure I understood the logic. So I created this method: 
for (char& c : item) {
        if (c >= 'A' && c <= 'Z') {
            c = c - 'A' + 'a';
               }
           }
This goes through each letter in a string on the line of a file and converts it to lowercase manually. This is not the most efficient way to do this, but again, I wanted to ensure I understood the logic of this before I included a function.
-What skills from this project will be particularly transferable to other projects or course work?
The ability to read and write to files is one of the most important skills for any project. This project tought me that, and it included error handling for these complext tasks and calculations which help make the program more rebust and reliable. 
-How did you make this program maintainable, readable, and adaptable?
The .h, .cpp and main are all separate. In each function I included a header comment and end comment, as well as its desire function. In some more complext functions, I created a 'step' list starting with "1)" down to the last step to ensure whomever is reading it can follow along easily.
