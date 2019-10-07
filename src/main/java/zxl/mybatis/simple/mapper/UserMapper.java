package zxl.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Param;
import zxl.mybatis.simple.model.SysRole;
import zxl.mybatis.simple.model.SysRoleAndUser;
import zxl.mybatis.simple.model.SysRoleExtend;
import zxl.mybatis.simple.model.SysUser;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * 通过ID查询用户
     * @param id
     * @return
     */
    SysUser selectById(Long id);

    SysUser selectByUserInfo(String userInfo);

    /**
     * 查询全部用户
     * @return
     */
    List<SysUser> selectAll();

    /**
     * 查询全部用户，采用驼峰
     * @return
     */
    List<SysUser> selectAllMapUnderscore();

    /**
     * 根据用户ID获取角色信息
     * @param userId
     * @return
     */
    List<SysRole> selectRoleByUserId1(Long userId);

    /**
     * 根据用户ID获取角色信息,加用户名称
     * @param userId
     * @return
     */
    List<SysRoleExtend> selectRoleByUserId2(Long userId);

    /**
     * 根据用户ID获取角色信息,加用户信息
     * @param userId
     * @return
     */
    List<SysRoleAndUser> selectRoleByUserId3(Long userId);

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    int insert(SysUser sysUser);

    /**
     * 新增用户-使用useGeneratedKeys方式
     * @param sysUser
     * @return
     */
    int insert2(SysUser sysUser);

    /**
     * 新增用户-使用selectKey方式
     * @param sysUser
     * @return
     */
    int insert3(SysUser sysUser);

    /**
     * 根据主键更新
     * @param sysUser
     * @return
     */
    int updateById(SysUser sysUser);

    /**
     * 通过主键删除
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据用户id和角色的enabled状态获取用户
     * @param userId
     * @param enabled
     * @return
     */
    List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId") Long userId, @Param("enabled") Integer enabled);

    /**
     * 根据动态条件查询用户信息
     * @return
     */
    List<SysUser> selectByUser(SysUser sysUser);

    /**
     * 根据主键更新
     * @param sysUser
     * @return
     */
    int updateByIdSelective(SysUser sysUser);

    /**
     * 根据用户id或用户名查询
     * @param sysUser
     * @return
     */
    SysUser selectByIdOrUserName(SysUser sysUser);

    /**
     * 根据用户id集合查询
     * @param list
     * @return
     */
    List<SysUser> selectByIdList( List<Long> list);

    /**
     * 批量插入用户信息
     * @param userList
     * @return
     */
    int insertList(List<SysUser> userList);

    /**
     * 通过Map更新列
     * @param map
     * @return
     */
    int updateByMap(Map<String,Object> map);

    /**
     * 根据用户id获取用户的信息和用户的角色信息
     * @param id
     * @return
     */
    SysUser selectUserAndRoleById(Long id);

    /**
     * 根据用户id获取用户的信息和用户的角色信息
     * @param id
     * @return
     */
    SysUser selectUserAndRoleById2(Long id);

    /**
     * 根据用户id获取用户的信息和用户的角色信息
     * @param id
     * @return
     */
    SysUser selectUserAndRoleById3(Long id);

    /**
     * 根据用户id获取用户的信息和用户的角色信息
     * @param id
     * @return
     */
    SysUser selectUserAndRoleByIdSelect(Long id);

    /**
     * 获取所有的用户以及对应的所有角色
     * @return
     */
    List<SysUser> selectAllUserAndRoles();

    /**
     * 使用存储过程查询用户信息
     * @param user
     */
    void selectUserById(SysUser user);

    /**
     * 使用存储过程分页查询
     * @param params
     * @return
     */
    List<SysUser> selectUserPage(Map<String,Object> params);

    /**
     * 保存用户信息和角色关联信息
     * @param user
     * @param roleIds
     * @return
     */
    int insertUserAndRoles(
            @Param("user") SysUser user,@Param("roleIds")String roleIds
    );

    /**
     * 根据用户id删除用户和用户的角色信息
     * @param id
     * @return
     */
    int deleteByUserId(Long id);
}
