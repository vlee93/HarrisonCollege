<!DOCTYPE html>
<html lang="en">
<head>
  <title>View Transcript</title>


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
       <li role="presentation"><a href="/HarrisonCollege/aTranscript.jsp">View Transcript</a></li>
       <li role="presentation"  class="active"><a href="/HarrisonCollege/enrollment.jsp">Enrollment</a></li>
       <li role="presentation"><a href="/HarrisonCollege/#">Courses</a></li>
       <li role="presentation"><a href="/HarrisonCollege/#">Classes</a></li>
       <li role="presentation"><a href="/HarrisonCollege/#">Majors</a></li><br><br><br>
    </u1>  -->                                
    <form class="form" role="form" action="aAdd_course" method="post">
       <label style="display:inline;">Enter student ID: </label>
       <input class="form-control" name="student_id" placeholder="student id" style="margin-right:10px;width:20%;display:inline;" required/>
       <label style="display:inline;">CRN Number: </label>
       <input class="form-control" name="crn_number" placeholder="CRN number" style="margin-right:250px;width:20%;display:inline;" required/>
       <button class="form-control" type="submit" value="Add" style=" margin-left: 50px;display:inline-block;width:10%;display:inline;">Add</button> 
       <button class="form-control" type="submit" value="Drop" style="margin-right: 10px;display:inline-block;width:10%;display:inline;" formaction="/HarrisonCollege/aDrop_course">Drop</button>
       <br>
       ${alert}
    </form>   
<!--   </div>
</nav> -->
  


</body>
</html>