package database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.*;
import customTools.DBUtil;

public class DBSubject
{

	public static List<HCourse> getCourseNos()
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT h.courseNo FROM HCourse h where h.available = 1";
		List<HCourse> courseNos = null;
		try
		{
			Query query = em.createQuery(qString);

			courseNos = query.getResultList();

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			em.close();
		}
		System.out.println(courseNos);		
		return courseNos;
	}
	
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
	
	public static List<HCourse> getCourseNames()
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT h.courseName FROM HCourse h where h.available = 1";
		List<HCourse> courseNames = null;
		try
		{
			Query query = em.createQuery(qString);

			courseNames = query.getResultList();

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			em.close();
		}
		System.out.println(courseNames);		
		return courseNames;
	}
	
	public static List<HCourse> getCredits()
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT h.credits FROM HCourse h where h.available = 1";
		List<HCourse> credits = null;
		try
		{
			Query query = em.createQuery(qString);

			credits = query.getResultList();

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			em.close();
		}
		System.out.println(credits);		
		return credits;
	}
	
	public static List<HMajor> getMajorName()
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT h.majorName FROM HMajor h where h.available = 1";
		List<HMajor> majors = null;
		try
		{
			Query query = em.createQuery(qString);

			majors = query.getResultList();

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			em.close();
		}
		System.out.println(majors);		
		return majors;
	}

}