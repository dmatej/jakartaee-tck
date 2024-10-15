/*
 * Copyright (c) 2007, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

/*
 * @(#)dateTimeClient1.java	1.18 03/05/16
 */

/*
 * @(#)dateTimeClient1.java	1.16 02/08/27
 */

package com.sun.ts.tests.jdbc.ee.dateTime.dateTime1;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.sun.ts.lib.harness.Status;

import tck.arquillian.porting.lib.spi.TestArchiveProcessor;
import tck.arquillian.protocol.common.TargetVehicle;


// Merant DataSource class
//import com.merant.sequelink.jdbcx.datasource.*;

/**
 * The dateTimeClient1 class tests methods of Timestamp class using Sun's J2EE
 * Reference Implementation.
 * 
 * @author
 * @version 1.7, 06/16/99
 */

@Tag("tck-appclient")

public class dateTimeClient1AppClient extends dateTimeClient1 implements Serializable {
  private static final String testName = "jdbc.ee.dateTime.dateTime1";
  
  @TargetsContainer("tck-appclient")
  @OverProtocol("appclient")
	@Deployment(name = "appclient",  testable = true)
	public static EnterpriseArchive createDeploymentAppclient(@ArquillianResource TestArchiveProcessor archiveProcessor) throws IOException {
		JavaArchive archive = ShrinkWrap.create(JavaArchive.class, "dateTime1_appclient_vehicle_client.jar");
		archive.addPackages(true, "com.sun.ts.tests.jdbc.ee.common");
		archive.addPackages(false, "com.sun.ts.tests.common.vehicle");
		archive.addPackages(true, "com.sun.ts.lib.harness");
		archive.addClasses(dateTimeClient1AppClient.class, dateTimeClient1.class);
		  // The appclient-client descriptor
	     URL appClientUrl = dateTimeClient1AppClient.class.getResource("/com/sun/ts/tests/jdbc/ee/dateTime/dateTime1/appclient_vehicle_client.xml");
	     if(appClientUrl != null) {
	     	archive.addAsManifestResource(appClientUrl, "application-client.xml");
	     }
	     // The sun appclient-client descriptor
	     URL sunAppClientUrl = dateTimeClient1AppClient.class.getResource("//com/sun/ts/tests/common/vehicle/appclient/appclient_vehicle_client.jar.sun-application-client.xml");
	     if(sunAppClientUrl != null) {
	     	archive.addAsManifestResource(sunAppClientUrl, "sun-application-client.xml");
	     }
		 	archive.addAsManifestResource(
					new StringAsset("Main-Class: " + "com.sun.ts.tests.common.vehicle.VehicleClient" + "\n"),
					"MANIFEST.MF");

	     // Call the archive processor
	     archiveProcessor.processClientArchive(archive, dateTimeClient1AppClient.class, sunAppClientUrl);
		  	EnterpriseArchive ear = ShrinkWrap.create(EnterpriseArchive.class, "dateTime1_appclient_vehicle.ear");
		 		ear.addAsModule(archive);

		 		return ear;
	};


  /* Run test in standalone mode */
  public static void main(String[] args) {
    dateTimeClient1AppClient theTests = new dateTimeClient1AppClient();
    Status s = theTests.run(args, System.out, System.err);
    s.exit();
  }

  /*
   * @testName: testTimestamp01
   * 
   * @assertion_ids: JDBC:JAVADOC:32;
   * 
   * @test_Strategy: Create a Timestamp Object with a long value as an argument.
   * Then get the String representation of that Timestamp object. Check whether
   * it is same as equivalent String Value in the property file.
   */
	@Test
	@TargetVehicle("appclient")
  public void testTimestamp01() throws Exception {
		super.testTimestamp01();
  }

  /*
   * @testName: testTimestamp02
   * 
   * @assertion_ids: JDBC:JAVADOC:32;
   * 
   * @test_Strategy: Create a Timestamp Object with a long value as an argument.
   * Then get the String representation of that Timestamp object. Check whether
   * it is same as equivalent String Value in the property file.
   */
	@Test
	@TargetVehicle("appclient")
  public void testTimestamp02() throws Exception {
		super.testTimestamp02();
  }

