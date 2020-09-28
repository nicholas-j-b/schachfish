import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    id("org.springframework.boot") version "2.3.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
    id("org.openapi.generator") version "4.3.1"
}

group = "com.nicholasbrooking.pgk"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.moshi:moshi-kotlin:1.9.3")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.junit.jupiter:junit-jupiter-api")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("redis.clients:jedis:3.3.0")
    implementation("com.google.code.gson:gson:2.8.6")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}


//sourceSets {
//    main {
//        java {
//            setSrcDirs(listOf(
//                    "$buildDir/generated/src/main/java"
//                    "$projectDir/src/main",
//                    "$projectDir/src/test"
//            ))
//            sourceDirectories += "$buildDir/generated"
//        }
//		kotlin {
//            setSrcDirs(listOf())
//			srcDir += "$buildDir/generated"
//		}
//    }
//}

//openApiGenerate {
//    generatorName.set("kotlin-server")
//    inputSpec.set("https://raw.githubusercontent.com/nicholas-j-b/schachfish-api/master/src/main/resources/openapi.yaml")
//	outputDir.set("$projectDir/src/main/kotlin/generated")
//    outputDir.set("$buildDir/generated")
//    generateModelDocumentation.set(false)
//    generateApiDocumentation.set(false)
//
//    invokerPackage.set("com.nicholasbrooking.pgk.schachfish")
//    configOptions.set(mapOf(
//            "delegatePattern" to "true",
//            "gradleBuildFile" to "false",
//            "useBeanValidation" to "true",
//            "serviceImplementation" to "true",
//            "packageName" to "com.nicholasbrooking.pgk.schachfish.api",
//            "groupName" to "com.nicholasbrooking.pgk.schachfish.api",
//            "apiPackage" to "com.nicholasbrooking.pgk.schachfish.api",
//            "basePackage" to "com.nicholasbrooking.pgk.schachfish.api",
//            "modelPackage" to "com.nicholasbrooking.pgk.schachfish.api.model",
//            "invokerPackage" to "com.nicholasbrooking.pgk.schachfish.api"
//    ))
//}

//    implementation("io.swagger:swagger-codegen-project:3.0.0-rc1")
//    implementation("io.springfox:springfox-swagger2:3.0.0")
//    implementation("io.springfox:springfox-swagger-ui:3.0.0")
//    implementation("io.springfox:springfox-boot-starter:3.0.0")
//    implementation("io.springfox:springfox-spring-web:2.3.1")
//    implementation("io.springfox:springfox-core:2.10.5")
//    implementation("io.springfox:springfox-swagger-common:3.0.0")
//    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    implementation("javax.validation:validation-api")
//    implementation("org.openapitools.empoa:empoa-jackson-serializer:1.2.1")
//    implementation("org.openapitools:jackson-databind-nullable:0.2.1")
