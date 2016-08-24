package projecttwo.app.server.service.appbasicsetup.usermanagement;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import java.util.List;
import projecttwo.app.shared.appbasicsetup.usermanagement.PasswordPolicy;
import java.util.Map;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "Service for PasswordPolicy Master table Entity", complexity = Complexity.LOW)
public abstract class PasswordPolicyService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> save(PasswordPolicy entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<PasswordPolicy> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(PasswordPolicy entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<PasswordPolicy> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> search(Map<String, Object> fieldData) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
