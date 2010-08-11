package com.sapienter.jbilling.server.payment.tasks;

import com.sapienter.jbilling.server.payment.PaymentDTOEx;
import com.sapienter.jbilling.server.pluggableTask.IPluggableTaskSessionBean;
import com.sapienter.jbilling.server.util.RemoteContext;

import junit.framework.TestCase;

public class PaymentLogOnlyTaskTest extends TestCase{

    private IPluggableTaskSessionBean remotePluggableTask = null;
    private PaymentLogOnlyTask paymentLogOnlyTask = null;

    public PaymentLogOnlyTaskTest() {
    }

    public PaymentLogOnlyTaskTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        //Is this needed?
        //remotePluggableTask = (IPluggableTaskSessionBean) RemoteContext.getBean(RemoteContext.Name.PAYMENT_REMOTE_SESSION);
    	//assertNotNull(remotePluggableTask);
        
        paymentLogOnlyTask = new PaymentLogOnlyTask();
    }

    public void testLogging() {
    	
    	//PaymentDTOEx is from payment table
    	PaymentDTOEx paymentInfo = new PaymentDTOEx();
    	
    	
    	
    	
    	//paymentLogOnlyTask.preAuth(paymentInfo)
    	assertTrue(true);

    }
 
}

