package com.tk.web;

import com.navercorp.pinpoint.common.service.ServiceTypeRegistryService;
import com.navercorp.pinpoint.web.applicationmap.ApplicationMap;
import com.navercorp.pinpoint.web.applicationmap.MapWrap;
import com.navercorp.pinpoint.web.applicationmap.link.Link;
import com.navercorp.pinpoint.web.applicationmap.link.LinkType;
import com.navercorp.pinpoint.web.applicationmap.nodes.Node;
import com.navercorp.pinpoint.web.applicationmap.nodes.NodeType;
import com.navercorp.pinpoint.web.service.ApplicationFactory;
import com.navercorp.pinpoint.web.service.CommonService;
import com.navercorp.pinpoint.web.service.MapService;
import com.navercorp.pinpoint.web.service.ResponseTimeHistogramService;
import com.navercorp.pinpoint.web.util.Limiter;
import com.navercorp.pinpoint.web.vo.Application;
import com.navercorp.pinpoint.web.vo.Range;
import com.navercorp.pinpoint.web.vo.SearchOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Component
public class WebService {

    private Logger logger = LoggerFactory.getLogger(WebService.class);

    private static final int DEFAULT_MAX_SEARCH_DEPTH = 8;

    @Autowired
    private CommonService commonService;

    @Autowired
    private MapService mapService;

    @Autowired
    private ResponseTimeHistogramService responseTimeHistogramService;

    @Autowired
    private Limiter dateLimit;

    @Autowired
    private ServiceTypeRegistryService registry;

    @Autowired
    private ApplicationFactory applicationFactory;

    public void createNode() {

        Calendar cal = Calendar.getInstance();
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.DATE, -1);
        final Range range = new Range(cal.getTimeInMillis(), endTime);
        this.dateLimit.limit(range);

        SearchOption searchOption = new SearchOption(DEFAULT_MAX_SEARCH_DEPTH, DEFAULT_MAX_SEARCH_DEPTH, true, false);

        List<Application> applications = commonService.selectAllApplicationNames();
        for (Application tmp : applications) {
            Application application = applicationFactory.createApplication(tmp.getName(), tmp.getServiceTypeCode());
            MapWrap mapWrap = selectApplicationMap(application, range, searchOption, NodeType.DETAILED, LinkType.DETAILED);
            ApplicationMap applicationMap = mapWrap.getApplicationMap();
            Collection<Node> nodes = applicationMap.getNodes();
            Collection<Link> links = applicationMap.getLinks();
            for (Node tmp1 : nodes) {
                logger.error("=============\n{},{},{}", tmp1.getNodeName(), tmp1.getServiceType(), tmp1.getServerInstanceList().getInstanceCount());
            }

            for (Link tmp1 : links) {
                logger.error("=============\n{},{},{},{},{}", tmp1.getFrom().getNodeName(), tmp1.getTo().getNodeName(), tmp1.getLinkKey(), tmp1.getLinkName(), tmp1.getLinkType());
            }
            
            //TODO create neo4j node and link
        }

    }

    private MapWrap selectApplicationMap(Application application, Range range, SearchOption searchOption, NodeType nodeType, LinkType linkType) {
        if (application == null) {
            throw new NullPointerException("application must not be null");
        }
        if (range == null) {
            throw new NullPointerException("range must not be null");
        }
        if (searchOption == null) {
            throw new NullPointerException("searchOption must not be null");
        }

        logger.info("getServerMap() application:{} range:{} searchOption:{}", application, range, searchOption);

        ApplicationMap map = mapService.selectApplicationMap(application, range, searchOption, nodeType, linkType);

        return new MapWrap(map);
    }
}
