package org.pachnanda.calculator.core;

import org.junit.Assert;
import org.junit.Before;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gautampachnanda on 06/04/15.
 */
public abstract class AbstractPojoTester {

    @SuppressWarnings("rawtypes")
	private Map testValues = new HashMap();

    @SuppressWarnings("unchecked")
	protected void addTestValue(@SuppressWarnings("rawtypes") Class propertyType, Object testValue) {
        testValues.put(propertyType, testValue);
    }

    @Before
    public void setUpTestValues() throws Exception {
        // add in further test values here.
        addTestValue(String.class, "foo");
        addTestValue(int.class, 123);
        addTestValue(long.class, 123456l);
        addTestValue(Integer.class, 123);
        addTestValue(double.class, 123.0);
        addTestValue(Double.class, 123.0);
        addTestValue(boolean.class, true);
        addTestValue(Boolean.class, true);
        addTestValue(java.util.Date.class, new java.util.Date(100000000));
    }

    /**
     * Call from subclass
     */
    protected void testPojo(@SuppressWarnings("rawtypes") Class pojoClass) {
        try {
            Object pojo = pojoClass.newInstance();
            BeanInfo pojoInfo = Introspector.getBeanInfo(pojoClass);
            for (PropertyDescriptor propertyDescriptor : pojoInfo
                    .getPropertyDescriptors()) {
                testProperty(pojo, propertyDescriptor);
            }
        } catch (Exception e) {
            // ignore
        }
    }

    private void testProperty(Object pojo, PropertyDescriptor propertyDescriptor) {
        try {
            @SuppressWarnings("rawtypes")
			Class propertyType = propertyDescriptor.getPropertyType();
            Object testValue = testValues.get(propertyType);
            if (testValue == null) {
                return;
            }
            Method writeMethod = propertyDescriptor.getWriteMethod();
            Method readMethod = propertyDescriptor.getReadMethod();
            if (readMethod != null && writeMethod != null) {
                writeMethod.invoke(pojo, testValue);
                Assert.assertEquals(readMethod.invoke(pojo), testValue);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            // ignore
        }
    }
}
