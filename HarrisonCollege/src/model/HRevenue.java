package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the H_REVENUE database table.
 * 
 */
@Entity
@Table(name="H_REVENUE", schema="TESTDB")
@NamedQuery(name="HRevenue.findAll", query="SELECT h FROM HRevenue h")
public class HRevenue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private double creditcost;

	private String semester;

	//bi-directional many-to-one association to HClass
	@OneToMany(mappedBy="HRevenue")
	private List<HClass> HClasses;

	public HRevenue() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getCreditcost() {
		return this.creditcost;
	}

	public void setCreditcost(double creditcost) {
		this.creditcost = creditcost;
	}

	public String getSemester() {
		return this.semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public List<HClass> getHClasses() {
		return this.HClasses;
	}

	public void setHClasses(List<HClass> HClasses) {
		this.HClasses = HClasses;
	}

	public HClass addHClass(HClass HClass) {
		getHClasses().add(HClass);
		HClass.setHRevenue(this);

		return HClass;
	}

	public HClass removeHClass(HClass HClass) {
		getHClasses().remove(HClass);
		HClass.setHRevenue(null);

		return HClass;
	}

}