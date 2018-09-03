// User session bean contains information for the user across the web pages
package cs636.music.presentation.web;

import cs636.music.domain.Cart;
import cs636.music.domain.Product;
import cs636.music.domain.User;

public class UserBean {

	private User user; 
	private Product product;
	private Cart cart;

	public UserBean() {}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the cart
	 */
	public Cart getCart() {
		return cart;
	}

	/**
	 * @param cart the cart to set
	 */
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	 
}

