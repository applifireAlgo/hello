package projecttwo.app.server.repository.appbasicsetup.usermanagement;
import com.spartan.server.password.interfaces.PasswordAlgoRepositoryIntefrace;
import projecttwo.app.server.repository.core.SearchInterface;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import com.spartan.server.password.interfaces.PasswordAlgoInterface;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "Repository for PasswordAlgo Master table Entity", complexity = Complexity.LOW)
public interface PasswordAlgoRepository<T> extends SearchInterface, PasswordAlgoRepositoryIntefrace {

    List<PasswordAlgoInterface> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    T findById(String algoId) throws Exception;
}
