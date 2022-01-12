package com.geo.api.demo.lookup.dao;

import com.geo.api.demo.Common;
import com.geo.api.demo.lookup.model.District;
import com.geo.api.demo.lookup.model.Division;

import java.util.List;

public interface DatabaseDao {

    public List<? extends Common> getAllFromDB(String com);

    public void saveDivision(Division division);
    public void saveDistrict(District district);

    public void saveAllDivisionToDB(List<Division> list);
}
