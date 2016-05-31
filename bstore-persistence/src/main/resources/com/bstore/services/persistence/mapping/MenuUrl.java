// default package
// Generated 31-may-2016 0:05:40 by Hibernate Tools 4.3.1

/**
 * MenuUrl generated by hbm2java
 */
public class MenuUrl implements java.io.Serializable {

	private Short id;
	private Menu menu;
	private String url;
	private Integer urlsOrder;

	public MenuUrl() {
	}

	public MenuUrl(Menu menu, String url) {
		this.menu = menu;
		this.url = url;
	}

	public MenuUrl(Menu menu, String url, Integer urlsOrder) {
		this.menu = menu;
		this.url = url;
		this.urlsOrder = urlsOrder;
	}

	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getUrlsOrder() {
		return this.urlsOrder;
	}

	public void setUrlsOrder(Integer urlsOrder) {
		this.urlsOrder = urlsOrder;
	}

}