package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PRODUCT database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator( name = "DemoCustomersSeq", sequenceName = "SEQ_GEN_IDENTITY", allocationSize = 1, initialValue = 1 )
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "DemoCustomersSeq" )
	private long id;

	private String description;

	@Column(name="P_NAME")
	private String pName;

	private String photolink;

	private long price;

	public Product() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPName() {
		return this.pName;
	}

	public void setPName(String pName) {
		this.pName = pName;
	}

	public String getPhotolink() {
		return this.photolink;
	}

	public void setPhotolink(String photolink) {
		this.photolink = photolink;
	}

	public long getPrice() {
		return this.price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

}