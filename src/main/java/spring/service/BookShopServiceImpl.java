package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.BookShopDao;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {
	@Autowired
	private BookShopDao bookShopDao;
	
	@Transactional
	@Override
	public void purchase(String username, String isbn) {
		int price = bookShopDao.findbookPriceByIsbn(isbn);
		bookShopDao.updateBookStock(isbn);
		bookShopDao.updateUserAccount(username, price);
	}

}
