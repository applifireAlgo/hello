package projecttwo.app.server.businessservice.appinsight;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import projecttwo.app.shared.appinsight.QueryOneRM;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "1", comments = "TestQueryOneBzService", complexity = Complexity.HIGH)
public interface TestQueryOneBzService {

    public List<QueryOneRM> executeQueryQueryOne() throws Exception;
}
