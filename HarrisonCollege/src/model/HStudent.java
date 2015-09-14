package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the H_STUDENT database table.
 * 
 */
@Entity
@Table(name="H_STUDENT", schema="TESTDB")
@NamedQuery(name="HStudent.findAll", query="SELECT h FROM HStudent h")
public class HStudent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="STUDENT_ID")
	private long studentId;

	@Column(name="START_YEAR")
	private int startYear;

	@Column(name="STUDENT_NAME")
	private String studentName;

	@Column(name="USER_PASSWORD")
	private String userPassword;

	//bi-directional many-to-one association to HEnrollment
	@OneToMany(mappedBy="HStudent")
	private List<HEnrollment> HEnrollments;

	//bi-directional many-to-one association to HMajor
	@ManyToOne
	@JoinColumn(name="MAJOR_ID")
	private HMajor HMajor;

	public HStudent() {
	}

	public long getStudentId() {
		return this.studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public int getStartYear() {
		return this.startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<HEnrollment> getHEnrollments() {
		return this.HEnrollments;
	}

	public void setHEnrollments(List<HEnrollment> HEnrollments) {
		this.HEnrollments = HEnrollments;
	}

	public HEnrollment addHEnrollment(HEnrollment HEnrollment) {
		getHEnrollments().add(HEnrollment);
		HEnrollment.setHStudent(this);

		return HEnrollment;
	}

	public HEnrollment removeHEnrollment(HEnrollment HEnrollment) {
		getHEnrollments().remove(HEnrollment);
		HEnrollment.setHStudent(null);

		return HEnrollment;
	}

	public HMajor getHMajor() {
		return this.HMajor;
	}

	public void setHMajor(HMajor HMajor) {
		this.HMajor = HMajor;
	}

}