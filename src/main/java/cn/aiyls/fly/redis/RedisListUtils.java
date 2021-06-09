package cn.aiyls.fly.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis list操作工具类
 */
@Component
public class RedisListUtils {
	
	private final StringRedisTemplate template;
    @Autowired
    public RedisListUtils(StringRedisTemplate template) {
        this.template = template;
    }

    public void setKey(String key, long index, String value){
    	ListOperations<String, String> ops = template.opsForList();
        ops.set(key, index, value);
    }
 
    public Object getValue(String hashKey, long start, long end){
    	ListOperations<String, String> ops = template.opsForList();
        return ops.range(hashKey, start, end);
    }
    
    public Long remove(String hashKey, long count, Object value){
    	ListOperations<String, String> ops = template.opsForList();
        return ops.remove(hashKey, count, value);
    }
    
}
