package com.employee.model;

import java.util.List;

public class EmployeeService {
	
	private EmployeeDAO_interface dao;
	
	public EmployeeService() {
		dao = new EmployeeJDBCDAO();
	}
	
	public EmployeeVO addEmployee (Integer job_id, String emp_name, Integer emp_status) {
		
		EmployeeVO employeeVO = new EmployeeVO();
		
		employeeVO.setJob_id(job_id);
		employeeVO.setEmp_name(emp_name);
		employeeVO.setEmp_status(emp_status);
		dao.insert(employeeVO);
		
		return employeeVO;
	}

	public EmployeeVO updateEmployee(Integer emp_no, Integer job_id, String emp_name, Integer emp_status) {
		
		EmployeeVO employeeVO = new EmployeeVO();
		
		employeeVO.setEmp_no(emp_no);
		employeeVO.setJob_id(job_id);
		employeeVO.setEmp_name(emp_name);
		employeeVO.setEmp_status(emp_status);
		dao.update(employeeVO);
		
		return employeeVO;
	}
	
	public void deleteEmployee(Integer emp_no) {
		dao.delete(emp_no);
	}
	
	public EmployeeVO getOneEmployee(Integer emp_no) {
		return dao.findByPrimaryKey(emp_no);
	}
	
	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}
}
