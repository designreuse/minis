package net.minis.api;
//package net.aces.core.rest.client.glossary.taglib;
//
//import static org.springframework.web.context.support.WebApplicationContextUtils.getWebApplicationContext;
//
//import java.io.IOException;
//
//import javax.servlet.ServletContext;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import org.springframework.context.ApplicationContext;
//
//@Getter
//@Setter
//public class DataItemTag extends SimpleTagSupport {
//
//    private String name;
//
//    private String locale;
//
//    @Override
//    public void doTag() throws JspException, IOException {
//
//        PageContext pageContext = (PageContext) getJspContext();
//        ServletContext servletContext = pageContext.getServletContext();
//        ApplicationContext context = getWebApplicationContext(servletContext);
//
//        DataDictionaryResource dictionaryWebService = 
//                (DataDictionaryResource) context.getBean(DataDictionaryResource.class);
//
//        DataDictionaryBean dataDictionaryBean = dictionaryWebService.getByDataItem(name, locale);
//        JspWriter out = getJspContext().getOut();
//        out.print(dataDictionaryBean.getDataName());
//        out.flush();
//    }
//
//}
