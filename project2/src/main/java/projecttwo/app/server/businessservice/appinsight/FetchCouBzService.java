package projecttwo.app.server.businessservice.appinsight;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import projecttwo.app.shared.appinsight.FetchCouRM;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "1", comments = "FetchCouBzService", complexity = Complexity.HIGH)
public interface FetchCouBzService {

    public List<FetchCouRM> executeQueryFetchCou() throws Exception;
}
