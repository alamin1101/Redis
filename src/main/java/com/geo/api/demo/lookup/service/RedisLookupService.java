package com.geo.api.demo.lookup.service;

import com.geo.api.demo.Common;
import com.geo.api.demo.lookup.Defs;
import com.geo.api.demo.lookup.LookupType;
import com.geo.api.demo.lookup.model.*;
import com.geo.api.demo.masterdata.RedisLookupData;
import com.geo.api.demo.redisService.RedisService;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RedisLookupService {


    @Autowired
    RedisService redisService;
    @Autowired
    DatabaseService databaseService;




    public void saveDivisionToRedis(Division division)
    {
        databaseService.saveDivision(division);
        RedisLookupData lookupData=new RedisLookupData();
        BeanUtils.copyProperties(division,lookupData);
        lookupData.setId(division.getId());
        redisService.save(lookupData,LookupType.division);
    }
    public void saveDistrictToRedis(District district)
    {
        databaseService.saveDistrict(district);
        RedisLookupData lookupData=new RedisLookupData();
        BeanUtils.copyProperties(district,lookupData);
        lookupData.setId(district.getId());
        redisService.save(lookupData,LookupType.district);
    }


    public void syncDatabase()
    {
        Map<Long, RedisLookupData> lookupDataMap=redisService.findAll(LookupType.division);
        List<Map.Entry<Long,RedisLookupData>> entryList=new ArrayList<>(lookupDataMap.entrySet());
        List<Division> divisionList=new ArrayList<>();
        entryList.forEach(eo ->
        {
            Division division=new Division();
            BeanUtils.copyProperties(eo,division);
            division.setId(eo.getValue().getId());
            divisionList.add(division);
        });
        databaseService.saveAllDivisionToDB(divisionList);

    }


    public void syncRedisPublish()
    {
        redisService.saveAll(syncRedisAllFromDB("division"), LookupType.division);
        redisService.saveAll(syncRedisAllFromDB("district"),LookupType.district);
        redisService.saveAll(syncRedisAllFromDB("upazila"),LookupType.upazila);
        redisService.saveAll(syncRedisAllFromDB("union"),LookupType.union);
        redisService.saveAll(syncRedisAllFromDB("nationality"),LookupType.nationality);

    }

    public List<RedisLookupData> getAllDivision()
    {
        List<RedisLookupData> returnList = new ArrayList<>();

        Map<Long, RedisLookupData> data = redisService.findAll(LookupType.division);
        System.err.println(" div : " + data.size());
        List<Map.Entry<Long,RedisLookupData>> sortList = new ArrayList<Map.Entry<Long, RedisLookupData>>(data.entrySet());

        Collections.sort(
                sortList, new Comparator<Map.Entry<Long, RedisLookupData>>() {
                    @Override
                    public int compare(Map.Entry<Long, RedisLookupData> lookup,
                                       Map.Entry<Long, RedisLookupData> lookup2) {
                        return lookup.getValue().getNameInEnglish()
                                .compareTo(lookup2.getValue().getNameInEnglish());
                    }
                }
        );
        final List<RedisLookupData> divList = new ArrayList<>();
        if (sortList != null && sortList.size() > 0) {
            for (int i = 0; i < sortList.size(); i++) {
                divList.add(sortList.get(i).getValue());
            }

        }
        return divList;

    }

    public List<RedisLookupData> getAllDistrict()
    {
        List<RedisLookupData> returnList = new ArrayList<>();

        Map<Long, RedisLookupData> data = redisService.findAll(LookupType.district);
        System.err.println(" div  : " + data.size());
        List< Map.Entry<Long,RedisLookupData>> sortList = new ArrayList<Map.Entry<Long, RedisLookupData>>(data.entrySet());

        Collections.sort(
                sortList, new Comparator<Map.Entry<Long, RedisLookupData>>() {
                    @Override
                    public int compare(Map.Entry<Long, RedisLookupData> lookup,
                                       Map.Entry<Long, RedisLookupData> lookup2) {
                        return lookup.getValue().getNameInEnglish()
                                .compareTo(lookup2.getValue().getNameInEnglish());
                    }
                }
        );
        final List<RedisLookupData> divList = new ArrayList<>();
        if (sortList != null && sortList.size() > 0) {
            for (int i = 0; i < sortList.size(); i++) {
                divList.add(sortList.get(i).getValue());
            }

        }
        return divList;

    }
    public List<RedisLookupData> getAllUpazila()
    {
        List<RedisLookupData> returnList = new ArrayList<>();

        Map<Long, RedisLookupData> data = redisService.findAll(LookupType.upazila);
        System.err.println(" div  : " + data.size());
        List< Map.Entry<Long,RedisLookupData>> sortList = new ArrayList<>(data.entrySet());

        Collections.sort(sortList, new Comparator<Map.Entry<Long, RedisLookupData>>() {
            @Override
            public int compare(Map.Entry<Long, RedisLookupData> o1, Map.Entry<Long, RedisLookupData> o2) {
               return o1.getValue().getNameInBangla().compareTo(o2.getValue().getNameInBangla());
            }
        });
        final List<RedisLookupData> divList = new ArrayList<>();
        if (sortList != null && sortList.size() > 0) {
            for (int i = 0; i < sortList.size(); i++) {
                divList.add(sortList.get(i).getValue());
            }

        }
        return divList;

    }
    public List<RedisLookupData> getAllUnion()
    {
        List<RedisLookupData> returnList = new ArrayList<>();

        Map<Long, RedisLookupData> data = redisService.findAll(LookupType.union);
        System.err.println(" div  : " + data.size());
       List<Map.Entry<Long,RedisLookupData>> sortList= new ArrayList<>(data.entrySet());
       Collections.sort(sortList, new Comparator<Map.Entry<Long, RedisLookupData>>() {
           @Override
           public int compare(Map.Entry<Long, RedisLookupData> o1, Map.Entry<Long, RedisLookupData> o2) {
               return o1.getValue().getNameInEnglish().compareTo(o2.getValue().getNameInEnglish());
           }
       });



        final List<RedisLookupData> divList = new ArrayList<>();
        if (sortList != null && sortList.size() > 0) {
            for (int i = 0; i < sortList.size(); i++) {
                divList.add(sortList.get(i).getValue());
            }

        }
        return divList;

    }
    public List<RedisLookupData> getAllNationality()
    {
        List<RedisLookupData> returnList = new ArrayList<>();

        Map<Long, RedisLookupData> data = redisService.findAll(LookupType.nationality);
        System.err.println(" div  : " + data.size());
        List< Map.Entry<Long,RedisLookupData>> sortList = new ArrayList<Map.Entry<Long, RedisLookupData>>(data.entrySet());
        Collections.sort(sortList, new Comparator<Map.Entry<Long, RedisLookupData>>() {
            @Override
            public int compare(Map.Entry<Long, RedisLookupData> o1, Map.Entry<Long, RedisLookupData> o2) {
                return o1.getValue().getId().compareTo(o2.getValue().getId());
            }
        });

        final List<RedisLookupData> divList = new ArrayList<>();
        if (sortList != null && sortList.size() > 0) {
            for (int i = 0; i < sortList.size(); i++) {
               divList.add(sortList.get(i).getValue());
            }
        }
        return divList;

    }





    public Map<Long, RedisLookupData> syncRedisAllFromDB(String com)
    {
        Map<Long,RedisLookupData> redisLookupDataMap= new HashMap<>();

       try {
           List<? extends Common> list = new ArrayList<>();
           list = this.databaseService.getAllFromDB(com);
           if (com.equals("division"))
           {
               list.forEach(eo ->
               {
                   RedisLookupData lookupData = new RedisLookupData();
                   BeanUtils.copyProperties(eo, lookupData);
                   lookupData.setId(((Division)eo).getId());
                   redisLookupDataMap.put(lookupData.getId(), lookupData);
               });
           }
           else  if (com.equals("district"))
           {
               list.forEach(eo ->
               {
                   RedisLookupData lookupData = new RedisLookupData();
                   BeanUtils.copyProperties(eo, lookupData);
                   lookupData.setId(((District)eo).getId());
                   redisLookupDataMap.put(lookupData.getId(), lookupData);
               });
           }
           else  if (com.equals("upazila"))
           {
               list.forEach(eo ->
               {
                   RedisLookupData lookupData = new RedisLookupData();
                   BeanUtils.copyProperties(eo, lookupData);
                   lookupData.setId(((Upazila)eo).getId());
                   redisLookupDataMap.put(lookupData.getId(), lookupData);
               });
           }
           else  if (com.equals("union"))
           {
               list.forEach(eo ->
               {
                   RedisLookupData lookupData = new RedisLookupData();
                   BeanUtils.copyProperties(eo, lookupData);
                   lookupData.setId(((Union)eo).getId());
                   redisLookupDataMap.put(lookupData.getId(), lookupData);
               });
           }
           else  if (com.equals("nationality"))
           {
               list.forEach(eo ->
               {
                   RedisLookupData lookupData = new RedisLookupData();
                   BeanUtils.copyProperties(eo, lookupData);
                   lookupData.setId(((Nationality)eo).getId());
                   redisLookupDataMap.put(lookupData.getId(), lookupData);
               });
           }
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       return  redisLookupDataMap;


    }

}
