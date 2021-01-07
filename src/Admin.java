import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Admin extends User implements AdminInterface{
	static Scanner sc = new Scanner(System.in);
	//Course Management section
	@Override
	public void createCourse() {//create a course and save input, and add to the ArrayList courseList
		Course cour = new Course();
		System.out.println("Enter course name: ");
		String courseName = sc.nextLine();
		cour.setName(courseName);
		System.out.println("Enter course ID: ");
		String courseID = sc.nextLine();
		cour.setCourseID(courseID);
		System.out.println("Enter course section number: ");
		int sectionNumber = Integer.parseInt(sc.nextLine());
		cour.setSectionNumber(sectionNumber);
		System.out.println("Enter max number of students allowed: ");
		int maxNumber = Integer.parseInt(sc.nextLine());
		cour.setMaxNumber(maxNumber);
		System.out.println("Enter course instructor: ");
		String instructor = sc.nextLine();
		cour.setInstructor(instructor);
		System.out.println("Enter course location: ");
		String location = sc.nextLine();
		cour.setLocation(location);
		CallingClass.courseList.add(cour);
		System.out.println("The course is successfully created");
		}
	@Override
	public void deleteCourse() {
		System.out.print("Enter the course ID: ");//asking for course ID in order to proceed
		String CourseID = sc.nextLine();
		System.out.println();
		int count = 0;
		int index = -1;
		for (int i = 0; i < CallingClass.courseList.size(); i++) {
			if ( CallingClass.courseList.get(i).getCourseID().equals(CourseID)) {
				count++;
				index = i;
			}
		}
		//if course name appears more than once
		//ask for section number
		if(count > 1 ) {
			index = -1;
			System.out.println("There are more than one course with the same Course ID! ");
			System.out.print("Enter the course section number to start editing the course: ");
			int courseSectionNumber = Integer.parseInt(sc.nextLine());
			for (int i = 0; i < CallingClass.courseList.size(); i++) {
				if ( CallingClass.courseList.get(i).getName().equals(CourseID) &&
						CallingClass.courseList.get(i).getSectionNumber() == courseSectionNumber) {// if there is a match // match condition
					CallingClass.courseList.remove(i);	//remove the matched course
					System.out.println("The course is successfully deleted");
					break;
				}
			}
		}
		
		if(index == -1) {//if index is unchanged, it means nothing matches
			System.out.println("No course found.");
			}
		else {
			CallingClass.courseList.remove(index);//remove the matched course
			System.out.println("The course is successfully deleted");
		}
		
	}
	@Override
	public void editCourse() throws Exception {
		System.out.print("Enter the course ID: ");//asking for course ID in order to proceed
		String CourseID = sc.nextLine();
		System.out.println();
		int count = 0;
		int index = -1;
		for (int i = 0; i < CallingClass.courseList.size(); i++) {
			if ( CallingClass.courseList.get(i).getCourseID().equals(CourseID)) {
				count++;
				index = i;
			}
		}
		//if course name appears more than once
		//ask for section number
		if(count > 1 ) {
			index = -1;
			System.out.println("There are more than one course with the same Course ID! ");
			System.out.print("Enter the course section number to start editing the course: ");
			int sectionNumber = Integer.parseInt(sc.nextLine());
			for (int i = 0; i < CallingClass.courseList.size(); i++) {
				if ( CallingClass.courseList.get(i).getName().equals(CourseID) &&
						CallingClass.courseList.get(i).getSectionNumber() == sectionNumber) {
					index = i;
					break;
				}
			}
		}
		if(index == -1) {//if index is unchanged, it means nothing matches
			System.out.println("No course found.");
		}else{
			System.out.println("Following are the options");
			boolean menuStatus = true;
				while(menuStatus==true) {
				System.out.println("(1)To edit the MAXIMUM NUMBER of students in a class");
				System.out.println("(2)To edit the NAME of the instructor");
				System.out.println("(3)To edit the SECTION NUMBER of the instructor");
				System.out.println("(4)To edit the LOCATION of the course");
				System.out.print("Please enter a number to choose: ");
				int option = Integer.parseInt(sc.nextLine());
				if(option == 1) {
					System.out.print("Please enter the MAXIMUM NUMBER of students in a class: ");
					int maxNumber = Integer.parseInt(sc.nextLine());
					CallingClass.courseList.get(index).setMaxNumber(maxNumber);
					System.out.println("The information has been successfully updated.");
					break;
				}
				else if (option == 2) {
					System.out.print("Please enter the NAME of the instructor: ");
					String instructor = sc.nextLine();
					CallingClass.courseList.get(index).setInstructor(instructor);
					System.out.println("The information has been successfully updated.");
					break;
				}
				else if (option == 3) {				
					System.out.print("Please enter the SECTION NUMBER of the instructor: ");
					int sectionNumber = Integer.parseInt(sc.nextLine());
					CallingClass.courseList.get(index).setSectionNumber(sectionNumber);	
					System.out.println("The information has been successfully updated.");
					break;
				}
				else if (option == 4) {				
					System.out.print("Please enter the LOCATION of the course: ");
					String location = sc.nextLine();
					CallingClass.courseList.get(index).setLocation(location);
					System.out.println("The information has been successfully updated.");
					break;
				}
				else if (option == 9) {menuStatus = false;CallingClass.adminCourseManagement(CallingClass.ad);}
				else {System.out.println("Invalid option. Try again!");}
				}
		}
	}
		@Override
	public void displayCourseInfo() {
		//asking for course ID in order to proceed
		System.out.print("Enter the course ID: ");
		String CourseID = sc.nextLine();
		System.out.println();
		int count = 0;
		int index = -1;
		for (int i = 0; i < CallingClass.courseList.size(); i++) {
			if ( CallingClass.courseList.get(i).getCourseID().equals(CourseID)) {
				count++;
				index = i;
			}
		}
		if(count > 1 ) {
			index = -1;
			System.out.println("There are more than one course with the same Course ID! ");
			System.out.print("Enter the course section number to start editing the course: ");
			int courseSectionNumber = Integer.parseInt(sc.nextLine());
			for (int i = 0; i < CallingClass.courseList.size(); i++) {
				if (CallingClass.courseList.get(i).getSectionNumber() == courseSectionNumber && CallingClass.courseList.get(i).getCourseID().equals(CourseID)) {
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
		if(index == -1) {//if index is unchanged, it means nothing matches
			System.out.println("No course found.");
			}
		else {
			System.out.println();
			System.out.println("Course name:           "+CallingClass.courseList.get(index).getName());
			System.out.println("Course ID:             "+CallingClass.courseList.get(index).getCourseID());
			System.out.println("Maximum students:      "+CallingClass.courseList.get(index).getMaxNumber());
			System.out.println("Current students:      "+CallingClass.courseList.get(index).getCurrentNumber());
			System.out.println("Course Instructor:     "+CallingClass.courseList.get(index).getInstructor());
			System.out.println("Course Section Number: "+CallingClass.courseList.get(index).getSectionNumber());
			System.out.println("Course Location:       "+CallingClass.courseList.get(index).getLocation());
			
		}
	}
	@Override
	public void createStudent() throws Exception {
		Student student = new Student();
		boolean status = true;
		int count = 0;
		while(status) {
			System.out.print("Please enter the USERNAME for this student: ");
			String username = sc.nextLine();
			student.setUsername(username);
			System.out.print("Please enter the PASSWORD for this student: ");
			String password = sc.nextLine();
			student.setPassword(password);
			System.out.print("Please enter the FIRST NAME for this student: ");
			String firstname = sc.nextLine();
			student.setFirstName(firstname);
			System.out.print("Please enter the LAST NAME for this student: ");
			String lastname = sc.nextLine();
			student.setLastName(lastname);
			System.out.print("Please enter the STUDENT ID for this student: ");
			String studentID =sc.nextLine();
			student.setStudentID(studentID);
			//making sure the student's info is unique
			
			boolean repetition = false;
			for(int i = 0; i < CallingClass.systemStudentList.size();i++) {
				if(CallingClass.systemStudentList.get(i).getUsername().toLowerCase().equals(student.getUsername().toLowerCase())){
					System.out.println();
					System.out.println("The username already exists!!!");
					System.out.println("Please try something new.");
					System.out.println();
					count++;
					if(count == 3) {
						System.out.println();
						System.out.println("You have exhausted all 3 trials.");
						System.out.println("Returning to the previous menu.");
						System.out.println();
						status = false;// condition to end the while loop
						break; // to break the for loop
					}
					repetition = true;
					break;
				}
			}
			if(count == 3) {
				break;
			}
			if(repetition == false) {
				CallingClass.systemStudentList.add(student);
				System.out.println();
				System.out.println("The student is successfully added.");
				System.out.println("Returning to the previous menu.");
				System.out.println();
				status = false;
			}
		}
	}
	//Report section
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
	@Override
	public void viewFullCourse() {
		System.out.println();
		System.out.println("Here are the results: ");
		for (int i = 0; i < CallingClass.courseList.size(); i++) {
			if(CallingClass.courseList.get(i).getMaxNumber() == CallingClass.courseList.get(i).getCurrentNumber()) {
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
		System.out.println();
	}
	@Override
	public void saveFullCourse() throws Exception {
		//produce a file containing all the full courses.
		String ourfile = CallingClass.fileAddress+"/Saved_Full_Courses.txt";  
		PrintWriter pw = new PrintWriter(ourfile);
		for (int i = 0; i < CallingClass.courseList.size(); i++) {
			String courseName = CallingClass.courseList.get(i).getName();
			if(CallingClass.courseList.get(i).getCurrentNumber() == CallingClass.courseList.get(i).getMaxNumber()) {
				pw.println(courseName);
			}
		}
		pw.close();
		System.out.println("The file is successfully created");
	}
	@Override
	public void viewCoursesRegisteredStudent() {
		//ask course id in order to perform other operations
		System.out.print("Enter the course ID: ");
		String CourseID = sc.nextLine();
		System.out.println();
		int count = 0;
		int index = -1;
		for (int i = 0; i < CallingClass.courseList.size(); i++) {
			if ( CallingClass.courseList.get(i).getCourseID().equals(CourseID)) {
				count++;
				index = i;
			}
		}
		//if two courses have the same name, it asks for section number
		if(count > 1 ) {
			index = -1;
			System.out.println("There are more than one course with the same Course ID! ");
			System.out.print("Enter the course section number to start editing the course: ");
			int courseSectionNumber = Integer.parseInt(sc.nextLine());
			for (int i = 0; i < CallingClass.courseList.size(); i++) {
				if (CallingClass.courseList.get(i).getSectionNumber() == courseSectionNumber && CallingClass.courseList.get(i).getCourseID().equals(CourseID)) {
					index = i;
					break;
				}
			}
		}
		if(index == -1) {//if index is unchanged, it means nothing matches//index hasn't changed, meaning no match
			System.out.println("No course matches the input information");
		}
		else {
			System.out.println("\nHere are the results.\n");
			System.out.print("Name List: ");
			for(int j = 0; j < CallingClass.courseList.get(index).CourseEnrolleeList.size();j++) {
				String[] buffarray;
				String buffer = CallingClass.courseList.get(index).CourseEnrolleeList.get(j);
		        buffarray = buffer.split("@");
		        for(int k = 0; k < buffarray.length; k++) {
		        	System.out.print(buffarray[k]+" ");
		        }
		        System.out.print("| ");
			}
		}
		System.out.println();
	}
	@Override
	public void viewStudentsRegisteredCourse() {
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
		System.out.println();
		System.out.println("Here are the results: ");
		System.out.println("Registered Courses: \n");
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
					break;
				}	
			}
		}
		else {
			System.out.println("\nNo such student found\n");
		}
	}
	@Override
	public void sortCourseOnNumberOfEnrolledStudents() {
		//sorting the course based on the defined comparable(number of enrolled students)
		Collections.sort(CallingClass.courseList);
		System.out.println("\nThe course has been sueccesfully sorted based on the number of enrolled students.\n");
	}
}
