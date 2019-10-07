package zxl.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import zxl.mybatis.simple.model.*;

import java.util.*;


public class UserMapperTest extends BaseMapperTest{


    //@Test
    public void testSelectById(){
        SqlSession sqlSession = getSqlSession();
        try{
            //获取UserMapper接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用selectById方法，查询id=1的用户
            SysUser user = userMapper.selectById(1l);
            //user不为空'
            Assert.assertNotNull(user);
            //userName=admin
            Assert.assertEquals("admin",user.getUserName());
        }finally {
            sqlSession.close();
        }
    }

//    @Test
    public void testSelectByUserInfo(){
        SqlSession sqlSession = getSqlSession();
        try{
            //获取UserMapper接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用selectById方法，查询id=1的用户
            SysUser user = userMapper.selectByUserInfo("管理员");
            //user不为空'
            Assert.assertNotNull(user);
            //userName=admin
            Assert.assertEquals("admin",user.getUserName());
        }finally {
            sqlSession.close();
        }
    }

    //@Test
    public void testSelectAll(){
        SqlSession sqlSession = getSqlSession();
        try{
            //获取UserMapper接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList= userMapper.selectAll();
            Assert.assertNotNull(userList);
            Assert.assertTrue(userList.size()>0);
            List<SysUser> userList1= userMapper.selectAllMapUnderscore();
            Assert.assertNotNull(userList1);
            Assert.assertTrue(userList1.size()>0);
        }finally {
            sqlSession.close();
        }
    }

    //@Test
    public void testSelectRoleByUserId(){
        SqlSession sqlSession= getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //关联查询获取SysRole对象
            List<SysRole> roleList = userMapper.selectRoleByUserId1(1l);
            Assert.assertNotNull(roleList);
            Assert.assertTrue(roleList.size()>0);
            //关联查询获取SysExtend对象，里面包含SysRole对象和自定义的SysUser里面的userName
            List<SysRoleExtend> roleList2 = userMapper.selectRoleByUserId2(1l);
            Assert.assertNotNull(roleList2);
            Assert.assertTrue(roleList2.size()>0);
            //关联查询获取SysExtend对象，里面包含SysRole对象和SysUser的对象，取user对象的username和userEmail
            List<SysRoleAndUser> roleList3 = userMapper.selectRoleByUserId3(1l);
            Assert.assertNotNull(roleList3);
            Assert.assertTrue(roleList3.size()>0);
            System.out.println(roleList3.get(0).getSysUser().getUserName());
        }finally {
            sqlSession.close();
        }
    }

