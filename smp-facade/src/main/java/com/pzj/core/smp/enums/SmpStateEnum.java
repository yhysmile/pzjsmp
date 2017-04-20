/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.smp.enums;

/**
 * 
 * @author Administrator
 * @version $Id: SmpState.java, v 0.1 2016年10月18日 下午4:42:12 Administrator Exp $
 */
public enum SmpStateEnum {
    /**
     * 正常.
     */
    AVAILABLE(1),
    /**
     * 停用.
     */
    DISABLED(2);

    /** 状态值. */
    private int state;

    public int getState() {
        return state;
    }

    private SmpStateEnum(int state) {
        this.state = state;
    }
}
