package database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.HClass;
import model.HCourse;
import model.HStaff;
import model.HStudent;
import customTools.DBUtil;

public class DBClass
{
	public static List<HClass> classSearch(String semester, String subject, String instructor, String department, String time)
	{
		String where = "";
		boolean hasSemester = false;
		boolean hasSubject = false;
		boolean hasInstructor = false;
		boolean hasDepartment = false;
		boolean hasTime = false;
		
		
		//for semester
		if (semester.equalsIgnoreCase("all"))
		{
			where += " WHERE 0=0 ";
		} else
		{
			where += " WHERE h.HClass.semester = :semester ";
			hasSemester = true;
		}
		
		//for subject
		if (subject.equalsIgnoreCase("all"))
		{
			where += " AND 0=0 ";
		} else
		{
			where += " AND h.HClass.HCourse.subjectCode = :subject ";
			hasSubject = true;
		}
		
		
		//for instructor
		if (instructor.equalsIgnoreCase("all"))
		{
			where += " AND 0=0 ";
		} else
		{
			where += " AND h.HClass.HStaff.staffName = :instructor ";
			hasInstructor = true;
		}
		
		//for department
		if (department.equalsIgnoreCase("all"))
		{
			where += " AND 0=0 ";
		} else
		{
			where += " AND h.HClass.HCourse.HDepartment.deptName = :department ";
			hasDepartment = true;
		}
		
		//for time
		if (time.equalsIgnoreCase("all"))
		{
			where += " AND 0=0 ";
		} else
		{
			where += " AND h.HClass.startTime = :startTime ";
			hasTime = true;
		}
		
		String qString = "SELECT h FROM HClass h " + where;
		System.out.println("Check full query = " + qString);
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		
		
		List<HClass> classes = null;
		try
		{
			Query query = em.createQuery(qString);

			if (hasSubject)
			{
				query.setParameter("subject", subject);
			}

			if (hasInstructor)
			{
				query.setParameter("instructor", instructor);
			}

			if (hasTime)
			{
				query.setParameter("startTime", time);
			}

			if (hasSemester)
			{
				query.setParameter("semester", semester);
			}
			
			if (hasDepartment)
			{
				query.setParameter("department", department);
			}

			classes = query.getResultList();

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			em.close();
		}
		return classes;
	}

}