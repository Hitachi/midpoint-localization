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
package com.evolveum.midpoint.model.lens;


import java.io.File;

/**
 * @author semancik
 *
 */
public class LensTestConstants {
	
	public static final File TEST_RESOURCE_DIR = new File("src/test/resources/lens");
	public static final File TEST_FOLDER_COMMON = new File("./src/test/resources/common");
	
	public static final File ASSIGNMENT_DIRECT_FILE = new File(TEST_RESOURCE_DIR, "assignment-direct.xml");
	
	public static final File USER_DRAKE_FILE = new File(TEST_RESOURCE_DIR, "user-drake.xml");

//	public static final String REQ_USER_JACK_MODIFY_ADD_ASSIGNMENT_ACCOUNT_OPENDJ = TEST_RESOURCE_DIR_NAME +
//            "/user-jack-modify-add-assignment-account-opendj.xml";
	
	public static final File REQ_USER_JACK_MODIFY_ADD_ASSIGNMENT_ACCOUNT_DUMMY = new File(TEST_RESOURCE_DIR, 
    		"user-jack-modify-add-assignment-account-dummy.xml");

	public static final File REQ_USER_JACK_MODIFY_ADD_ASSIGNMENT_ACCOUNT_DUMMY_ATTR = new File(TEST_RESOURCE_DIR,
    		"user-jack-modify-add-assignment-account-dummy-attr.xml");

	public static final File REQ_USER_JACK_MODIFY_DELETE_ASSIGNMENT_ACCOUNT_DUMMY = new File(TEST_RESOURCE_DIR,
			"user-jack-modify-delete-assignment-account-dummy.xml");
	
	public static final File REQ_USER_BARBOSSA_MODIFY_ADD_ASSIGNMENT_ACCOUNT_DUMMY_ATTR = new File(TEST_RESOURCE_DIR,
            "user-barbossa-modify-add-assignment-account-dummy-attr.xml");
	
	public static final File REQ_USER_BARBOSSA_MODIFY_DELETE_ASSIGNMENT_ACCOUNT_DUMMY_ATTR = new File(TEST_RESOURCE_DIR,
            "user-barbossa-modify-delete-assignment-account-dummy-attr.xml");

}
