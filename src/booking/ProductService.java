package booking;

public class ProductService {
	private int productServiceId;
	private String productName;
	private String productService_desc;
	private double price;
	
	public ProductService(int productServiceId, String productName, String productService_desc, double price) {
		super();
		this.productServiceId = productServiceId;
		this.productName = productName;
		this.productService_desc = productService_desc;
		this.price = price;
	}

	public int getProductServiceId() {
		return productServiceId;
	}

	public void setProductServiceId(int productServiceId) {
		this.productServiceId = productServiceId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductService_desc() {
		return productService_desc;
	}

	public void setProductService_desc(String productService_desc) {
		this.productService_desc = productService_desc;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
