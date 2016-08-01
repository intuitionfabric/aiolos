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
package be.iminds.aiolos.monitor.component;

import java.util.ArrayList;
import java.util.List;

import be.iminds.aiolos.info.ServiceInfo;

public class MonitoredMethodCall {

	String methodName;
	ServiceInfo service;
	
	MonitoredMethodCall parent = null;
	List<MonitoredMethodCall> children = new ArrayList<MonitoredMethodCall>();
	
	long start; // start time
	long end;  // end time
	long self = 0; // self time (without time of child method calls)
	
	long argSize;
	long retSize;
	
	public MonitoredMethodCall(String methodName, ServiceInfo service){
		this.methodName = methodName;
		this.service = service;
	}
}