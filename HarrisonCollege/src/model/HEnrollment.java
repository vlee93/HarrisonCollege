package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the H_ENROLLMENT database table.
 * 
 */
@Entity
@Table(name="H_ENROLLMENT", schema="TESTDB")
@NamedQuery(name="HEnrollment.findAll", query="SELECT h FROM HEnrollment h")
public class HEnrollment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ENR_ID")
	private long enrId;

	private double grade;

	//bi-directional many-to-one association to HClass
	@ManyToOne
	@JoinColumn(name="CLASS_ID")
	private HClass HClass;

	//bi-directional many-to-one association to HStudent
	@ManyToOne
	@JoinColumn(name="STUDENT_ID")
	private HStudent HStudent;

	public HEnrollment() {
	}

	public long getEnrId() {
		return this.enrId;
	}

	public void setEnrId(long enrId) {
		this.enrId = enrId;
	}

	public double getGrade() {
		return this.grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public HClass getHClass() {
		return this.HClass;
	}

	public void setHClass(HClass HClass) {
		this.HClass = HClass;
	}

	public HStudent getHStudent() {
		return this.HStudent;
	}

	public void setHStudent(HStudent HStudent) {
		this.HStudent = HStudent;
	}

}