<!DOCTYPE html>
<html lang="en">
<head>
  <title>Students</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<style>
select {    
    margin: 20px;
    font-family:"Times New Roman";
}
</style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<nav class="navbar navbar-default " style="background-color:#D5D5D5">
  <div class="container-fluid">
     <ul class="nav nav-pills" style="font-family:Times New Roman;">
       <li role="presentation"><a href="/HarrisonCollege/iClasses.jsp">Classes</a></li>
       <li role="presentation" class="active"><a href="/HarrisonCollege/iStudents">Students</a></li>
       <li role="presentation"><a href="/HarrisonCollege/iGrades">Grades</a></li>
       <li role="presentation"><a href="/HarrisonCollege/#">Courses</a></li>
       <li role="presentation"><a href="/HarrisonCollege/#">Majors</a></li><br><br><br>
    </u1> 
  </div>
</nav>
  
 <c:if test="${not empty students}">
	 <div class="container">
		 <table class="table table-striped">
		   <thead>
		     <tr>
		       <th>ID</th>
		       <th>Name</th>
		       <th>Major</th>
		     </tr>
		   </thead>
		   <tbody>
		   <c:forEach var="student" items="${students}"  varStatus="status">
		     <tr>
		       <td><a href="/HarrisonCollege/Student_details?student_id=${students.get(status.index).getStudentId()}">${students.get(status.index).getStudentId()}</a></td>
		       <td>${students.get(status.index).getStudentName()}</td>
		       <td>${majors.get(status.index).getMajorName()}</td>
		     </tr>
		     </c:forEach>
		    </tbody>
		  </table>
	 </div>
     
           
     </c:if>
    <c:if test="${empty students}">
       <p style="color:red"> No student available in your classes</p>
    </c:if>
       
       
       



</body>
</html>