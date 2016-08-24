package projecttwo.app.server.repository.appbasicsetup.usermanagement;
import projecttwo.app.shared.appbasicsetup.usermanagement.ArtAppNotificationTemplate;

public interface ArtAppNotificationTemplateRepository {

	ArtAppNotificationTemplate findById(String templateId) throws Exception;
}
