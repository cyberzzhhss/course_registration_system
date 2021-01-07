import java.util.ArrayList; 
import java.io.Serializable;
public class Course implements Comparable<Course>,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String courseID;
	private int currentNumber;
	//ArrayList to store student information, delimited by "@"
	ArrayList<String> CourseEnrolleeList = new ArrayList<String>();
	private int maxNumber;
	private String instructor;
	private int sectionNumber;
	private String location;
	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public int getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}
	public int getCurrentNumber() {
		return currentNumber;
	}
	public void setCurrentNumber(int currentNumber) {
		this.currentNumber = currentNumber;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public int getSectionNumber() {
		return sectionNumber;
	}
	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public int compareTo(Course inCourse) {
		int number = inCourse.currentNumber;
		//sorting based on the number of students enrolled in the course
		//if I'm less than inCourse,  return a negative number;
		//if I'm equal to the inCourse, return 0
		//if I'm greater than inCourse, return a positive number;
		return this.currentNumber-number;
	}
}
