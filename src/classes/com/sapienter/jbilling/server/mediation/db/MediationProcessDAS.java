/*
    jBilling - The Enterprise Open Source Billing System
    Copyright (C) 2003-2009 Enterprise jBilling Software Ltd. and Emiliano Conde

    This file is part of jbilling.

    jbilling is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    jbilling is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with jbilling.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.sapienter.jbilling.server.mediation.db;

import java.util.List;

import org.hibernate.Query;

import com.sapienter.jbilling.server.util.db.AbstractDAS;

public class MediationProcessDAS extends AbstractDAS<MediationProcess> {

    // QUERIES
    private static final String findAllByEntitySQL =
        "SELECT b " +
        "  FROM MediationProcess b " + 
        " WHERE b.configuration.entityId = :entity " +
        " ORDER BY id DESC";

    public List<MediationProcess> findAllByEntity(Integer entityId) {
        Query query = getSession().createQuery(findAllByEntitySQL);
        query.setParameter("entity", entityId);
        //return query.getResultList();
        return query.list();
    }

    public void touch(List<MediationProcess> list) {
    	super.touch(list, "getOrdersAffected");
    	for(MediationProcess proc: list) {
    		proc.getConfiguration().getCreateDatetime();
    	}
    }
}