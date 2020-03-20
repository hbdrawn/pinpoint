package com.tk.neo4j.repository;

import com.tk.neo4j.domain.relationship.RelationShip;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationShipRepository extends Neo4jRepository<RelationShip, Long> {

    @Query("unwind {relations} as row with row, row.startNode as start, row.endNode as end "
            + "merge (a:ServerInfo {applicationName:start.applicationName}) "
            + "merge (b:ServerInfo {applicationName:end.applicationName}) "
            + "merge (a)-[r:RelationType {type:row.type, relationship:row.relationship}]->(b) set r.invokeTimes = row.invokeTimes")
    void saveRelations(@Param("relations") List<RelationShip> relations);

//	@Query("unwind {relations} as row with row, row.startNode as start, row.endNode as end "
//			+ "merge (a:NaturalPerson {customerId:start.customerId}) "
//			+ "merge (b:ContactInfo {id:end.id}) on create set b.type = end.type, b.number = end.number "
//			+ "merge (a)-[r:RelationType {type:row.type, relationship:row.relationship}]->(b)")
//	void saveContactRelations(@Param("relations") List<RelationShip> relations);
//
//	@Query("unwind {relations} as row with row, row.startNode as start, row.endNode as end "
//			+ "merge (a:NaturalPerson {customerId:start.customerId}) "
//			+ "merge (b:Policy {policyNo:end.policyNo}) "
//			+ "merge (a)-[r:RelationType {type:row.type, relationship:row.relationship}]->(b)")
//	void savePolicyRelations(@Param("relations") List<RelationShip> relations);
//
//	@Query("unwind {relations} as row with row, row.startNode as start, row.endNode as end "
//			+ "merge (a:NaturalPerson {customerId:start.customerId}) "
//			+ "merge (b:Account {accountNo:end.accountNo}) set b.accountName = end.accountName,"
//			+ "b.payBankName = end.payBankName "
//			+ "merge (a)-[r:RelationType {type:row.type, relationship:row.relationship}]->(b)")
//	void saveAccountRelations(@Param("relations") List<RelationShip> relations);
//
//	@Query("unwind {relations} as row with row, row.startNode as start, row.endNode as end "
//			+ "merge (a:Account {accountNo:start.accountNo}) "
//			+ "merge (b:City {cityCode:end.cityCode})  on create set b.cityName = end.cityName "
//			+ "merge (a)-[r:RelationType {type:row.type, relationship:row.relationship}]->(b)")
//	void saveAccountCityRelations(@Param("relations") List<RelationShip> relations);
//
//	@Query("unwind {relations} as row with row, row.startNode as start, row.endNode as end "
//			+ "merge (a:Policy {policyNo:start.policyNo}) "
//			+ "merge (b:City {cityCode:end.cityCode})  on create set b.cityName = end.cityName "
//			+ "merge (a)-[r:RelationType {type:row.type, relationship:row.relationship}]->(b)")
//	void savePolicyCityRelations(@Param("relations") List<RelationShip> relations);
//
//	@Query("unwind {relations} as row with row, row.startNode as start, row.endNode as end "
//			+ "merge (a:NaturalPerson {customerId:start.customerId}) "
//			+ "merge (b:City {cityCode:end.cityCode}) on create set b.cityName = end.cityName "
//			+ "merge (a)-[r:RelationType {type:row.type, relationship:row.relationship}]->(b)")
//	void saveCtmCityRelations(@Param("relations") List<RelationShip> relations);
//
//	@Query("unwind {relations} as row with row, row.startNode as start, row.endNode as end "
//			+ "merge (a:Organization {customerId:start.customerId}) "
//			+ "merge (b:Account {accountNo:end.accountNo}) set b.accountName = end.accountName,"
//			+ "b.payBankName = end.payBankName "
//			+ "merge (a)-[r:RelationType {type:row.type, relationship:row.relationship}]->(b)")
//	void saveOrgAccountRelations(@Param("relations") List<RelationShip> relations);
//
//	/**
//	 * 清空两客户原有关系并新建关系。若客户不存在不做操作。
//	 */
//	@Query("unwind {relations} as row "
//			+ "match (n:NaturalPerson {customerId:row.applicantId}), (m:NaturalPerson{customerId:row.insuredId}) "
//			+ "optional match (n)-[r:RelationType]->(m) "
//			+ "with n,m,row,collect(r) as rs foreach(r in rs | delete r) "
//			+ "with n,m,row unwind row.relationships as c "
//			+ "create (n)-[:RelationType {relationship:c, type:'1'}]->(m)")
//	void changeCtmRelation(@Param("relations") List<RelationDto> relations);

}
