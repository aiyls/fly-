package cn.aiyls.fly.redis;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis common操作工具类
 */
@Component
public class RedisCommonUtils {
	
	private final StringRedisTemplate template;
    @Autowired
    public RedisCommonUtils(StringRedisTemplate template) {
        this.template = template;
    }

    public Set<String> getAllKeys(String pattern) {
    	return template.keys(pattern);
    }
	
    public boolean isExists(String key) {
    	return template.hasKey(key);
    }
    
}
