/*
 * Copyright (c) 2007, 2023 Oracle and/or its affiliates. All rights reserved.
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

package ee.jakarta.tck.persistence.core.entitytest.detach.oneXmany;


import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import com.sun.ts.lib.harness.Status;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ee.jakarta.tck.persistence.common.PMClientBase;
import jakarta.persistence.EntityExistsException;

public class Client extends PMClientBase {



	public Client() {
	}
	public static void main(String[] args) {
		Client theTests = new Client();
		Status s = theTests.run(args, System.out, System.err);
		s.exit();
	}

	public JavaArchive createDeployment() throws Exception {

		String pkgNameWithoutSuffix = Client.class.getPackageName();
		String pkgName = pkgNameWithoutSuffix + ".";
		String[] classes = { pkgName + "A", pkgName + "B" };
		return createDeploymentJar("jpa_core_entitytest_remove_oneXone.jar", pkgNameWithoutSuffix, classes);

	}

	
	public void setup(String[] args, Properties p) throws Exception {
		logTrace( "setup");
		try {
			super.setup(args,p);
			createDeployment();
			removeTestData();
		} catch (Exception e) {
			logErr( "Exception: ", e);
			throw new Exception("Setup failed:", e);

		}
	}

	/*
	 * BEGIN Test Cases
	 */

	/*
	 * @testName: detach1XMTest1
	 * 
	 * @assertion_ids: PERSISTENCE:SPEC:625; PERSISTENCE:SPEC:742;
	 * PERSISTENCE:JAVADOC:31
	 * 
	 * @test_Strategy: The new entity bean instance becomes both managed and
	 * persistent by invoking the persist method on it. The semantics of the persist
	 * operation as applied to entity X is as follows:
	 *
	 * If X is a detached object and the persist method is invoked on it, an
	 * IllegalArgumentException is thrown or the commit() will fail. Check for an
	 * IllegalArgumentException, or an EntityExistsException. Invoke persist on a
	 * detached entity.
	 *
	 */
	@Test
	public void detach1XMTest1() throws Exception {
		logTrace( "Begin detach1XMTest1");
		boolean pass = false;
		final A aRef = new A("1", "a1", 1);

		try {
			createA(aRef);
			clearCache();

			getEntityTransaction().begin();
			logTrace( "Persist Instance");

			logTrace( "Call contains to determined if the instance is detached");

			if (getEntityManager().contains(aRef)) {
				logTrace( "entity is not detached, cannot proceed with test.");
				pass = false;
			} else {
				try {
					logTrace( "Status is false as expected, try perist()");
					getEntityManager().persist(aRef);
				} catch (IllegalArgumentException iae) {
					logTrace(
							"IllegalArgumentException thrown trying to" + " persist a detached entity", iae);
					pass = true;
				} catch (EntityExistsException eee) {
					logTrace(
							"entityExistsException thrown trying to" + " persist a detached entity", eee);
					pass = true;
				}
			}
			getEntityTransaction().commit();
		} catch (Exception e) {
			logTrace( "or, Transaction commit will fail. " + " Test the commit failed by testing"
					+ " the transaction is marked for rollback");

			if ((!pass) && (e instanceof jakarta.transaction.TransactionRolledbackException
					|| e instanceof jakarta.persistence.PersistenceException)) {
				pass = true;
			}

		} finally {
			try {
				if (getEntityTransaction().isActive()) {
					getEntityTransaction().rollback();
				}
			} catch (Exception fe) {
				logErr( "Unexpected exception rolling back TX:", fe);
			}

		}

		if (!pass)
			throw new Exception("detach1XMTest1 failed");
	}

	/*
	 * @testName: detach1XMTest2
	 * 
	 * @assertion_ids: PERSISTENCE:SPEC:625; PERSISTENCE:SPEC:635
	 * 
	 * @test_Strategy: If X is a detached entity, invoking the remove method on it
	 * will cause an IllegalArgumentException to be thrown or the transaction commit
	 * will fail. Invoke remove on a detached entity.
	 *
	 */
	@Test
	public void detach1XMTest2() throws Exception {
		logTrace( "Begin detach1XMTest2");
		boolean pass = false;

		try {
			B b1 = new B("1", "b1", 2);
			B b2 = new B("2", "b2", 2);
			B b3 = new B("3", "b3", 2);
			B b4 = new B("4", "b4", 2);
			Vector v1 = new Vector();
			v1.add(b1);
			v1.add(b2);
			v1.add(b3);
			v1.add(b4);
			A aRef = new A("2", "bean2", 2, v1);
			createA(aRef);

			Collection newCol = aRef.getBCol();
			dumpCollectionDataB(newCol);

			clearCache();

			logTrace( "Begin Transaction and make sure instance is detached prior to remove");
			getEntityTransaction().begin();

			if ((!getEntityManager().contains(aRef)) && (newCol.contains(b1)) && (newCol.contains(b2))
					&& (newCol.contains(b3)) && (newCol.contains(b4))) {

				try {
					logTrace( "aref is detached, Try remove");
					getEntityManager().remove(aRef);

				} catch (IllegalArgumentException iae) {
					logTrace( "IllegalArgumentException thrown trying to remove a detached entity",
							iae);
					pass = true;
				}
			}

			getEntityTransaction().commit();
		} catch (Exception e) {
			logTrace( "or, Transaction commit will fail.  Test the commit failed by testing"
					+ " the transaction is marked for rollback");

			if ((!pass) && (e instanceof jakarta.transaction.TransactionRolledbackException
					|| e instanceof jakarta.persistence.PersistenceException)) {
				pass = true;
			}

		} finally {
			try {
				if (getEntityTransaction().isActive()) {
					getEntityTransaction().rollback();
				}
			} catch (Exception fe) {
				logErr(
						"Unexpected exception caught trying to " + " remove entity instance :" + fe);
			}
		}

		if (!pass)
			throw new Exception("detach1XMTest2 failed");

	}

	/*
	 *
	 * Business Methods to set up data for Test Cases
	 *
	 */

	private void createA(final A a) {
		logTrace( "Entered createA method");
		getEntityTransaction().begin();
		getEntityManager().persist(a);
		getEntityTransaction().commit();
	}

	private void dumpCollectionDataB(Collection c) {
		logTrace( "collection Data");
		logTrace( "---------------");
		logTrace( "- size=" + c.size());
		Iterator i = c.iterator();
		int elem = 1;
		while (i.hasNext()) {
			B v = (B) i.next();
			logTrace( "- Element #" + elem++);
			logTrace(
					"  id=" + v.getBId() + ", name=" + v.getBName() + ", value=" + v.getBValue());
		}
	}

	@AfterEach
	public void cleanup() throws Exception {
		try {
			logTrace( "Cleanup data");
			removeTestData();
			logTrace( "cleanup complete, calling super.cleanup");
			super.cleanup();
		} finally {

        }
	}

	private void removeTestData() {
		logTrace( "removeTestData");
		if (getEntityTransaction().isActive()) {
			getEntityTransaction().rollback();
		}
		try {
			getEntityTransaction().begin();
			getEntityManager().createNativeQuery("DELETE FROM AEJB_1XM_BI_BTOB").executeUpdate();
			getEntityManager().createNativeQuery("DELETE FROM BEJB_1XM_BI_BTOB").executeUpdate();
			getEntityTransaction().commit();
		} catch (Exception e) {
			logErr( "Exception encountered while removing entities:", e);
		} finally {
			try {
				if (getEntityTransaction().isActive()) {
					getEntityTransaction().rollback();
				}
			} catch (Exception re) {
				logErr( "Unexpected Exception in removeTestData:", re);
			}
		}
	}

}
