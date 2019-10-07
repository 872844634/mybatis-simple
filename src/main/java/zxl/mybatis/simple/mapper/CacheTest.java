package zxl.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import zxl.mybatis.simple.model.SysUser;

public class CacheTest extends BaseMapperTest{
    @Test
    public void testL1Cache(){
        //获取sqlsession
        SqlSession sqlSession = getSqlSession();
        SysUser user1=null;
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            user1=userMapper.selectById(1l);
            user1.setUserName("New Name");
            SysUser user2 = userMapper.selectById(1l);
            Assert.assertEquals("New Name",user2.getUserName());
            Assert.assertEquals(user1,user2);

        }finally {
            sqlSession.close();
        }
        System.out.println("开启新的sqlsession");
        sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user2 = userMapper.selectById(1l);
            Assert.assertNotEquals("New Name",user2.getUserName());
            Assert.assertNotEquals(user1,user2);
            userMapper.deleteById(2l);
            SysUser user3=userMapper.selectById(1l);
            Assert.assertNotEquals(user2,user3);
        }finally {
            sqlSession.close();
        }
    }
}
