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
package com.evolveum.midpoint.model.intest;

import static com.evolveum.midpoint.test.IntegrationTestTools.display;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import com.evolveum.icf.dummy.resource.DummyGroup;
import com.evolveum.midpoint.prism.Containerable;
import com.evolveum.midpoint.prism.PrismContainer;
import com.evolveum.midpoint.prism.path.ItemPath;
import com.evolveum.midpoint.schema.GetOperationOptions;
import com.evolveum.midpoint.schema.SelectorOptions;
import com.evolveum.midpoint.schema.result.OperationResultStatus;
import com.evolveum.midpoint.schema.util.MiscSchemaUtil;
import com.evolveum.midpoint.test.DummyResourceContoller;
import com.evolveum.midpoint.test.IntegrationTestTools;
import com.evolveum.midpoint.util.exception.ObjectNotFoundException;
import com.evolveum.midpoint.util.exception.SchemaException;
import com.evolveum.midpoint.xml.ns._public.common.common_2a.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.evolveum.midpoint.prism.PrismObject;
import com.evolveum.midpoint.prism.PrismReferenceValue;
import com.evolveum.midpoint.prism.delta.ChangeType;
import com.evolveum.midpoint.prism.delta.ObjectDelta;
import com.evolveum.midpoint.prism.delta.ReferenceDelta;
import com.evolveum.midpoint.prism.util.PrismAsserts;
import com.evolveum.midpoint.prism.util.PrismTestUtil;
import com.evolveum.midpoint.schema.result.OperationResult;
import com.evolveum.midpoint.task.api.Task;
import com.evolveum.midpoint.test.util.TestUtil;
import com.evolveum.midpoint.util.MiscUtil;

/**
 * Test of account-entitlement association.
 * 
 * @author Radovan Semancik
 *
 */
