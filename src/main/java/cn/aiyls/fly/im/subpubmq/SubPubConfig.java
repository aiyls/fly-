package cn.aiyls.fly.im.subpubmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Component
public class SubPubConfig {

    /**
     * 并发解决方案三
     * 线程池加redis订阅与发布模式的消息队列
     * 能集群，能并发
     */

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    private ThreadPoolTaskExecutor taskExecutor;

    private ThreadPoolTaskExecutor threadPoolTaskExecutor() {

        if (taskExecutor == null) {
            taskExecutor = new ThreadPoolTaskExecutor();

            //配置核心线程数
            taskExecutor.setCorePoolSize(5);

            //配置最大线程数
            taskExecutor.setMaxPoolSize(10);

            //配置队列大小
            taskExecutor.setQueueCapacity(500);

            // 线程最大空闲时间
            taskExecutor.setKeepAliveSeconds(300);

            //配置线程池中的线程的名称前缀
            taskExecutor.setThreadNamePrefix("redis-msg-sub-service-");

            // rejection-policy：当pool已经达到max size的时候，如何处理新任务
            // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
            taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

            //执行初始化
            taskExecutor.initialize();

        }
        return taskExecutor;
    }

    @Bean
    public RedisMessageListenerContainer initContainer() {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(redisConnectionFactory);

        container.setTaskExecutor(threadPoolTaskExecutor());

        container.addMessageListener(new SubscriberListener(), new PatternTopic(ChannelEnum.WEBSOCKET.getTopic()));

        return container;
    }
}
