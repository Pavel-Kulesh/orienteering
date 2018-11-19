package com.itacademy.jd2.pk.hop.web.tag;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class I18N extends SimpleTagSupport {
    public static final String SESSION_LOCALE_KEY = "current-locale";

    private final Locale DEFAULT_LOCALE = new Locale("en");
    private String key;

    @Override
    public void doTag() throws JspException, IOException {

        final JspContext jspContext = getJspContext();

        Locale locale = (Locale) jspContext.findAttribute(SESSION_LOCALE_KEY);
        if (locale == null) {
            locale = DEFAULT_LOCALE;
        }

        jspContext.getOut().println(getLocalized(key, locale));
    }

    private String getLocalized(String key, Locale locale) {
    	if (locale.getLanguage().equals("en")) {
    		ResourceBundle bundle = ResourceBundle.getBundle("messages_en",locale);
    		return bundle.getString(key);
		} else {ResourceBundle bundle = ResourceBundle.getBundle("messages_ru",locale);
		return bundle.getString(key);

		}
    	/*ResourceBundle bundle = ResourceBundle.getBundle(key,locale);
        return key;*/
    }

    public void setKey(final String key) {
        this.key = key;
    }

}