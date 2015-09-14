package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the H_CLASSROOM database table.
 * 
 */
@Entity
@Table(name="H_CLASSROOM", schema="TESTDB")
@NamedQuery(name="HClassroom.findAll", query="SELECT h FROM HClassroom h")
public class HClassroom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ROOM_ID")
	private long roomId;

	private int available;

	@Column(name="BLDG_NAME")
	private String bldgName;

	@Column(name="MAX_CAPACITY")
	private int maxCapacity;

	@Column(name="ROOM_NO")
	private String roomNo;

	//bi-directional many-to-one association to HClass
	@OneToMany(mappedBy="HClassroom")
	private List<HClass> HClasses;

	public HClassroom() {
	}

	public long getRoomId() {
		return this.roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public int getAvailable() {
		return this.available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public String getBldgName() {
		return this.bldgName;
	}

	public void setBldgName(String bldgName) {
		this.bldgName = bldgName;
	}

	public int getMaxCapacity() {
		return this.maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public String getRoomNo() {
		return this.roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public List<HClass> getHClasses() {
		return this.HClasses;
	}

	public void setHClasses(List<HClass> HClasses) {
		this.HClasses = HClasses;
	}

	public HClass addHClass(HClass HClass) {
		getHClasses().add(HClass);
		HClass.setHClassroom(this);

		return HClass;
	}

	public HClass removeHClass(HClass HClass) {
		getHClasses().remove(HClass);
		HClass.setHClassroom(null);

		return HClass;
	}

}