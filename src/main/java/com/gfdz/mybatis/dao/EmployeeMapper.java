package com.gfdz.mybatis.dao;

import com.gfdz.mybatis.entity.Employee;

public interface EmployeeMapper {
   public Employee getEmpById(Integer id);
   public void addEmp(Employee employee);
   public void updateEmp(Employee employee);
   public void deleteEmpById(Integer id);
}
