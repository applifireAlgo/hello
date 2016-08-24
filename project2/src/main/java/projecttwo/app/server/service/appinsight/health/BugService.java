package projecttwo.app.server.service.appinsight.health;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import java.util.List;
import projecttwo.app.shared.appinsight.health.Bug;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "1", comments = "Service for Bug Master table Entity", complexity = Complexity.LOW)
public abstract class BugService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> save(Bug entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<Bug> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(Bug entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<Bug> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
