package com.liefeng.core.mybatis;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.liefeng.common.util.ValidateHelper;

/**
 * Mybatis配置
 * @author Huangama
 * @date 2015-12-11
 */
@Configuration
@MapperScan(basePackages = {"com.liefeng.property.repository.mybatis" }, 
			sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisConfig {
    
    @Autowired
    private MybatisDatasourceProperties datasourceProperties;
    
    /**
     * 创建数据源
     * 参考源码：@see DataSourceAutoConfiguration.NonEmbeddedConfiguration
     * @return 数据源对象
     */
    @Bean
    public DataSource dataSource() {
    	DataSourceBuilder builder = 
    			DataSourceBuilder.create(datasourceProperties.getClassLoader())
    			.driverClassName(datasourceProperties.getDriverClassName())
    			.url(datasourceProperties.getUrl())
    			.username(datasourceProperties.getUsername())
    			.password(datasourceProperties.getPassword())
    			//如果不指定DataSource类型，则默认使用Tomcat的数据源。@see:DataSourceBuilder.DATA_SOURCE_TYPE_NAMES
    	        .type(BasicDataSource.class); 
    	
    	BasicDataSource dataSource = (BasicDataSource)builder.build();
    	if (datasourceProperties.getMaxActive() != null) {
    		dataSource.setMaxActive(datasourceProperties.getMaxActive());
    	}
    	if (datasourceProperties.getMaxIdle() != null) {
    		dataSource.setMaxIdle(datasourceProperties.getMaxIdle());
    	}
    	if (datasourceProperties.getMinIdle() != null) {
    		dataSource.setMinIdle(datasourceProperties.getMinIdle());
    	}
    	if (datasourceProperties.getInitialSize() != null) {
    		dataSource.setInitialSize(datasourceProperties.getInitialSize());
    	}
    	if (datasourceProperties.getTimeBetweenEvictionRunsMillis() != null) {
    		dataSource.setTimeBetweenEvictionRunsMillis(datasourceProperties.getTimeBetweenEvictionRunsMillis());
    	}
    	if (datasourceProperties.getMinEvictableIdleTimeMillis() != null) {
    		dataSource.setMinEvictableIdleTimeMillis(datasourceProperties.getMinEvictableIdleTimeMillis());
    	}
    	if (datasourceProperties.getTestWhileIdle() != null) {
    		dataSource.setTestWhileIdle(datasourceProperties.getTestWhileIdle());
    	}
    	if (datasourceProperties.getTestOnBorrow() != null) {
    		dataSource.setTestOnBorrow(datasourceProperties.getTestOnBorrow());
    	}
    	if (datasourceProperties.getTestOnReturn() != null) {
    		dataSource.setTestOnReturn(datasourceProperties.getTestOnReturn());
    	}
    	if (datasourceProperties.getRemoveAbandoned() != null) {
    		dataSource.setRemoveAbandoned(datasourceProperties.getRemoveAbandoned());
    	}
    	if (datasourceProperties.getRemoveAbandonedTimeout() != null) {
    		dataSource.setRemoveAbandonedTimeout(datasourceProperties.getRemoveAbandonedTimeout());
    	}
    	if (ValidateHelper.isNotEmptyString(datasourceProperties.getValidationQuery())) {
    		dataSource.setValidationQuery(datasourceProperties.getValidationQuery());
    	}
    	if (datasourceProperties.getValidationQueryTimeout() != null) {
    		dataSource.setValidationQueryTimeout(datasourceProperties.getValidationQueryTimeout());
    	}
    	if (datasourceProperties.getNumTestsPerEvictionRun() != null) {
    		dataSource.setNumTestsPerEvictionRun(datasourceProperties.getNumTestsPerEvictionRun());
    	}
    	
    	return dataSource;
    }
    
    @Bean
	public SqlSessionFactory sqlSessionFactory() {
    	SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
    	sessionFactoryBean.setDataSource(dataSource());
    	
    	SqlSessionFactory sessionFactory = null;
    	try {
    		PathMatchingResourcePatternResolver resolver = 
    				new PathMatchingResourcePatternResolver();
    		sessionFactoryBean.setMapperLocations(
    				resolver.getResources("classpath:com/liefeng/*/mapper/*Mapper.xml"));
    		
    		sessionFactory = sessionFactoryBean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0); 
		}
    	
    	return sessionFactory;
    }
    
    @Bean
	public static PropertySourcesPlaceholderConfigurer placehodlerConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
