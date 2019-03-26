package com.chewords.jsv.action;
/*
 *
 * @Author Joey
 * @Date 25/03/2019 11:37:11
 * @Desc
 *
 */

import com.chewords.jsv.JsvRecord;

public interface Action {
    // Action should performed at load stage
    Object load(JsvRecord record) throws Exception;

    // Action should performed at dump stage
    String dump(Object value) throws Exception;
}
