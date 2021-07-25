package com.lagou.eduorderboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @BelongsProject: edu-lagou
 * @Author: GuoAn.Sun
 * @CreateTime: 2020-10-28 12:31
 * @Description: 配置分片规则
 */
@Configuration
public class ShardingJdbcConfig {
    //定义主键生成策略
    private static KeyGeneratorConfiguration getKeyGeneratorConfiguration() {
        return new KeyGeneratorConfiguration("SNOWFLAKE", "id");
    }

    //定义数据源
    Map<String, DataSource> createDataSourseMap() {
        DruidDataSource ds = new DruidDataSource();
        /*com.mysql.cj.jdbc.Driver*/
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/edu_order?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("123456");
        Map<String, DataSource> map = new HashMap<>();
        map.put("ds1", ds);
        return map;
    }

    //定义表 user_course_order_0..9的分片策略
    TableRuleConfiguration getTableRuleConfiguration() {
        TableRuleConfiguration rule = new TableRuleConfiguration("user_course_order", "ds1.user_course_order_$->{0..9}");
        rule.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "user_course_order_$->{id%2+1}"));
        rule.setKeyGeneratorConfig(getKeyGeneratorConfiguration());// 设置主键策略
        return rule;
    }

    // 定义sharding-jdbc数据源
    @Bean
    public DataSource getShardingDataSource() throws SQLException {
        ShardingRuleConfiguration config = new ShardingRuleConfiguration();
        config.getTableRuleConfigs().add(getTableRuleConfiguration());
        Properties pp = new Properties();
        pp.put("sql.show", true);
        // 数据源，分片配置，其他配置
        return ShardingDataSourceFactory.createDataSource(createDataSourseMap(), config, pp);
    }
}

