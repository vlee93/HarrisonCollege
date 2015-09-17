package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import database.DBSemester;

public class DBSemesterTest
{

	@Test
	public void testGetAllSemesters()
	{
		List<String> semesters= DBSemester.getAllSemesters();
		for (String semester : semesters)
		{
			System.out.println(semester);
		}
	}

}
