package cmcc.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

	

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("cmcc.api.web"))
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("移动培训APP RESTful APIs").description("所有接口返回的数据格式<br>"+
					 "{<br>"+
					 "'code': '状态码',<br>"+
					 "'msg': '结果描述'<br>"+
					 "'datas': '结果'<br>"+
					 "}<br>"+
					 "其中code=1成功，code=0结果为空，code<0失败"
					)
				.contact("263608237@qq.com").version("1.0").build();
	}
}
