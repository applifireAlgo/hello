package projecttwo.app.server.repository.appinsight.health;
import org.springframework.stereotype.Repository;
import projecttwo.app.server.repository.core.SearchInterfaceImpl;
import projecttwo.app.shared.appinsight.health.Bug;
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
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "1", comments = "Repository for Bug Master table Entity", complexity = Complexity.LOW)
public class BugRepositoryImpl extends SearchInterfaceImpl implements BugRepository<Bug> {

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
    public List<Bug> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Bug> query = emanager.createNamedQuery("Bug.findAll").getResultList();
        Log.out.println("AISHI324900200", runtimeLogInfoHelper.getRequestHeaderBean(), "BugRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <Bug> object.
     * @return Bug
     * @Params Object of Bug
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Bug save(Bug entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("AISHI322900200", runtimeLogInfoHelper.getRequestHeaderBean(), "BugRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Bug> object.
     * @return java.util.List<Bug>
     * @Params list of Bug
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Bug> save(List<Bug> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            Bug obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("AISHI322900200", runtimeLogInfoHelper.getRequestHeaderBean(), "BugRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Bug> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Bug object = emanager.find(projecttwo.app.shared.appinsight.health.Bug.class, id);
        emanager.remove(object);
        Log.out.println("AISHI328900200", runtimeLogInfoHelper.getRequestHeaderBean(), "BugRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <Bug> object.
     * @Params Object of Bug
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Bug entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("AISHI321900200", runtimeLogInfoHelper.getRequestHeaderBean(), "BugRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Bug> object.
     * @Params list of Bug
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Bug> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            Bug obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("AISHI321900200", runtimeLogInfoHelper.getRequestHeaderBean(), "BugRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return Bug object by filtering on refernce key <bugId>
     * @return Bug
     * @Params bugId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Bug findById(String bugId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Bug.findById");
        query.setParameter("bugId", bugId);
        Bug listOfBug = (Bug) query.getSingleResult();
        Log.out.println("AISHI324900200", runtimeLogInfoHelper.getRequestHeaderBean(), "BugRepositoryImpl", "findById", "Total Records Fetched = " + listOfBug);
        return listOfBug;
    }
}
