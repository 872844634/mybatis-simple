package zxl.mybatis.simple.mapper;

import org.apache.ibatis.annotations.*;
import zxl.mybatis.simple.model.SysPrivilege;
import zxl.mybatis.simple.model.SysRole;

import java.util.List;

@CacheNamespaceRef(RoleMapper.class)
public interface RoleMapper {
    /**
     * 注解使用方法
     * @param
     * @return
     */
    //1.使用驼峰命名法配制
    @Select({"select * from  sys_role "," where id = #{id} "})
    SysRole selectById(Long id);
    //2.使用属性与列映射配制
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "roleName",column = "role_name"),
            @Result(property = "enabled",column = "enabled"),
            @Result(property = "createBy",column = "create_by"),
            @Result(property = "createTime",column = "create_time")
    })
    @Select("select id ,role_name,enabled,create_by,create_time from sys_role where id = #{id} ")
    SysRole selectById2(Long id);

    //3.使用全局映射配制，每个@Select上加@ResultMap并与id对应，即全局通用，myBatis3.3.1版本开始就可以用了
    //此方法单独使用，@Results必须和@Select先用在一起，不然会找不到这个id
    @Results(id="roleResultMap",value={
            @Result(property = "id",column = "id",id = true),
            @Result(property = "roleName",column = "role_name"),
            @Result(property = "enabled",column = "enabled"),
            @Result(property = "createBy",column = "create_by"),
            @Result(property = "createTime",column = "create_time")
    })
    @Select("select id ,role_name,enabled,create_by,create_time from sys_role where id = #{id} ")
    SysRole selectById3(Long id);


    @ResultMap("roleResultMap")
    @Select({"select * from sys_role"})
    List<SysRole> selectAllRole();

    @Insert({
            "insert into sys_role values(#{id},#{roleName},#{enabled},#{createBy},#{createTime,jdbcType=TIMESTAMP})"
    })
    int insertRole (SysRole sysRole);

    @Insert({
            "insert into sys_role(role_name,enabled,create_by,create_time) values(#{roleName},#{enabled},#{createBy},#{createTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertRole2(SysRole sysRole);


    @Insert({
            "insert into sys_role(role_name,enabled,create_by,create_time) values(#{roleName},#{enabled},#{createBy},#{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()",
    keyProperty = "id",
            resultType = Long.class,
            before = false
    )
    int insertRole3(SysRole sysRole);

    @Update({
            " update sys_role set ",
            " role_name = #{roleName},",
            " enabled=#{enabled},",
            " create_by=#{createBy},",
            " create_time=#{createTime,jdbcType=TIMESTAMP} ",
            " where id = #{id}"
    })
    int updateRoleById(SysRole sysRole);

    @Delete("delete from sys_role where id = #{id} ")
    int deleteRoleById(Long id);

    List<SysRole> selectAllRoleAndPrivileges();
}
