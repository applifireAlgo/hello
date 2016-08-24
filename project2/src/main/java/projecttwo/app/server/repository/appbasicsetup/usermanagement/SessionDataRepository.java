package projecttwo.app.server.repository.appbasicsetup.usermanagement;
import com.spartan.server.interfaces.LoginSessionDataRepository;
import projecttwo.app.server.repository.core.SearchInterface;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "Repository for SessionData Transaction table", complexity = Complexity.MEDIUM)
public interface SessionDataRepository<T> extends SearchInterface, LoginSessionDataRepository {
}
