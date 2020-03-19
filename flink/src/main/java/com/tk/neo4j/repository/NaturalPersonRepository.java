//package com.tk.neo4j.repository;
//
//import cn.tk.cip.domain.node.NaturalPerson;
//import org.springframework.data.neo4j.annotation.Query;
//import org.springframework.data.neo4j.repository.Neo4jRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//
//@Repository
//public interface NaturalPersonRepository extends Neo4jRepository<NaturalPerson, String> {
//
//	/**
//	 * 覆盖保存自然人节点(标签传则更新标签，不传保留原标签)
//	 * @param b
//	 */
//	@Query("UNWIND {rows} as row MERGE (n:NaturalPerson{customerId: row.customerId}) "
//			+ "with n.label as c,n,row SET n=row foreach(red in case when row.label is null then [1] else [] end | set n.label = c)")
//	void saveAllPerson(@Param("rows") List<NaturalPerson> b);
//
//	/**
//	 * 保存标签
//	 * @param naturalPersons
//	 */
//	@Query("UNWIND {rows} as row MERGE (n:NaturalPerson{customerId: row.customerId}) SET n.label=row.label")
//	void saveLabels(@Param("rows") List<NaturalPerson> naturalPersons);
//
//}
