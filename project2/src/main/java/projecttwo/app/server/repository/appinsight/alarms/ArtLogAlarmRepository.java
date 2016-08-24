package projecttwo.app.server.repository.appinsight.alarms;
import projecttwo.app.shared.appinsight.alarms.ArtLogAlarm;

import org.springframework.context.annotation.Scope;

@Scope(value = "request")
public interface ArtLogAlarmRepository {
	void save(ArtLogAlarm artLogAlarm);

	ArtLogAlarm findByAlarmType(int alarmType);

	void update(ArtLogAlarm artLogAlarm);

	String getAlarmDataByType(int alarmType);

	int getVersionNumber();

}
