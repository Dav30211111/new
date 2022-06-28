package com.authority_function.model;

import java.util.*;


public interface Authority_FunctionDAO_interface {
	
    public void insert(FunctionVO vOFunction);
    public void update(FunctionVO vOFunction);
    public void delete(Integer function_id);
    public FunctionVO findByPrimaryKey(Integer function_id);
    public List<FunctionVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}


