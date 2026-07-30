plugins {
    application
}

application {
    mainClass.set("com.designpatterns.capstone.di.DependencyInjectionDemo")
}

dependencies {
    implementation(platform(libs.spring.boot.dependencies))
    implementation("org.springframework:spring-context")
    implementation("jakarta.annotation:jakarta.annotation-api")

    testImplementation(platform(libs.spring.boot.dependencies))
    testImplementation("org.springframework:spring-test")
}
