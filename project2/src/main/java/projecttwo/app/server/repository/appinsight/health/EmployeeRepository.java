package projecttwo.app.server.repository.appinsight.health;
import projecttwo.app.server.repository.core.SearchInterface;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "1", comments = "Repository for Employee Master table Entity", complexity = Complexity.LOW)
public interface EmployeeRepository<T> extends SearchInterface {

    List<T> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    T findById(String empId) throws Exception;
}
