package com.geo.api.demo.redisService;

import com.geo.api.demo.lookup.LookupType;
import com.geo.api.demo.masterdata.RedisLookupData;

import java.util.Map;

public interface RedisService {

    void saveAll(Map<Long, RedisLookupData> lookupData, LookupType type);
    void save(RedisLookupData lookupData , LookupType type);

    Map<Long, RedisLookupData> findAll(LookupType type);


}
