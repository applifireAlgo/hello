package projecttwo.app.server.repository.appbasicsetup.usermanagement;
import projecttwo.app.shared.appbasicsetup.usermanagement.ArtDocumentTemplate;

public interface ArtDocumentTemplateRepository {
	ArtDocumentTemplate findById(final String docTempId) throws Exception;
}
