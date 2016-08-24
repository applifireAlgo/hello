package projecttwo.app.server.businessservice.appinsight.alarms;
import projecttwo.app.shared.appinsight.alarms.ArtDomain;

import java.util.List;

public interface ArtDomainService {
	
	public List<ArtDomain> findAll();
	
	public ArtDomain findById(String domainId);
	
	public List<ArtDomain> findByArtBoundedContext(String boundedContextId);
	
	public ArtDomain findByPrefix(String prefix);
}
