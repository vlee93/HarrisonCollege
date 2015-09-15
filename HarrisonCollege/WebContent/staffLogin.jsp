<jsp:include page="./header.jsp"/>

<div class="container">
${error}
<div class="col-sm-6">
<h3>Create an Account</h3>
<form class="form-horizontal" role="form" name="createAccForm" id="createAccForm" action="staffLoginServ" method="post">
  <div class="form-group">
    <label class="control-label col-sm-2" for="staName">Name:</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="staName" id="staName" placeholder="Enter name">
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="staPwd">Password:</label>
    <div class="col-sm-10"> 
      <input type="password" class="form-control" name="staPwd" id="staPwd" placeholder="Enter password">
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="department">Department:</label>
    <div class="col-sm-10"> 
      <input type="text" class="form-control" name="department" id="department" placeholder="Enter department">
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="office">Office #:</label>
    <div class="col-sm-10"> 
      <input type="text" class="form-control" name="office" id="office" placeholder="Enter office number">
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
<form class="form-horizontal" role="form" name="loginForm" id="loginForm" action="staffLoginServ" method="post">
  <div class="form-group">
    <label class="control-label col-sm-2" for="staID">Staff ID:</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="staID" id="staID" placeholder="Enter staff ID">
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