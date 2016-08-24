package spring.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import spring.dao.BookShopDao;
import spring.exception.BookStockException;
import spring.exception.UserAccountException;

public class BookShopDaoImpl implements BookShopDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate=jdbcTemplate;
	}
	
	@Override
	public int findbookPriceByIsbn(String isbn) {
		String sql = "select price from book where isbn = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, isbn);
	}

	@Override
	public void updateBookStock(String isbn) {
		// check
		String checkSql = "select stock from book_stock where isbn = ?";
		int stock = jdbcTemplate.queryForObject(checkSql, Integer.class, isbn);
		if (stock == 0) {
			throw new BookStockException("Stock isn't enough!");
		}
		String sql = "update book_stock set stock=stock-1 where isbn = ?";
		jdbcTemplate.update(sql, isbn);
	}

	@Override
	public void updateUserAccount(String username, int price) {
		// check
		String checkSql = "select balance from account where username = ?";
		int balance = jdbcTemplate.queryForObject(checkSql, Integer.class, username);
		if (balance < price) {
			throw new UserAccountException("Balace isn't enough!");
		}

		String sql = "update account set balance=balance-? where username = ?";
		jdbcTemplate.update(sql, price, username);
	}

}
