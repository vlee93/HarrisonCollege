package Tests;

import static org.junit.Assert.*;

import org.junit.Test;
import customTools.*;
import model.*;

public class DBTest {

	@Test
	public void test() {
		HClassroom classroom = new HClassroom();
		classroom.setBldgName("Humanities Bldg");
		classroom.setRoomNo("HU321");
		classroom.setMaxCapacity(50);
		DBUtil.addToDB(classroom);
		HClassroom test = DBUtil.createQuery("SELECT h FROM HClassroom h WHERE h.roomId = 3", HClassroom.class).getSingleResult();
		assertTrue(test.getRoomNo().equals("HU321"));
	}
	
	@Test
	public void updateDB() {
		HClassroom classroom = DBUtil.createQuery("SELECT h FROM HClassroom h WHERE h.roomId = 3", HClassroom.class).getSingleResult();
		classroom.setBldgName("Science Bldg");
		classroom.setRoomNo("SC321");
		classroom.setMaxCapacity(50);
		
		DBUtil.updateDB(classroom);
		HClassroom test = DBUtil.createQuery("SELECT h FROM HClassroom h WHERE h.roomId = 3", HClassroom.class).getSingleResult();
		assertTrue(test.getRoomNo().equals("SC321"));
	}
	
	@Test
	public void deleteDB() {
		HClassroom classroom = DBUtil.createQuery("SELECT h FROM HClassroom h WHERE h.roomId = 4", HClassroom.class).getSingleResult();
		DBUtil.deleteFromDB(classroom);
		assertTrue(DBUtil.createQuery("SELECT h FROM HClassroom h WHERE h.roomId = 4", HClassroom.class).getSingleResult()== null);
	}

}
