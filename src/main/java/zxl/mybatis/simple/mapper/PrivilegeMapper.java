package zxl.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import zxl.mybatis.simple.model.SysPrivilege;

public interface PrivilegeMapper {

//    @ResultMap("BaseResultMap")
    @SelectProvider(type = PrivilegeProvider.class,method = "selectPrivilegeById")
    SysPrivilege selectPrivilegeById(@Param("id")Long id);
}
