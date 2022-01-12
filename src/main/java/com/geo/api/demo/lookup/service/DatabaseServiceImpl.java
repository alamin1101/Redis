package com.geo.api.demo.lookup.service;

import com.geo.api.demo.Common;
import com.geo.api.demo.lookup.dao.DatabaseDao;
import com.geo.api.demo.lookup.model.District;
import com.geo.api.demo.lookup.model.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    DatabaseDao databaseDao;

    @Override
    public List<? extends Common> getAllFromDB(String com) {
        return databaseDao.getAllFromDB(com);
    }

    @Override
    public void saveDivision(Division division) {
        databaseDao.saveDivision(division);
    }

    @Override
    public void saveDistrict(District district) {
        databaseDao.saveDistrict(district);
    }

    @Override
    public void saveAllDivisionToDB(List<Division> list) {
        databaseDao.saveAllDivisionToDB(list);
    }
}
