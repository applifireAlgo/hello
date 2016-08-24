package projecttwo.app.server.repository.appbasicsetup.userrolemanagement;
import projecttwo.app.server.repository.core.SearchInterface;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import projecttwo.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "Repository for Roles Transaction table", complexity = Complexity.MEDIUM)
public interface RolesRepository<T> extends SearchInterface {

    List<T> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    public void deleteRoleMenuBridge(List<RoleMenuBridge> rolemenubridge);

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    T findById(String roleId) throws Exception;
}
