package com.futao.fund.core.usercontext;

import com.futao.fund.core.constant.RpcCons;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/24
 */
@Slf4j
@Activate(group = {CommonConstants.CONSUMER, CommonConstants.PROVIDER})
public class DubboUserFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext context = RpcContext.getContext();
        log.info("remote application name:{}", context.getRemoteApplicationName());
        try {
            String attachmentRpcUserId = context.getAttachment(RpcCons.USER_ID);
            if (context.isProviderSide()) {
                // 当前是生产者 - 将用户信息保存在threadlocal中
                CurrentUser.set(attachmentRpcUserId);
                log.debug("provider side, user id: {}", attachmentRpcUserId);
            }
            if (context.isConsumerSide()) {
                // 当前是消费者，需要读取用户信息，给到生产者
                String currentUserId = CurrentUser.s();
                currentUserId = StringUtils.isBlank(currentUserId) ? attachmentRpcUserId : currentUserId;
                log.debug("consumer side, user id: {}", currentUserId);
                context.setAttachment(RpcCons.USER_ID, currentUserId);

            }
            return invoker.invoke(invocation);
        } finally {
            log.debug("rm user info");
            // 清除当前线程的用户信息
            CurrentUser.clear();
        }
    }
}
