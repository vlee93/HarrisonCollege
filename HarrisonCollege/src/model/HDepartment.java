package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the H_DEPARTMENT database table.
 * 
 */
@Entity
@Table(name="H_DEPARTMENT", schema="TESTDB")
@NamedQuery(name="HDepartment.findAll", query="SELECT h FROM HDepartment h")
public class HDepartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DEPT_ID")
	private long deptId;

	private int available;

	@Column(name="DEPT_NAME")
	private String deptName;

	//bi-directional many-to-one association to HCourse
	@OneToMany(mappedBy="HDepartment")
	private List<HCourse> HCourses;

	//bi-directional many-to-one association to HMajor
	@OneToMany(mappedBy="HDepartment")
	private List<HMajor> HMajors;

	//bi-directional many-to-one association to HStaff
	@OneToMany(mappedBy="HDepartment")
	private List<HStaff> HStaffs;

	public HDepartment() {
	}

	public long getDeptId() {
		return this.deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	public int getAvailable() {
		return this.available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<HCourse> getHCourses() {
		return this.HCourses;
	}

	public void setHCourses(List<HCourse> HCourses) {
		this.HCourses = HCourses;
	}

	public HCourse addHCours(HCourse HCours) {
		getHCourses().add(HCours);
		HCours.setHDepartment(this);

		return HCours;
	}

	public HCourse removeHCours(HCourse HCours) {
		getHCourses().remove(HCours);
		HCours.setHDepartment(null);

		return HCours;
	}

	public List<HMajor> getHMajors() {
		return this.HMajors;
	}

	public void setHMajors(List<HMajor> HMajors) {
		this.HMajors = HMajors;
	}

	public HMajor addHMajor(HMajor HMajor) {
		getHMajors().add(HMajor);
		HMajor.setHDepartment(this);

		return HMajor;
	}

	public HMajor removeHMajor(HMajor HMajor) {
		getHMajors().remove(HMajor);
		HMajor.setHDepartment(null);

		return HMajor;
	}

	public List<HStaff> getHStaffs() {
		return this.HStaffs;
	}

	public void setHStaffs(List<HStaff> HStaffs) {
		this.HStaffs = HStaffs;
	}

	public HStaff addHStaff(HStaff HStaff) {
		getHStaffs().add(HStaff);
		HStaff.setHDepartment(this);

		return HStaff;
	}

	public HStaff removeHStaff(HStaff HStaff) {
		getHStaffs().remove(HStaff);
		HStaff.setHDepartment(null);

		return HStaff;
	}

}