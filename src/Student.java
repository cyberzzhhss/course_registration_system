import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;
public class Student extends User implements StudentInterface, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Scanner sc = new Scanner(System.in);
	private String studentID;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	//view all courses by looping through them
	@Override
	public void viewCourse() {
		System.out.println();
		for (int i = 0; i < CallingClass.courseList.size(); i++) {
			System.out.println();
			System.out.println("Course name:           "+CallingClass.courseList.get(i).getName());
			System.out.println("Course ID:             "+CallingClass.courseList.get(i).getCourseID());
			System.out.println("Maximum students:      "+CallingClass.courseList.get(i).getMaxNumber());
			System.out.println("Current students:      "+CallingClass.courseList.get(i).getCurrentNumber());
			System.out.println("Course Instructor:     "+CallingClass.courseList.get(i).getInstructor());
			System.out.println("Course Section Number: "+CallingClass.courseList.get(i).getSectionNumber());
			System.out.println("Course Location:       "+CallingClass.courseList.get(i).getLocation());
		}
	}
	//view all available courses by filtering them and looping them
	@Override
	public void viewAvailableCourse() {
		System.out.println();
		for (int i = 0; i < CallingClass.courseList.size(); i++) {
			if(CallingClass.courseList.get(i).getMaxNumber() > CallingClass.courseList.get(i).getCurrentNumber()) {
				System.out.println();
				System.out.println("Course name:           "+CallingClass.courseList.get(i).getName());
				System.out.println("Course ID:             "+CallingClass.courseList.get(i).getCourseID());
				System.out.println("Maximum students:      "+CallingClass.courseList.get(i).getMaxNumber());
				System.out.println("Current students:      "+CallingClass.courseList.get(i).getCurrentNumber());
				System.out.println("Course Instructor:     "+CallingClass.courseList.get(i).getInstructor());
				System.out.println("Course Section Number: "+CallingClass.courseList.get(i).getSectionNumber());
				System.out.println("Course Location:       "+CallingClass.courseList.get(i).getLocation());
			}
		}	
	}
	//creating a course
	@Override
	public void registerCourse() {
		int index = -1; // index for classify the situation
		System.out.print("Enter the COURSE ID: ");
		String courseID = sc.nextLine();
		System.out.print("Enter the COURSE SECTION NUMBER: ");
		int sectionNumber = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < CallingClass.courseList.size(); i++) {
			if (courseID.equalsIgnoreCase(CallingClass.courseList.get(i).getCourseID())
					&& sectionNumber==CallingClass.courseList.get(i).getSectionNumber()) {
				index = i; break;
			}
		}
		if(index == -1) {
			System.out.println("No course matches the input info");
		}
		else {
			if (CallingClass.courseList.get(index).getMaxNumber() == CallingClass.courseList.get(index).getCurrentNumber()) {
				System.out.println("The course is full");
				System.out.println("Returnining to the previous menu");
			}
			else{
				System.out.println();
				System.out.println("We need to collect information to register for you.");
				System.out.println();
				System.out.print("Enter the FIRST NAME: ");
				String firstName = (sc.nextLine()).replaceAll("\\s","");
				System.out.print("Enter the LAST NAME: ");
				String lastName = (sc.nextLine()).replaceAll("\\s","");
				System.out.print("Enter the STUDENT ID: ");
				String studentID = (sc.nextLine()).replaceAll("\\s","");
				//@ is a delimiter for each piece that makes up the whole string.
				CallingClass.courseList.get(index).CourseEnrolleeList.add(firstName+"@"+lastName+"@"+studentID);
				CallingClass.courseList.get(index).setCurrentNumber(CallingClass.courseList.get(index).getCurrentNumber()+1);
				System.out.println("Course is successfully registered!");
			}
		}
	}
	@Override
	public void withdrawCourse() {
		int index = -1; // index for classify the situation
		System.out.print("Enter the COURSE ID: ");
		String courseID = sc.nextLine();
		System.out.print("Enter the COURSE SECTION NUMBER: ");
		int sectionNumber = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < CallingClass.courseList.size(); i++) {
			if ( CallingClass.courseList.get(i).getCourseID().equals(courseID) &&
				CallingClass.courseList.get(i).getSectionNumber() == sectionNumber) {
				index = i;
				break;
			}
		}
		if(index == -1) { //index hasn't changed, meaning no match
			System.out.println("No course matches the input information.");
		}
		else {
			//I choose to use Student ID instead of name
			System.out.print("Enter the STUDENT FIRST NAME: ");
			//removing the whitespace in the input string
			String firstName = (sc.nextLine()).replaceAll("\\s","");
			System.out.print("Enter the STUDENT LAST NAME: ");
			//removing the whitespace in the input string
			String lastName = (sc.nextLine()).replaceAll("\\s","");
			System.out.print("Enter the STUDENT ID: ");
			String studentID = (sc.nextLine()).replaceAll("\\s","");
			for (int j = 0; j <CallingClass.courseList.get(index).CourseEnrolleeList.size();j++) {
				//matching the student information
				if (CallingClass.courseList.get(index).CourseEnrolleeList.get(j).toLowerCase().replaceAll("\\s","").contains(firstName.toLowerCase())
				  &&CallingClass.courseList.get(index).CourseEnrolleeList.get(j).toLowerCase().replaceAll("\\s","").contains(lastName.toLowerCase())
				  &&CallingClass.courseList.get(index).CourseEnrolleeList.get(j).toLowerCase().replaceAll("\\s","").contains(studentID.toLowerCase())){
					CallingClass.courseList.get(index).setCurrentNumber(CallingClass.courseList.get(index).getCurrentNumber()-1);
					System.out.println("You have been removed from this course.");
					CallingClass.courseList.get(index).CourseEnrolleeList.remove(j);
					break;
				}
				else {
					System.out.println("No student matches your information.");
					System.out.println("Returning to the previous menu");
				}
			}
		}
	}
	@Override
	public void viewEnrolledCourse() {
		//ask student id (a unique feature) to view student's registered course
		System.out.print("Enter the STUDENT ID: ");
		String studentID = (sc.nextLine()).replaceAll("\\s","");
		ArrayList<Student> list = CallingClass.systemStudentList;
		//looping all students to compare student ID
		boolean found = false;
		for(int i = 0; i < list.size(); i++) {
			if(studentID.equals((list.get(i).getStudentID()))) {
				found = true;
				break;
			}
		}
		if(found == true) {
		System.out.println("Registered Courses");
		//loop through all the courses in the ArrayList courseList
			for (int i = 0; i < CallingClass.courseList.size(); i++) {
				//loop through all the names in the each course's ArrayList CourseEnrolleeList
				for ( int j = 0; j <CallingClass.courseList.get(i).CourseEnrolleeList.size();j++) {
					//once match is found, the result will be printed
					if (CallingClass.courseList.get(i).CourseEnrolleeList.get(j).toLowerCase().replaceAll("\\s","").contains(studentID.toLowerCase())) {
						System.out.println();
						System.out.println("Course name:           "+CallingClass.courseList.get(i).getName());
						System.out.println("Course ID:             "+CallingClass.courseList.get(i).getCourseID());
						System.out.println("Course Instructor:     "+CallingClass.courseList.get(i).getInstructor());
						System.out.println("Course Section Number: "+CallingClass.courseList.get(i).getSectionNumber());
						System.out.println("Course Location:       "+CallingClass.courseList.get(i).getLocation());
					}
				}	
			}
		}
		else {
			System.out.println("\nNo such student found\n");
		}
	}
}
