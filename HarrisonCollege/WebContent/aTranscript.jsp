!DOCTYPE html>
<html lang="en">
<head>
  <title>Transcript</title>
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
       <li role="presentation" class="active"><a href="/HarrisonCollege/aTranscript.jsp">View Transcript</a></li>
       <li role="presentation"><a href="/HarrisonCollege/enrollment.jsp">Enrollment</a></li>
       <li role="presentation"><a href="/HarrisonCollege/#">Courses</a></li>
       <li role="presentation"><a href="/HarrisonCollege/#">Classes</a></li>
       <li role="presentation"><a href="/HarrisonCollege/#">Majors</a></li><br><br><br>
    </u1>
    <form class="form" role="form" action="aTranscript" method="get">
       <label style="display:inline;">Enter student ID: </label>
       <input class="form-control" name="student_id" placeholder="student id" style="margin-right:10px;width:20%;display:inline;" required/>
       <button class="form-control" type="submit" value="View" style=" margin-left: 50px;display:inline-block;width:10%;display:inline;">View</button> 
 
       <br>
       ${alert}
    </form>   
  </div>
</nav>

${addClassError}

${addClassMsg}

${scheduleMsg}

${transMsg}

${message3}
</body>
</html>