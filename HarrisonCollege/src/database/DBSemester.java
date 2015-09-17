package database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.*;
import customTools.DBUtil;

public class DBSemester
{

	public static List<String> getAllSemesters()
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT distinct h.semester FROM HClass h";
		List<String> semesters = null;
		try
		{
			Query query = em.createQuery(qString);

			semesters = query.getResultList();

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			em.close();
		}
		System.out.println(semesters);		
		return semesters;
	}

}