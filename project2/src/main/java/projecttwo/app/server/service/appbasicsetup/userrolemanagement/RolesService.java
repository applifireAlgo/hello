package projecttwo.app.server.service.appbasicsetup.userrolemanagement;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import java.util.List;
import projecttwo.app.shared.appbasicsetup.userrolemanagement.Roles;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "Service for Roles Transaction table", complexity = Complexity.MEDIUM)
public abstract class RolesService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> save(Roles entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<Roles> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(Roles entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<Roles> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
