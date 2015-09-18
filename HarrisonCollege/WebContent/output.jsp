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
</script>
<!-- validation not done yet -->

${addClassError}
${depterror}
${addClassMsg}
${scheduleMsg}
${transMsg}
${adminCourMsg}
${adminRoomMsg}
${adminMajorMsg}
${adminDeptMsg}
${adminStfSuccess}
${adminStfMsg}
${studFMsg}
${insFMsg}
${courseFMsg}

</body>
</html>