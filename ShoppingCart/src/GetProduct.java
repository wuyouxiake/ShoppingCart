import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import postTools.DBUtil;
import model.Product;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/GetProduct")
public class GetProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select p from Product p order by p.id";
		TypedQuery<Product> q = em.createQuery(qString, Product.class);
		List<Product> productList;
		try{
			productList=q.getResultList();
			if(productList ==null ||productList.isEmpty()){
				productList=null;
			}
			
		
		String fullList = "";
		for(int i=0;i<productList.size();i++)
        {
            fullList+="<li class=\"list-group-item\"><img src=\""+productList.get(i).getPhotolink()
            		+"\" style=\"width:120px;height:120px\"> <a href=\"GetProductDetail?p_name="
            		+productList.get(i).getPName().replace(" ", "%20")+"\">"+productList.get(i).getPName()+"</a><br>  "
            		+productList.get(i).getDescription()+"<br><br>"
            		+"<b>Price: $"+productList.get(i).getPrice()+"</b><br>"
            		+"<a href=\"AddToCart?productid="+productList.get(i).getId()+"\"><b>Add To Cart</b></a>"
            		+"</li>";
            
        }
		
		// Set response content type
				response.setContentType("text/html");

				request.setAttribute("fullList", fullList);
				
				getServletContext().getRequestDispatcher("/list.jsp")
						.forward(request, response);
				fullList = "";
				
		}catch(Exception e){
		}finally{
			em.close();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}