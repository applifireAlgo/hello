package projecttwo.app.server.repository.appinsight.health;
import org.springframework.stereotype.Repository;
import projecttwo.app.server.repository.core.SearchInterfaceImpl;
import projecttwo.app.shared.appinsight.health.Employee;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import javax.persistence.EntityManager;
import java.lang.Override;
import java.util.List;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "1", comments = "Repository for Employee Master table Entity", complexity = Complexity.LOW)
public class EmployeeRepositoryImpl extends SearchInterfaceImpl implements EmployeeRepository<Employee> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * Method for fetching list of entities
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Employee> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Employee> query = emanager.createNamedQuery("Employee.findAll").getResultList();
        Log.out.println("AISHI324900200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <Employee> object.
     * @return Employee
     * @Params Object of Employee
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Employee save(Employee entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("AISHI322900200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Employee> object.
     * @return java.util.List<Employee>
     * @Params list of Employee
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Employee> save(List<Employee> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            Employee obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("AISHI322900200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Employee> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Employee object = emanager.find(projecttwo.app.shared.appinsight.health.Employee.class, id);
        emanager.remove(object);
        Log.out.println("AISHI328900200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <Employee> object.
     * @Params Object of Employee
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Employee entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("AISHI321900200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Employee> object.
     * @Params list of Employee
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Employee> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            Employee obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("AISHI321900200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return Employee object by filtering on refernce key <empId>
     * @return Employee
     * @Params empId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Employee findById(String empId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Employee.findById");
        query.setParameter("empId", empId);
        Employee listOfEmployee = (Employee) query.getSingleResult();
        Log.out.println("AISHI324900200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeRepositoryImpl", "findById", "Total Records Fetched = " + listOfEmployee);
        return listOfEmployee;
    }
}
