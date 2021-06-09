package cn.aiyls.fly.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * redis string操作工具类
 */
@Component
public class RedisStringUtils {
	
	private final StringRedisTemplate template;
    @Autowired
    public RedisStringUtils(StringRedisTemplate template) {
        this.template = template;
    }

    public void setKey(String key,String value){
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key,value);
    }
 
    public String getValue(String key){
        ValueOperations<String, String> ops = template.opsForValue();
        return ops.get(key);
    }
    
}
