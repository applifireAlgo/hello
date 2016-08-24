package projecttwo.app.server.repository.appbasicsetup.usermanagement;
import com.spartan.server.password.interfaces.PasswordPolicyRepositoryInterface;
import projecttwo.app.server.repository.core.SearchInterface;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import com.spartan.server.password.interfaces.PasswordPolicyInterface;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "Repository for PasswordPolicy Master table Entity", complexity = Complexity.LOW)
public interface PasswordPolicyRepository<T> extends SearchInterface, PasswordPolicyRepositoryInterface {

    List<PasswordPolicyInterface> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    T findById(String policyId) throws Exception;
}
