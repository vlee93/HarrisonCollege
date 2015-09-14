package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the H_STAFF database table.
 * 
 */
@Entity
@Table(name="H_STAFF", schema="TESTDB")
@NamedQuery(name="HStaff.findAll", query="SELECT h FROM HStaff h")
public class HStaff implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="STAFF_ID")
	private long staffId;

	@Column(name="OFFICE_NO")
	private String officeNo;

	@Column(name="STAFF_NAME")
	private String staffName;

	@Column(name="\"TYPE\"")
	private String type;

	@Column(name="USER_PASSWORD")
	private String userPassword;

	//bi-directional many-to-one association to HClass
	@OneToMany(mappedBy="HStaff")
	private List<HClass> HClasses;

	//bi-directional many-to-one association to HDepartment
	@ManyToOne
	@JoinColumn(name="DEPT_ID")
	private HDepartment HDepartment;

	public HStaff() {
	}

	public long getStaffId() {
		return this.staffId;
	}

	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}

	public String getOfficeNo() {
		return this.officeNo;
	}

	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<HClass> getHClasses() {
		return this.HClasses;
	}

	public void setHClasses(List<HClass> HClasses) {
		this.HClasses = HClasses;
	}

	public HClass addHClass(HClass HClass) {
		getHClasses().add(HClass);
		HClass.setHStaff(this);

		return HClass;
	}

	public HClass removeHClass(HClass HClass) {
		getHClasses().remove(HClass);
		HClass.setHStaff(null);

		return HClass;
	}

	public HDepartment getHDepartment() {
		return this.HDepartment;
	}

	public void setHDepartment(HDepartment HDepartment) {
		this.HDepartment = HDepartment;
	}

}