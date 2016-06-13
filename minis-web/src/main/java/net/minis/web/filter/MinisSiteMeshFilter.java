package net.minis.web.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class MinisSiteMeshFilter extends ConfigurableSiteMeshFilter {
    
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "/WEB-INF/templates/template.jsp");
    }
    
}
