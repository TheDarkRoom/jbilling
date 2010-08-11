package com.sapienter.jbilling.server.payment.tasks;

import org.apache.log4j.Logger;

import com.sapienter.jbilling.server.payment.event.PaymentFailedEvent;
import com.sapienter.jbilling.server.payment.event.PaymentSuccessfulEvent;
import com.sapienter.jbilling.server.pluggableTask.PluggableTask;
import com.sapienter.jbilling.server.pluggableTask.admin.PluggableTaskException;
import com.sapienter.jbilling.server.system.event.Event;
import com.sapienter.jbilling.server.system.event.task.IInternalEventsTask;


public class HelloPaymentTask extends PluggableTask implements IInternalEventsTask{

	private static final Class<Event> events[] = new Class[]{ PaymentFailedEvent.class, PaymentSuccessfulEvent.class };
	
	private static final Logger LOG = Logger.getLogger(HelloPaymentTask.class);	
	
	@Override
	public Class<Event>[] getSubscribedEvents() {

		LOG.debug("HelloPaymentTask - The getSubscribedEvents called. Returning events of size:"+events.length);		
		return events;
	}

	@Override
	public void process(Event event) throws PluggableTaskException {
		
		if (event instanceof PaymentFailedEvent) {
			
			PaymentFailedEvent failed = (PaymentFailedEvent) event;
			LOG.debug("HelloPaymentTask - The payment " + failed.getPayment() + " failed");
			
		}
		else if (event instanceof PaymentSuccessfulEvent) {
			
			PaymentSuccessfulEvent success = (PaymentSuccessfulEvent) event;
			LOG.debug("HelloPaymentTask - The payment " + success.getPayment() +" succeeded");
		} 
		else {
			throw new PluggableTaskException("Cant not process event "+ event);
		}
		
	}
	
	

}
