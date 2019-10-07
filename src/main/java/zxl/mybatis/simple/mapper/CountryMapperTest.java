package zxl.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import zxl.mybatis.simple.model.CountryOld;
import zxl.mybatis.simple.model.CountryExample;

import java.util.List;


public class CountryMapperTest extends BaseMapperTest{



//    @Test
    public void testSelectAll(){
        SqlSession sqlSession = getSqlSession();
        try{
            List<CountryOld> countryList = sqlSession.selectList("zxl.mybatis.simple.mapper.CountryMapper.selectAll");
            printCountryList(countryList);
        }finally {
            sqlSession.close();
        }
    }

    private void printCountryList(List<CountryOld> countryList){
        for(CountryOld country:countryList){
            System.out.printf("%-4d%4s%4s\n",country.getId(),country.getId(),country.getCountrycode());
        }

    }

//    @Test
    public void testExample(){
        SqlSession sqlSession = getSqlSession();
        try{
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            //创建Example对象
            CountryExample example = new CountryExample();
            //设置排序规则
            example.setOrderByClause("id desc,countryname asc");
            //设置是否distinct去重
            example.setDistinct(true);
            //创建条件
            CountryExample.Criteria criteria = example.createCriteria();
            //id>=1
            criteria.andIdGreaterThanOrEqualTo(1);
            //id<4
            criteria.andIdLessThan(4);
            //countryCode like '%U%'
            //最容易出错的地方，注意like必须自己写上通配符的位置
            criteria.andCountrycodeLike("%U%");
            //or的情况
            CountryExample.Criteria or = example.or();
            //countryname=中国
            or.andCountrynameEqualTo("中国");
            //执行查询
            List<CountryOld> countryList = countryMapper.selectByExample(example);
            printCountryList(countryList);
        }finally {
            sqlSession.close();
        }
    }

//    @Test
    public void testUpdateByExampleSelective(){
        SqlSession sqlSession = getSqlSession();
        try{
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            CountryExample example = new CountryExample();
            //创建条件，只能有一个createCriteria
            CountryExample.Criteria criteria = example.createCriteria();
            //更新所有ID>2的国家
            criteria.andIdGreaterThan(2);
            //创建一个要设置的对象
            CountryOld country = new CountryOld();
            //将国家的名字设置为China
            country.setCountryname("China");
            //执行查询
            countryMapper.updateByExampleSelective(country,example);
            printCountryList(countryMapper.selectByExample(example));
        }finally {
            sqlSession.close();
        }
    }

//    @Test
    public void testDeleteByExample (){
        SqlSession sqlSession = getSqlSession();

        try {
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            //创建Example对象
            CountryExample example = new CountryExample();
            //创建条件
            CountryExample.Criteria criteria = example.createCriteria();
            //删除所有ID>2的国家
            criteria.andIdGreaterThan(2);
            //执行查询
            countryMapper.deleteByExample(example);
            //使用countryByExample查询符合条件的数量，因为已删除，所以这里应该是0
            Assert.assertEquals(0,countryMapper.countByExample(example));
        }finally {
            sqlSession.close();
        }
    }

}
