package com.tk.neo4j.repository;

import com.tk.neo4j.domain.node.ServerInfo;
import com.tk.neo4j.domain.node.TestModel;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangyj178
 */
@Repository
public interface ServerInfoRepository extends Neo4jRepository<ServerInfo, String> {

    /**
     * 保存服务器节点
     *
     * @param serverInfoList
     */
    @Query("UNWIND {rows} as row MERGE (n:ServerInfo{applicationName: row.applicationName}) SET n = row")
    void saveServerInfo(@Param("rows") List<ServerInfo> serverInfoList);
}