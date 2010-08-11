package com.sapienter.jbilling.server.payment.tasks;

import org.apache.log4j.Logger;

import com.sapienter.jbilling.server.payment.PaymentDTOEx;
import com.sapienter.jbilling.server.payment.db.PaymentAuthorizationDTO;
import com.sapienter.jbilling.server.pluggableTask.PaymentTask;
import com.sapienter.jbilling.server.pluggableTask.PaymentTaskWithTimeout;
import com.sapienter.jbilling.server.pluggableTask.admin.PluggableTaskException;


public class PaymentLogOnlyTask extends PaymentTaskWithTimeout implements PaymentTask {

    private static final Logger LOG = Logger.getLogger(PaymentLogOnlyTask.class);	
	
	@Override
	public boolean confirmPreAuth(PaymentAuthorizationDTO auth,
			PaymentDTOEx paymentInfo) throws PluggableTaskException {
		
		LOG.debug("PaymentLogOnlyTask confirmPreAuth called");		
		return true;
	}

	@Override
	public void failure(Integer userId, Integer retry) {
		// TODO Auto-generated method stub
		LOG.debug("PaymentLogOnlyTask failure called");		
		
	}

	@Override
	public boolean preAuth(PaymentDTOEx paymentInfo)
			throws PluggableTaskException {
		LOG.debug("PaymentLogOnlyTask preAuth called");	
		return false;
	}

	@Override
	public boolean process(PaymentDTOEx paymentInfo)
			throws PluggableTaskException {
		LOG.debug("PaymentLogOnlyTask process called");	
		return false;
	} 

}
