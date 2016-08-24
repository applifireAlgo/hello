package projecttwo.app.server.service.organization.locationmanagement;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import java.util.List;
import projecttwo.app.shared.organization.locationmanagement.City;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "Service for City Master table Entity", complexity = Complexity.LOW)
public abstract class CityService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> save(City entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<City> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(City entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<City> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findByCountryId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByStateId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
