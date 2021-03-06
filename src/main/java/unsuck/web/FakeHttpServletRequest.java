/*
 */

package unsuck.web;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * HttpServletRequest which implements only the attribute-related methods (and not even
 * all of those).  Suitable as a scope for framework bits that need such a thing.
 *
 * All methods except attribute-related methods will either do nothing or return null.
 */
public class FakeHttpServletRequest extends HttpServletRequestWrapper
{
	/** Create a stub interface via dynamic proxy that does nothing */
	private static HttpServletRequest makeStub() {
		return (HttpServletRequest)Proxy.newProxyInstance(
				Thread.currentThread().getContextClassLoader(),
				new Class<?>[] { HttpServletRequest.class },
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						return null;
					}
				});
	}

	Map<String, Object> attrs = new HashMap<String, Object>();

	public FakeHttpServletRequest() {
		// Can't actually pass null here
		super(makeStub());
	}

	@Override
	public Object getAttribute(String key) {
		return attrs.get(key);
	}

	@Override
	public void setAttribute(String key, Object value) {
		attrs.put(key, value);
	}

	@Override
	public void removeAttribute(String key) {
		attrs.remove(key);
	}

	@Override
	public String getRemoteAddr() {
		return "127.0.0.1";
	}

	@Override
	public Enumeration<?> getHeaderNames() {
		return Collections.emptyEnumeration();
	}

	@Override
	public Map<?, ?> getParameterMap() {
		return Collections.emptyMap();
	}
}
