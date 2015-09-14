package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the H_MAJOR database table.
 * 
 */
@Entity
@Table(name="H_MAJOR", schema="TESTDB")
@NamedQuery(name="HMajor.findAll", query="SELECT h FROM HMajor h")
public class HMajor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MAJOR_ID")
	private long majorId;

	private int available;

	@Column(name="MAJOR_NAME")
	private String majorName;

	//bi-directional many-to-one association to HDepartment
	@ManyToOne
	@JoinColumn(name="DEPT_ID")
	private HDepartment HDepartment;

	//bi-directional many-to-one association to HStudent
	@OneToMany(mappedBy="HMajor")
	private List<HStudent> HStudents;

	public HMajor() {
	}

	public long getMajorId() {
		return this.majorId;
	}

	public void setMajorId(long majorId) {
		this.majorId = majorId;
	}

	public int getAvailable() {
		return this.available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public String getMajorName() {
		return this.majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public HDepartment getHDepartment() {
		return this.HDepartment;
	}

	public void setHDepartment(HDepartment HDepartment) {
		this.HDepartment = HDepartment;
	}

	public List<HStudent> getHStudents() {
		return this.HStudents;
	}

	public void setHStudents(List<HStudent> HStudents) {
		this.HStudents = HStudents;
	}

	public HStudent addHStudent(HStudent HStudent) {
		getHStudents().add(HStudent);
		HStudent.setHMajor(this);

		return HStudent;
	}

	public HStudent removeHStudent(HStudent HStudent) {
		getHStudents().remove(HStudent);
		HStudent.setHMajor(null);

		return HStudent;
	}

}