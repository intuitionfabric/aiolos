/*******************************************************************************
 * AIOLOS  - Framework for dynamic distribution of software components at runtime.
 * Copyright (C) 2014-2016  iMinds - IBCN - UGent
 *
 * This file is part of AIOLOS.
 *
 * AIOLOS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contributors:
 *     Tim Verbelen, Steven Bohez, Elias Deconinck
 *******************************************************************************/
package be.iminds.aiolos.ui;

import java.util.Dictionary;
import java.util.Hashtable;

import javax.servlet.Servlet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;

import be.iminds.aiolos.util.log.Logger;

public class Activator implements BundleActivator {
	
	public static Logger logger; 

	
	@Override
	public void start(final BundleContext context) throws Exception {
		logger = new Logger(context);
		logger.open();
		ServiceReference<?> sRef = context.getServiceReference(HttpService.class.getName());
	    if (sRef != null)
	    {
			CommonServlet servlet = new NodeServlet(context);
			Dictionary<String,Object> props = new Hashtable<String,Object>();
			props.put("felix.webconsole.label", servlet.LABEL);
			props.put("felix.webconsole.title", servlet.TITLE);
			props.put("felix.webconsole.css", servlet.CSS);
			props.put("felix.webconsole.category", servlet.CATEGORY);
			//props.put("alias", "/"); //whiteboard
			context.registerService(Servlet.class.getName(), servlet, props);
			
			servlet = new ComponentServlet(context);
			props = new Hashtable<String,Object>();
			props.put("felix.webconsole.label", servlet.LABEL);
			props.put("felix.webconsole.title", servlet.TITLE);
			props.put("felix.webconsole.css", servlet.CSS);
			props.put("felix.webconsole.category", servlet.CATEGORY);
			//props.put("alias", "/"); //whiteboard
			context.registerService(Servlet.class.getName(), servlet, props);
			
			//HttpService service = (HttpService) context.getService(sRef);
			//service.registerResources("/system/console/aiolos/res", "/res", null);
	    }
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		logger.close();
	}

}
