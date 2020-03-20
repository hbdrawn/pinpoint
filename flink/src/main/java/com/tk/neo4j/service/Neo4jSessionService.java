package com.tk.neo4j.service;

//import org.neo4j.driver.Driver;
//import org.neo4j.driver.Session;
//import org.neo4j.driver.Driver;
//import org.neo4j.driver.Session;
//import org.neo4j.driver.Result;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author itw_zhangxuan01
 * @date 2020/3/9
 */
@Service
public class Neo4jSessionService {

    @Autowired
    Driver driver;

    public StatementResult getFamilyRelationByCustomerId(String customerId) throws Exception {
        Session session = driver.session();
        String prepareCql = "match path = (:NaturalPerson {customerId:'%s'})-[:RelationType*{type:'1'}]-(:NaturalPerson) unwind nodes(path) as n with path, size(collect(distinct n)) as testLength where testLength = length(path)+1 return path";
        String cql = String.format(prepareCql, customerId);
        StatementResult result = session.run(cql);
        return result;
    }


    public StatementResult getShortestPath(String sourceCustomerId, String targetCustomerId) throws Exception {
        Session session = driver.session();
        String prepareCql = "MATCH (source:NaturalPerson{customerId:'%s'}), (target:NaturalPerson{customerId:'%s'})\n" +
                "CALL algo.shortestPath.stream(source, target, 'cost',{nodeQuery:'NaturalPerson',relationshipQuery:'RelationType'})\n" +
                "YIELD nodeId, cost\n" +
                "MATCH (other:NaturalPerson) WHERE id(other) = nodeId\n" +
                "RETURN other.customerId AS customerId, cost";
        String cql = String.format(prepareCql, sourceCustomerId, targetCustomerId);
        StatementResult result = session.run(cql);
        return result;
    }


    /**
     * 通过SCC方法对有账户号的客户进行分集
     *
     * @return
     * @throws Exception
     */
    public StatementResult getAccountCustomerByStrongConnectedComponents() throws Exception {
        Session session = driver.session();
        String prepareCql = "CALL algo.scc.stream(\n" +
                "  \"match (n:NaturalPerson)-[r:RelationType{type:'5'}]->(c:Account) with n as n,count(*) as num where num>0 return id(n) as id\",\n" +
                "  \"match (n:NaturalPerson)-[r:RelationType{type:'1'}]->(m:NaturalPerson) return id(n) AS source, id(m) AS target\",\n" +
                "  {graph:'cypher'}\n" +
                "  )\n" +
                "  YIELD nodeId, partition \n" +
                "  with algo.getNodeById(nodeId).customerId as customerId,partition \n" +
                "  with  partition as partition ,collect(customerId) as customerList where size(customerList)>1 \n" +
                "  return partition,customerList;";
        String cql = String.format(prepareCql);
        StatementResult result = session.run(cql);
        return result;
    }
}
