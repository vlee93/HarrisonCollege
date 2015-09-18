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
<div class="container">                             
    <form class="form" role="form" action="aAdd_course" method="post">
       <label style="display:inline;">Enter student ID: </label>
       <input class="form-control" name="student_id" placeholder="student id" style="margin-right:10px;width:20%;display:inline;" required/>
       <label style="display:inline;">CRN Number: </label>
       <input class="form-control" name="crn_number" placeholder="CRN number" style="width:20%;display:inline;" required/>
       <button class="form-control" type="submit" value="Add" style="display:inline-block;width:10%;display:inline;">Add</button> 
       <button class="form-control" type="submit" value="Drop" style="display:inline-block;width:10%;display:inline;" formaction="/HarrisonCollege/aDrop_course">Drop</button>
       <br />
       ${alert}
    </form>   
</div>

</body>
</html>