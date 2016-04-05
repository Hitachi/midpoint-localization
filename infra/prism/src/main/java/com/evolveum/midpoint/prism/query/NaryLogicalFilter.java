/*
 * Copyright (c) 2010-2013 Evolveum
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.evolveum.midpoint.prism.query;

import java.util.ArrayList;
import java.util.List;

public abstract class NaryLogicalFilter extends LogicalFilter {
	
	public NaryLogicalFilter() {
		super();
	}
	
	public NaryLogicalFilter(List<ObjectFilter> conditions) {
		super();
		setConditions(conditions);
	}

	@Override
	public String debugDump() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String debugDump(int indent) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectFilter getLastCondition() {
		List<ObjectFilter> conditions = getConditions();
		if (conditions.isEmpty()) {
			return null;
		} else {
			return conditions.get(conditions.size()-1);
		}
	}
}