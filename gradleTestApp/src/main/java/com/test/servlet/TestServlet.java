package com.test.servlet;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet class
 */
public class TestServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServletContext context = null;
	private final static String DEFAULT_PACKAGE = "com.test.controller.";
	private final static String RESPONSE_DATA = "responseData";
	private final static String REDIRECT_ATTR = "redirectPage";
	private final static String ERROR_JSP = "/Error.jsp";

	/**
	 * doGet implementation
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
		commonProcessing(request,response);
    }
	/**
	 * doPost implementation
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	commonProcessing(request,response);
    }
    protected void commonProcessing(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {

        if(context == null) {
        	context = getServletContext();
        }
        
        setContextParametersFromRequest(request);
      
        String classAndMethodName = request.getRequestURI().substring(request.getContextPath().length());
        if(classAndMethodName.endsWith(".jsp")) {
        	//JSP handling
        	processJspRequest(request, response, classAndMethodName); 
        } else {
        	//Assume java method call
        	processJavaRequest(request, response, classAndMethodName); 
        }//end else
        
    }
    /**
     * Sets context parameters based on http request
     * @param request
     */
    public void setContextParametersFromRequest(HttpServletRequest request) {
    	try {
    		   Enumeration<String> parameterNames =  request.getParameterNames();
    	        
    		   
    	        while(parameterNames.hasMoreElements()) {
    	            String currentParameterName = parameterNames.nextElement();
    	            		if(context.getAttribute(currentParameterName) == null) {
    	            			context.removeAttribute(currentParameterName);
    	            			context.setAttribute(currentParameterName, request.getParameter(currentParameterName));
    	            		}
    	            		else {
    	            			context.setAttribute(currentParameterName, request.getParameter(currentParameterName));
    	            		}
    	        }
    	} catch (Exception e) {
    		System.out.println("Exception getting parameters from http request");
    		e.printStackTrace();
    	}
    	
    }
    /**
     * clears out context parameters
     * @param request
     */
    public void removeRequestParametersFromContext(HttpServletRequest request) {
    	try {
    		   Enumeration<String> parameterNames =  request.getParameterNames();
    	        
    	        while(parameterNames.hasMoreElements()) {
    	            String currentParameterName = parameterNames.nextElement();
    	            		if(context.getAttribute(currentParameterName) != null) {
    	            			context.removeAttribute(currentParameterName);
    	            		}		
    	        }
    	} catch (Exception e) {
    		System.out.println("Exception removing parameters from http request");
    		e.printStackTrace();
    	}
    }
    /**
     * Processes a java request
     * @param request
     * @param response
     * @param classAndMethodName
     */
    public void processJavaRequest(HttpServletRequest request, HttpServletResponse response, String classAndMethodName) {
    	try {
        	String className = classAndMethodName.substring(classAndMethodName.lastIndexOf("/") + 1, classAndMethodName.lastIndexOf("."));
            String methodName = classAndMethodName.substring(classAndMethodName.lastIndexOf(".") + 1, classAndMethodName.length());
        		
        	Class<?> classType = Class.forName(DEFAULT_PACKAGE + className);
        	
        	//Get constructor
        	Constructor<?> constructor = classType.getConstructor(new Class[] {ServletContext.class});
        	Object constructorInstance =  constructor.newInstance(new Object[] {context});
        	
        	//call the method
        	Object methodResponse = classType.getMethod(methodName, new Class[] {}).invoke(constructorInstance, new Object[] {});
        	
	        	if(methodResponse instanceof JSONObject) {
	        		if(request.getAttribute(RESPONSE_DATA) != null) {
	        			request.removeAttribute(RESPONSE_DATA);
	        		}
	        		
	        		JSONObject json = (JSONObject)methodResponse;
	        		request.getSession().setAttribute("responseData",json);
	        		String redirectLocation = (String)context.getAttribute(REDIRECT_ATTR);
	        		
	        		response.sendRedirect(request.getContextPath() + redirectLocation);
	        	} 

        	
        	}
        	catch (ClassNotFoundException cnfe) {
        		//cnfe.printStackTrace();
        		cnfe.toString();
        		redirectToErrorPage(request,response);
        	
        	}
        	catch (Exception e) {
        		//e.printStackTrace();
        		redirectToErrorPage(request,response);
        	}
    }
    /**
     * Processes a JSP request
     * @param request
     * @param response
     * @param classAndMethodName
     */
    public void processJspRequest(HttpServletRequest request, HttpServletResponse response, String classAndMethodName) {
    		try {
                    request.getRequestDispatcher(classAndMethodName).forward(request, response);
    		} catch (Exception e) {
    			System.out.println("unable to process request: " + classAndMethodName);
    			redirectToErrorPage(request,response);
    		}
    }
    /**
     * redirects user to error JSP
     * @param request
     * @param response
     */
    private void redirectToErrorPage(HttpServletRequest request, HttpServletResponse response) {
    	try {
			response.sendRedirect(request.getContextPath() + ERROR_JSP);
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}