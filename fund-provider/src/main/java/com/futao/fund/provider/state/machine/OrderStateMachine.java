package com.futao.fund.provider.state.machine;

import com.futao.fund.api.state.machine.OrderEventEnum;
import com.futao.fund.api.state.machine.OrderStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.StateDoActionPolicy;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineConfig;
import org.springframework.statemachine.config.builders.*;
import org.springframework.statemachine.config.common.annotation.AnnotationBuilder;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

/**
 * @author futaosmile@gmail.com
 * @date 2022/6/15
 */
@Slf4j
@Configuration
@EnableStateMachine
public class OrderStateMachine extends EnumStateMachineConfigurerAdapter<OrderStateEnum, OrderEventEnum> {
    @Override
    public void configure(StateMachineConfigBuilder<OrderStateEnum, OrderEventEnum> config) throws Exception {
        super.configure(config);
    }

    @Override
    public void configure(StateMachineModelConfigurer<OrderStateEnum, OrderEventEnum> model) throws Exception {
        super.configure(model);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderStateEnum, OrderEventEnum> config) throws Exception {
        config.withConfiguration()
                .machineId("订单状态状态机")
                .autoStartup(true)
                .listener(orderEventEnumStateMachineListener())
                .stateDoActionPolicyTimeout(10, TimeUnit.SECONDS)
                .stateDoActionPolicy(StateDoActionPolicy.IMMEDIATE_CANCEL);
    }

    @Override
    public void configure(StateMachineStateConfigurer<OrderStateEnum, OrderEventEnum> states) throws Exception {
        states.withStates()
                .initial(OrderStateEnum.ToBePaid)
                .states(EnumSet.allOf(OrderStateEnum.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStateEnum, OrderEventEnum> transitions) throws Exception {
        transitions.withExternal()
                // 支付
                .source(OrderStateEnum.ToBePaid).target(OrderStateEnum.ToBeDelivered).event(OrderEventEnum.PAY)
                // 守卫
                .guard(orderEventEnumGuard())
                .and().withExternal()
                // 发货
                .source(OrderStateEnum.ToBeDelivered).target(OrderStateEnum.PendingReceipt).event(OrderEventEnum.Ship)
                .and().withExternal()
                // 收货
                .source(OrderStateEnum.PendingReceipt).target(OrderStateEnum.Completed).event(OrderEventEnum.Receipt);
    }

    @Override
    public boolean isAssignable(AnnotationBuilder<StateMachineConfig<OrderStateEnum, OrderEventEnum>> builder) {
        return super.isAssignable(builder);
    }

    @Bean
    public StateMachineListener<OrderStateEnum, OrderEventEnum> orderEventEnumStateMachineListener() {
        return new StateMachineListenerAdapter<OrderStateEnum, OrderEventEnum>() {
            @Override
            public void stateChanged(State<OrderStateEnum, OrderEventEnum> from, State<OrderStateEnum, OrderEventEnum> to) {
                log.info("订单状态变更:{} - {}", from.getId(), to.getId());
            }
        };
    }

    /**
     * 状态转换守卫
     *
     * @return
     */
    @Bean
    public Guard<OrderStateEnum, OrderEventEnum> orderEventEnumGuard() {
        return new Guard<OrderStateEnum, OrderEventEnum>() {
            @Override
            public boolean evaluate(StateContext<OrderStateEnum, OrderEventEnum> context) {
                OrderEventEnum event = context.getEvent();
                // 判断这个操作是否允许执行
                return true;
            }
        };
    }
}
