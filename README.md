ReadMe

(Case sensitive)
Admin username: Admin
Admin username: Admin001

There is no default student username and password;
In order to create a Student, you have to log in as an Admin and go to (1)Course Management (5) Register a student



The following instruction is for Mac users, not sure about Windows users.

If Windows user have trouble making the program work, please email me as soon as possible.

The main java file that runs is "CallingClass.java" in the "src" file.

There are 2 ways to run the program successfully

METHOD 1
(1) Import the program into your eclipse and run.  (CallingClass) (easiest way)


METHOD 2
(2) Move the files

(i) Move all the files in "src" folder and all the files in "bin" folder to the new folder named "system"
(ii) Move CSV file "MyUniversityCourses" to "system" folder. (IMPORTANT)

["system" folder is empty, you must move the needed files]

****************************WARNING****************************


If you choose method 2, don't forget to move the files. 

REMEMBER to move CSV file "MyUniversityCourses" to "system" folder.
REMEMBER to move CSV file "MyUniversityCourses" to "system" folder.
REMEMBER to move CSV file "MyUniversityCourses" to "system" folder.


You need to go to the terminal in your system and find where the CallingClass.java code is.

Here is the case of my Mac OS computer: 

STEPS:
	(i) open terminal


	(ii)find the location of the file
For example, if your crs folder is on the desktop you can type the line 
"cd desktop/program/system"
Type "ls" in the command line, the program will print members in the program
Make sure it contains "CallingClass.java"

	(iii)Then you can type the line within the quotation mark to compile the program
"javac CallingClass.java"

//Or, you can skip step 3, if you move every member files in BOTH "src" and "bin" folders to the "system" folder. REMEMBER to move CSV file "MyUniversityCourses" to "system" folder.

//if you only move the csv file and member files in "src" folder to "system folder" , you MUST do step 3

	(iv)Then, you type the line to run the program
"java CallingClass"





![alt text](https://github.com/cyberzzhhss/course_registration_system/blob/main/UML.png)

![alt text](https://github.com/cyberzzhhss/course_registration_system/blob/main/Documentation.pdf)
