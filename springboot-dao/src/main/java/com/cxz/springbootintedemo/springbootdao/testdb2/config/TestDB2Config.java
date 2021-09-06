package com.cxz.springbootintedemo.springbootdao.testdb2.config;

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
@MapperScan(basePackages ="com.cxz.springbootintedemo.springbootdao.testdb2",
        sqlSessionFactoryRef = "TestDB2SqlSessionFactory")
public class TestDB2Config {

    @Autowired
    @Qualifier("TestDB2Source")
    private DataSource ds;

    @Bean(name="TestDB2SqlSessionFactory")
    @Primary
    public SqlSessionFactory TestDB2SqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(ds);
        //指定mapper xml目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:mapper/testdb2/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);


        return sqlSessionFactoryBean.getObject();
    }



    @Bean
    public SqlSessionTemplate TestDB2SqlSessionTemplate(@Qualifier("TestDB2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factoryc
        return template;
    }
}