  /*
   * @testName: testSetNanos01
   * 
   * @assertion_ids: JDBC:JAVADOC:38;
   * 
   * @test_Strategy: Get a Timestamp object and call the setNanos(int n) method
   * and call getNanos() to check and it should return an Integer value that is
   * been set
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetNanos01() throws Exception {
		super.testSetNanos01();
  }

  /*
   * @testName: testSetNanos02
   * 
   * @assertion_ids: JDBC:JAVADOC:38; JDBC:JAVADOC:37;
   * 
   * 
   * @test_Strategy: Get a Timestamp object and call the setNanos(int n) method
   * and call getNanos() to check and it should return an Integer value that is
   * been set
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetNanos02() throws Exception {
		super.testSetNanos02();
  }

  /*
   * @testName: testSetNanos03
   * 
   * @assertion_ids: JDBC:JAVADOC:38;
   * 
   * @test_Strategy: Get a Timestamp object and call the setNanos(int n) method
   * with the invalid value of argument and it should throw
   * IllegalArgumentException
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetNanos03() throws Exception {
		super.testSetNanos03();
  }

  /*
   * @testName: testSetNanos04
   * 
   * @assertion_ids: JDBC:JAVADOC:38;
   * 
   * @test_Strategy: Get a Timestamp object and call the setNanos(int n) method
   * with an invalid value as argument and it should throw
   * IllegalArgumentException
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetNanos04() throws Exception {
		super.testSetNanos04();
  }

  /*
   * @testName: testGetNanos
   * 
   * @assertion_ids: JDBC:JAVADOC:37;
   * 
   * @test_Strategy: Get a Timestamp object and call the getNanos() method. It
   * should return an Integer value.
   */
	@Test
	@TargetVehicle("appclient")
  public void testGetNanos() throws Exception {
		super.testGetNanos();
  }

  /*
   * @testName: testToString01
   * 
   * @assertion_ids: JDBC:JAVADOC:36;
   * 
   * @test_Strategy: Create a Timestamp Object with a long value as an argument.
   * Then get the String representation of that Timestamp object. using the
   * toString() method.Check whether it is same as equivalent String Value in
   * property file.
   */
	@Test
	@TargetVehicle("appclient")
  public void testToString01() throws Exception {
		super.testToString01();
  }

  /*
   * @testName: testToString02
   * 
   * @assertion_ids: JDBC:JAVADOC:36;
   * 
   * @test_Strategy: Create a Timestamp Object with a long value as an argument.
   * Then get the String representation of that Timestamp object. using the
   * toString() method.Check whether it is same as equivalent String Value in
   * property file.
   * 
   */
	@Test
	@TargetVehicle("appclient")
  public void testToString02() throws Exception {
		super.testToString02();
  }

  /*
   * @testName: testAfter01
   * 
   * @assertion_ids: JDBC:JAVADOC:42;
   * 
   * @test_Strategy: Get a Timestamp object and call the after(Timestamp ts)
   * method with the value of ts is after than the Timestamp It should return a
   * boolean value and the value should be equal to true
   */
	@Test
	@TargetVehicle("appclient")
  public void testAfter01() throws Exception {
		super.testAfter01();
  }

  /*
   * @testName: testAfter02
   * 
   * @assertion_ids: JDBC:JAVADOC:42;
   * 
   * @test_Strategy: Get a Timestamp object and call the after(Timestamp ts)
   * method with the value of ts is not after than the Timestamp It should
   * return a boolean value and the value should be equal to false
   */
	@Test
	@TargetVehicle("appclient")
  public void testAfter02() throws Exception {
		super.testAfter02();
  }

  /*
   * @testName: testAfter03
   * 
   * @assertion_ids: JDBC:JAVADOC:42;
   * 
   * @test_Strategy: Get a Timestamp object and call the after(Timestamp ts)
   * method with the value of ts is after than the Timestamp in Nano second
   * level It should return a boolean value and the value should be equal to
   * true
   */
	@Test
	@TargetVehicle("appclient")
  public void testAfter03() throws Exception {
		super.testAfter03();
  }

  /*
   * @testName: testAfter04
   * 
   * @assertion_ids: JDBC:JAVADOC:42;
   * 
   * @test_Strategy: Get a Timestamp object and call the after(Timestamp ts)
   * method with the value of ts is not after than the Timestamp with Nano
   * seconds level It should return a boolean value and the value should be
   * equal to false
   */
	@Test
	@TargetVehicle("appclient")
  public void testAfter04() throws Exception {
		super.testAfter04();
  }

