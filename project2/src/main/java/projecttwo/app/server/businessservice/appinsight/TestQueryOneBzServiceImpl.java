package projecttwo.app.server.businessservice.appinsight;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.stereotype.Component;
import com.athena.server.dataengine.bizService.QueryExecuterService;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.Override;
import java.util.List;
import projecttwo.app.shared.appinsight.QueryOneRM;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "1", comments = "TestQueryOneBzServiceImpl", complexity = Complexity.HIGH)
@Component
public class TestQueryOneBzServiceImpl implements TestQueryOneBzService {

    @Autowired
    private QueryExecuterService queryExecuterService;

    @Override
    public List<QueryOneRM> executeQueryQueryOne() throws Exception {
        java.util.List<projecttwo.app.shared.appinsight.QueryOneRM> listDtoInterface = new java.util.ArrayList<projecttwo.app.shared.appinsight.QueryOneRM>();
        try {
            atg.taglib.json.util.JSONObject queryParams = new atg.taglib.json.util.JSONObject();
            queryParams.put("queryId", "74BFBB00-9F21-4B74-8C1E-CCA3FB577522");
            atg.taglib.json.util.JSONArray jsonArray = new atg.taglib.json.util.JSONArray();
            queryParams.put("queryCriteria", jsonArray);
            listDtoInterface = queryExecuterService.getAllQueryData("projecttwo.app.shared.appinsight.QueryOneRM", queryParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDtoInterface;
    }
}
