<!DOCTYPE html>
<html lang="en">
<head>
  <title>Grades</title>
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
<!-- <nav class="navbar navbar-default " style="background-color:#D5D5D5">
  <div class="container-fluid">
     <ul class="nav nav-pills" style="font-family:Times New Roman;">
       <li role="presentation"><a href="/HarrisonCollege/iClasses.jsp">Classes</a></li>
       <li role="presentation"><a href="/HarrisonCollege/iStudents">Students</a></li>
       <li role="presentation" class="active"><a href="/HarrisonCollege/iGrades">Grades</a></li>
       <li role="presentation"><a href="/HarrisonCollege/#">Courses</a></li>
       <li role="presentation"><a href="/HarrisonCollege/#">Majors</a></li><br><br><br>
    </u1>  -->
<div class="container">
   <form action="/HarrisonCollege/iGrades.jsp" method="get">    
	    <button class="form-control " type="submit" value="Add Grades" style="margin-left:500px;display:inline-block;width:10%;">Add grades</button>
	    <button class="form-control " type="submit" value="View Grades" style="margin-left:100px;display:inline-block;width:10%;"formaction="/HarrisonCollege/iViewGrades.jsp" >View Grades</button>
    </form>
<!--   </div>
</nav> -->
<label style="color:orange; margin-left:110px;">Note: insert -1 for status "Incomplete" and -2 for status "Withdraw"</label>
<form action="iAddGrade" method="get">
 <c:if test="${not empty students}">
	 <div class="container">
		 <table class="table table-striped">
		   <thead>
		     <tr>
		       <th>Student Name</th>
		       <th>Course Code</th>
		       <th>Grades</th>
		     </tr>
		   </thead>
		   <tbody>
		   <c:forEach var="student" items="${students}"  varStatus="status">
		     <tr>
		       <td>${students.get(status.index).getStudentName()}</td>
		       <td>${courses.get(status.index).getSubjectCode()}  ${courses.get(status.index).getCourseNo()}</td>
		       <td>
		       <input type="hidden" name="enrollment_id" value="${enrollments.get(status.index).getEnrId()}" />
		       <input class="form-control" name="sGrade" value="${enrollments.get(status.index).getGrade()}" style="width:15%;"/>
		       </td>
		     </tr>
		     </c:forEach>
		    </tbody>
		  </table>
	 </div>
     
           
     </c:if>
    <c:if test="${empty students}">
       <p style="color:red"> No results</p>
    </c:if>
       
 ${alert}    
<button class="form-control " type="submit" value="Submit" style="margin-left:1130px;display:inline-block;width:10%;" >Submit</button>

</form>
</div>
</body>
</html>