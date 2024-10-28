/*
 * Copyright (c) 2009, 2020 Oracle and/or its affiliates and others.
 * All rights reserved.
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
 * $Id$
 */

package com.sun.ts.tests.el.api.jakarta_el.expression;

import java.util.Properties;

import com.sun.ts.lib.harness.Status;
import com.sun.ts.lib.harness.ServiceEETest;
import com.sun.ts.lib.util.TestUtil;
import com.sun.ts.tests.common.el.api.expression.ExpressionTest;
import com.sun.ts.tests.el.common.elcontext.SimpleELContext;
import com.sun.ts.tests.el.common.util.ELTestUtil;
import com.sun.ts.tests.el.common.util.ResolverType;

import jakarta.el.ELContext;
import jakarta.el.Expression;
import jakarta.el.ExpressionFactory;
import jakarta.el.MethodExpression;
import jakarta.el.ValueExpression;


import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tck.arquillian.protocol.common.TargetVehicle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import java.lang.System.Logger;

@ExtendWith(ArquillianExtension.class)
@Tag("el")
@Tag("platform")
@Tag("web")
@Tag("tck-javatest")
public class ELClientServletTest extends ServiceEETest {

  static final String VEHICLE_ARCHIVE = "expression_servlet_vehicle";

  private static final Logger logger = System.getLogger(ELClientServletTest.class.getName());

  @BeforeEach
  void logStartTest(TestInfo testInfo) {
      logger.log(Logger.Level.INFO, "STARTING TEST : " + testInfo.getDisplayName());
  }

  @AfterEach
  void logFinishTest(TestInfo testInfo) {
      logger.log(Logger.Level.INFO, "FINISHED TEST : " + testInfo.getDisplayName());
  }


  public static String inputStreamToString(InputStream inStream) throws IOException {
    try (BufferedReader bufReader = new BufferedReader(new InputStreamReader(inStream, StandardCharsets.UTF_8))) {
      return bufReader.lines().collect(Collectors.joining(System.lineSeparator()));
    }
  }

  public static String editWebXmlString(InputStream inStream, String servlet_vehicle) throws IOException{
    return inputStreamToString(inStream).replaceAll("el_servlet_vehicle", servlet_vehicle);
  }


  @TargetsContainer("tck-javatest")
  @OverProtocol("javatest")
  @Deployment(name = VEHICLE_ARCHIVE, order = 2)
  public static WebArchive createDeploymentVehicle() throws IOException {

    WebArchive servlet_vehicle_web = ShrinkWrap.create(WebArchive.class, "expression_servlet_vehicle_web.war");
  
    servlet_vehicle_web.addClasses(
        com.sun.ts.lib.harness.EETest.Fault.class,
        com.sun.ts.lib.harness.EETest.SetupException.class,
        com.sun.ts.lib.harness.EETest.class,
        com.sun.ts.lib.harness.ServiceEETest.class,
        com.sun.ts.tests.common.el.api.resolver.BarELResolver.class,
        com.sun.ts.tests.common.el.api.expression.ExpressionTest.class,
        com.sun.ts.tests.common.el.api.resolver.ResolverTest.class,
        com.sun.ts.tests.common.vehicle.VehicleClient.class,
        com.sun.ts.tests.common.vehicle.VehicleRunnable.class,
        com.sun.ts.tests.common.vehicle.VehicleRunnerFactory.class,
        com.sun.ts.tests.common.vehicle.servlet.ServletVehicle.class,
        com.sun.ts.tests.el.api.jakarta_el.expression.ELClientServletTest.class,
        com.sun.ts.tests.el.common.elcontext.BareBonesELContext.class,
        com.sun.ts.tests.el.common.util.ELTestUtil.class,
        com.sun.ts.tests.el.common.elcontext.SimpleELContext.class,
        com.sun.ts.tests.el.common.elcontext.SimpleELContext.Resolver.class,
        com.sun.ts.tests.el.common.util.SimpleBean.class,
        com.sun.ts.tests.el.common.util.SimpleInterface.class,
        com.sun.ts.tests.el.common.util.ResolverType.class,
        com.sun.ts.tests.el.common.elresolver.EmployeeELResolver.class,
        com.sun.ts.tests.el.common.elresolver.VariableELResolver.class,
        com.sun.ts.tests.el.common.elresolver.VectELResolver.class
    );

    
    InputStream inStream = ELClientServletTest.class.getResourceAsStream("/vehicle/servlet/servlet_vehicle_web.xml");

    // Replace the el_servlet_vehicle in servlet_vehicle_web.xml with the servlet_vehicle name for archive
    String webXml = editWebXmlString(inStream, VEHICLE_ARCHIVE);
    servlet_vehicle_web.setWebXML(new StringAsset(webXml));

    return servlet_vehicle_web;
    /*
    EnterpriseArchive servlet_vehicle_ear = ShrinkWrap.create(EnterpriseArchive.class, "expression_servlet_vehicle.ear");
    servlet_vehicle_ear.addAsModule(servlet_vehicle_web);
    return servlet_vehicle_ear;
    */

  }
  private Properties testProps;

