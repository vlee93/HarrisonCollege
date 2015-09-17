package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the H_CLASS database table.
 */
@Entity
@Table(name="H_CLASS", schema="TESTDB")
@NamedQuery(name="HClass.findAll", query="SELECT h FROM HClass h")
public class HClass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CRN_NUMBER")
	private long crnNumber;

	@Column(name="CLASS_DAYS")
	private String classDays;

	@Column(name="END_TIME")
	private String endTime;

	@Column(name="ENROLL_CAP")
	private int enrollCap;

	private String semester;

	@Column(name="START_TIME")
	private String startTime;

	//bi-directional many-to-one association to HClassroom
	@ManyToOne
	@JoinColumn(name="ROOM_ID")
	private HClassroom HClassroom;

	//bi-directional many-to-one association to HCourse
	@ManyToOne
	@JoinColumn(name="COURSE_ID")
	private HCourse HCourse;

	//bi-directional many-to-one association to HStaff
	@ManyToOne
	@JoinColumn(name="STAFF_ID")
	private HStaff HStaff;

	//bi-directional many-to-one association to HEnrollment
	@OneToMany(mappedBy="HClass")
	private List<HEnrollment> HEnrollments;

	public HClass() {
	}

	public long getCrnNumber() {
		return this.crnNumber;
	}

	public void setCrnNumber(long crnNumber) {
		this.crnNumber = crnNumber;
	}

	public String getClassDays() {
		return this.classDays;
	}

	public void setClassDays(String classDays) {
		this.classDays = classDays;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getEnrollCap() {
		return this.enrollCap;
	}

	public void setEnrollCap(int enrollCap) {
		this.enrollCap = enrollCap;
	}

	public String getSemester() {
		return this.semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public HClassroom getHClassroom() {
		return this.HClassroom;
	}

	public void setHClassroom(HClassroom HClassroom) {
		this.HClassroom = HClassroom;
	}

	public HCourse getHCourse() {
		return this.HCourse;
	}

	public void setHCourse(HCourse HCourse) {
		this.HCourse = HCourse;
	}

	public HStaff getHStaff() {
		return this.HStaff;
	}

	public void setHStaff(HStaff HStaff) {
		this.HStaff = HStaff;
	}

	public List<HEnrollment> getHEnrollments() {
		return this.HEnrollments;
	}

	public void setHEnrollments(List<HEnrollment> HEnrollments) {
		this.HEnrollments = HEnrollments;
	}

	public HEnrollment addHEnrollment(HEnrollment HEnrollment) {
		getHEnrollments().add(HEnrollment);
		HEnrollment.setHClass(this);

		return HEnrollment;
	}

	public HEnrollment removeHEnrollment(HEnrollment HEnrollment) {
		getHEnrollments().remove(HEnrollment);
		HEnrollment.setHClass(null);

		return HEnrollment;
	}

}