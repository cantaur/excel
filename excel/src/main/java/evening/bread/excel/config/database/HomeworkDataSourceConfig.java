package evening.bread.excel.config.database;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.yml")
public class HomeworkDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.member-datasource")
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource datasource =  DataSourceBuilder.create().type(org.apache.tomcat.jdbc.pool.DataSource.class).build();
        datasource.setValidationQuery("/* ping */ SELECT 1");
        datasource.setValidationInterval(1000);
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(true);
//        datasource.setLogValidationErrors(true);

        return datasource;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}