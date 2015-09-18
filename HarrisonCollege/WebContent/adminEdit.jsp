<jsp:include page="./header.jsp"/>
<script type="text/javascript">
function validateForm1() {
    var a = document.forms["editCourse"]["subjcode"].value;
    var b = document.forms["editCourse"]["coursenum"].value;
    var c = document.forms["editCourse"]["courname"].value;
    var d = document.forms["editCourse"]["descrip"].value;
    var e = document.forms["editCourse"]["crednum"].value;
    var f = document.forms["editCourse"]["dept"].value;
    var g = document.forms["editCourse"]["avail"].value;
    if (a == null || a == "" || b == null || b == "" || c == null || c == "" || d == null || d == "" || e == null || e == "" || f == null || f == "" || g == null || g == "") {
        alert("All fields must be filled out.");
        return false;
    }
}

function validateForm2() {
    var a = document.forms["editRoom"]["bldgname"].value;
    var b = document.forms["editRoom"]["roomno"].value;
    var c = document.forms["editRoom"]["capacity"].value;
    var d = document.forms["editRoom"]["avail"].value;
    if (a == null || a == "" || b == null || b == "" || c == null || c == "" || d == null || d == "") {
        alert("All fields must be filled out.");
        return false;
    }
}

function validateForm3() {
    var a = document.forms["editMajor"]["majorname"].value;
    var b = document.forms["editMajor"]["dept"].value;
    var c = document.forms["editMajor"]["avail"].value;
    if (a == null || a == "" || b == null || b == "" || c == null || c == "") {
        alert("All fields must be filled out.");
        return false;
    }
}

function validateForm4() {
    var a = document.forms["editDept"]["deptname"].value;
    var b = document.forms["editDept"]["avail"].value;
    if (a == null || a == "" || b == null || b == "") {
        alert("All fields must be filled out.");
        return false;
    }
}
</script>

${adminEditErr}
${adminEdit}

</body>
</html>