package spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.pojo.Employee;

public class EmployeeDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public Employee get(Integer id){
		String sql = "select id, last_name lastName, email, dept_id as \"department.id\" from employees where id = ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		Employee emp = jdbcTemplate.queryForObject(sql, rowMapper,1);
		return emp;
	}
}
