package me.alanfoster.application.services;

import me.alanfoster.application.models.IEmployee;
import java.util.Map;
/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface IEmployeeService extends IService<IEmployee, Integer> {
    Map<Integer, String> getJobTitles();
}
