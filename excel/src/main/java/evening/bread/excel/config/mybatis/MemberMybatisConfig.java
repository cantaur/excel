package evening.bread.excel.config.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = "evening.bread.excel.mapper.member", sqlSessionFactoryRef = "memberSqlSessionFactory")
public class MemberMybatisConfig extends AbstractMybatisConfig{

    public MemberMybatisConfig(DataSource dataSource) {
        this.dataSource = dataSource;
        this.mapperLocationsPath = "classpath:/sql/member/*.xml";
    }

    @Override
    @Bean(name = "memberSqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        return configureSqlSessionFactory().getObject();
    }


}
