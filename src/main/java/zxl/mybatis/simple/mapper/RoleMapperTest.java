package zxl.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import zxl.mybatis.simple.model.SysPrivilege;
import zxl.mybatis.simple.model.SysRole;
import zxl.mybatis.simple.model.SysUser;
import zxl.mybatis.simple.type.Enabled;

import java.util.Date;
import java.util.List;

public class RoleMapperTest extends BaseMapperTest{

//    @Test
    public void testSelectById(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = roleMapper.selectById(1l);
            Assert.assertNotNull(sysRole);
            Assert.assertEquals("管理员",sysRole.getRoleName());
        }finally {
            sqlSession.close();
        }
    }

//    @Test
    public void testSelectById2(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = roleMapper.selectById2(1l);
            Assert.assertNotNull(sysRole);
            Assert.assertEquals("管理员",sysRole.getRoleName());
        }finally {
            sqlSession.close();
        }
    }

//    @Test
    public void testSelectAll(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> list = roleMapper.selectAllRole();
            Assert.assertNotNull(list);
            Assert.assertTrue(list.size()>0);
            Assert.assertNotNull(list.get(0).getRoleName());
        }finally {
            sqlSession.close();
        }
    }

//    @Test
    public void testInsertRole(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper= sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = new SysRole();
            sysRole.setRoleName("tom");
//            sysRole.setEnabled(0);
//            sysRole.setCreateBy(1l);
//            sysRole.setCreateTime(new Date());
            //将新建的对象插入数据库，特别注意这里返回的值result是执行的SQL影响的行数
            int result = roleMapper.insertRole(sysRole);
            System.out.println(result);
            //只插入一条数据
            Assert.assertEquals(1,result);
            //id为null，没有给id赋值，并且没有配制回写id
            Assert.assertNull(sysRole.getId());
            System.out.println(sysRole.getId());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

//    @Test
    public void testInsertRole2(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper= sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = new SysRole();
            sysRole.setRoleName("Jerry");
//            sysRole.setEnabled(0);
//            sysRole.setCreateBy(1l);
//            sysRole.setCreateTime(new Date());
            //将新建的对象插入数据库，特别注意这里返回的值result是执行的SQL影响的行数
            int result = roleMapper.insertRole2(sysRole);
            System.out.println(result);
            //只插入一条数据
            Assert.assertEquals(1,result);
            //id为null，没有给id赋值，并且没有配制回写id
            Assert.assertNotNull(sysRole.getId());
            System.out.println(sysRole.getId());
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

//    @Test
    public void testInsertRole3(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper= sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = new SysRole();
            sysRole.setRoleName("Kimmi");
//            sysRole.setEnabled(0);
//            sysRole.setCreateBy(1l);
//            sysRole.setCreateTime(new Date());
            //将新建的对象插入数据库，特别注意这里返回的值result是执行的SQL影响的行数
            int result = roleMapper.insertRole3(sysRole);
            System.out.println(result);
            //只插入一条数据
            Assert.assertEquals(1,result);
            //id为null，没有给id赋值，并且没有配制回写id
            Assert.assertNotNull(sysRole.getId());
            System.out.println(sysRole.getId());
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

//    @Test
    public void testUpdateById(){
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper= sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = roleMapper.selectById(1l);
            Assert.assertEquals("管理员",sysRole.getRoleName());
            sysRole.setRoleName("admin_test");
            int result= roleMapper.updateRoleById(sysRole);
            Assert.assertEquals(1,result);
            sysRole= roleMapper.selectById(1L);
            Assert.assertEquals("admin_test",sysRole.getRoleName());
            sqlSession.commit();
        }finally {
//            sqlSession.rollback();
            sqlSession.close();
        }
    }

//    @Test
    public void testDeleteById(){
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper= sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole1 = roleMapper.selectById(1l);
            Assert.assertNotNull(sysRole1);
            Assert.assertEquals(1,roleMapper.deleteRoleById(1l));
            Assert.assertNull(roleMapper.selectById(1l));


        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

//    @Test
    public void testSelectAllRoleAndPrivileges(){
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roleList = roleMapper.selectAllRoleAndPrivileges();
            System.out.println("角色数："+roleList.size());
            for(SysRole role:roleList){
                System.out.println("角色名："+role.getRoleName());
                for(SysPrivilege privilege:role.getPrivilegeList()){
                    System.out.println("权限名："+privilege.getPrivilegeName());
                }
            }

        }finally {
            sqlSession.close();
        }
    }

//    @Test
    public void testUpdateByIdEnum(){
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = roleMapper.selectById(2L);
            Assert.assertEquals(Enabled.enabled,sysRole.getEnabled());
            sysRole.setEnabled(Enabled.diabled);
            roleMapper.updateRoleById(sysRole);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testL2Cache(){
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        SysRole role1=null;
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            role1 = roleMapper.selectById(1l);
            role1.setRoleName("New Name");
            SysRole role2= roleMapper.selectById(1l);
            Assert.assertEquals("New Name",role2.getRoleName());
            Assert.assertEquals(role1,role2);
        }finally {
            sqlSession.close();
        }
        System.out.println("开启新的sqlSession");
        sqlSession= getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role2 = roleMapper.selectById(1l);
            Assert.assertEquals("New Name",role2.getRoleName());
            Assert.assertNotEquals(role1,role2);
            SysRole role3 = roleMapper.selectById(1l);
            Assert.assertNotEquals(role2,role3);
        }finally {
            sqlSession.close();
        }
    }
}
