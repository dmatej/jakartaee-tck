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
 * %W% %E%
 */

package com.sun.ts.tests.jdbc.ee.callStmt.callStmt8;

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
 * The callStmtClient8 class tests methods of CallableStatement interface (to
 * check the Support for IN, OUT and INOUT parameters of Stored Procedure) using
 * Sun's J2EE Reference Implementation.
 * 
 * @author
 * @version 1.7, 06/16/99
 */

@Tag("tck-appclient")

public class callStmtClient8AppClient extends callStmtClient8 implements Serializable {
  private static final String testName = "jdbc.ee.callStmt.callStmt8";
  
  @TargetsContainer("tck-appclient")
  @OverProtocol("appclient")
	@Deployment(name = "appclient",  testable = true)
	public static EnterpriseArchive createDeploymentAppclient(@ArquillianResource TestArchiveProcessor archiveProcessor) throws IOException {
		JavaArchive archive = ShrinkWrap.create(JavaArchive.class, "callStmt8_appclient_vehicle_client.jar");
		archive.addPackages(true, "com.sun.ts.tests.jdbc.ee.common");
		archive.addPackages(false, "com.sun.ts.tests.common.vehicle");
		archive.addPackages(true, "com.sun.ts.lib.harness");
		archive.addClasses(callStmtClient8AppClient.class, callStmtClient8.class);
		  // The appclient-client descriptor
	     URL appClientUrl = callStmtClient8AppClient.class.getResource("/com/sun/ts/tests/jdbc/ee/callStmt/callStmt8/appclient_vehicle_client.xml");
	     if(appClientUrl != null) {
	     	archive.addAsManifestResource(appClientUrl, "application-client.xml");
	     }
	     // The sun appclient-client descriptor
	     URL sunAppClientUrl = callStmtClient8AppClient.class.getResource("//com/sun/ts/tests/common/vehicle/appclient/appclient_vehicle_client.jar.sun-application-client.xml");
	     if(sunAppClientUrl != null) {
	     	archive.addAsManifestResource(sunAppClientUrl, "sun-application-client.xml");
	     }
	     
		 	archive.addAsManifestResource(
					new StringAsset("Main-Class: " + "com.sun.ts.tests.common.vehicle.VehicleClient" + "\n"),
					"MANIFEST.MF");

	     // Call the archive processor
	     archiveProcessor.processClientArchive(archive, callStmtClient8AppClient.class, sunAppClientUrl);

		archive.addAsManifestResource(callStmtClient8AppClient.class.getPackage(), "appclient_vehicle_client.xml");
	 	EnterpriseArchive ear = ShrinkWrap.create(EnterpriseArchive.class, "callStmt8_appclient_vehicle.ear");
			ear.addAsModule(archive);

			return ear;
	};


  /* Run test in standalone mode */
  public static void main(String[] args) {
    callStmtClient8AppClient theTests = new callStmtClient8AppClient();
    Status s = theTests.run(args, System.out, System.err);
    s.exit();
  }

