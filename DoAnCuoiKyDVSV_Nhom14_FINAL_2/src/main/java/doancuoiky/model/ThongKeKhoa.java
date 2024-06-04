package doancuoiky.model;

public class ThongKeKhoa {

	 private String courseName;
     private int numberOfStudents;
     private String khoaType;

     public ThongKeKhoa(String courseName, int numberOfStudents) {
         this.courseName = courseName;
         this.numberOfStudents = numberOfStudents;
     }

     public String getCourseName() {
         return courseName;
     }

     public int getNumberOfStudents() {
         return numberOfStudents;
     }
     public String getKhoaType() {
 		return khoaType;
 	}
 	public void setKhoaType(String khoaType) {
 		this.khoaType = khoaType;
 	}
}
