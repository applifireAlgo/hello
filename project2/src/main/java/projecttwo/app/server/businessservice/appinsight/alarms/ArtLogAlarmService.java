package projecttwo.app.server.businessservice.appinsight.alarms;
import projecttwo.app.shared.appinsight.alarms.ArtDomain;

import org.springframework.http.HttpEntity;

import com.athena.server.pluggable.utils.bean.ResponseBean;

public abstract class ArtLogAlarmService {

	public abstract HttpEntity<ResponseBean> getLogConfigDetails();

	public abstract HttpEntity<ResponseBean> updateLogAlarm(/*ArtDomain logDomain*/);

	public abstract HttpEntity<ResponseBean> getListOfAlarms();

	public abstract HttpEntity<ResponseBean> getGridData(String domainId);
}