@ContextConfiguration(locations = {"classpath:ctx-model-intest-test-main.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class TestEntitlements extends AbstractInitializedModelIntegrationTest {
	
	public static final File TEST_DIR = new File("src/test/resources/entitlements");
	
	public static final File ROLE_SWASHBUCKLER_FILE = new File(TEST_DIR, "role-swashbuckler.xml");
	public static final String ROLE_SWASHBUCKLER_OID = "10000000-0000-0000-0000-000000001601";
	
	public static final File SHADOW_GROUP_DUMMY_SWASHBUCKLERS_FILE = new File(TEST_DIR, "group-swashbucklers.xml");
	public static final String SHADOW_GROUP_DUMMY_SWASHBUCKLERS_OID = "20000000-0000-0000-3333-000000000001";
	public static final String GROUP_DUMMY_SWASHBUCKLERS_NAME = "swashbucklers";
	public static final String GROUP_DUMMY_SWASHBUCKLERS_DESCRIPTION = "Scurvy swashbucklers";

	private static final QName RESOURCE_DUMMY_GROUP_OBJECTCLASS = new QName(RESOURCE_DUMMY_NAMESPACE, "GroupObjectClass");

    @Override
    public void initSystem(Task initTask, OperationResult initResult) throws Exception {
        super.initSystem(initTask, initResult);

        importObjectFromFile(ROLE_SWASHBUCKLER_FILE);
    }
    
    /**
     * Add group shadow using model service. Group should appear on dummy resource.
     */
    @Test
    public void test100AddGroupShadowSwashbucklers() throws Exception {
		final String TEST_NAME = "test100AddGroupShadowSwashbucklers";
        TestUtil.displayTestTile(this, TEST_NAME);

        Task task = taskManager.createTaskInstance(TestEntitlements.class.getName() + "." + TEST_NAME);
        OperationResult result = task.getResult();
        
        
        PrismObject<ShadowType> group = prismContext.parseObject(SHADOW_GROUP_DUMMY_SWASHBUCKLERS_FILE);
        ObjectDelta<ShadowType> delta = group.createAddDelta();
        Collection<ObjectDelta<? extends ObjectType>> deltas = MiscSchemaUtil.createCollection(delta);
        
		// WHEN
        modelService.executeChanges(deltas, null, task, result);
        
        // THEN
        result.computeStatus();
        TestUtil.assertSuccess(result);
        
        DummyGroup dummyGroup = dummyResource.getGroupByName(GROUP_DUMMY_SWASHBUCKLERS_NAME);
        assertNotNull("No group created on dummy resource", dummyGroup);
        display("Group", dummyGroup);
        assertEquals("Wrong group description", GROUP_DUMMY_SWASHBUCKLERS_DESCRIPTION, 
        		dummyGroup.getAttributeValue(DummyResourceContoller.DUMMY_GROUP_ATTRIBUTE_DESCRIPTION));
	}
    
    @Test
    public void test101GetGroupShadowSwashbucklers() throws Exception {
		final String TEST_NAME = "test101GetGroupShadowSwashbucklers";
        TestUtil.displayTestTile(this, TEST_NAME);

        Task task = taskManager.createTaskInstance(TestEntitlements.class.getName() + "." + TEST_NAME);
        OperationResult result = task.getResult();
        
		// WHEN
        PrismObject<ShadowType> shadow = modelService.getObject(ShadowType.class, SHADOW_GROUP_DUMMY_SWASHBUCKLERS_OID, null, task, result);
        
        // THEN
        result.computeStatus();
        TestUtil.assertSuccess(result);
        display("Shadow", shadow);
        
        assertShadowModel(shadow, SHADOW_GROUP_DUMMY_SWASHBUCKLERS_OID, GROUP_DUMMY_SWASHBUCKLERS_NAME, resourceDummyType, RESOURCE_DUMMY_GROUP_OBJECTCLASS);
        IntegrationTestTools.assertAttribute(shadow, resourceDummyType, 
        		DummyResourceContoller.DUMMY_GROUP_ATTRIBUTE_DESCRIPTION, GROUP_DUMMY_SWASHBUCKLERS_DESCRIPTION);
	}
    
    /**
     * Add account to a group using model service (shadow operations).
     */
    @Test
    public void test110AssociateGuybrushToSwashbucklers() throws Exception {
		final String TEST_NAME = "test110AssociateGuybrushToSwashbucklers";
        TestUtil.displayTestTile(this, TEST_NAME);

        Task task = taskManager.createTaskInstance(TestEntitlements.class.getName() + "." + TEST_NAME);
        OperationResult result = task.getResult();
        
        ObjectDelta<ShadowType> delta = IntegrationTestTools.createEntitleDelta(ACCOUNT_SHADOW_GUYBRUSH_OID, 
				dummyResourceCtl.getAttributeQName(DummyResourceContoller.DUMMY_ENTITLEMENT_GROUP_NAME),
				SHADOW_GROUP_DUMMY_SWASHBUCKLERS_OID, prismContext);
        Collection<ObjectDelta<? extends ObjectType>> deltas = MiscSchemaUtil.createCollection(delta);
        
		// WHEN
        modelService.executeChanges(deltas, null, task, result);
        
        // THEN
        result.computeStatus();
        TestUtil.assertSuccess(result);
        
        DummyGroup dummyGroup = dummyResource.getGroupByName(GROUP_DUMMY_SWASHBUCKLERS_NAME);
        assertNotNull("No group created on dummy resource", dummyGroup);
        display("Group", dummyGroup);
        assertEquals("Wrong group description", GROUP_DUMMY_SWASHBUCKLERS_DESCRIPTION, 
        		dummyGroup.getAttributeValue(DummyResourceContoller.DUMMY_GROUP_ATTRIBUTE_DESCRIPTION));
        IntegrationTestTools.assertMember(dummyGroup, ACCOUNT_GUYBRUSH_DUMMY_USERNAME);
	}

    @Test(enabled=false) // WORK IN PROGRESS
    public void test200AssignRoleSwashbucklerToJack() throws Exception {
		final String TEST_NAME = "test200AssignRoleSwashbucklerToJack";
        TestUtil.displayTestTile(this, TEST_NAME);

        Task task = taskManager.createTaskInstance(TestEntitlements.class.getName() + "." + TEST_NAME);
        OperationResult result = task.getResult();
        
        ObjectDelta<ShadowType> delta = IntegrationTestTools.createEntitleDelta(ACCOUNT_SHADOW_GUYBRUSH_OID, 
				dummyResourceCtl.getAttributeQName(DummyResourceContoller.DUMMY_ENTITLEMENT_GROUP_NAME),
				SHADOW_GROUP_DUMMY_SWASHBUCKLERS_OID, prismContext);
        Collection<ObjectDelta<? extends ObjectType>> deltas = MiscSchemaUtil.createCollection(delta);
        
		// WHEN
        assignRole(USER_JACK_OID, ROLE_SWASHBUCKLER_OID, task, result);
        
        // THEN
        result.computeStatus();
        TestUtil.assertSuccess(result);
        
        PrismObject<UserType> user = getUser(USER_JACK_OID);
        display("User jack", user);
        // TODO: assert role assignment
        
        DummyGroup dummyGroup = dummyResource.getGroupByName(GROUP_DUMMY_SWASHBUCKLERS_NAME);
        assertNotNull("No group on dummy resource", dummyGroup);
        display("Group", dummyGroup);
        assertEquals("Wrong group description", GROUP_DUMMY_SWASHBUCKLERS_DESCRIPTION, 
        		dummyGroup.getAttributeValue(DummyResourceContoller.DUMMY_GROUP_ATTRIBUTE_DESCRIPTION));
        IntegrationTestTools.assertMember(dummyGroup, ACCOUNT_GUYBRUSH_DUMMY_USERNAME);
        IntegrationTestTools.assertMember(dummyGroup, ACCOUNT_JACK_DUMMY_USERNAME);
	}
}
