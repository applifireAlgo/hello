package projecttwo.app.server.businessservice.appinsight;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.stereotype.Component;
import com.athena.server.dataengine.bizService.QueryExecuterService;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.Override;
import java.util.List;
import projecttwo.app.shared.appinsight.FetchCouRM;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "1", comments = "FetchCouBzServiceImpl", complexity = Complexity.HIGH)
@Component
public class FetchCouBzServiceImpl implements FetchCouBzService {

    @Autowired
    private QueryExecuterService queryExecuterService;

    @Override
    public List<FetchCouRM> executeQueryFetchCou() throws Exception {
        java.util.List<projecttwo.app.shared.appinsight.FetchCouRM> listDtoInterface = new java.util.ArrayList<projecttwo.app.shared.appinsight.FetchCouRM>();
        try {
            atg.taglib.json.util.JSONObject queryParams = new atg.taglib.json.util.JSONObject();
            queryParams.put("queryId", "C226A56A-292F-4DB4-809C-422DFEEB32A9");
            atg.taglib.json.util.JSONArray jsonArray = new atg.taglib.json.util.JSONArray();
            queryParams.put("queryCriteria", jsonArray);
            listDtoInterface = queryExecuterService.getAllQueryData("projecttwo.app.shared.appinsight.FetchCouRM", queryParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDtoInterface;
    }
}
