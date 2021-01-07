public interface AdminInterface {
	//the method signatures
	public void createCourse();
	public void deleteCourse();
	public void editCourse() throws Exception;
	public void displayCourseInfo();
	public void createStudent() throws Exception;
	public void viewCourse();
	public void viewFullCourse();
	public void saveFullCourse() throws Exception;
	public void viewCoursesRegisteredStudent();
	public void viewStudentsRegisteredCourse();
	public void sortCourseOnNumberOfEnrolledStudents();
}
