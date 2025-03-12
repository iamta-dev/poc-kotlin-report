package com.ktb.dcb.report.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("KTB DCB Report API")
                    .description("API สำหรับการสร้างรายงานต่างๆ เช่น Billing Report และ Receipt Report")
                    .version("v1.0.0")
                    .contact(
                        Contact()
                            .name("KTB DCB Team")
                            .email("dcb.team@ktb.co.th")
                    )
                    .license(
                        License()
                            .name("KTB License")
                    )
            )
            .addServersItem(
                Server()
                    .url("/")
                    .description("Development Server")
            )
    }
}
