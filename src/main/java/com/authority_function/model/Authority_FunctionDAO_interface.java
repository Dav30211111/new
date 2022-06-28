package com.authority_function.model;

import java.util.*;


public interface Authority_FunctionDAO_interface {
	
    public void insert(FunctionVO vOFunction);
    public void update(FunctionVO vOFunction);
    public void delete(Integer function_id);
    public FunctionVO findByPrimaryKey(Integer function_id);
    public List<FunctionVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}


