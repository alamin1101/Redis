package com.geo.api.demo.redisService;

import com.geo.api.demo.lookup.LookupType;
import com.geo.api.demo.masterdata.RedisLookupData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Map;

@Repository
public class RedisServiceImpl implements RedisService{

    HashOperations<String, Long, RedisLookupData> hashOperations;

    private RedisTemplate<String, Object> redisTemplate;


    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Autowired
    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void saveAll(Map<Long, RedisLookupData> lookupData, LookupType type) {
        hashOperations.putAll(type.name(),lookupData);
    }

    @Override
    public void save(RedisLookupData lookupData , LookupType type) {
        hashOperations.put(type.name(),lookupData.getId(),lookupData);
    }

    @Override
    public Map<Long, RedisLookupData> findAll(LookupType type)
    {
        System.out.println("  "+ type.name());
        return hashOperations.entries(type.name());
    }

}
