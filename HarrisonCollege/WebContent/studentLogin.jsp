<jsp:include page="./header.jsp"/>
<script type="text/javascript">
function validateForm1() {
    var a = document.forms["createAccForm"]["stuName"].value;
    var b = document.forms["createAccForm"]["stuPwd"].value;
    var c = document.forms["createAccForm"]["major"].value;
    var d = document.forms["createAccForm"]["year"].value;
    if (a == null || a == "" || b == null || b == "" || c == null || c == "" || d == null || d == "") {
        alert("All fields must be filled out.");
        return false;
    }
}

function validateForm2() {
    var a = document.forms["loginForm"]["stuID"].value;
    var b = document.forms["loginForm"]["loginPwd"].value;
    if (a == null || a == "" || b == null || b == "") {
        alert("All fields must be filled out.");
        return false;
    }
}
</script>
<div class="container">
${error}
</div>
<div class="container">
<h3> Student Login </h3>
<div class="col-sm-6">
<h3>Create an Account</h3>
<form class="form-horizontal" role="form" name="createAccForm" id="createAccForm" onsubmit="return validateForm1()" action="studentLoginServ" method="post">
  <div class="form-group">
    <label class="control-label col-sm-2" for="stuName">Name:</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="stuName" id="stuName" placeholder="Enter name">
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="stuPwd">Password:</label>
    <div class="col-sm-10"> 
      <input type="password" class="form-control" name="stuPwd" id="stuPwd" placeholder="Enter password">
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="major">Major:</label>
    <div class="col-sm-10"> 
      <input type="text" class="form-control" name="major" id="major" placeholder="Enter major">
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="year">Year of Entry:</label>
    <div class="col-sm-10"> 
      <input type="text" class="form-control" name="year" id="year" placeholder="Enter year of entry">
    </div>
  </div>
  <div class="form-group"> 
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default" name="createAcc" id="createAcc">Signup</button>
    </div>
  </div>
</form>
</div>
&nbsp;
<div class="col-sm-6">
<h3>Login</h3>
<form class="form-horizontal" role="form" name="loginForm" id="loginForm" onsubmit="return validateForm2()" action="studentLoginServ" method="post">
  <div class="form-group">
    <label class="control-label col-sm-2" for="stuID">Student ID:</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="stuID" id="stuID" placeholder="Enter student ID">
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="loginPwd">Password:</label>
    <div class="col-sm-10"> 
      <input type="password" class="form-control" name="loginPwd" id="loginPwd" placeholder="Enter password">
    </div>
  </div>
  <div class="form-group"> 
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default" name="login" id="login">Login</button>
    </div>
  </div>
</form>
</div>
</div>
</body>
</html>