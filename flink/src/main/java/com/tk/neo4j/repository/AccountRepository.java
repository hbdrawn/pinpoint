package com.tk.neo4j.repository;

import com.tk.neo4j.domain.node.TestModel;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends Neo4jRepository<TestModel,String> {

}