package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import database.DBInstructor;
import database.DBSemester;

public class DBInstructorTest
{

	@Test
	public void testGetAllSemesters()
	{
		List<String> instructors= DBInstructor.getAllInstructors();
		for (String instructor : instructors)
		{
			System.out.println(instructor);
		}
	}

}
