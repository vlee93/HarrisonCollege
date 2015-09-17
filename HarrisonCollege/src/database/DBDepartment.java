package database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.*;
import customTools.DBUtil;

public class DBDepartment
{

	public static List<HDepartment> getAvailableDepartments()
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT h.deptName FROM HDepartment h WHERE h.available = 1";
		List<HDepartment> departments = null;
		try
		{
			Query query = em.createQuery(qString);

			departments = query.getResultList();

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			em.close();
		}
		System.out.println(departments);		
		return departments;
	}

}