package projecttwo.app.server.businessservice.appbasicsetup.usermanagement;
import projecttwo.app.shared.appbasicsetup.usermanagement.SMSConfig;

import projecttwo.app.bean.SMSData;

import projecttwo.app.server.repository.appbasicsetup.usermanagement.SMSNotificationRepository;

import projecttwo.app.shared.appbasicsetup.usermanagement.SMSNotification;

import java.util.List;

public interface SMSNotificationBusinessService {

	/**
	 * Method for sending SMS to single user
	 * 
	 * @param smsConfig
	 * @param smsTemplate
	 * @param mobileNo
	 * @return
	 * @throws Exception
	 */
	boolean sendSMS(final SMSConfig smsConfig, final SMSData smsData, String mobileNo) throws Exception;

	/**
	 * Method for sending SMS to multiple user
	 * 
	 * @param smsConfig
	 * @param smsTemplate
	 * @param mobileNos
	 * @return
	 * @throws Exception
	 */
	boolean sendSMS(final SMSConfig smsConfig, final SMSData smsData, final List<String> mobileNos) throws Exception;

}
