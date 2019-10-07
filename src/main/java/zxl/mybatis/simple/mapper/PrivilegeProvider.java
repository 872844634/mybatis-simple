package zxl.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class PrivilegeProvider {
    public String selectPrivilegeById(@Param("id") long id){
//        return new SQL(){{
//            SELECT(" id, privilege_name, privilege_url ");
//            FROM(" sys_privilege ");
//            WHERE("id = #{id}");
//        }}.toString();
        return " select id, privilege_name, privilege_url  from sys_privilege where id = #{id} ";
        //或者
        // return ” select id, privilege_name, privilege_url from sys_privilege where id = #{id }”;
    }
}
