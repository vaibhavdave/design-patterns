plugins {
    application
}

application {
    mainClass.set("com.designpatterns.structural.proxy.ProxyDemo")
}

dependencies {
    implementation(platform(libs.spring.boot.dependencies))
    implementation("org.springframework:spring-aop")
    implementation("org.springframework:spring-context")

    testImplementation(platform(libs.spring.boot.dependencies))
    testImplementation("org.springframework:spring-test")
}
