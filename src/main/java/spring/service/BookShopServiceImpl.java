package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.BookShopDao;
import spring.exception.UserAccountException;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {
	@Autowired
	private BookShopDao bookShopDao;
	
	//1.Default propagation is REQUIRED : tx1 start -> tx1 end
	//REQUIRES_NEW :  tx1 start-> tx2 start -> tx2 end ->tx1 end
	//2.Use isolateion specify Isolation, common value is READ_COMMITTED
	//3.Spring annotation transaction will all be rollbacked by default, use property noRollbackFor to specify
//	@Transactional(propagation=Propagation.REQUIRES_NEW,
//			isolation=Isolation.READ_COMMITTED,
//			noRollbackFor={UserAccountException.class}
//			)
	//4.Use property readOnly to specify whether is read transaction (only read data ,no update data)
	//5.Use property timeout to specify transaction cost time before force rollback. Unit is second
	@Transactional(propagation=Propagation.REQUIRES_NEW,
			isolation=Isolation.READ_COMMITTED,
			readOnly=false,
			timeout=3
			)
	@Override
	public void purchase(String username, String isbn) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int price = bookShopDao.findbookPriceByIsbn(isbn);
		bookShopDao.updateBookStock(isbn);
		bookShopDao.updateUserAccount(username, price);
	}

}
