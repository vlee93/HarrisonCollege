<jsp:include page="./header.jsp" />
${loggedout}

<style>
.carousel-inner > .item > img, .carousel-inner > .item > a > img {
	width: 70%;
	margin: auto;
}
</style>

<div class="container">
	<h1 style="color: #1E6912" align="center">Welcome to Harrison College!</h1>
	<div id="myCarousel" class="carousel slide" data-ride="carousel"
		style="width: 65%; margin-left: auto; margin-right: auto">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
			<li data-target="#myCarousel" data-slide-to="3"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img
					src="http://static.squarespace.com/static/54372714e4b02e62e3f5cb96/t/54ae32b8e4b07588aa08d126/1420702392679/Harvard.jpg"
					alt="Building1" style="width: 600px; height: 440px;">
				<div class="carousel-caption">
					<h3>BUILDINGS!!!!!</h3>
				</div>
			</div>

			<div class="item">
				<img
					src="http://41.media.tumblr.com/tumblr_m2jubiWLnw1rtlgyyo1_1280.jpg"
					alt="Building2" style="width: 600px; height: 440px;">
				<div class="carousel-caption">
					<h3>Other Buildings</h3>
				</div>
			</div>

			<div class="item">
				<img
					src="http://www.csn.edu/Images/Diversity/photos/College-Group.jpg"
					alt="Student" style="width: 600px; height: 440px;">
				<div class="carousel-caption">
					<h3>Students</h3>
				</div>
			</div>

			<div class="item">
				<img
					src="http://www.cbc.ca/sports-content/football/opinion/assets_c/2012/09/mckenzie_620-thumb-620xauto-225813.jpg"
					alt="sport" style="width: 600px; height: 440px;">
				<div class="carousel-caption">
					<h3>Sports</h3>
				</div>
			</div>
		</div>

		<!-- Left and right controls -->
		<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			<span class="sr-only">Previous</span>
		</a>
		<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
</div>


</body>
</html>