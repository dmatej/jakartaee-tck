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
 * @(#)batUpdExceptClient.java	1.19 03/05/16
 */

package com.sun.ts.tests.jdbc.ee.exception.batUpdExcept;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.sun.ts.lib.harness.Status;

import tck.arquillian.porting.lib.spi.TestArchiveProcessor;
import tck.arquillian.protocol.common.TargetVehicle;

// Merant DataSource class
// import com.merant.sequelink.jdbcx.datasource.*;

/**
 * The batUpdExceptClient class tests methods of BatchUpdateException using
 * Sun's J2EE Reference Implementation.
 * 
 * @author
 * @version 1.7, 06/16/99
 */

@Tag("tck-javatest")

public class batUpdExceptClientServlet extends batUpdExceptClient implements Serializable {
  private static final String testName = "jdbc.ee.exception.batUpdExcept";
  
  @TargetsContainer("tck-javatest")
  @OverProtocol("javatest")
	@Deployment(name = "servlet", testable = true)
	public static WebArchive createDeploymentservlet(@ArquillianResource TestArchiveProcessor archiveProcessor) throws IOException {
		WebArchive archive = ShrinkWrap.create(WebArchive.class, "batUpdExcept_servlet_vehicle_web.war");
		archive.addPackages(true, "com.sun.ts.tests.jdbc.ee.common");
		archive.addPackages(false, "com.sun.ts.tests.common.vehicle");
		archive.addPackages(false, "com.sun.ts.tests.common.vehicle.servlet");
		archive.addPackages(true, "com.sun.ts.lib.harness");
		archive.addClasses(batUpdExceptClientServlet.class, batUpdExceptClient.class);
	       // The servlet descriptor
URL servletUrl = batUpdExceptClientServlet.class.getResource("servlet_vehicle_web.xml");
if(servletUrl != null) {
	archive.addAsWebInfResource(servletUrl, "web.xml");
}
// The sun servlet descriptor
URL sunServletUrl = batUpdExceptClientServlet.class.getResource("batUpdExcept_servlet_vehicle_web.war.sun-web.xml");
if(sunServletUrl != null) {
	archive.addAsWebInfResource(sunServletUrl, "sun-web.xml");
}
// Call the archive processor
archiveProcessor.processWebArchive(archive, batUpdExceptClientServlet.class, sunServletUrl);
		
		return archive;
	};


  /* Run test in standalone mode */
  public static void main(String[] args) {
    batUpdExceptClientServlet theTests = new batUpdExceptClientServlet();
    Status s = theTests.run(args, System.out, System.err);
    s.exit();
  }


  /*
   * @testName: testGetUpdateCounts
   * 
   * @assertion_ids: JDBC:SPEC:6; JDBC:JAVADOC:121;
   * 
   * @test_Strategy: Get a Statement object and call the addBatch() method with
   * 4 SQL statements ,out of which first 3 statements are insert,update and
   * delete statements followed by a select statement and call the
   * executeBatch() method. This causes a BatchUpdateException being thrown and
   * getUpdateCounts() method on this BatchUpdateException object should return
   * an integer array of length 3 with each value indicating the number of
   * corressponding rows affected by execution of 3 SQL statements in the same
   * order as added to the batch.
   *
   */
	@Test
	@TargetVehicle("servlet")
  public void testGetUpdateCounts() throws Exception {
		super.testGetUpdateCounts();
  }

 
  /*
   * @testName: testBatchUpdateException01
   * 
   * @assertion_ids: JDBC:SPEC:6; JDBC:JAVADOC:120;
   * 
   * @test_Strategy: This method constructs a BatchUpdateException Object with
   * no arguments and for that object the reason, SQLState,ErrorCode and
   * updateCounts are checked for default values.
   */
	@Test
	@TargetVehicle("servlet")
  public void testBatchUpdateException01() throws Exception {
		super.testBatchUpdateException01();
  }

  /*
   * @testName: testBatchUpdateException02
   * 
   * @assertion_ids: JDBC:SPEC:6; JDBC:JAVADOC:119;
   * 
   * @test_Strategy: This method constructs a BatchUpdateException Object with
   * one argument and for that object the reason, SQLState,ErrorCode are checked
   * for default values.The updateCount array is checked for whatever is been
   * assigned while creating the new instance.
   */

	@Test
	@TargetVehicle("servlet")
  public void testBatchUpdateException02() throws Exception {
		super.testBatchUpdateException02();
  }

  /*
   * @testName: testBatchUpdateException03
   * 
   * @assertion_ids: JDBC:SPEC:6; JDBC:JAVADOC:118;
   * 
   * @test_Strategy: This method constructs a BatchUpdateException Object with
   * two arguments and for that object. SQLState,ErrorCode are checked for
   * default values.The reason and updateCount array is checked for whatever is
   * been assigned while creating the new instance.
   */

	@Test
	@TargetVehicle("servlet")
  public void testBatchUpdateException03() throws Exception {
		super.testBatchUpdateException03();
  }

  /*
   * @testName: testBatchUpdateException04
   * 
   * @assertion_ids: JDBC:SPEC:6; JDBC:JAVADOC:117;
   * 
   * @test_Strategy: This method constructs a BatchUpdateException Object with
   * three arguments and for that object. ErrorCode is checked for default
   * values.The reason,SQLState and updateCount array is checked for whatever is
   * been assigned while creating the new instance.
   */

	@Test
	@TargetVehicle("servlet")
  public void testBatchUpdateException04() throws Exception {
		super.testBatchUpdateException04();
  }

  /*
   * @testName: testBatchUpdateException05
   * 
   * @assertion_ids: JDBC:SPEC:6; JDBC:JAVADOC:116;
   * 
   * @test_Strategy: This method constructs a BatchUpdateException Object with
   * four arguments The reason,SQLState ,ErrorCode and updateCount array is
   * checked for whatever is been assigned while creating the new instance.
   */

	@Test
	@TargetVehicle("servlet")
  public void testBatchUpdateException05() throws Exception {
		super.testBatchUpdateException05();
  }

}