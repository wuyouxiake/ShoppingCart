import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import postTools.DBUtil;
import model.Product;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/GetSummary")
public class GetSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSummary() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		//String productid = request.getParameter("productid");
		List<Product> cartList;
		cartList=(List<Product>) session.getAttribute("cartList");
		String fullList = "";
		double subtotal=0;
		for(int i=0;i<cartList.size();i++)
        {
            fullList+="<li class=\"list-group-item\"><img src=\""+cartList.get(i).getPhotolink()
            		+"\" style=\"width:120px;height:120px\"> <a href=\"GetProductDetail?p_name="
            		+cartList.get(i).getPName().replace(" ", "%20")+"\">"+cartList.get(i).getPName()+"</a><br>  "
            		+"<b>Price: $"+cartList.get(i).getPrice()+"</b><br>"
            		+"</li>";
            subtotal+=cartList.get(i).getPrice();
        }
		
		// Set response content type
				response.setContentType("text/html");

				request.setAttribute("fullList", fullList);
				request.setAttribute("subtotal", subtotal);
				
				getServletContext().getRequestDispatcher("/summary.jsp")
						.forward(request, response);
				fullList = "";
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}