package com.employee.model;

import java.util.*;

public interface EmployeeDAO_interface {
          public void insert(EmployeeVO employeeVO);
          public void update(EmployeeVO employeeVO);
          public void delete(Integer emp_no);
          public EmployeeVO findByPrimaryKey(Integer emp_no);
          public List<EmployeeVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
