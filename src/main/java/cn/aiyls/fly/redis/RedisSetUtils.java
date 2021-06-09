package cn.aiyls.fly.redis;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis set操作工具类
 */
@Component
public class RedisSetUtils {
	
	private final StringRedisTemplate template;
    @Autowired
    public RedisSetUtils(StringRedisTemplate template) {
        this.template = template;
    }

    public void setKey(String key, String... values){
    	SetOperations<String, String> ops = template.opsForSet();
        ops.add(key, values);
    }
 
    public Set<String> getValue(String key){
    	SetOperations<String, String> ops = template.opsForSet();
        return ops.members(key);
    }
    
}
