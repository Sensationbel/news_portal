plugins {
	java
	id("org.springframework.boot") version "3.2.1"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "by.bulaukin"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
	developmentOnly
	runtimeClasspath {
		extendsFrom
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:org.springframework.boot.gradle.plugin:3.1.2")
	// https://mvnrepository.com/artifact/commons-beanutils/commons-beanutils
	implementation("commons-beanutils:commons-beanutils:1.9.4")
//// https://mvnrepository.com/artifact/org.hibernate/hibernate-annotations
//	implementation("org.hibernate:hibernate-annotations:3.5.6-Final")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

	compileOnly("org.projectlombok:lombok")
	developmentOnly( "org.springframework.boot:spring-boot-devtools" )
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
