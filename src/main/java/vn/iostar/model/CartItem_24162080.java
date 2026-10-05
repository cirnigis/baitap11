package vn.iostar.model;

import java.math.BigDecimal;

public class CartItem_24162080 {

	private int cartId;
	private int userId;
	private int bookId;
	private int quantity;

	private String title;
	private BigDecimal price;
	private String coverImage;
	private int stock;

	public CartItem_24162080() {
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public BigDecimal getSubtotal() {
		if (price == null) {
			return BigDecimal.ZERO;
		}

		return price.multiply(BigDecimal.valueOf(quantity));
	}
}