  public static void main(String[] args) {
    ELClientServletTest theTests = new ELClientServletTest();
    Status s = theTests.run(args, System.out, System.err);
    s.exit();
  }

  public void setup(String[] args, Properties p) throws Exception {
    TestUtil.logTrace("Setup method called");
    this.testProps = p;
  }

  /**
   * Does nothing.
   * 
   * @throws Exception
   */
  @AfterEach
  public void cleanup() throws Exception {
    // does nothing at this point
  }

  /**
   * @testName: negativeEqualsTest
   * 
   * @assertion_ids: EL:JAVADOC:56
   * 
   * @test_Strategy: Validate the behavior of Expression API Expression.equals()
   * 
   *                 Verify that an Expression cannot equal null, and that a
   *                 ValueExpression and a MethodExpression cannot be equal.
   */
  @Test
  @TargetVehicle("servlet")
  public void negativeEqualsTest() throws Exception {

    StringBuffer buf = new StringBuffer();

    boolean pass = true;

    try {

      ExpressionFactory expFactory = ExpressionFactory.newInstance();
      ELContext context = (new SimpleELContext()).getELContext();

      // compare Expressions to null
      ValueExpression vexp1 = expFactory.createValueExpression(context,
          "${null}", Object.class);

      if (ExpressionTest.equalsTest(vexp1, null, buf)) {
        pass = false;
        buf.append("ValueExpression tested equal to null" + TestUtil.NEW_LINE);
      }

      MethodExpression mexp1 = expFactory.createMethodExpression(context,
          "null", null, new Class[] {});

      if (ExpressionTest.equalsTest(mexp1, null, buf)) {
        pass = false;
        buf.append("MethodExpression tested equal to null" + TestUtil.NEW_LINE);
      }

      // compare ValueExpressions to MethodExpressions
      ValueExpression vexp2 = expFactory.createValueExpression(context,
          "literal", Object.class);

      MethodExpression mexp2 = expFactory.createMethodExpression(context,
          "literal", null, new Class[] {});

      if (ExpressionTest.equalsTest(vexp2, mexp2, buf)) {
        pass = false;
        buf.append("ValueExpression tested equal to " + "MethodExpression"
            + TestUtil.NEW_LINE);
      }

      if (ExpressionTest.equalsTest(mexp2, vexp2, buf)) {
        pass = false;
        buf.append("MethodExpression tested equal to " + "ValueExpression"
            + TestUtil.NEW_LINE);
      }

    } catch (Exception ex) {
      throw new Exception(ex);
    }

    if (!pass)
      throw new Exception(ELTestUtil.FAIL + buf.toString());

    TestUtil.logTrace(buf.toString());

  }// End negativeEqualsTest

  /**
   * @testName: expressionHashCodeTest
   * 
   * @assertion_ids: EL:JAVADOC:59
   * 
   * @test_Strategy: Validate the if two objects are equal according to the
   *                 equals(Object) method, then calling the hashCode method on
   *                 each of the two objects must produce the same integer
   *                 result.
   * 
   */
  @Test
  @TargetVehicle("servlet")
  public void expressionHashCodeTest() throws Exception {
    SimpleELContext simpleContext = new SimpleELContext(
        ResolverType.VECT_ELRESOLVER);

    ExpressionFactory expFactory = ExpressionFactory.newInstance();
    ELContext context = simpleContext.getELContext();

    Class<?>[] paramTypes1 = { Object.class };

    String exprStr1 = "#{vect.add}";

    Expression mexp1 = expFactory.createMethodExpression(context, exprStr1,
        boolean.class, paramTypes1);

    Expression mexp2 = expFactory.createMethodExpression(context, exprStr1,
        boolean.class, paramTypes1);

    if (!mexp1.equals(mexp2)) {
      throw new Exception("Failed: equals check failed!");

    }

    if (!(mexp1.hashCode() == mexp2.hashCode())) {
      throw new Exception("Failed: hashCode check failed!");
    }

  }// End expressionHashCodeTest

}
