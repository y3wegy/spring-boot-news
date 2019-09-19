package com.y3wegy.gateway.service;

import com.y3wegy.gateway.GateWayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author e631876
 */
@Service
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher publisher;


    /**
     * 增加路由
     * @param definition
     * @return
     */
    public void add(RouteDefinition definition) {
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }


    /**
     * 更新路由
     * @param definition
     * @return
     */
    public void update(RouteDefinition definition) throws GateWayException {
        try {
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
        } catch (Exception e) {
            throw new GateWayException(String.format("update fail,not find route  routeId:%s", definition.getId()), e);
        }
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
        } catch (Exception e) {
            throw new GateWayException("update route  fail", e);
        }


    }

    /**
     * 删除路由
     * @param id
     * @return
     */
    public void delete(String id) throws GateWayException {
        try {
            this.routeDefinitionWriter.delete(Mono.just(id));
        } catch (Exception e) {
            throw new GateWayException(String.format("delete fail,route id:%s", id), e);
        }

    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
