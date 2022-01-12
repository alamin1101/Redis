package com.geo.api.demo.lookup.dao;

import com.geo.api.demo.Common;
import com.geo.api.demo.lookup.model.*;
import com.geo.api.demo.lookup.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class DatabaseDaoImpl implements DatabaseDao {

    @Autowired
    DivisionRepository divisionRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    UpazilaRepository upazilaRepository;
    @Autowired
    UnionRepository unionRepository;
    @Autowired
    NationalityRepository nationalityRepository;

    @Override
    public List<? extends Common> getAllFromDB(String com)
    {

        System.out.println("com  :  "+ com);
        if (com.equals("division"))
        {
            List<? extends Common> list = new ArrayList<>();
            try {
                System.out.println("dfjhgjdfh");
                list = this.divisionRepository.findAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

        else  if (com.equals("district"))
        {
            List<District> list = new ArrayList<>();
            try {
                System.out.println("dfjhgjdfh");
                list = this.districtRepository.findAll();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
        else  if (com.equals("upazila")) {
            List<Upazila> list = new ArrayList<>();
            try {
                System.out.println("dfjhgjdfh");
                list = this.upazilaRepository.findAll();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
        else  if (com.equals("union")) {
            List<Union> list = new ArrayList<>();
            try {
                System.out.println("dfjhgjdfh");
                list = this.unionRepository.findAll();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
        else  if (com.equals("nationality")) {
            List<Nationality> list = new ArrayList<>();
            try {
                System.out.println("dfjhgjdfh");
                list = this.nationalityRepository.findAll();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
        else
        {
            List<? extends  Common> list =null;
            return  list;
        }
    }

    @Override
    public void saveDivision(Division division) {
        System.out.println("division"  + division);
        divisionRepository.save(division);
    }
    @Override
    public void saveDistrict(District district) {
        System.out.println("district" +district);
        districtRepository.save(district);
    }

    @Override
    public void saveAllDivisionToDB(List<Division> list) {
        System.out.println("List"+  list);
        divisionRepository.deleteAll();
        divisionRepository.saveAll(list);
    }
}
