package com.antonsyzko.recruiting.dao;

import com.antonsyzko.recruiting.domain.Department;
import com.antonsyzko.recruiting.exception.SingleResultNotFoundException;
import com.antonsyzko.recruiting.utils.ResultUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * @author ihor zadyra
 */

@Repository
public class DepartmentRepository extends BaseRepository<Department> {

    public DepartmentRepository() {
        super(Department.class);
    }

    public Department getDepartmentByName(String departmentName) throws SingleResultNotFoundException {
        Query query = em.createQuery("select e from Department e where e.department_name=:name").setParameter("name", departmentName).setMaxResults(1);
        return ResultUtil.result(query);
    }

    public List<Department> getLimitDepartments(String desc, String sortingCriteria, Integer start) {
        final int maxRows = 20;
        Query emQuery = null;
        if (sortingCriteria.equals("department")) {
            emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Department e order by e.department_name asc") : em.createQuery("SELECT e  FROM Department e order by e.department_name desc");
        } else {
            emQuery = desc.equals("asc") ? em.createQuery("SELECT e  FROM Department e order by e.id asc") : em.createQuery("SELECT e  FROM Department e order by e.id desc");
        }

        emQuery.setFirstResult(start);

        emQuery.setMaxResults(maxRows);
        return ResultUtil.resultList(emQuery);

    }

    public Long countAllDepartmentsBySearch(String searchInput) {
        Query emQuery = null;
        if (searchInput.trim().contains(" "))
            emQuery = em.createQuery("SELECT COUNT (b) FROM Department b where b.department_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.department_name LIKE '%" + searchInput.trim().split(" ")[1] + "%'");
        else
            emQuery = em.createQuery("SELECT COUNT (b) FROM Department b where b.department_name LIKE '%" + searchInput.trim() + "%' or b.department_name LIKE '%" + searchInput.trim().trim() + "%'");

        return (Long) emQuery.getResultList().get(0);
    }


    public List<Department> getLimitDepartmentsBySearch(String searchInput, String desc, String sortingCriteria, Integer start) {
        final int maxRows = 20;
        Query emQuery = null;

        if (sortingCriteria.equals("department")) {
            if (searchInput.trim().contains(" "))
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Department b where b.department_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.department_name LIKE '%" + searchInput.trim().split(" ")[1] + "%'  order by b.department_name asc ") : em.createQuery("SELECT b FROM Department b where b.department_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.department_name LIKE '%" + searchInput.trim().split(" ")[1] + "%' order by b.department_name desc ");
            else
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Department b where b.department_name LIKE '%" + searchInput.trim() + "%' and b.department_name LIKE '%" + searchInput.trim() + "%' order by b.department_name asc ") : em.createQuery("SELECT b FROM Department b where b.department_name LIKE '%" + searchInput.trim() + "%' and b.department_name LIKE '%" + searchInput.trim() + "%' order by b.department_name desc ");
        } else {
            if (searchInput.trim().contains(" "))
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Department b where b.department_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.department_name LIKE '%" + searchInput.trim().split(" ")[1] + "%'  order by b.department_name asc ") : em.createQuery("SELECT b FROM Department b where b.department_name LIKE '%" + searchInput.trim().split(" ")[0] + "%' and b.department_name LIKE '%" + searchInput.trim().split(" ")[1] + "%'  order by b.department_name desc ");
            else
                emQuery = desc.equals("asc") ? em.createQuery("SELECT b FROM Department b where b.department_name LIKE '%" + searchInput.trim() + "%'  order by b.department_name asc ") : em.createQuery("SELECT b FROM Department b where b.department_name LIKE '%" + searchInput.trim() + "%'  order by b.department_name  desc ");
        }

        emQuery.setFirstResult(start);
        emQuery.setMaxResults(maxRows);
        return ResultUtil.resultList(emQuery);

    }
}
