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

package com.evolveum.midpoint.wf.impl;

import com.evolveum.midpoint.prism.Containerable;
import com.evolveum.midpoint.prism.PrismContext;
import com.evolveum.midpoint.prism.PrismObject;
import com.evolveum.midpoint.prism.query.ObjectQuery;
import com.evolveum.midpoint.schema.GetOperationOptions;
import com.evolveum.midpoint.schema.SearchResultList;
import com.evolveum.midpoint.schema.SelectorOptions;
import com.evolveum.midpoint.schema.result.OperationResult;
import com.evolveum.midpoint.task.api.Task;
import com.evolveum.midpoint.util.exception.ObjectNotFoundException;
import com.evolveum.midpoint.util.exception.SchemaException;
import com.evolveum.midpoint.util.exception.SecurityViolationException;
import com.evolveum.midpoint.wf.api.ProcessListener;
import com.evolveum.midpoint.wf.api.WorkItemListener;
import com.evolveum.midpoint.wf.api.WorkflowManager;
import com.evolveum.midpoint.wf.impl.activiti.dao.ProcessInstanceManager;
import com.evolveum.midpoint.wf.impl.activiti.dao.ProcessInstanceProvider;
import com.evolveum.midpoint.wf.impl.activiti.dao.WorkItemManager;
import com.evolveum.midpoint.wf.impl.activiti.dao.WorkItemProvider;
import com.evolveum.midpoint.wf.impl.tasks.WfTaskController;
import com.evolveum.midpoint.wf.impl.tasks.WfTaskUtil;
import com.evolveum.midpoint.wf.impl.util.MiscDataUtil;
import com.evolveum.midpoint.wf.util.ApprovalUtils;
import com.evolveum.midpoint.xml.ns._public.common.common_3.ObjectReferenceType;
import com.evolveum.midpoint.xml.ns._public.common.common_3.ObjectType;
import com.evolveum.midpoint.xml.ns._public.common.common_3.WorkItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author mederly
 */
@Component("workflowManager")
public class WorkflowManagerImpl implements WorkflowManager {

    //private static final transient Trace LOGGER = TraceManager.getTrace(WorkflowManagerImpl.class);

    @Autowired
    private PrismContext prismContext;
    
    @Autowired
    private WfConfiguration wfConfiguration;

    @Autowired
    private ProcessInstanceProvider processInstanceProvider;

    @Autowired
    private ProcessInstanceManager processInstanceManager;

    @Autowired
    private WfTaskController wfTaskController;

    @Autowired
    private WorkItemProvider workItemProvider;

    @Autowired
    private WorkItemManager workItemManager;

    @Autowired
    private WfTaskUtil wfTaskUtil;

    @Autowired
    private MiscDataUtil miscDataUtil;

    private static final String DOT_INTERFACE = WorkflowManager.class.getName() + ".";


    /*
     * Work items
     * ==========
     */

    @Override
    public <T extends Containerable> Integer countContainers(Class<T> type, ObjectQuery query, Collection<SelectorOptions<GetOperationOptions>> options, OperationResult parentResult)
            throws SchemaException {
        OperationResult result = parentResult.createSubresult(DOT_INTERFACE + ".countContainers");
        result.addParams(new String[] { "type", "query" }, type, query);
        result.addCollectionOfSerializablesAsParam("options", options);
		try {
			if (!WorkItemType.class.equals(type)) {
				throw new UnsupportedOperationException("countContainers is available only for work items");
			}
			return workItemProvider.countWorkItems(query, options, result);
		} catch (SchemaException|RuntimeException e) {
			result.recordFatalError("Couldn't count items: " + e.getMessage(), e);
			throw e;
		} finally {
			result.computeStatusIfUnknown();
		}
    }

	@SuppressWarnings("unchecked")
    @Override
    public <T extends Containerable> SearchResultList<T> searchContainers(Class<T> type, ObjectQuery query, Collection<SelectorOptions<GetOperationOptions>> options, OperationResult parentResult)
            throws SchemaException {
		OperationResult result = parentResult.createSubresult(DOT_INTERFACE + ".searchContainers");
		result.addParams(new String[] { "type", "query" }, type, query);
		result.addCollectionOfSerializablesAsParam("options", options);
		try {
			if (!WorkItemType.class.equals(type)) {
				throw new UnsupportedOperationException("searchContainers is available only for work items");
			}
			return (SearchResultList<T>) workItemProvider.searchWorkItems(query, options, result);
		} catch (SchemaException|RuntimeException e) {
			result.recordFatalError("Couldn't count items: " + e.getMessage(), e);
			throw e;
		} finally {
			result.computeStatusIfUnknown();
		}
    }

    @Override
    public void approveOrRejectWorkItem(String taskId, boolean decision, String comment, OperationResult parentResult)
			throws SecurityViolationException {
        workItemManager.completeWorkItem(taskId, ApprovalUtils.approvalStringValue(decision), comment, parentResult);
    }

    @Override
    public void claimWorkItem(String workItemId, OperationResult result) throws ObjectNotFoundException, SecurityViolationException {
        workItemManager.claimWorkItem(workItemId, result);
    }

    @Override
    public void releaseWorkItem(String workItemId, OperationResult result) throws SecurityViolationException, ObjectNotFoundException {
        workItemManager.releaseWorkItem(workItemId, result);
    }

    /*
     * Process instances
     * =================
     */

    @Override
    public void stopProcessInstance(String instanceId, String username, OperationResult parentResult) {
        processInstanceManager.stopProcessInstance(instanceId, username, parentResult);
    }

    @Override
    public void deleteProcessInstance(String instanceId, OperationResult parentResult) {
        processInstanceManager.deleteProcessInstance(instanceId, parentResult);
    }

    /*
     * Tasks
     * =====
     */

    @Override
    public <T extends ObjectType> void augmentTaskObject(PrismObject<T> object,
            Collection<SelectorOptions<GetOperationOptions>> options, Task task, OperationResult result) {
        processInstanceProvider.augmentTaskObject(object, options, task, result);
    }

    @Override
    public <T extends ObjectType> void augmentTaskObjectList(SearchResultList<PrismObject<T>> list,
            Collection<SelectorOptions<GetOperationOptions>> options, Task task, OperationResult result) {
        processInstanceProvider.augmentTaskObjectList(list, options, task, result);
    }

    /*
     * Other
     * =====
     */

    @Override
    public boolean isEnabled() {
        return wfConfiguration.isEnabled();
    }

    @Override
    public PrismContext getPrismContext() {
        return prismContext;
    }

    public WfTaskUtil getWfTaskUtil() {
        return wfTaskUtil;
    }

    public MiscDataUtil getMiscDataUtil() {
        return miscDataUtil;
    }

    @Override
    public void registerProcessListener(ProcessListener processListener) {
        wfTaskController.registerProcessListener(processListener);
    }

    @Override
    public void registerWorkItemListener(WorkItemListener workItemListener) {
        wfTaskController.registerWorkItemListener(workItemListener);
    }

    @Override
    public List<? extends ObjectReferenceType> getApprovedBy(Task task, OperationResult result) throws SchemaException {
        return wfTaskUtil.getApprovedByFromTaskTree(task, result);
    }

    @Override
    public boolean isCurrentUserAuthorizedToSubmit(WorkItemType workItem) {
        return miscDataUtil.isAuthorizedToSubmit(workItem);
    }

    @Override
    public boolean isCurrentUserAuthorizedToClaim(WorkItemType workItem) {
        return miscDataUtil.isAuthorizedToClaim(workItem);
    }
}