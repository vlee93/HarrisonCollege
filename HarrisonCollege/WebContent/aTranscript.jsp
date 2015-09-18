<jsp:include page="header.jsp"></jsp:include>
<div class="container">
	<form class="form" role="form" action="aTranscript" method="get">
		<label style="display: inline;">Enter student ID: </label> <input
			class="form-control" name="student_id" placeholder="student id"
			style="margin-right: 10px; width: 20%; display: inline;" required />
		<button class="form-control" type="submit" value="View"
			style="margin-left: 50px; display: inline-block; width: 10%; display: inline;">View</button>

		<br> ${alert}
	</form>
</div>
<br />

${addClassError}
${addClassMsg}
${scheduleMsg}
${transMsg}

</body>
</html>