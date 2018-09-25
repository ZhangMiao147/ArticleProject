package com.zm.androidUnitTest;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Author: zhangmiao
 * Date: 2018/9/25
 */
@RunWith(AndroidJUnit4.class)
public class SharedPreferenceDaoTest {
    public static final String TEST_KEY = "name";
    public static final String TEST_STRING = "zhangmiao";

    SharedPreferenceDao spDao;

    @Before
    public void setUp() {
        spDao = new SharedPreferenceDao(App.getAppContext());
    }

    @Test
    public void sharedPreferenceDaoWriteRead() {
        spDao.put(TEST_KEY, TEST_STRING);
        Assert.assertEquals(TEST_STRING, spDao.get(TEST_KEY));
    }
}