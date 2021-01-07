import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.ArrayList;
public class CallingClass {
	static String directory = System.getProperty("user.dir");//computer find the file place automatically.
	static String fileAddress = directory+"/";
	//a device to read info.
	static Scanner sc = new Scanner(System.in);
	//Storing all courses
	static ArrayList<Course> courseList = new ArrayList<Course>();
	//Storing all students
    static ArrayList<Student> systemStudentList = new ArrayList<Student>();
    //creating a Admin object
	public static Admin ad = new Admin(); 
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception{
		ad.setUsername("Admin");
		ad.setPassword("Admin001");
        // Get the file 
        File f = new File(fileAddress+"courseList.ser"); 
        // Check if the specified file exists or not 
        // As long as one of the file exists,the rest will exist, too.
        if (f.exists()) {
			//Now we will deserialize the stored object from the file
			try{
			//FileInputSystem receives bytes from a file
			FileInputStream fis = new FileInputStream(fileAddress+"courseList.ser");
			//ObjectInputStream does the deserialization -- it reconstructs the data into an object
			ObjectInputStream ois = new ObjectInputStream(fis);
			//Cast as ArrayList. readObject will take the object from ObjectInputStream
			//ArrayList<?> courseList = (ArrayList<?>)ois.readObject();
			courseList = (ArrayList<Course>)ois.readObject();
			ois.close();
			fis.close();
			//reading another list
			fis = new FileInputStream(fileAddress+"systemStudentList.ser");
			//ObjectInputStream does the deserialization-- it reconstructs the data into an object
			ois = new ObjectInputStream(fis);
			//Cast as ArrayList. readObject will take the object from ObjectInputStream
			//ArrayList<?> systemStudentList = (ArrayList<?>)ois.readObject();
			systemStudentList = (ArrayList<Student>)ois.readObject();
			
			ois.close();
			fis.close();
			}
			//catch error
			catch(IOException ioe) {
				 ioe.printStackTrace();
				 return;
			}
			catch(ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
				return;
			}
			//the following prompts will show
			System.out.println("Welcome");
			userMenu();
        }
        //no file has been stored before, reading info from the default CSV file
        else{
    		File theFile = new File(fileAddress+"MyUniversityCourses.csv"); 
    		Scanner input = new Scanner(theFile);
    		String buffer;
    		String[] buffarray;
    		buffer = input.nextLine();
    		//looping through each line
    		while (input.hasNextLine()) {
    			//putting a into a string 
    			buffer  = input.nextLine();
    			//dividing up the string into an array of strings
                buffarray = buffer.split(",");
                //creating a course and loading in the information
                Course cour = new Course();
                cour.setName(buffarray[0]); 
                cour.setCourseID(buffarray[1]);
                cour.setMaxNumber(Integer.parseInt(buffarray[2]));
                cour.setCurrentNumber(Integer.parseInt(buffarray[3]));
                cour.setInstructor(buffarray[5]);
                cour.setSectionNumber(Integer.parseInt(buffarray[6]));
                cour.setLocation(buffarray[7]);
                //adding the course to the courseList.
                courseList.add(cour);
    		}
    		//because the program reads the file from the default line
    		//it means that it is the first time the user logs in
    		System.out.println("It seems like this is your first time to log in the system.");
    		//verify identity
    		adminVerification(ad);
    		System.out.println("Welcome!");
    		System.out.println();
    		adminOption(ad);
    	}
        // Once the program is finished, the saving process will kicks in
        // Storing the ArrayLists of objects into the files
        // Serialization 
		try {		
			//FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream(fileAddress+"courseList.ser");
			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			//storing the ArrayList courseList
			oos.writeObject(courseList);
			//Close both streams
			oos.close();
			fos.close();		
			//FileOutput Stream writes data to a file
			fos = new FileOutputStream(fileAddress+"systemStudentList.ser");
			//ObjectOutputStream writes objects to a stream (A sequence of data)
			oos = new ObjectOutputStream(fos);
			//storing the ArrayList systemStudentList
			oos.writeObject(systemStudentList);
			//Close both streams
			oos.close();
			fos.close();
			System.out.println("\nSerialization is completed");	
			System.out.println("Data saved");
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	static void adminOption(Admin ad) throws Exception {
		System.out.println("Following are the options");
		boolean menuStatus = true;
		while(menuStatus) {
			System.out.println();
    		System.out.println("(1) Course Management");
    		System.out.println("(2) Reports");
    		System.out.println();
    		System.out.println("(9) Return to user menu");
    		System.out.println("(0) Terminate program");
    		System.out.print("Please enter the number: ");
			int n = -1;
			//only read integer
			try {
				n = Integer.parseInt(sc.nextLine());
			}
			//skip non-integer input
			catch(Exception e){
			}
    		if     (n == 1) {menuStatus = false;adminCourseManagement(ad);}//debuggin ad.viewStudent();}
    		else if(n == 2) {menuStatus = false;adminReports(ad);}
    		else if(n == 0) {System.out.println("Program successfully terminated.");break;}
    		else if(n == 9) {menuStatus = false;userMenu();break;}
    		else 			{System.out.println("Invalid option. Try again!");}
    	}
	}
	static void studentOption(Student st) throws Exception {
		//student's only option is automatically triggered
		studentCourseManagement(st);
	}
	static void adminCourseManagement(Admin ad) throws Exception {
		//allows the admin to choose what they want to operate on.
		System.out.println("Following are the options, please enter the number");
		boolean menuStatus = true;
		while(menuStatus) {
			System.out.println();
			System.out.println("(1) Create a course");
			System.out.println("(2) Delete a course");
			System.out.println("(3) Edit a course");
			System.out.println("(4) Display a course's information");
			System.out.println("(5) Register a student");
			System.out.println();
			System.out.println("(0) Terminate program");
			System.out.println("(9) Go back to the last menu");
			System.out.print("Please enter the number: ");
			int n = -1;
			//only read integer
			try {
				n = Integer.parseInt(sc.nextLine());
			}
			//skip non-integer input
			catch(Exception e){
			}
			if	   (n == 1) {ad.createCourse();}
			else if(n == 2) {ad.deleteCourse();}
			else if(n == 3) {ad.editCourse();}
			else if(n == 4) {ad.displayCourseInfo();}
			else if(n == 5) {ad.createStudent();}
			else if(n == 9) {menuStatus = false;adminOption(ad);}
			else if(n == 0) {menuStatus = false;System.out.println("Program successfully terminated.");break;}
			else            {System.out.println("Invalid option. Please try again");}
		}
	}
	static void adminReports(Admin ad) throws Exception {
		//allows the admin to choose what they want to operate on.
		System.out.println("Following are the options");
		boolean menuStatus = true;
		while(menuStatus) {
			System.out.println();
			System.out.println("(1) View all courses");
			System.out.println("(2) View full courses");
			System.out.println("(3) Save a file for full courses");
			System.out.println("(4) View the names of the students that are registered in a specific course");
			System.out.println("(5) View the list of courses that a given student is registered in");
			System.out.println("(6) Sort the courses based on the current number of students registered");
			System.out.println();
			System.out.println("(0) Terminate program");
			System.out.println("(9) Go back to the last menu");
			System.out.print("Please enter the number: ");
			int n = -1;
			//only read integer
			try {
				n = Integer.parseInt(sc.nextLine());
			}
			//skip non-integer input
			catch(Exception e){
			}
			if	   (n ==1 ) {ad.viewCourse();}
			else if(n == 2) {ad.viewFullCourse();}
			else if(n == 3) {ad.saveFullCourse();}
			else if(n == 4) {ad.viewCoursesRegisteredStudent();}
			else if(n == 5) {ad.viewStudentsRegisteredCourse();}
			else if(n == 6) {ad.sortCourseOnNumberOfEnrolledStudents();}
			else if(n == 9) {menuStatus = false;adminOption(ad);}
			else if(n == 0) {System.out.println("Program successfully terminated.");break;}
			else 			{System.out.println("Invalid option. Please try again");}
			}
		}
	static void studentCourseManagement(Student st) throws Exception {
		//allows the student to choose what they want to operate on.
		System.out.println("\nFollowing are the options");
		boolean menuStatus = true;
		while(menuStatus) {
			System.out.println();
			System.out.println("(1) View all courses");
			System.out.println("(2) View available courses");
			System.out.println("(3) Register in a course");
			System.out.println("(4) Withdraw from a course");
			System.out.println("(5) View enrolled courses");
			System.out.println();
			System.out.println("(0) Terminate program");
			System.out.println("(9) Return to the previous menu.");
			System.out.print("Please enter the number: ");
			int n = -1;
			//only read integer
			try {
				n = Integer.parseInt(sc.nextLine());
			}
			//skip non-integer input
			catch(Exception e){
			}
			if(n == 1) {st.viewCourse();}
			else if(n == 2) {st.viewAvailableCourse();}
			else if(n == 3) {st.registerCourse();}
			else if(n == 4) {st.withdrawCourse();}
			else if(n == 5) {st.viewEnrolledCourse();}
			else if(n == 0) {menuStatus = false;System.out.println("Program successfully terminated.");break;}
			else if(n == 9) {menuStatus = false;userMenu();break;}
			else            {System.out.println("Invalid option. Please try again");}
		}
	}
	static void adminVerification(Admin ad) {
		System.out.print("Please enter your USERNAME:");
		//eliminating whitespace
		String username = (sc.nextLine()).replaceAll("\\s","");
		System.out.print("Please enter your PASSWORD:");
		//eliminating whitespace
		String password = (sc.nextLine()).replaceAll("\\s","");
		int count = 0;
		//if either username OR password is FALSE, the remaining trial drops
		while(  !(ad.getUsername().equals(username)&&password.equals(ad.getPassword()))  ){
			System.out.println("Incorrect username or password!");
			System.out.println("You have "+(2-count)+" trials left.");
    		System.out.print("Please enter your USERNAME:");
    		//eliminating whitespace
    		username = (sc.nextLine()).replaceAll("\\s","");
    		System.out.print("Please enter your PASSWORD:");
    		//eliminating whitespace
    		password = (sc.nextLine()).replaceAll("\\s","");
    		// the 1 attempt outside the while loop 
    		//and 2 attempts inside the loop add up to 3 trials
			if(count == 2) {
				System.out.println("You have exhausted all 3 trials.");
				System.out.println("System terminated");
				System.exit(1);
			}
			count++;
		}
	}
	static Student studentVerification(ArrayList<Student> list) {
		Student matchedStudent = null;
		System.out.print("Please enter your USERNAME:");
		//eliminating whitespace
		String username = sc.nextLine();// (sc.nextLine()).replaceAll("\\s","");
		System.out.print("Please enter your PASSWORD:");
		//eliminating whitespace
		String password = sc.nextLine();//(sc.nextLine()).replaceAll("\\s","");
		int count = 0;
		boolean verification = false;
		while(verification == false) {
			//looping all students to compare username and password
			for(int i = 0; i < list.size(); i++) {
				if(username.equals((list.get(i).getUsername()))&& password.equals(list.get(i).getPassword())) {
					matchedStudent = list.get(i);
					verification = true;
					break;
				}
			}
			if(verification == true) {
				break;
			}
			System.out.println("Incorrect username or password!");
			System.out.println("You have "+(3-count)+" trials left.");
    		System.out.print("Please enter your USERNAME:");
    		//eliminating whitespace
    		username = (sc.nextLine()).replaceAll("\\s","");
    		System.out.print("Please enter your PASSWORD:");
    		//eliminating whitespace
    		password = (sc.nextLine()).replaceAll("\\s","");
    		// the 1 attempt outside the while loop 
    		//and 2 attempts inside the loop add up to 3 trials
			if(count == 2) {
				System.out.println("You have exhausted all 3 trials.");
				System.out.println("System terminated");
				System.exit(1);
			}
			count++;
		}
		return matchedStudent;
	}
	static void userMenu() throws Exception {
		System.out.println("Please tell us your identity: ");
		boolean menuStatus = true;
		while(menuStatus) {
			System.out.println("(1) Admin");
			System.out.println("(2) Student");
			System.out.println("(0) Terminate the program");
			System.out.print("Please enter the number: ");
			int id = -1;
			//only read integer
			try {
				id = Integer.parseInt(sc.nextLine());
			}
			//skip non-integer input
			catch(Exception e){
			}
			if(id == 1) {
				menuStatus = false;
				//verify identity
				adminVerification(ad);
				//show admin option
				adminOption(ad);
			}
			else if(id == 2) {
				menuStatus = false;
				//create a temp student
				Student matchedStudent = null;
				//verify student's identity
				matchedStudent = studentVerification(systemStudentList);
				System.out.println("\nWelcome!!");
				studentOption(matchedStudent);
			}
			else if(id == 0) {break;//terminate the userMenu function
			}
			else {
				System.out.println("\nOption doesn't exit. Please try again.");
			}
		}
	}
}
