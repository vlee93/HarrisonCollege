package database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.*;
import customTools.DBUtil;

public class DBSubject
{

	public static List<HCourse> getAllAvailableSubjects()
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT h.subjectCode FROM HCourse h where h.available = 1";
		List<HCourse> subjects = null;
		try
		{
			Query query = em.createQuery(qString);

			subjects = query.getResultList();

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			em.close();
		}
		System.out.println(subjects);		
		return subjects;
	}

}