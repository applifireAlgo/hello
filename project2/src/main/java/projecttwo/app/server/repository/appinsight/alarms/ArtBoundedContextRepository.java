package projecttwo.app.server.repository.appinsight.alarms;
import projecttwo.app.shared.appinsight.alarms.ArtBoundedContext;

import java.util.List;

public interface ArtBoundedContextRepository {

	void save(ArtBoundedContext artBoundedContext);

	void update(ArtBoundedContext artBoundedContext);

	ArtBoundedContext findByBoundedContextPrefix(String boundedContextPrefix);

	List<ArtBoundedContext> findAll();
	
	ArtBoundedContext findById(String boundedContextId);

}