  /*
   * @testName: testBefore01
   * 
   * @assertion_ids: JDBC:JAVADOC:41;
   * 
   * @test_Strategy: Get a Timestamp object and call the before(Timestamp ts)
   * method with the value of ts is before than the Timestamp It should return a
   * boolean value and the value should be equal to true
   */
	@Test
	@TargetVehicle("appclient")
  public void testBefore01() throws Exception {
		super.testBefore01();
  }

  /*
   * @testName: testBefore02
   * 
   * @assertion_ids: JDBC:JAVADOC:41;
   * 
   * @test_Strategy: Get a Timestamp object and call the before(Timestamp ts)
   * method with the value of ts is not before than the Timestamp It should
   * return a boolean value and the value should be equal to false
   *
   */
	@Test
	@TargetVehicle("appclient")
  public void testBefore02() throws Exception {
		super.testBefore02();
  }

  /*
   * @testName: testBefore03
   * 
   * @assertion_ids: JDBC:JAVADOC:41;
   * 
   * @test_Strategy: Get a Timestamp object and call the before(Timestamp ts)
   * method with the value of ts is before than the Timestamp in Nano second
   * level It should return a boolean value and the value should be equal to
   * true.
   * 
   */
	@Test
	@TargetVehicle("appclient")
  public void testBefore03() throws Exception {
		super.testBefore03();
  }

  /*
   * @testName: testBefore04
   * 
   * @assertion_ids: JDBC:JAVADOC:41;
   * 
   * @test_Strategy: Get a Timestamp object and call the before(Timestamp ts)
   * method with the value of ts is not before than the Timestamp with Nano
   * seconds level It should return a boolean value and the value should be
   * equal to false
   */
	@Test
	@TargetVehicle("appclient")
  public void testBefore04() throws Exception {
		super.testBefore04();
  }

  /*
   * @testName: testEqualsTimestamp01
   * 
   * @assertion_ids: JDBC:JAVADOC:39;
   * 
   * @test_Strategy: Get a Timestamp object and call the equals(Timestamp ts)
   * method with equal value of Timestamp It should return a boolean value and
   * the value should be equal to true
   */
	@Test
	@TargetVehicle("appclient")
  public void testEqualsTimestamp01() throws Exception {
		super.testEqualsTimestamp01();
  }

  /*
   * @testName: testEqualsObject01
   * 
   * @assertion_ids: JDBC:JAVADOC:40;
   * 
   * @test_Strategy: Get a Timestamp object and call the equals(Object obj)
   * method with equal value of Timestamp It should return a boolean value and
   * the value should be equal to true
   */
	@Test
	@TargetVehicle("appclient")
  public void testEqualsObject01() throws Exception {
		super.testEqualsObject01();
  }

  /*
   * @testName: testEqualsObject02
   * 
   * @assertion_ids: JDBC:JAVADOC:40;
   * 
   * @test_Strategy: Get a Timestamp object and call the equals(Object obj)
   * method with equal value of Timestamp in Nano seconds level It should return
   * a boolean value and the value should be equal to true
   */
	@Test
	@TargetVehicle("appclient")
  public void testEqualsObject02() throws Exception {
		super.testEqualsObject02();
  }

  /*
   * @testName: testValueOf01
   * 
   * @assertion_ids: JDBC:JAVADOC:35;
   * 
   * @test_Strategy: Call valueof(String ts) static method in java.sql.Timestamp
   * class with a String argument to get a Timestamp object Check whether it is
   * same as Timestamp object obtained from equivalent long value .
   */
	@Test
	@TargetVehicle("appclient")
  public void testValueOf01() throws Exception {
		super.testValueOf01();
  }

  /*
   * @testName: testValueOf02
   * 
   * @assertion_ids: JDBC:JAVADOC:35;
   * 
   * @test_Strategy: Call valueof(String ts) static method in java.sql.Timestamp
   * class with a String argument to get a Timestamp object Check whether it is
   * same as Timestamp object obtained from equivalent long value .
   */
	@Test
	@TargetVehicle("appclient")
  public void testValueOf02() throws Exception {
		super.testValueOf02();
  }

}