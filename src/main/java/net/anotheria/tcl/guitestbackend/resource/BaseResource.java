package net.anotheria.tcl.guitestbackend.resource;

import net.anotheria.moskito.aop.annotation.TagParameter;
import net.anotheria.tcl.guitestbackend.notification.NotificationSlackSender;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 24.08.18 14:16
 */
public class BaseResource {
	protected NotificationSlackSender notificationSlackSender = NotificationSlackSender.getInstance();

	protected void setUserName(@TagParameter(name = "username") String userName){
		
	}
}
