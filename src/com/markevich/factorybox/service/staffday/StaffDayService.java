package com.markevich.factorybox.service.staffday;

import businessObjectFactoryBox.Day;
import businessObjectFactoryBox.StaffDays;
import com.markevich.factorybox.service.interfaces.Service;

import java.util.List;

public class StaffDayService implements Service<StaffDays> {


    @Override
    public List<StaffDays> loadAll() {
        LoadAllStaffDay loadAllStaffDay = new LoadAllStaffDay();
        return loadAllStaffDay.loadAllStaffDay();
    }

    @Override
    public StaffDays loadById(String id) {
        LoadStaffDayByID loadStaffDayByID = new LoadStaffDayByID();
        return loadStaffDayByID.loadStaffDayById(id);
    }

    @Override
    public void save(StaffDays staffDay) {
        SaveStaffDay saveStaffDay = new SaveStaffDay();
        saveStaffDay.saveStaffDay(staffDay);

    }

    @Override
    public void update(StaffDays staffDay) {
        UpdateStaffDay updateStaffDay = new UpdateStaffDay();
        updateStaffDay.updateStaffDay(staffDay);
    }

    @Override
    public void delete(String id) {
        DeleteStaffDay deleteStaffDay = new DeleteStaffDay();
        deleteStaffDay.deleteStaffDay(id);
    }


    public void deleteDay(Day day) {
        DeleteStaffDayDay deleteStaffDayDay = new DeleteStaffDayDay();
        deleteStaffDayDay.deleteStaffDayDay(day);
    }
}
