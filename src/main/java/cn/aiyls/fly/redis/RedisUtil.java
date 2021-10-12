package cn.aiyls.fly.redis;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import cn.aiyls.fly.utils.SerializeUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

/**
 * redis操作工具类
 */
@Component
public class RedisUtil {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private final StringRedisTemplate template;
	private final RedisTemplate<String, Object> redisTemplate;
	@Autowired
	public RedisUtil(StringRedisTemplate template, RedisTemplate redisTemplate) {
		this.template = template;
		this.redisTemplate = redisTemplate;
	}

	// ==============common
	public Set<String> getAllKeys(String pattern) {
		return template.keys(pattern);
	}

	public boolean isExists(String key) {
		return template.hasKey(key);
	}

	public boolean expire(String key, long timeout) {
		return template.expire(key, timeout, TimeUnit.HOURS);
	}
	
	public boolean expireMinutes(String key, long timeout) {
		return template.expire(key, timeout, TimeUnit.MINUTES);
	}
	// ==============common

	// ==============hash
	public void setKey(String key, Object hashKey, Object value) {
		HashOperations<String, Object, Object> ops = template.opsForHash();
		ops.put(key, hashKey, value);
		logger.info("redis存入key为：" + key + ",keyType为：" + hashKey + ",value为：" + value);
	}

	public Object getValue(String key, String hashKey) {
		HashOperations<String, Object, Object> ops = template.opsForHash();
		Object value = ops.get(key, hashKey);
		logger.info("通过key：" + key + "，keyType：" + hashKey + "从redis中取出值为：" + value);
		return value;
	}

	public boolean isExists(String key, String hashKey) {
		HashOperations<String, Object, Object> ops = template.opsForHash();
		return ops.hasKey(key, hashKey);
	}

	// ==============hash

	// ==============set
	public void setKey(String key, long index, String value) {
		ListOperations<String, String> ops = template.opsForList();
		ops.set(key, index, value);
	}

	public Object getValue(String hashKey, long start, long end) {
		ListOperations<String, String> ops = template.opsForList();
		return ops.range(hashKey, start, end);
	}

	public Long remove(String hashKey, long count, Object value) {
		ListOperations<String, String> ops = template.opsForList();
		return ops.remove(hashKey, count, value);
	}
	
	public Long getExpire(String key) {
		return template.boundHashOps(key).getExpire();
	}
	
	public void delete(String key) {
		template.delete(key);
	}
	
	// ==============set

	// ==============string
	public void setKey(String key, String value) {
		ValueOperations<String, String> ops = template.opsForValue();
		ops.set(key, value);
	}

	public String getValue(String key) {
		ValueOperations<String, String> ops = template.opsForValue();
		return ops.get(key);
	}
	// ==============string

	// ==============object
	public void setObject(String key, Object object) {
		ValueOperations ops = redisTemplate.opsForValue();
		byte[] bytes = SerializeUtil.serizlize(object);
		ops.set(key, object);
		logger.info("通过key：" + key + "，keyType：" + "从redis中取出值为：" + bytes);
	}

	public Object getObject(String key) {
		ValueOperations ops = redisTemplate.opsForValue();
		Object object = ops.get(key);
		return object;
	}
	// ==============object
}
