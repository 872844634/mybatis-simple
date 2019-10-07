package zxl.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import zxl.mybatis.simple.model.SysPrivilege;

public class PrivilegeMapperTest extends BaseMapperTest{
    @Test
    public void testSelectById(){
        SqlSession sqlSession = getSqlSession();
        try{
            PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
            SysPrivilege sysPrivilege = privilegeMapper.selectPrivilegeById(1l);
            Assert.assertNotNull(sysPrivilege);
            Assert.assertEquals("用户管理",sysPrivilege.getPrivilegeName());
        }finally {
            sqlSession.close();
        }
    }
}
