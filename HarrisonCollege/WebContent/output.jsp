<jsp:include page="./header.jsp"/>
<script type="text/javascript">
function validateForm1() {
    var a = document.forms["addCourse"]["subjcode"].value;
    var b = document.forms["addCourse"]["coursenum"].value;
    var c = document.forms["addCourse"]["courname"].value;
    var d = document.forms["addCourse"]["descrip"].value;
    var e = document.forms["addCourse"]["crednum"].value;
    var f = document.forms["addCourse"]["dept"].value;
    var g = document.forms["addCourse"]["avail"].value;
    if (a == null || a == "" || b == null || b == "" || c == null || c == "" || d == null || d == "" || e == null || e == "" || f == null || f == "" || g == null || g == "") {
        alert("All fields must be filled out.");
        return false;
    }
}

function validateForm2() {
    var a = document.forms["addRoom"]["bldgname"].value;
    var b = document.forms["addRoom"]["roomno"].value;
    var c = document.forms["addRoom"]["capacity"].value;
    var d = document.forms["addRoom"]["avail"].value;
    if (a == null || a == "" || b == null || b == "" || c == null || c == "" || d == null || d == "") {
        alert("All fields must be filled out.");
        return false;
    }
}

function validateForm3() {
    var a = document.forms["addMajor"]["majorname"].value;
    var b = document.forms["addMajor"]["dept"].value;
    var c = document.forms["addMajor"]["avail"].value;
    if (a == null || a == "" || b == null || b == "" || c == null || c == "") {
        alert("All fields must be filled out.");
        return false;
    }
}

function validateForm4() {
    var a = document.forms["addDept"]["deptname"].value;
    var b = document.forms["addDept"]["avail"].value;
    if (a == null || a == "" || b == null || b == "") {
        alert("All fields must be filled out.");
        return false;
    }
}
</script>

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
${deptRevMsg}
${courseRevMsg}
${instRevMsg}
${classRevMsg}

</body>
</html>