//    @Test
    public void testInsert(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper= sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybattis.zxl");
            user.setUserInfo("test info");
            //假设下面的数据时图片
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            //将新建的对象插入数据库，特别注意这里返回的值result是执行的SQL影响的行数
            int result = userMapper.insert(user);
            System.out.println(result);
            //只插入一条数据
            Assert.assertEquals(1,result);
            //id为null，没有给id赋值，并且没有配制回写id
            Assert.assertNull(user.getId());
            System.out.println(user.getId());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    //@Test
    public void testInsert2(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper= sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybattis.zxl");
            user.setUserInfo("test info");
            //假设下面的数据时图片
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            //这个result是指影响到的行数，不是指ID
            int result = userMapper.insert2(user);
            Assert.assertEquals(1,result);
            Assert.assertNotNull(user.getId());
            System.out.println(user.getId());
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    //@Test
    public void testInsert3(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper= sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybattis.zxl");
            user.setUserInfo("test info");
            //假设下面的数据时图片
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            //这个result是指影响到的行数，不是指ID
            int result = userMapper.insert3(user);
            Assert.assertEquals(1,result);
            Assert.assertNotNull(user.getId());
            System.out.println(user.getId());
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    //@Test
    public void testUpdateById(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectById(1L);
            Assert.assertEquals("admin",user.getUserName());
            user.setUserName("admin_test");
            user.setUserEmail("test@mybatis.zxl");
            int result= userMapper.updateById(user);
            Assert.assertEquals(1,result);
            user= userMapper.selectById(1L);
            Assert.assertEquals("admin_test",user.getUserName());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    //@Test
    public void testDeleteById(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser1 = userMapper.selectById(1l);
            Assert.assertNotNull(sysUser1);
            Assert.assertEquals(1,userMapper.deleteById(1l));
            Assert.assertNull(userMapper.selectById(1l));


        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    //@Test
    public void testSelectRolesByUserIdAndEnabled(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> list = userMapper.selectRolesByUserIdAndRoleEnabled(1l,1);
            Assert.assertNotNull(list);
            Assert.assertTrue(list.size()>0);
        }finally {
            sqlSession.close();
        }
    }

//    @Test
    public void testSelectByUser(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //只查询用户名的时候
            SysUser query = new SysUser();
            query.setUserName("ad");
            List<SysUser> userList = userMapper.selectByUser(query);
            Assert.assertTrue(userList.size()>0);
            //当同时查询用户名和邮箱时
            query = new SysUser();
            query.setUserName("ad");
            query.setUserEmail("test@mybatis.zxl");
            userList = userMapper.selectByUser(query);
            Assert.assertTrue(userList.size()==0);

        }finally {
            sqlSession.close();
        }
    }

//    @Test
    public void testUpdateByIdSelective(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setId(1L);
            user.setUserEmail("test@mybatis.zxl");
            int result = userMapper.updateByIdSelective(user);
            Assert.assertEquals(1,result);
            user= userMapper.selectById(1l);
            Assert.assertEquals("admin",user.getUserName());
            Assert.assertEquals("test@mybatis.zxl",user.getUserEmail());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

//    @Test
    public void testInsert2Selective(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test-selective");
            user.setUserPassword("123456");
            user.setUserInfo("test info");
            user.setCreateTime(new Date());
            userMapper.insert2(user);

            user= userMapper.selectById(user.getId());
            Assert.assertEquals("test@mybatis.zxl",user.getUserEmail());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

//    @Test
    public void testSelectByIdOrUserName(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser query = new SysUser();
            query.setId(1l);
            query.setUserName("admin");
            SysUser user=userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            query.setId(null);
            user= userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            query.setUserName(null);
            user= userMapper.selectByIdOrUserName(query);
            Assert.assertNull(user);
        }finally {
            sqlSession.close();
        }
    }

//    @Test
    public void testSelectByIdList(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Long> idList = new ArrayList<Long>();
            idList.add(1l);
            idList.add(1001l);
            List<SysUser> userList = userMapper.selectByIdList(idList);
            Assert.assertEquals(2,userList.size());
        }finally {
            sqlSession.close();
        }
    }

//    @Test
    public void testInsertList(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = new ArrayList<SysUser>();
            for(int i = 0 ;i<2;i++){
                SysUser user = new SysUser();
                user.setUserName("test"+i);
                user.setUserPassword("123456");
                user.setUserEmail("test@mybatis.zxl");
                userList.add(user);
            }
            int result = userMapper.insertList(userList);
            Assert.assertEquals(2,result);
            for(SysUser user:userList){
                System.out.println(user.getId());
            }
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

//    @Test
    public void testUpdateByMap(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("id",1l);
            map.put("user_email","test@mybatis.zxl");
            map.put("user_password","12345678");
            userMapper.updateByMap(map);
            SysUser user = userMapper.selectById(1l);
            Assert.assertEquals("test@mybatis.zxl",user.getUserEmail());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

//    @Test
    public void testSelectUserAndRoleById(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //这里测试数据中，id为1的用户有两个角色，不适合这个例子
            //这里使用只有一个角色的用户1001
            SysUser user = userMapper.selectUserAndRoleById(1001l);
            Assert.assertNotNull(user);
//            Assert.assertNotNull(user.getRole());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

//    @Test
    public void testSelectUserAndRoleById2(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //这里测试数据中，id为1的用户有两个角色，不适合这个例子
            //这里使用只有一个角色的用户1001
            SysUser user = userMapper.selectUserAndRoleById2(1001l);
            Assert.assertNotNull(user);
//            Assert.assertNotNull(user.getRole());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

//    @Test
    public void testSelectUserAndRoleById3(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //这里测试数据中，id为1的用户有两个角色，不适合这个例子
            //这里使用只有一个角色的用户1001
            SysUser user = userMapper.selectUserAndRoleById3(1001l);
            Assert.assertNotNull(user);
//            Assert.assertNotNull(user.getRole());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

//    @Test
    public void testSelectUserAndRoleByIdSelect(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectUserAndRoleByIdSelect(1001l);
            Assert.assertNotNull(user);
            System.out.println("user.equals(null)");
            user.equals(null);
            System.out.println("调用user.getRole()");
//            Assert.assertNotNull(user.getRole());
        }finally {

            sqlSession.close();
        }
    }

//    @Test
    public void testSelectAllUserAndRoles(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = userMapper.selectAllUserAndRoles();
            System.out.println("用户数："+userList.size());
            for(SysUser user :userList){
                System.out.println("角色名："+user.getUserName());
                for(SysRole role:user.getRoleList()){
                    System.out.println("角色名："+role.getRoleName());
                    for(SysPrivilege privilege:role.getPrivilegeList()){
                        System.out.println("权限名："+privilege.getPrivilegeName());
                    }
                }
            }
        }finally {
            sqlSession.close();
        }
    }

//    @Test
    public void testSelectUserById(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setId(1L);
            userMapper.selectUserById(user);
            Assert.assertNotNull(user.getUserName());
            System.out.println("用户名："+user.getUserName());
        }finally {
            sqlSession.close();
        }
    }

//    @Test
    public void testSelectUserPage(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("userName","ad");
            params.put("offset","0");
            params.put("limit","10");
            List<SysUser> userList = userMapper.selectUserPage(params);
            Long total = (Long)params.get("total");
            System.out.println("总数："+total);
            for (SysUser user :userList){
                System.out.println("用户名："+user.getUserName());
            }
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertUserAndRoles(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.zxl");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3});
            //插入用户信息和角色关联信息
            userMapper.insertUserAndRoles(user,"1,2");
            Assert.assertNotNull(user.getId());
            Assert.assertNotNull(user.getCreateTime());
            sqlSession.commit();
            userMapper.deleteByUserId(user.getId());
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }

}


