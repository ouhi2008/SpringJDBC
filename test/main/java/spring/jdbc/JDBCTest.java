package spring.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import spring.pojo.Employee;

public class JDBCTest {

	private ApplicationContext ctx =null;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	{
		ctx = new ClassPathXmlApplicationContext("beans.xml");
		jdbcTemplate = (JdbcTemplate)ctx.getBean("jdbcTemplate");
		namedParameterJdbcTemplate = (NamedParameterJdbcTemplate)ctx.getBean(NamedParameterJdbcTemplate.class);
	}
	

	@Test
	public void testDS() throws SQLException {
		DataSource ds = ctx.getBean(DataSource.class);
		System.out.println(ds.getConnection());
	}
	
	@Test
	public void testUpdate() throws SQLException {
		String sql = "update employees set last_name=? where id = ?";
		jdbcTemplate.update(sql,"Jack",5);
	}

	@Test
	public void testBatchUpdate() throws SQLException {
		String sql = "insert into employees(last_name,email,dept_id) values(?,?,?)";
		List<Object[]> batchArgs = new ArrayList<>();
		batchArgs.add(new Object[]{"AA","aa@163.com",1});
		batchArgs.add(new Object[]{"BB","bb@163.com",2});
		batchArgs.add(new Object[]{"CC","cc@163.com",3});
		batchArgs.add(new Object[]{"DD","dd@163.com",3});
		batchArgs.add(new Object[]{"EE","ee@163.com",2});
		jdbcTemplate.batchUpdate(sql,batchArgs);
	}
	
	/**
	 * attention: Need invoke queryForObject(String sql, RowMapper<Employee> rowMapper, Object... args) 
	 * @throws SQLException
	 * not support link-property dept_id as \"department.id\"
	 */
	@Test
	public void testQueryForObject() throws SQLException {
		String sql = "select id, last_name lastName, email, dept_id as \"department.id\" from employees where id = ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		Employee emp = jdbcTemplate.queryForObject(sql, rowMapper,1);
		System.out.println(emp);
	}
	
	@Test
	public void testQueryForObject2() throws SQLException {
		String sql = "select count(id) from employees ";
		long count = jdbcTemplate.queryForObject(sql, Long.class);
		System.out.println(count);
	}
	
	/**
	 * attention: Not invoke queryForList
	 * @throws SQLException
	 */
	@Test
	public void testQueryForList() throws SQLException {
		String sql = "select id, last_name lastName, email from employees where id > ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		List<Employee> list = jdbcTemplate.query(sql, rowMapper,5);
		System.out.println(list);
	}

	
	/**
	 * could name parameter,use ":VARIABLE"
	 * NEEDN'T focus on parameter position, just focus on name
	 * @throws SQLException
	 */
	@Test
	public void testNamedParameterJdbcTemplate() throws SQLException {
		String sql = "insert into employees(last_name,email,dept_id) values(:ln,:email,:deptid)";
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("ln", "FF");
		paramMap.put("email", "ff@163.com");
		paramMap.put("deptid", 2);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	@Test
	public void testNamedParameterJdbcTemplate2() throws SQLException {
		String sql = "insert into employees(last_name,email,dept_id) values(:lastName,:email,:deptId)";
		Employee emp = new Employee();
		emp.setLastName("XYZ");
		emp.setEmail("xyz@163.com");
		emp.setDeptId(3);
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(emp);
		namedParameterJdbcTemplate.update(sql, paramSource);
	}
}
