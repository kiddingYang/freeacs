package com.github.freeacs.tr069.methods;

import com.github.freeacs.tr069.HTTPReqResData;
import com.github.freeacs.tr069.exception.TR069Exception;
import com.github.freeacs.tr069.xml.Parser;

public class SPAres {

	public static void process(HTTPReqResData reqRes) throws TR069Exception {
		reqRes.getRequest().setMethod(TR069Method.SET_PARAMETER_ATTRIBUTES);
		Parser parser = new Parser(reqRes.getRequest().getXml());
		if (parser.getHeader().getNoMoreRequests() != null && parser.getHeader().getNoMoreRequests().getNoMoreRequestFlag())
			reqRes.getSessionData().setNoMoreRequests(true);
	}
}
