package com.tk.neo4j.config;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.ogm.config.ClasspathConfigurationSource;
import org.neo4j.ogm.config.ConfigurationSource;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author zhangyj178
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "com.tk.neo4j.repository")
@EnableTransactionManagement
public class Neo4jConfiguration {

    private final String URL = "bolt://10.130.219.60:7687";
    private final String username = "neo4j";
    private final String password = "neo4j";


    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder().uri(URL)
                .credentials(username, password).build();
        return configuration;
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory(configuration(), "com.tk.neo4j.domain.node", "com.tk.neo4j.domain.relationship");
    }

    @Bean
    Driver driver() {
        return GraphDatabase.driver(URL, AuthTokens.basic(username, password));
    }

}
