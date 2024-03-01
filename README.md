The bigO for the linearSearch method is O(N). linearSearch method takes in the ArrayList called wordList which contains all the 5 character long words. Inside the lineSearch there is a for loop which iterate for loop. This 
for loop is depended on the size of the wordList. Considering the worst case for this for loop where it has to literate all the way to the end to check if the target word exist on the wordList, the time for the linearSearch to 
operate will be related to the size of the wordList. Which means linearSearch has O(N) for its complexity. 

I chose to run this program for french. I used the French.Random.Txt file that was provided by the professor. Chirs Squires(squics23@wfu.edu) played my games several time in French. However, it was not easy for him to find the target word.
One of feedbacks from Chris was that there are 16 special character for french. After manually testing the program and thinking about my logic, my program read each special characters as a normal characters. However, this is just simple 
problem to solve since I could tell the user that french words that contains special characters could appear as an answer. Also he told me that there are several french words where it contains space. I beleive that there is two ways to solve
this problem. My programm defenetly reads a space as a character. So I could tell the user that space also counts as a charcter. Alternative solution is when choosing the 5 word character I could take each character of the word into an arraylist
as char variable and test if there is a space in it and remove it. But this case, I have to tell the users that they shouldn't count space as a characters when guessing the target word. I think second solution is more reasonable solution since
if I choose to take space as a character, assigning color for space will have many factors to consider which will make the program more complicated. 
