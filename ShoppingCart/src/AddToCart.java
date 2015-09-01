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
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String productid = request.getParameter("productid");
		List<Product> cartList;
		if(session.getAttribute("cartList")==null){
			cartList= new ArrayList();
		}else{
			cartList=(List<Product>) session.getAttribute("cartList");
		}
		Product prod = new Product();
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select p from Product p where p.id = ?1";
		TypedQuery<Product> q = em.createQuery(qString, Product.class);
		q.setParameter(1, Long.parseLong(productid));
		
		try{
			prod=q.getSingleResult();
			cartList.add(prod);
			String alert = "Added to cart!";
			request.setAttribute("alert", alert);
			session.setAttribute("cartList", cartList);
		getServletContext().getRequestDispatcher("/successful.jsp")
		.forward(request, response);
		}catch(Exception e){
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}