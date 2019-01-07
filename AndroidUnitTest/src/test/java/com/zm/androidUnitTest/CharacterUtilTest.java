package com.zm.androidUnitTest;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * CharacterUtil类isJudgeSymbol方法的测试
 * Author: zhangmiao
 * Date: 2019/1/7
 */
public class CharacterUtilTest {
    @Test
    public void isJudgeSymbol() throws Exception {
//        assertThat(CharacterUtil.isJudgeSymbol("<", "＜"), is(true)); //输入正确结果
        assertThat(CharacterUtil.isJudgeSymbol("", "＜"), is(true)); //输入错误结果
    }
}