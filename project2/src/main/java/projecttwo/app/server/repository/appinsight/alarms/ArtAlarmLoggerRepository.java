package projecttwo.app.server.repository.appinsight.alarms;
import projecttwo.app.shared.appinsight.alarms.ArtLogConfig;

import projecttwo.app.shared.appinsight.alarms.ArtLogSeverity;

import java.util.List;

public interface ArtAlarmLoggerRepository {
	void saveLoggerConfig(ArtLogConfig awsLogConfig);

	void updateLoggerConfig(ArtLogConfig awsLogConfig);

	void mergeSeverity(ArtLogSeverity radLogSeverity);

	ArtLogSeverity getBySeverityId(int severityId);

	void persistSeverity(ArtLogSeverity awsLogSeverity);

	List<ArtLogConfig> findAll();

	List<ArtLogSeverity> getBySeverity(Integer severity);
}
