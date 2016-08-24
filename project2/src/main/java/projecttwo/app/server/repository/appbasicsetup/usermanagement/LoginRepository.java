package projecttwo.app.server.repository.appbasicsetup.usermanagement;
import projecttwo.app.server.repository.core.SearchInterface;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "Repository for Login Transaction table", complexity = Complexity.MEDIUM)
public interface LoginRepository<T> extends SearchInterface {

    List<T> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    List<T> findByContactId(String contactId) throws Exception;

    List<T> findByUserId(String userId) throws Exception;

    T findById(String loginPk) throws Exception;

    List<T> FindUnMappedUser() throws Exception;

    List<T> FindMappedUser() throws Exception;
}
