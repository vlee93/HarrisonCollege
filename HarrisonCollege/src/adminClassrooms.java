

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HClassroom;
import customTools.DBUtil;

/**
 * Servlet implementation class adminClassrooms
 */
@WebServlet("/adminClassrooms")
public class adminClassrooms extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String adminRoomMsg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminClassrooms() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		adminRoomMsg = "";
			
		adminRoomMsg += "<div class=\"container\"><h3>Create Classroom</h3><form class=\"form-horizontal\" role=\"form\" name=\"addRoom\" id=\"addRoom\" onsubmit=\"return validateForm2()\" action=\"adminClassrooms\" method=\"post\">"
				+ "<div class=\"form-group\"><label for=\"bldgname\">Building Name:</label><input type=\"text\" class=\"form-control\" name=\"bldgname\" id=\"bldgname\"></div>"
				+ "<div class=\"form-group\"><label for=\"roomno\">Room #:</label><input type=\"text\" class=\"form-control\" name=\"roomno\" id=\"roomno\"></div>"
				+ "<div class=\"form-group\"><label for=\"capacity\">Capacity:</label><input type=\"text\" class=\"form-control\" name=\"capacity\" id=\"capacity\"></div>"
				+ "<div class=\"form-group\"><label for=\"avail\">Availability:</label><br /><label class=\"radio-inline\"><input type=\"radio\" name=\"avail\" id= \"avail\" value=\"1\">Yes</label><label class=\"radio-inline\"><input type=\"radio\" name=\"avail\" id = \"avail\" value = \"0\">No</label></div>"
				+ "<button type=\"submit\" class=\"btn btn-default\" name=\"addR\" id=\"addR\">Submit</button></form>";
		
		adminRoomMsg += "<br /><h3>Classrooms</h3><table class=\"table\"><thead><tr><th>Building Name</th><th>Room #</th><th>Capacity</th><th>Available</th><th>Edit</th></tr></thead><tbody>";
		List<HClassroom> classroom = DBUtil.createQuery("SELECT h FROM HClassroom h", HClassroom.class).getResultList();
		for (int i = 0; i < classroom.size(); i++) {
			String avail = "";
			if (classroom.get(i).getAvailable() == 1) {
				avail = "yes";
			} else {
				avail = "no";
			}
			adminRoomMsg += "<tr><td>" + classroom.get(i).getBldgName() + "</td><td>" + classroom.get(i).getRoomNo() + "</td><td>" + classroom.get(i).getMaxCapacity() + "</td><td>" + avail + "</td><td><a href=\"adminEditDetails?roomid=" + classroom.get(i).getRoomId() + "\" class=\"btn btn-info\" role=\"button\">Edit</a></td></tr>";
		}
		adminRoomMsg += "</tbody></table></div>";
		request.setAttribute("adminRoomMsg", adminRoomMsg);
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("addR") != null) {
			String bldgname = request.getParameter("bldgname");
			String roomno = request.getParameter("roomno");
			int capacity = Integer.parseInt(request.getParameter("capacity"));
			int available = Integer.parseInt(request.getParameter("avail"));
			HClassroom newroom = new HClassroom();
			newroom.setAvailable(available);
			newroom.setBldgName(bldgname);
			newroom.setRoomNo(roomno);
			newroom.setMaxCapacity(capacity);
			DBUtil.addToDB(newroom);
			response.sendRedirect("/HarrisonCollege/adminClassrooms");
		}
	}

}
