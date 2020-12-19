package org.javaboy.meeting.mapper;

import org.apache.ibatis.annotations.Param;
import org.javaboy.meeting.model.Department;

import java.util.List;


public interface DepartmentMapper {

    Department getDepById(Integer id);

    List<Department> getAllDeps();

    Integer adddepartment(String departmentname);

    Department getDepByName(String departmentname);

    Integer deletedep(Integer departmentid);

    Integer updatedep(@Param("id") Integer id, @Param("name") String name);

}
