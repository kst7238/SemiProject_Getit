package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.service.MemberService;
import member.vo.Member;

/**
 * Servlet implementation class shoppingBagPayServlet
 */
@WebServlet("/memeber/shoppingPay")
public class shoppingBagPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shoppingBagPayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 장바구니 거치고 결제
		 HttpSession session = request.getSession(); 
		 String userId = ((Member)session.getAttribute("member")).getMemberId();
		 
		 StringBuilder sb = new StringBuilder();
		 String otherAddr = request.getParameter("address");
		 String otherDetailAddr = request.getParameter("detailAddress");
		 String otherZipcode = request.getParameter("zipcode");
		 
		 if ( otherAddr == "" && otherDetailAddr == "" && otherZipcode == "") {
			 String address=((Member)session.getAttribute("member")).getAddress();
			 String detailAddress =((Member)session.getAttribute("member")).getDetailAddress();
			 String zipcode =((Member)session.getAttribute("member")).getZipcode();
			// 배송주소
			 sb.append(address);
			 sb.append(detailAddress);
			 sb.append(zipcode);
		 }else {
			 sb.append(otherAddr);
			 sb.append(otherDetailAddr);
			 sb.append(otherZipcode);
		 }
		 
		 
		 
		 // 상품코드
		 String [] pCode = request.getParameterValues("checkRow");
		 // 총 금액
		 int allPrice = Integer.parseInt(request.getParameter("allPrice"));
		 // 배송 메세지
		 String dMessage = request.getParameter("dMessage");
		 // 결과값 받기
		 int result = 0;
		 for (int i = 0; i < pCode.length; i++) {
			 if(i==0) {
				 result = new MemberService().shoppingPayInsert(sb,userId,pCode[i],allPrice,dMessage);
			 }else {
				 result = new MemberService().shoppingPayInsertCurr(sb,userId,pCode[i],allPrice,dMessage);
			 }
		 }
		 if(result>0) {
			
			 response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('결제가 완료 되었습니다.'); location.href='/WEB-INF/views/member/orderInfo.jsp';</script>"); 
				writer.close();
			 
		 }else {
			 response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('결제 실패! 다시 시도해주세요.'); location.href='/basket.jsp';</script>"); 
				writer.close();
			 
			 
		 }
		 
		 
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
