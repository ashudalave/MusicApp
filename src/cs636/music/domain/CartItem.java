package cs636.music.domain;

import java.math.BigDecimal;

/**
 * CartItem: memory-only object for item in the cart
 * suitable to being in the presentation layer
 * i.e., no domain objects, just a product id
 * Data is moved to a LineItem object in checkout
 * For API calls, see related POJO service.data/CartItemData.java
 *
 */
public class CartItem {

	private long productId;
	private int quantity;
	
	// added to escape the java not found property exception
	private String productCode;
	private String description;
	private BigDecimal price;
	
	// no-args constructor, to be proper JavaBean
	public CartItem() {}
	
	public CartItem (long productId, int quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}
	
	public long getProductId() {
		return productId;
	}

	public void setProductId(long id) {
		this.productId = id;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}
	
	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	
	
	

}
