package projecttwo.app.server.repository.appbasicsetup.userrolemanagement;
import projecttwo.app.server.repository.core.SearchInterface;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "Repository for UserRoleBridge Transaction table", complexity = Complexity.MEDIUM)
public interface UserRoleBridgeRepository<T> extends SearchInterface {

    List<T> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    List<T> findByRoleId(String roleId) throws Exception;

    List<T> findByUserId(String userId) throws Exception;

    T findById(String roleUserMapId) throws Exception;
}
