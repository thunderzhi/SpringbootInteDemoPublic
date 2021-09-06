package com.cxz.springbootintedemo.springbootdao.testdb.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/8/31 16:57
 */
@Configuration
@MapperScan(basePackages ="com.cxz.springbootintedemo.springbootdao.testdb",
        sqlSessionFactoryRef = "TestDBSqlSessionFactory")
public class TestDBConfig {

    @Autowired
    @Qualifier("TestDBSource")
    private DataSource ds;

    @Bean(name="TestDBSqlSessionFactory")
    @Primary
    public SqlSessionFactory TestDBSqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(ds);
        //指定mapper xml目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:mapper/testdb/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);


        return sqlSessionFactoryBean.getObject();
    }



    @Bean
    public SqlSessionTemplate TestDBSqlSessionTemplate(@Qualifier("TestDBSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factoryc
        return template;
    }
}
