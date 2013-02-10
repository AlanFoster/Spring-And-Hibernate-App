package me.alanfoster.application.controllers.core.config;

/**
 * This interface should be implemented when wanting to expose a set of
 * Request Mapping addresses that a controller supports. The common pattern
 * used is to implement this interface in an Enum
 * <br />
 * This has been created to give a nice senseof static typing, rather
 * than dealing with a lot of Strings which can be absolutely anything.
 * Plus the String datatype is final, so you can't even start using your
 * own datatype which extends String in an attempt to acquire more
 * static typing
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface RequestMappingConfig {
    /**
     * Get the request mapping, IE "search".
     *
     * @return The request mapping
     */
    String getRequestMapping();

    /**
     * Get the redirect url for the request mapping, IE "redirect:search".
     *
     * @return The request mapping redirect string
     */
    String getRedirectRequestMapping();
}
