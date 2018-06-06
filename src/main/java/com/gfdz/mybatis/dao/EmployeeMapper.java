package com.gfdz.mybatis.dao;

import com.gfdz.mybatis.entity.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    public Employee getById(Integer id);
    public void addEmp(Employee employee);
    public void updateEmp(Employee employee);
    public void deleteEmpById(Integer id);
    public Employee getEmpByIdAndLastName(@Param("id") Integer id,@Param("lastName") String lastName);
    public Employee getEmpByMap(Map<String,Object> map);
    public List<Employee> getEmpsByLastNameLike(String lastName);
    public Map<String,Object> getEmpByIdReturnMap(Integer id);

    //多条记录封装一个map：Map<Interger,Employee>:键是这条记录的主键，值是记录封装后的javaBean
    @MapKey("id")
    public Map<Integer, Employee> getEmpByLastNameLikeReturnMap(String lastName);

    public Employee getEmpAndDeptById(Integer id);
}
