package projecttwo.app.server.service.organization.contactmanagement;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import java.util.List;
import projecttwo.app.shared.organization.contactmanagement.CommunicationType;
import java.util.Map;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "Service for CommunicationType Master table Entity", complexity = Complexity.LOW)
public abstract class CommunicationTypeService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> save(CommunicationType entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<CommunicationType> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(CommunicationType entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<CommunicationType> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> search(Map<String, Object> fieldData) throws Exception;

    abstract HttpEntity<ResponseBean> findByCommGroupId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
