package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the H_COURSE database table.
 * 
 */
@Entity
@Table(name="H_COURSE", schema="TESTDB")
@NamedQuery(name="HCourse.findAll", query="SELECT h FROM HCourse h")
public class HCourse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COURSE_ID")
	private long courseId;

	private int available;

	@Column(name="COURSE_DESC")
	private String courseDesc;

	@Column(name="COURSE_NAME")
	private String courseName;

	@Column(name="COURSE_NO")
	private int courseNo;

	private double credits;

	@Column(name="SUBJECT_CODE")
	private String subjectCode;

	//bi-directional many-to-one association to HClass
	@OneToMany(mappedBy="HCourse")
	private List<HClass> HClasses;

	//bi-directional many-to-one association to HDepartment
	@ManyToOne
	@JoinColumn(name="DEPT_ID")
	private HDepartment HDepartment;

	public HCourse() {
	}

	public long getCourseId() {
		return this.courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public int getAvailable() {
		return this.available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public String getCourseDesc() {
		return this.courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseNo() {
		return this.courseNo;
	}

	public void setCourseNo(int courseNo) {
		this.courseNo = courseNo;
	}

	public double getCredits() {
		return this.credits;
	}

	public void setCredits(double credits) {
		this.credits = credits;
	}

	public String getSubjectCode() {
		return this.subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public List<HClass> getHClasses() {
		return this.HClasses;
	}

	public void setHClasses(List<HClass> HClasses) {
		this.HClasses = HClasses;
	}

	public HClass addHClass(HClass HClass) {
		getHClasses().add(HClass);
		HClass.setHCourse(this);

		return HClass;
	}

	public HClass removeHClass(HClass HClass) {
		getHClasses().remove(HClass);
		HClass.setHCourse(null);

		return HClass;
	}

	public HDepartment getHDepartment() {
		return this.HDepartment;
	}

	public void setHDepartment(HDepartment HDepartment) {
		this.HDepartment = HDepartment;
	}

}