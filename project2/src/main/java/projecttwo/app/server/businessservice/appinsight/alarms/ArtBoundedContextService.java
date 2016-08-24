package projecttwo.app.server.businessservice.appinsight.alarms;
import projecttwo.app.shared.appinsight.alarms.ArtBoundedContext;

public interface ArtBoundedContextService {

	public String findAll();
	
	public ArtBoundedContext findById(String bcId);
	
	public ArtBoundedContext findByPrefix(String prefix);
	
}
