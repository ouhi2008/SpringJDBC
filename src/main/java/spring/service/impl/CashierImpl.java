package spring.service.impl;

import java.util.List;

import spring.service.BookShopService;
import spring.service.Cashier;

public class CashierImpl implements Cashier {

	private BookShopService bookShopService;
	
	public void setBookShopService(BookShopService bookShopService){
		this.bookShopService=bookShopService;
	}
	
	@Override
	public void checkout(String username, List<String> isbns) {
		for(String isbn:isbns){
			bookShopService.purchase(username, isbn);
		}
	}

}