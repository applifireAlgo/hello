package projecttwo.app.server.businessservice.appinsight.health;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projecttwo.app.server.businessservice.appinsight.TestQueryOneBzService;
import projecttwo.app.server.repository.appinsight.health.BugRepository;
import projecttwo.app.shared.appinsight.health.Bug;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import java.lang.Override;

@Component
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "7", comments = "CreateSampleServiceImpl", complexity = Complexity.HIGH)
public class CreateSampleServiceImpl implements CreateSampleService {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private TestQueryOneBzService testQueryOneBzService;

    @Autowired
    private BugRepository<Bug> bugRepository;

    @Override
    public void createSampleService() throws Exception {
        java.util.List<projecttwo.app.shared.appinsight.QueryOneRM> queryOneRMList = testQueryOneBzService.executeQueryQueryOne();
        for (projecttwo.app.shared.appinsight.QueryOneRM queryOneRMListElement : queryOneRMList) {
            projecttwo.app.shared.appinsight.health.Bug bug = new projecttwo.app.shared.appinsight.health.Bug();
            bug.setBugName(queryOneRMListElement.getBugName() + "Issue");
            bug.setComponent(queryOneRMListElement.getComponent());
            bug.setPriority(queryOneRMListElement.getPriority());
            bugRepository.update(bug);
        }
    }
}
