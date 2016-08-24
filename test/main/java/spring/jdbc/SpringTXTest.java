package spring.jdbc;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.dao.BookShopDao;
import spring.service.BookShopService;
import spring.service.Cashier;

public class SpringTXTest {
	private ApplicationContext ctx =null;
	private BookShopDao bookShopDao = null;
	private BookShopService bookShopService =null;
	private Cashier cashier =null;
	{
		ctx = new ClassPathXmlApplicationContext("beans-tx.xml");
		bookShopDao = (BookShopDao)ctx.getBean("bookShopDao");
		bookShopService = (BookShopService)ctx.getBean("bookShopService");
		cashier = (Cashier)ctx.getBean(Cashier.class);
	}
	
	
	@Test
	public void testPurchase() {
		bookShopService.purchase("AA", "1001");
	}
	
	@Test
	public void testTransactionPropagation() {
		cashier.checkout("AA",Arrays.asList("1001","1002"));
	}
}
