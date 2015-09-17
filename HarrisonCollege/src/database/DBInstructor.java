package database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.*;
import customTools.DBUtil;

public class DBInstructor
{

	public static List<String> getAllInstructors()
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT distinct h.staffName FROM HStaff h where h.type = 'Instructor'";
		List<String> instructors = null;
		try
		{
			Query query = em.createQuery(qString);

			instructors = query.getResultList();

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			em.close();
		}
		System.out.println(instructors);		
		return instructors;
	}

}