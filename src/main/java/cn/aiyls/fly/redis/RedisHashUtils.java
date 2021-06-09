package cn.aiyls.fly.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis hash操作工具类
 */
@Component
public class RedisHashUtils {
	
	private final StringRedisTemplate template;
    @Autowired
    public RedisHashUtils(StringRedisTemplate template) {
        this.template = template;
    }

    public void setKey(String key,Object hashKey, Object value){
    	HashOperations<String, Object, Object> ops = template.opsForHash();
        ops.put(key, hashKey, value);
    }
 
    public Object getValue(String key, String hashKey){
    	HashOperations<String, Object, Object> ops = template.opsForHash();
        return ops.get(key, hashKey);
    }
    
    public boolean isExists(String key, String hashKey){
    	HashOperations<String, Object, Object> ops = template.opsForHash();
        return ops.hasKey(key, hashKey);
    }
    
}
