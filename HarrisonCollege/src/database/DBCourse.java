package database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.HClass;
import model.HCourse;
import model.HMajor;
import model.HStaff;
import model.HStudent;
import customTools.DBUtil;

public class DBCourse
{
	public static List<HCourse> courseSearch(String department)
	{
		String where = "";
		boolean hasDepartment = false;
		
		
		//for department
		if (department.equalsIgnoreCase("all"))
		{
			where += " WHERE 0=0 ";
		} else
		{
			where += " WHERE h.HDepartment.deptName = :department ";
			hasDepartment = true;
		}
		
		String qString = "SELECT h FROM HCourse h " + where;
		System.out.println("Check full query = " + qString);
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		
		
		List<HCourse> courses = null;
		try
		{
			Query query = em.createQuery(qString);

			
			if (hasDepartment)
			{
				query.setParameter("department", department);
			}

			courses = query.getResultList();

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			em.close();
		}
		return courses;
	}
	
	public static List<HMajor> majorSearch(String department)
	{
		String where = "";
		boolean hasDepartment = false;
		
		
		//for department
		if (department.equalsIgnoreCase("all"))
		{
			where += " WHERE 0=0 ";
		} else
		{
			where += " WHERE h.HDepartment.deptName = :department ";
			hasDepartment = true;
		}
		
		String qString = "SELECT h FROM HMajor h " + where;
		System.out.println("Check full query = " + qString);
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		
		
		List<HMajor> majors = null;
		try
		{
			Query query = em.createQuery(qString);

			
			if (hasDepartment)
			{
				query.setParameter("department", department);
			}

			majors = query.getResultList();

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			em.close();
		}
		return majors;
	}

}