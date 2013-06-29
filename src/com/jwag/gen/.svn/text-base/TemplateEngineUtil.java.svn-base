/*
 * TemplateEngineUtil.java
 *
 * Created on 04 September 2007, 00:19
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jwag.gen;

/**
 *
 * @author Saifo
 */


import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.Template;
import org.apache.velocity.exception.VelocityException;

public abstract class TemplateEngineUtil {

    /**
     *
     * @param velocityEngine
     * @param templateLocation
     * @param model
     * @throws org.apache.velocity.exception.VelocityException
     * @return
     */
    public static String mergeTemplateIntoString(TemplateEngine templateEngine, String templateLocation, Map model) throws VelocityException {
        StringWriter result = new StringWriter();
        mergeTemplate(templateEngine, templateLocation, model, result);
        return result.toString();
    }

    /**
     *
     * @param velocityEngine
     * @param templateLocation
     * @param model
     * @param writer
     * @throws org.apache.velocity.exception.VelocityException
     */
    public static void mergeTemplate(TemplateEngine templateEngine, String templateLocation, Map model, Writer writer) throws VelocityException {
        try {
            VelocityContext velocityContext = new VelocityContext(model);
            templateEngine.mergeTemplate(templateLocation, velocityContext, writer);
        } catch (VelocityException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {

            throw new VelocityException(ex.getMessage());
        }
    }

    /**
     * 
     * @param templateName 
     * @param templateEngine 
     * @return 
     */
    public static String getTemplate(String templateName, TemplateEngine templateEngine) {
        Template template = null;
        try {
            template = templateEngine.getTemplate(templateName);
        } catch (Exception e) {
            System.out.println("Exception caught in getTemplate Method: " + e.getMessage());
        }
        String strname = template.getName();
        return strname;
    }
}