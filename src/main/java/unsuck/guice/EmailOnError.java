package unsuck.guice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Guice AOP annotation which indicates method call should send us an email if an exception passes through it.</p>
 * <p>Note that methods with this annotation CANNOT BE PRIVATE.</p>
 *
 * @author Jeff Schnitzer <jeff@infohazard.org>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface EmailOnError
{
}