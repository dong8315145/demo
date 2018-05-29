package com.example.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Redis管理器
 * @param <K> key
 * @param <V> value
 *
 * @author pengz
 * @date 2018/03/25
 */
@Component("redisManager")
public class RedisManager<K, V> {

    @Autowired
    protected RedisTemplate<K, V> redisTemplate;

	//用于判断对redis的操作的类型
    /**
     * 错误
     */
	public static final int ERROR = 0;
    /**
     * 添加
     */
	public static final int ADD = 1;
    /**
     * 修改
     */
	public static final int UPDATE = 2;

    public void zsetAdd(final K keyId, final V obj, final double v1) {
        redisTemplate.opsForZSet().add(keyId, obj, v1);
    }

    public void zsetRemove(final K keyId, final V obj) {
        redisTemplate.opsForZSet().remove(keyId, obj);
    }

    public Set<V> zsetMembers(final K keyId) {
        return redisTemplate.opsForZSet().range(keyId, 0, -1);
    }

	public void setAdd(final K keyId, final V obj) {
	    redisTemplate.opsForSet().add(keyId, obj);
    }

    public Set<V> setMembers(final K keyId) {
	    return redisTemplate.opsForSet().members(keyId);
    }

    public void setRemove(final K keyId, final V obj) {
	    redisTemplate.opsForSet().remove(keyId, obj);
    }


    /**
     * 修改  
     * @param keyId
     * @param obj
     * @return  
     */
	@SuppressWarnings("unchecked")
	public int update(final String keyId, final Object obj) {  
        boolean result;
        //用户对redis的操作是哪种类型
        int retCode;
        if (get(keyId) == null) {  
        	result = add(keyId, obj);
        	if(result) {
                retCode = ADD;
            } else {
                retCode = ERROR;
            }
        } else {
	        result = redisTemplate.execute(new RedisCallback<Boolean>() {
	            @Override
                public Boolean doInRedis(RedisConnection connection)
	                    throws DataAccessException {
	                RedisSerializer<String> serializer = getStringRedisSerializer();
	                RedisSerializer<Object> objectSerializer = (RedisSerializer<Object>) getObjectRedisSerializer();
	                byte[] key  = serializer.serialize(keyId);  
	                byte[] byteObj = objectSerializer.serialize(obj); 
	                connection.set(key, byteObj);
	                return true;  
	            }  
	        });  
	        if(result) {
                retCode = UPDATE;
            } else {
                retCode = ERROR;
            }
        }
        return retCode;  
    }
	
	/**  
     * 新增 
     * @param keyId
     * @param obj
     * @return 
     */  
	@SuppressWarnings("unchecked")
    public boolean add(final String keyId, final Object obj) {  
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
            	RedisSerializer<String> stringSerializer = getStringRedisSerializer();
            	RedisSerializer<Object> objectSerializer = (RedisSerializer<Object>) getObjectRedisSerializer();
                byte[] key  = stringSerializer.serialize(keyId);  
                byte[] byteObj = objectSerializer.serialize(obj);  
                return connection.setNX(key, byteObj);
            }  
        });  
        return result;  
    } 
	
	/** 
     * 修改  
     * @param keyId
     * @param obj
     * @return  
     */
	@SuppressWarnings("unchecked")
	public int updateWithExpire(final String keyId, final Object obj) {  
        //String key = user.getId();  
        boolean result;
        //用户对redis的操作是哪种类型
        int retCode;
        if (get(keyId) == null) {  
            //throw new NullPointerException("数据行不存在, key = " + key);  
        	result = addWidthExpire(keyId, obj);
        	if(result) {
                retCode = ADD;
            } else {
                retCode = ERROR;
            }
        }  
        else {
	        result = redisTemplate.execute(new RedisCallback<Boolean>() {
	            @Override
                public Boolean doInRedis(RedisConnection connection)
	                    throws DataAccessException {
	                RedisSerializer<String> serializer = getStringRedisSerializer();
	                RedisSerializer<Object> objectSerializer = (RedisSerializer<Object>) getObjectRedisSerializer();
	                byte[] key  = serializer.serialize(keyId);  
	                byte[] byteObj = objectSerializer.serialize(obj); 
	                connection.set(key, byteObj);
	                return true;
	            }  
	        });  
	        if(result) {
                retCode = UPDATE;
            } else {
                retCode = ERROR;
            }
        }
        return retCode;  
    }
	
	/**  
     * 新增 
     * @param keyId
     * @Param obj
     * @return 
     */  
	@SuppressWarnings("unchecked")
    public boolean addWidthExpire(final String keyId, final Object obj) {  
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
            	RedisSerializer<String> stringSerializer = getStringRedisSerializer();
            	RedisSerializer<Object> objectSerializer = (RedisSerializer<Object>) getObjectRedisSerializer();
                byte[] key  = stringSerializer.serialize(keyId);  
                byte[] byteObj = objectSerializer.serialize(obj);  
                boolean flag = connection.setNX(key, byteObj); 
                if(flag) {
                    connection.expire(key, 3600);
                }
                return flag;
            }  
        });  
        return result;  
    } 
	
	/**  
     * 通过key获取 
     * @param keyId
     * @return 
     */  
    @SuppressWarnings("unchecked")
    public Object get(final String keyId) {  
        Object result = redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
				RedisSerializer<String> serializer = getStringRedisSerializer();
                RedisSerializer<Object> objectSerializer = (RedisSerializer<Object>) getObjectRedisSerializer();
                byte[] key = serializer.serialize(keyId);  
                byte[] value = connection.get(key);  
                if (value == null) {  
                    return null;  
                }  
                Object obj = objectSerializer.deserialize(value);  
                return obj;  
            }  
        });  
        return result;  
    } 
    
    /** 
     * 删除一个 
     * @param key
     */  
    public long delete(final String key) {
    	long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection)
            		throws DataAccessException {
                long result = connection.del(key.getBytes());
                return result;
            }
        });
    	return result;
    }

    /**
     * 批量删除
     * @param pattern
     */
    public void deleteKeys(final K pattern) {
        Set<K> keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }

    /**
     * 设置redisTemplate 
     * @param redisTemplate the redisTemplate to set 
     */  
    public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }  

    public RedisTemplate<K,V> getRedisTemplate() {
        return this.redisTemplate;
    }

    /** 
     * 获取 RedisSerializer 
     */
    protected RedisSerializer<String> getStringRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }
    
	protected RedisSerializer<?> getObjectRedisSerializer() {
        return redisTemplate.getValueSerializer();
    }
}
