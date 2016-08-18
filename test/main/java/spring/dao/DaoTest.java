package spring.dao;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class DaoTest {


	private ApplicationContext ctx =null;
	private EmployeeDao employeeDao;
	private DepartmentDao departmentDao;
	{
		ctx = new ClassPathXmlApplicationContext("beans.xml");
		employeeDao = (EmployeeDao)ctx.getBean("employeeDao");
		departmentDao = (DepartmentDao)ctx.getBean("departmentDao");
	}
	
	@Test
	public void testEmployeeDao() throws SQLException {
		System.out.println(employeeDao.get(2));
	}
	@Test
	public void testDepartmentDao() throws SQLException {
		System.out.println(departmentDao.get(3));
	}
}
