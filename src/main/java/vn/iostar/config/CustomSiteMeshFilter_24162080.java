package vn.iostar.config;

import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.builder.SiteMeshFilterBuilder;

import jakarta.servlet.annotation.WebFilter;

@WebFilter("/*")
public class CustomSiteMeshFilter_24162080 extends ConfigurableSiteMeshFilter {

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {

		builder.addDecoratorPath("/*", "user.jsp").addDecoratorPath("/admin/*", "admin.jsp");
	}
}