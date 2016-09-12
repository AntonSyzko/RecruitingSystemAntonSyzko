package com.antonsyzko.recruiting.dao;

import com.antonsyzko.recruiting.domain.Employee;
import com.antonsyzko.recruiting.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * @author ihor zadyra
 */

@Repository
public class EmployeeRepository extends BaseRepository<Employee> {

    static Logger logger = LoggerFactory.getLogger(EmployeeRepository.class);

    public EmployeeRepository() {
        super(Employee.class);
    }

    public List<Employee> getLimitEmployeesBySearch(String searchInput, String desc, String sortingCriteria, Integer start) {
        final int maxRows = 20;
        Query emQuery = null;
        if (sortingCriteria.equals("last_name")) {
            if (searchInput.trim().contains(" "))
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.last_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' or b.first_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' and b.last_name LIKE '%" + searchInput.trim().trim().split(" ")[0] + "%' order by b.last_name asc ") : em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.last_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' or b.first_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' and b.last_name LIKE '%" + searchInput.trim().trim().split(" ")[0] + "%' order by b.last_name desc");
            else
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim() + "%' or b.last_name LIKE '%" + searchInput.trim().trim() + "%' order by b.last_name asc") : em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim() + "%' or b.last_name LIKE '%" + searchInput.trim().trim() + "%' order by b.last_name desc");

        } else if (sortingCriteria.equals("first_name")) {
            if (searchInput.trim().contains(" "))
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.last_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' or b.first_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' and b.last_name LIKE '%" + searchInput.trim().trim().split(" ")[0] + "%' order by b.first_name asc ") : em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.last_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' or b.first_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' and b.last_name LIKE '%" + searchInput.trim().trim().split(" ")[0] + "%' order by b.first_name desc");
            else
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim() + "%' or b.last_name LIKE '%" + searchInput.trim().trim() + "%' order by b.first_name asc") : em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim() + "%' or b.last_name LIKE '%" + searchInput.trim().trim() + "%' order by b.first_name desc");


        } else if (sortingCriteria.equals("department")) {
            if (searchInput.trim().contains(" "))
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.last_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' or b.first_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' and b.last_name LIKE '%" + searchInput.trim().trim().split(" ")[0] + "%' order by b.department.department_name asc ") : em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.last_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' or b.first_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' and b.last_name LIKE '%" + searchInput.trim().trim().split(" ")[0] + "%' order by b.department.department_name desc");
            else
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim() + "%' or b.last_name LIKE '%" + searchInput.trim().trim() + "%' order by b.department.department_name asc") : em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim() + "%' or b.last_name LIKE '%" + searchInput.trim().trim() + "%' order by b.department.department_name desc");

        } else if (sortingCriteria.equals("salary")) {
            if (searchInput.trim().contains(" "))
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.last_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' or b.first_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' and b.last_name LIKE '%" + searchInput.trim().trim().split(" ")[0] + "%' order by b.salary asc ") : em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.last_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' or b.first_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' and b.last_name LIKE '%" + searchInput.trim().trim().split(" ")[0] + "%' order by b.salary desc");
            else
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim() + "%' or b.last_name LIKE '%" + searchInput.trim().trim() + "%' order by b.salary asc") : em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim() + "%' or b.last_name LIKE '%" + searchInput.trim().trim() + "%' order by b.salary desc");

        } else if (sortingCriteria.equals("recruited_date")) {
            if (searchInput.trim().contains(" "))
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.last_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' or b.first_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' and b.last_name LIKE '%" + searchInput.trim().trim().split(" ")[0] + "%' order by b.recruited_date asc ") : em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.last_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' or b.first_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' and b.last_name LIKE '%" + searchInput.trim().trim().split(" ")[0] + "%' order by b.recruited_date desc");
            else
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim() + "%' or b.last_name LIKE '%" + searchInput.trim().trim() + "%' order by b.recruited_date asc") : em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim() + "%' or b.last_name LIKE '%" + searchInput.trim().trim() + "%' order by b.recruited_date desc");

        } else if (sortingCriteria.equals("working")) {
            if (searchInput.trim().contains(" "))
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.last_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' or b.first_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' and b.last_name LIKE '%" + searchInput.trim().trim().split(" ")[0] + "%' order by b.is_active asc ") : em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.last_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' or b.first_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' and b.last_name LIKE '%" + searchInput.trim().trim().split(" ")[0] + "%' order by b.is_active desc");
            else
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim() + "%' or b.last_name LIKE '%" + searchInput.trim().trim() + "%' order by b.is_active asc") : em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim() + "%' or b.last_name LIKE '%" + searchInput.trim().trim() + "%' order by b.is_active desc");

        } else if (sortingCriteria.equals("id")) {
            if (searchInput.trim().contains(" "))
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.last_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' or b.first_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' and b.last_name LIKE '%" + searchInput.trim().trim().split(" ")[0] + "%' order by b.id asc ") : em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.last_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' or b.first_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' and b.last_name LIKE '%" + searchInput.trim().trim().split(" ")[0] + "%' order by b.id desc");
            else
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim() + "%' or b.last_name LIKE '%" + searchInput.trim().trim() + "%' order by b.id asc") : em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim() + "%' or b.last_name LIKE '%" + searchInput.trim().trim() + "%' order by b.id desc");

        } else {
            if (searchInput.trim().contains(" "))
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.last_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' or b.first_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' and b.last_name LIKE '%" + searchInput.trim().trim().split(" ")[0] + "%' order by b.id asc ") : em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.last_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' or b.first_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' and b.last_name LIKE '%" + searchInput.trim().trim().split(" ")[0] + "%' order by b.id desc");
            else
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim() + "%' or b.last_name LIKE '%" + searchInput.trim().trim() + "%' order by b.id asc") : em.createQuery("SELECT b FROM Employee b where b.first_name LIKE '%" + searchInput.trim() + "%' or b.last_name LIKE '%" + searchInput.trim().trim() + "%' order by b.id desc");
        }
        emQuery.setFirstResult(start);
        emQuery.setMaxResults(maxRows);
        return ResultUtil.resultList(emQuery);

    }


    public List<Employee> getLimitEmployees(String desc, String sortingCriteria, Integer start) {
        final int maxRows = 20;
        Query emQuery = null;
        if (sortingCriteria.equals("last_name")) {
            emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e order by e.last_name asc") : em.createQuery("SELECT e  FROM Employee e order by e.last_name desc");
        } else if (sortingCriteria.equals("first_name")) {
            emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e order by e.first_name asc") : em.createQuery("SELECT e  FROM Employee e order by e.first_name desc");
        } else if (sortingCriteria.equals("department")) {
            emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e order by e.department.department_name asc") : em.createQuery("SELECT e  FROM Employee e order by e.department.department_name desc");
        } else if (sortingCriteria.equals("salary")) {
            emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e order by e.salary asc") : em.createQuery("SELECT e  FROM Employee e order by e.salary desc");
        } else if (sortingCriteria.equals("recruited_date")) {
            emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e order by e.recruited_date asc") : em.createQuery("SELECT e  FROM Employee e order by e.recruited_date desc");
        } else if (sortingCriteria.equals("working")) {
            emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e order by e.is_active asc") : em.createQuery("SELECT e  FROM Employee e order by e.is_active desc");
        } else if (sortingCriteria.equals("id")) {
            emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e order by e.id asc") : em.createQuery("SELECT e  FROM Employee e order by e.id desc");
        } else {
            emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e order by e.id asc") : em.createQuery("SELECT e  FROM Employee e order by e.id desc");
        }
        emQuery.setFirstResult(start);
        emQuery.setMaxResults(maxRows);
        return ResultUtil.resultList(emQuery);

    }


    public List<Employee> getLimitEmployeesByCategory(String categoryName, String categoryValue, String desc, String sortingCriteria, int start) {
        final int maxRows = 20;
        Query emQuery = null;
        if (categoryName.equals("department")) {
            if (sortingCriteria.equals("last_name")) {
                emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e where e.department.department_name=:department order by e.last_name asc").setParameter("department", categoryValue) : em.createQuery("SELECT e  FROM Employee e where e.department.department_name=:department order by e.last_name desc").setParameter("department", categoryValue);
            } else if (sortingCriteria.equals("first_name")) {
                emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e where e.department.department_name=:department order by e.first_name asc").setParameter("department", categoryValue) : em.createQuery("SELECT e  FROM Employee e where e.department.department_name=:department order by e.first_name desc").setParameter("department", categoryValue);
            } else if (sortingCriteria.equals("department")) {
                emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e where e.department.department_name=:department order by e.department.department_name asc").setParameter("department", categoryValue) : em.createQuery("SELECT e  FROM Employee e where e.department.department_name=:department order by e.department.department_name desc").setParameter("department", categoryValue);
            } else if (sortingCriteria.equals("salary")) {
                emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e where e.department.department_name=:department order by e.salary asc").setParameter("department", categoryValue) : em.createQuery("SELECT e  FROM Employee e where e.department.department_name=:department order by e.salary desc").setParameter("department", categoryValue);
            } else if (sortingCriteria.equals("recruited_date")) {
                emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e where e.department.department_name=:department order by e.recruited_date asc").setParameter("department", categoryValue) : em.createQuery("SELECT e  FROM Employee e where e.department.department_name=:department order by e.recruited_date desc").setParameter("department", categoryValue);
            } else if (sortingCriteria.equals("working")) {
                emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e where e.department.department_name=:department order by e.is_active asc").setParameter("department", categoryValue) : em.createQuery("SELECT e  FROM Employee e where e.department.department_name=:department order by e.is_active desc").setParameter("department", categoryValue);
            } else if (sortingCriteria.equals("id")) {
                emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e where e.department.department_name=:department order by e.id asc").setParameter("department", categoryValue) : em.createQuery("SELECT e  FROM Employee e where e.department.department_name=:department order by e.id desc").setParameter("department", categoryValue);
            } else {
                emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e where e.department.department_name=:department order by e.id asc").setParameter("department", categoryValue) : em.createQuery("SELECT e  FROM Employee e where e.department.department_name=:department order by e.id desc").setParameter("department", categoryValue);
            }
        } else {
            Date recruited_date = null;
            try {
                recruited_date = new SimpleDateFormat("yyyy-MM-dd").parse(categoryValue);
            } catch (ParseException e) {
                logger.error("ParseException '{}", e);
                emQuery = em.createQuery("SELECT e  FROM Employee e order by e.id asc");
                emQuery.setFirstResult(start);
                emQuery.setMaxResults(maxRows);
                return ResultUtil.resultList(emQuery);
            }

            if (sortingCriteria.equals("last_name")) {
                emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e where e.recruited_date=:recruited_date order by e.last_name asc").setParameter("recruited_date", new Timestamp(recruited_date.getTime()), TemporalType.TIMESTAMP) : em.createQuery("SELECT e  FROM Employee e where e.recruited_date=:recruited_date order by e.last_name desc ").setParameter("recruited_date", new Timestamp(recruited_date.getTime()), TemporalType.TIMESTAMP);
            } else if (sortingCriteria.equals("first_name")) {
                emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e where e.recruited_date=:recruited_date order by e.first_name asc").setParameter("recruited_date", new Timestamp(recruited_date.getTime()), TemporalType.TIMESTAMP) : em.createQuery("SELECT e  FROM Employee e where e.recruited_date=:recruited_date order by e.first_name desc ").setParameter("recruited_date", new Timestamp(recruited_date.getTime()), TemporalType.TIMESTAMP);
            } else if (sortingCriteria.equals("department")) {
                emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e where e.recruited_date=:recruited_date order by e.department.department_name asc").setParameter("recruited_date", new Timestamp(recruited_date.getTime()), TemporalType.TIMESTAMP) : em.createQuery("SELECT e  FROM Employee e where e.recruited_date=:recruited_date order by e.department.department_name desc ").setParameter("recruited_date", new Timestamp(recruited_date.getTime()), TemporalType.TIMESTAMP);
            } else if (sortingCriteria.equals("salary")) {
                emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e where e.recruited_date=:recruited_date order by e.salary asc").setParameter("recruited_date", new Timestamp(recruited_date.getTime()), TemporalType.TIMESTAMP) : em.createQuery("SELECT e  FROM Employee e where e.recruited_date=:recruited_date order by e.salary desc ").setParameter("recruited_date", new Timestamp(recruited_date.getTime()), TemporalType.TIMESTAMP);
            } else if (sortingCriteria.equals("recruited_date")) {
                emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e where e.recruited_date=:recruited_date order by e.recruited_date asc").setParameter("recruited_date", new Timestamp(recruited_date.getTime()), TemporalType.TIMESTAMP) : em.createQuery("SELECT e  FROM Employee e where e.recruited_date=:recruited_date order by e.recruited_date desc ").setParameter("recruited_date", new Timestamp(recruited_date.getTime()), TemporalType.TIMESTAMP);
            } else if (sortingCriteria.equals("working")) {
                emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e where e.recruited_date=:recruited_date order by e.is_active asc").setParameter("recruited_date", new Timestamp(recruited_date.getTime()), TemporalType.TIMESTAMP) : em.createQuery("SELECT e  FROM Employee e where e.recruited_date=:recruited_date order by e.is_active desc ").setParameter("recruited_date", new Timestamp(recruited_date.getTime()), TemporalType.TIMESTAMP);
            } else if (sortingCriteria.equals("id")) {
                emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e where e.recruited_date=:recruited_date order by e.id asc").setParameter("recruited_date", new Timestamp(recruited_date.getTime()), TemporalType.TIMESTAMP) : em.createQuery("SELECT e  FROM Employee e where e.recruited_date=:recruited_date order by e.id desc ").setParameter("recruited_date", new Timestamp(recruited_date.getTime()), TemporalType.TIMESTAMP);
            } else {
                emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Employee e where e.recruited_date=:recruited_date order by e.id asc").setParameter("recruited_date", new Timestamp(recruited_date.getTime()), TemporalType.TIMESTAMP) : em.createQuery("SELECT e  FROM Employee e where e.recruited_date=:recruited_date order by e.id desc ").setParameter("recruited_date", new Timestamp(recruited_date.getTime()), TemporalType.TIMESTAMP);
            }
        }
        emQuery.setFirstResult(start);
        emQuery.setMaxResults(maxRows);
        return ResultUtil.resultList(emQuery);

    }

    public Long countAllEmployeesByCategory(String categoryName, String categoryValue) {
        Query emQuery = null;
        if (categoryName.equals("department")) {
            emQuery = em.createQuery("select COUNT (e) from  Employee e where e.department.department_name=:categoryValue").setParameter("categoryValue", categoryValue);

        } else {

            Date recruited_date = null;
            try {
                recruited_date = new SimpleDateFormat("yyyy-MM-dd").parse(categoryValue);
            } catch (ParseException e) {
                logger.error("ParseException '{}", e);

                return 0L;
            }
            emQuery = em.createQuery("select COUNT (e) from  Employee e where e.recruited_date=:categoryValue").setParameter("categoryValue", new Timestamp(recruited_date.getTime()), TemporalType.TIMESTAMP);
        }
        return (Long) emQuery.getResultList().get(0);
    }


    public Long countAllEmployeesBySearch(String searchInput) {
        Query emQuery = null;
        if (searchInput.trim().contains(" "))
            emQuery = em.createQuery("SELECT COUNT (b) FROM Employee b where b.first_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.last_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' or b.first_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' and b.last_name LIKE '%" + searchInput.trim().trim().split(" ")[0] + "%'");
        else
            emQuery = em.createQuery("SELECT COUNT (b) FROM Employee b where b.first_name LIKE '%" + searchInput.trim() + "%' or b.last_name LIKE '%" + searchInput.trim().trim() + "%'");

        return (Long) emQuery.getResultList().get(0);
    }
}
