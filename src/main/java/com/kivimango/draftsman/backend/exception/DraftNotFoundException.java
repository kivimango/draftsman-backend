package com.kivimango.draftsman.backend.exception;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public final class DraftNotFoundException extends Exception {

	private static final long serialVersionUID = 1922035709239253047L;
	
	public DraftNotFoundException(Integer id) {
		super("The requested item ("+id+") could nout be found !");
	}

}
