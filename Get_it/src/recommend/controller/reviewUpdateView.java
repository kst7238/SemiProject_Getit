package product.controller.recommend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;

/**
 * Servlet implementation class reviewUpdateView
 */
@WebServlet("/recommend/review/update")
public class reviewUpdateView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reviewUpdateView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
//		System.out.println(reviewNo);
		String pCode = request.getParameter("pCode");
		String pFilename = request.getParameter("pFilename");
//		System.out.println(pCode);
		request.setAttribute("pFilename", pFilename);
		request.setAttribute("reviewNo", reviewNo);
		request.setAttribute("pCode", pCode);
		request.getRequestDispatcher("/WEB-INF/views/recommend/recommendUpdate.jsp").forward(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
