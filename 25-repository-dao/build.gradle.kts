plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

springBoot {
    mainClass.set("com.designpatterns.capstone.repository.spring.RepositoryDaoApplication")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly(libs.h2)

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
