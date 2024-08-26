package com.sun.ts.tests.ejb30.bb.session.stateful.callback.listener.descriptor;

import com.sun.ts.tests.ejb30.bb.session.stateful.callback.listener.descriptor.Client;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import tck.arquillian.porting.lib.spi.TestArchiveProcessor;
import tck.arquillian.protocol.common.TargetVehicle;



@ExtendWith(ArquillianExtension.class)
@Tag("platform")
@Tag("ejb_3x_remote_optional")
@Tag("web_optional")
@Tag("tck-appclient")

@TestMethodOrder(MethodOrderer.MethodName.class)
public class ClientTest extends com.sun.ts.tests.ejb30.bb.session.stateful.callback.listener.descriptor.Client {
    /**
        EE10 Deployment Descriptors:
        ejb3_bb_stateful_callback_listener_descriptor: 
        ejb3_bb_stateful_callback_listener_descriptor_client: 
        ejb3_bb_stateful_callback_listener_descriptor_ejb: META-INF/ejb-jar.xml,jar.sun-ejb-jar.xml

        Found Descriptors:
        Client:

        Ejb:

        /com/sun/ts/tests/ejb30/bb/session/stateful/callback/listener/descriptor/ejb3_bb_stateful_callback_listener_descriptor_ejb.xml
        /com/sun/ts/tests/ejb30/bb/session/stateful/callback/listener/descriptor/ejb3_bb_stateful_callback_listener_descriptor_ejb.jar.sun-ejb-jar.xml
        Ear:

        */
        @TargetsContainer("tck-appclient")
        @OverProtocol("appclient")
        @Deployment(name = "ejb3_bb_stateful_callback_listener_descriptor", order = 2)
        public static EnterpriseArchive createDeployment(@ArquillianResource TestArchiveProcessor archiveProcessor) {
        // Client
            // the jar with the correct archive name
            JavaArchive ejb3_bb_stateful_callback_listener_descriptor_client = ShrinkWrap.create(JavaArchive.class, "ejb3_bb_stateful_callback_listener_descriptor_client.jar");
            // The class files
            ejb3_bb_stateful_callback_listener_descriptor_client.addClasses(
            com.sun.ts.tests.ejb30.common.callback.CallbackIF.class,
            com.sun.ts.tests.ejb30.common.callback.ClientBase.class,
            com.sun.ts.lib.harness.EETest.Fault.class,
            com.sun.ts.tests.ejb30.common.helper.Helper.class,
            com.sun.ts.tests.ejb30.bb.session.stateful.callback.listener.descriptor.Client.class,
            com.sun.ts.tests.ejb30.common.callback.Callback2IF.class,
            com.sun.ts.lib.harness.EETest.class,
            com.sun.ts.tests.ejb30.common.helper.TLogger.class,
            com.sun.ts.tests.ejb30.common.helper.ServiceLocator.class,
            com.sun.ts.lib.harness.EETest.SetupException.class
            );
            // The application-client.xml descriptor
            URL resURL = Client.class.getResource("com/sun/ts/tests/ejb30/bb/session/stateful/callback/listener/descriptor/ejb3_bb_stateful_callback_listener_descriptor_client.xml");
            if(resURL != null) {
              ejb3_bb_stateful_callback_listener_descriptor_client.addAsManifestResource(resURL, "application-client.xml");
            }
            // The sun-application-client.xml file need to be added or should this be in in the vendor Arquillian extension?
            resURL = Client.class.getResource("/com/sun/ts/tests/ejb30/bb/session/stateful/callback/listener/descriptor/ejb3_bb_stateful_callback_listener_descriptor_client.jar.sun-application-client.xml");
            if(resURL != null) {
              ejb3_bb_stateful_callback_listener_descriptor_client.addAsManifestResource(resURL, "application-client.xml");
            }
            ejb3_bb_stateful_callback_listener_descriptor_client.addAsManifestResource(new StringAsset("Main-Class: " + Client.class.getName() + "\n"), "MANIFEST.MF");
            // Call the archive processor
            archiveProcessor.processClientArchive(ejb3_bb_stateful_callback_listener_descriptor_client, Client.class, resURL);

        // Ejb
            // the jar with the correct archive name
            JavaArchive ejb3_bb_stateful_callback_listener_descriptor_ejb = ShrinkWrap.create(JavaArchive.class, "ejb3_bb_stateful_callback_listener_descriptor_ejb.jar");
            // The class files
            ejb3_bb_stateful_callback_listener_descriptor_ejb.addClasses(
                com.sun.ts.tests.ejb30.common.callback.CallbackIF.class,
                com.sun.ts.tests.ejb30.bb.session.stateful.callback.listener.descriptor.StatefulCallbackListener.class,
                com.sun.ts.tests.ejb30.common.callback.Callback2BeanBase.class,
                com.sun.ts.tests.ejb30.bb.session.stateful.callback.listener.descriptor.Callback2Bean.class,
                com.sun.ts.tests.ejb30.common.callback.SharedCallbackBeanBase.class,
                com.sun.ts.tests.ejb30.common.callback.CallbackBeanBase.class,
                com.sun.ts.tests.ejb30.bb.session.stateful.callback.listener.descriptor.CallbackBean.class,
                com.sun.ts.tests.ejb30.bb.session.stateful.callback.listener.descriptor.StatefulCallbackListener2.class,
                com.sun.ts.tests.ejb30.common.callback.Callback2IF.class,
                com.sun.ts.tests.ejb30.common.helper.TLogger.class
            );
            // The ejb-jar.xml descriptor
            URL ejbResURL = Client.class.getResource("/com/sun/ts/tests/ejb30/bb/session/stateful/callback/listener/descriptor/ejb3_bb_stateful_callback_listener_descriptor_ejb.xml");
            if(ejbResURL != null) {
              ejb3_bb_stateful_callback_listener_descriptor_ejb.addAsManifestResource(ejbResURL, "ejb-jar.xml");
            }
            // The sun-ejb-jar.xml file
            ejbResURL = Client.class.getResource("/com/sun/ts/tests/ejb30/bb/session/stateful/callback/listener/descriptor/ejb3_bb_stateful_callback_listener_descriptor_ejb.jar.sun-ejb-jar.xml");
            if(ejbResURL != null) {
              ejb3_bb_stateful_callback_listener_descriptor_ejb.addAsManifestResource(ejbResURL, "sun-ejb-jar.xml");
            }
            // Call the archive processor
            archiveProcessor.processEjbArchive(ejb3_bb_stateful_callback_listener_descriptor_ejb, Client.class, ejbResURL);

        // Ear
            EnterpriseArchive ejb3_bb_stateful_callback_listener_descriptor_ear = ShrinkWrap.create(EnterpriseArchive.class, "ejb3_bb_stateful_callback_listener_descriptor.ear");

            // Any libraries added to the ear

            // The component jars built by the package target
            ejb3_bb_stateful_callback_listener_descriptor_ear.addAsModule(ejb3_bb_stateful_callback_listener_descriptor_ejb);
            ejb3_bb_stateful_callback_listener_descriptor_ear.addAsModule(ejb3_bb_stateful_callback_listener_descriptor_client);



            // The application.xml descriptor
            URL earResURL = Client.class.getResource("/com/sun/ts/tests/ejb30/bb/session/stateful/callback/listener/descriptor/application.xml");
            if(earResURL != null) {
              ejb3_bb_stateful_callback_listener_descriptor_ear.addAsManifestResource(earResURL, "application.xml");
            }
            // The sun-application.xml descriptor
            earResURL = Client.class.getResource("/com/sun/ts/tests/ejb30/bb/session/stateful/callback/listener/descriptor/application.ear.sun-application.xml");
            if(earResURL != null) {
              ejb3_bb_stateful_callback_listener_descriptor_ear.addAsManifestResource(earResURL, "sun-application.xml");
            }
            // Call the archive processor
            archiveProcessor.processEarArchive(ejb3_bb_stateful_callback_listener_descriptor_ear, Client.class, earResURL);
        return ejb3_bb_stateful_callback_listener_descriptor_ear;
        }

        @Test
        @Override
        public void isPostConstructCalledTest() throws java.lang.Exception {
            super.isPostConstructCalledTest();
        }

        @Test
        @Override
        public void isInjectionDoneTest() throws java.lang.Exception {
            super.isInjectionDoneTest();
        }

        @Test
        @Override
        public void isPostConstructOrPreDestroyCalledTest() throws java.lang.Exception {
            super.isPostConstructOrPreDestroyCalledTest();
        }


}