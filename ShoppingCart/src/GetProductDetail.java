import java.io.IOException;
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
@WebServlet("/GetProductDetail")
public class GetProductDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProductDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p_name = request.getParameter("p_name");
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
//		
		String qString = "select p from Product p where p.pName = ?1";
		TypedQuery<Product> q = em.createQuery(qString, Product.class);
		q.setParameter(1, p_name);
		List<Product> productDetail;
		try{
			productDetail=q.getResultList();
			if(productDetail ==null ||productDetail.isEmpty()){
				productDetail=null;
			}
			
		String fullList = "";
		for(int i=0;i<productDetail.size();i++)
        {
			fullList="<li class=\"list-group-item\">Product Name: "+productDetail.get(i).getPName()+"</li>"
					+"<li class=\"list-group-item\">Photo: <img src=\""+productDetail.get(i).getPhotolink()+
					"\" style=\"width:120px;height:120px\"></li>"
					+"<li class=\"list-group-item\">Description: "+productDetail.get(i).getDescription()+"</li>"
					+"<li class=\"list-group-item\">Price: $"+productDetail.get(i).getPrice()+"</li>"
					+"<li class=\"list-group-item\"><a href=\"AddToCart?productid="+productDetail.get(i).getId()
					+"\"><b>Add To Cart</b></a>"
					+"</li><br><br>";
            
        }
		

		//Set response content type
				response.setContentType("text/html");

				request.setAttribute("fullList", fullList);
				getServletContext().getRequestDispatcher("/profile.jsp")
						.forward(request, response);
				fullList = "";
				productDetail.clear();
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