  /*
   * @testName: testSetObject01
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set String object for SQL Type CHAR and
   * call statement.executeQuery(String sql) method and call
   * ResultSet.getObject(int column). It should return a String object that is
   * been set. Compare the result with the extracted value from the tssql.stmt
   * file. Both the values should be equal.
   *
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject01() throws Exception {
	  super.testSetObject01();
   }

  /*
   * @testName: testSetObject02
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database. execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set String object for SQL Type VARCHAR
   * and call statement.executeQuery(String sql) method and call
   * ResultSet.getObject(int column). It should return a String object that is
   * been set. Compare the result with the extracted value from the tssql.stmt
   * file. Both the values should be equal.
   *
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject02() throws Exception {
	  super.testSetObject02();
  }

  /*
   * @testName: testSetObject03
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database. execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set String object for SQL Type
   * LONGVARCHAR and call statement.executeQuery(String sql) method and call
   * ResultSet.getObject(int column). It should return a String object that is
   * been set. Compare the result with the extracted value from tssql.stmt file.
   * Both the values should be equal.
   *
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject03() throws Exception {
	  super.testSetObject03();
  }

  /*
   * @testName: testSetObject04
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database. execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set BigDecimal object for SQL Type
   * Numeric and call statement.executeQuery(String sql) method and call
   * ResultSet.getObject(int column). It should return a BigDecimal object.
   * Compare the returned value with the extracted value from tssql.stmt file.
   * Both the values should be equal.
   *
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject04() throws Exception {
	  super.testSetObject04();
  }

  /*
   * @testName: testSetObject05
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database. execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set BigDecimal object for SQL Type
   * Numeric and call statement.executeQuery(String sql) method and call
   * ResultSet.getObject(int column) method. It should return a BigDecimal
   * object. Compare the result with the extracted value from the tssql.stmt
   * file. Both the values should be equal.
   *
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject05() throws Exception {
	  super.testSetObject05();
  }

  /*
   * @testName: testSetObject06
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database. execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set BigDecimal object for SQL Type
   * Decimal and call statement.executeQuery(String sql) method and call
   * ResultSet.getObject(int column). It should return a BigDecimal object.
   * Compare the returned value with the value extracted from the tssql.stmt
   * file. Both the values should be equal.
   *
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject06() throws Exception {
	  super.testSetObject06();
  }

  /*
   * @testName: testSetObject07
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database. execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set BigDecimal object for SQL Type
   * Decimal and call statement.executeQuery(String sql) method and call
   * ResultSet.getObject(int column). It should return a BigDecimal object.
   * Compare the result with the extracted value from the tssql.stmt file. Both
   * the values should be equal.
   *
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject07() throws Exception {
	  super.testSetObject07();
  }

  /*
   * @testName: testSetObject08
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database. execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set Boolean object for SQL Type Bit and
   * call statement.executeQuery(String sql) method and call
   * ResultSet.getBoolean(int column). Compare the result with the extracted
   * value from the tssql.stmt file. Both the values should be equal.
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject08() throws Exception {
	  super.testSetObject08();
  }

  /*
   * @testName: testSetObject09
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database. execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set Boolean object for SQL Type Bit and
   * call statement.executeQuery(String sql) method and call
   * ResultSet.getBoolen(int column). Compare the result with the extracted
   * value from the tssql.stmt file. Both the values should be equal.
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject09() throws Exception {
	  super.testSetObject09();
  }

  /*
   * @testName: testSetObject10
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database. execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set Integer object for SQL Type Integer
   * and call statement.executeQuery(String sql) method and call
   * ResultSet.getObject(int column). It should return a Integer object. Compare
   * the result with the extracted value from the tssql.stmt file. Both the
   * values should be equal.
   *
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject10() throws Exception {
	  super.testSetObject10();
  }

  /*
   * @testName: testSetObject11
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database. execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set Integer object for SQL Type Integer
   * and call statement.executeQuery(String sql) method and call
   * ResultSet.getObject(int column). It should return a Integer object. Compare
   * the result with the extracted value from the tssql.stmt file. Both the
   * values should be equal.
   *
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject11() throws Exception {
	  super.testSetObject11();
  }

  /*
   * @testName: testSetObject12
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database. execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set Long object for SQL Type Bigint and
   * call statement.executeQuery(String sql) method and call
   * ResultSet.getObject(int column). It should return a Long object. Compare
   * the result with the extracted value from the tssql.stmt file. Both the
   * values should be equal.
   *
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject12() throws Exception {
	  super.testSetObject12();
  }

  /*
   * @testName: testSetObject13
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database. execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set Long object for SQL Type Bigint and
   * call statement.executeQuery(String sql) method and call
   * ResultSet.getObject(int column). It should return a Long object. Compare
   * the result with the extracted value from the tssql.stmt file. Both the
   * values should be equal.
   *
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject13() throws Exception {
	  super.testSetObject13();
  }

  /*
   * @testName: testSetObject14
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database. execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set Double object for SQL Type Double
   * and call statement.executeQuery(String sql) method and call
   * ResultSet.getObject(int column). It should return a Double object. Compare
   * the result with the extracted value from the tssql.stmt file. Both the
   * values should be equal.
   *
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject14() throws Exception {
	  super.testSetObject14();
  }

  /*
   * @testName: testSetObject15
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database. execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set Double object for SQL Type Double
   * and call statement.executeQuery(String sql) method and call
   * ResultSet.getObject(int column). It should return a Double object. Compare
   * the result with the extracted value from the tssql.stmt file. Both the
   * values should be equal.
   *
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject15() throws Exception {
	  super.testSetObject15();
  }

  /*
   * @testName: testSetObject16
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database. execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set Double object for SQL Type Float
   * and call statement.executeQuery(String sql) method and call
   * ResultSet.getObject(int column). It should return a Double object. Compare
   * the result with the extracted value from the tssql.stmt file. Both the
   * values should be equal.
   *
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject16() throws Exception {
	  super.testSetObject16();
  }

  /*
   * @testName: testSetObject17
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database. execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set Float object for SQL Type Float and
   * call statement.executeQuery(String sql) method and call
   * ResultSet.getObject(int column). It should return a Float object. Compare
   * the result with the extracted value from the tssql.stmt file. Both the
   * values should be equal.
   *
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject17() throws Exception {
	  super.testSetObject17();
  }

  /*
   * @testName: testSetObject18
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database. execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set Date object for SQL Type Date and
   * call statement.executeQuery(String sql) method and call
   * ResultSet.getObject(int column). It should return a Date object. Compare
   * the result with the extracted value from the tssql.stmt file. Both the
   * values should be equal.
   *
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject18() throws Exception {
	  super.testSetObject18();
  }

  /*
   * @testName: testSetObject19
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database. execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set Time object for SQL Type Time and
   * call statement.executeQuery(String sql) method and call
   * ResultSet.getObject(int column). It should return a Time object. Compare
   * the result with the extracted value from the tssql.stmt file. Both the
   * values should be equal.
   *
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject19() throws Exception {
	  super.testSetObject19();
  }

  /*
   * @testName: testSetObject20
   * 
   * @assertion_ids: JDBC:SPEC:9; JDBC:SPEC:10; JDBC:JAVADOC:696;
   * JDBC:JAVADOC:697; JavaEE:SPEC:186;
   *
   * @test_Strategy: Get a CallableStatement object from the connection to the
   * database. execute the stored procedure and call the setObject(int
   * parameterIndex, Object x) method to set Timestamp object for SQL Type
   * Timestamp & call statement.executeQuery(String sql) method and call
   * ResultSet.getObject(int column). It should return a Timestamp object.
   * Compare the result with the extracted value from the tssql.stmt file. Both
   * the values should be equal.
   *
   */
	@Test
	@TargetVehicle("appclient")
  public void testSetObject20() throws Exception {
	  super.testSetObject20();
  }

}