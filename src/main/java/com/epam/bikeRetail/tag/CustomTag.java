package com.epam.bikeRetail.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CustomTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {

        String welcome = "<hr/>custom tag<hr/>";


        try{
            JspWriter writer = pageContext.getOut();
            writer.write(welcome);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}

