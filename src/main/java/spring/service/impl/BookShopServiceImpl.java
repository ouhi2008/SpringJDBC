package spring.service.impl;

import spring.dao.BookShopDao;
import spring.service.BookShopService;

public class BookShopServiceImpl implements BookShopService {
	private BookShopDao bookShopDao;
	
	public void setBookShopDao(BookShopDao bookShopDao){
		this.bookShopDao=bookShopDao;
	}
	
	@Override
	public void purchase(String username, String isbn) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int price = bookShopDao.findbookPriceByIsbn(isbn);
		bookShopDao.updateBookStock(isbn);
		bookShopDao.updateUserAccount(username, price);
	}

}
