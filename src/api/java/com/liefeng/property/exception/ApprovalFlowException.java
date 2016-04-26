package com.liefeng.property.exception;

import com.liefeng.core.exception.LiefengException;

/**
 *  工作流模块异常类
 * @author wuzhijing
 *
 */
public class ApprovalFlowException extends LiefengException {

	private static final long serialVersionUID = 3935006586458842095L;

	public ApprovalFlowException() {		
		super();
	}
	
	public ApprovalFlowException(String message) {
        super(message);
    }
	
	public ApprovalFlowException(String code, String message) {
        super(code, message);
    }

    public ApprovalFlowException(Throwable cause) {
        super(cause);
    }

    public ApprovalFlowException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ApprovalFlowException(Enum<?> en) {
    	super(en);
    }
    
    public ApprovalFlowException(Enum<?> en, Object ... args) {
    	super(en, args);
    }
}
