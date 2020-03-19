package com.tk.neo4j.domain.relationship;

import com.tk.neo4j.domain.node.BaseNode;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * neo4j关系:HAVE
 */
@RelationshipEntity(type = "RelationType")
public class RelationShip<S extends BaseNode, E extends BaseNode> {

    private String type;

    private Long invokeTimes;

    private String relationship;

    private boolean isCreate = false;

    @StartNode
    private S startNode;
    @EndNode
    private E endNode;


    public RelationShip(S startNode, E endNode, String relationship,
                        String type) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.relationship = relationship;
        this.type = type;
    }

    public RelationShip() {

    }

//    public HaveDynamicRelationShip(S startNode, E endNode,String relationship) {
//        this.startNode = startNode;
//        this.endNode = endNode;
//        this.relationship=relationship;
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public S getStartNode() {
        return startNode;
    }

    public void setStartNode(S startNode) {
        this.startNode = startNode;
    }

    public E getEndNode() {
        return endNode;
    }

    public void setEndNode(E endNode) {
        this.endNode = endNode;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Long getInvokeTimes() {
        return invokeTimes;
    }

    public void setInvokeTimes(Long invokeTimes) {
        this.invokeTimes = invokeTimes;
    }

    public boolean isCreate() {
        return isCreate;
    }

    public void setCreate(boolean create) {
        isCreate = create;
    }
}
