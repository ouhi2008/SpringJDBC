package spring.dao;

public interface BookShopDao {
	public int findbookPriceByIsbn(String isbn);
	// count-1
	public void updateBookStock(String isbn);
	//balance-price
	public void updateUserAccount(String username,int price);
}
