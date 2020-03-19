package com.navercorp.pinpoint.flink.vo;

import com.navercorp.pinpoint.common.server.bo.stat.join.JoinStatBo;
import lombok.Data;

import java.util.Date;

/**
 * flink内部状态维护信息
 */

@Data
public class ApplicationStatus4Neo4j implements JoinStatBo {
    String applicationId;
    String agentId;
    /**
     * 最新调用时间
     */
    Date lastTime;
    Date firstTime;

    /**
     * 调用的累加次数
     */
    long times;
    /**
     * 是否更新
     */
    boolean create;

    @Override
    public long getTimestamp() {
        return lastTime.getTime();
    }

    @Override
    public String getId() {
        return applicationId;
    }
}
