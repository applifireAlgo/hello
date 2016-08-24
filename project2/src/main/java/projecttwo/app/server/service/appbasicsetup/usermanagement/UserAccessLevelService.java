package projecttwo.app.server.service.appbasicsetup.usermanagement;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import java.util.List;
import projecttwo.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "Service for UserAccessLevel Master table Entity", complexity = Complexity.LOW)
public abstract class UserAccessLevelService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> save(UserAccessLevel entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<UserAccessLevel> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(UserAccessLevel entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<UserAccessLevel> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
