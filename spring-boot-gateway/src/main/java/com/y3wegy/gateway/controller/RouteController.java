package com.y3wegy.gateway.controller;

import com.y3wegy.base.exception.ServiceException;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.bean.web.ResponseJson;
import com.y3wegy.gateway.GateWayException;
import com.y3wegy.gateway.bean.gateway.GatewayFilterDefinition;
import com.y3wegy.gateway.bean.gateway.GatewayPredicateDefinition;
import com.y3wegy.gateway.bean.gateway.GatewayRouteDefinition;
import com.y3wegy.gateway.service.DynamicRouteServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author e631876
 */
@RestController
@RequestMapping("/route")
public class RouteController {
    private static final Logger logger = LoggerFactory.getLogger(RouteController.class);
    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;

    /**
     * 增加路由
     * @param gatewayRouteDefinition
     * @return
     */
    @PostMapping("/add")
    public String add(@RequestBody GatewayRouteDefinition gatewayRouteDefinition) throws ServiceException {
        RouteDefinition definition = assembleRouteDefinition(gatewayRouteDefinition);
        this.dynamicRouteService.add(definition);
        ResponseJson responseJson = new ResponseJson("add success");
        return JackSonHelper.obj2JsonStr(responseJson);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) throws GateWayException, ServiceException {
        this.dynamicRouteService.delete(id);
        ResponseJson responseJson = new ResponseJson("delete success");
        return JackSonHelper.obj2JsonStr(responseJson);
    }

    @PostMapping("/update")
    public String update(@RequestBody GatewayRouteDefinition gatewayRouteDefinition) throws ServiceException {
        RouteDefinition definition = assembleRouteDefinition(gatewayRouteDefinition);
        ResponseJson responseJson = new ResponseJson();
        try {
            this.dynamicRouteService.update(definition);
        } catch (GateWayException e) {
            responseJson.fail(e.getMessage());
        }
        return JackSonHelper.obj2JsonStr(responseJson);
    }

    private RouteDefinition assembleRouteDefinition(GatewayRouteDefinition gatewayRouteDefinition) {
        RouteDefinition definition = new RouteDefinition();
        List<PredicateDefinition> pdList = new ArrayList<>();
        definition.setId(gatewayRouteDefinition.getId());
        List<GatewayPredicateDefinition> gatewayPredicateDefinitionList = gatewayRouteDefinition.getPredicates();
        for (GatewayPredicateDefinition gpDefinition : gatewayPredicateDefinitionList) {
            PredicateDefinition predicate = new PredicateDefinition();
            predicate.setArgs(gpDefinition.getArgs());
            predicate.setName(gpDefinition.getName());
            pdList.add(predicate);
        }

        List<GatewayFilterDefinition> gatewayFilterDefinitions = gatewayRouteDefinition.getFilters();
        List<FilterDefinition> filterList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(gatewayFilterDefinitions)) {
            for (GatewayFilterDefinition gatewayFilterDefinition : gatewayFilterDefinitions) {
                FilterDefinition filterDefinition = new FilterDefinition();
                filterDefinition.setName(gatewayFilterDefinition.getName());
                filterDefinition.setArgs(gatewayFilterDefinition.getArgs());
                filterList.add(filterDefinition);
            }
        }
        definition.setPredicates(pdList);
        definition.setFilters(filterList);
        URI uri = UriComponentsBuilder.fromHttpUrl(gatewayRouteDefinition.getUri()).build().toUri();
        definition.setUri(uri);
        return definition;
    }
}
