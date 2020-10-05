import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    id("org.springframework.boot") version "2.3.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
    id("org.openapi.generator") version "4.3.1"
}

group = "com.nicholasbrooking.pkg"
version = "0.0.4"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    implementation("redis.clients:jedis:3.3.0")

    implementation("com.squareup.moshi:moshi-kotlin:1.9.3")
    implementation("com.google.code.gson:gson:2.8.6")

    implementation("io.swagger:swagger-annotations:1.6.2")
    implementation("org.openapitools:jackson-databind-nullable:0.2.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.3")

    implementation("org.junit.jupiter:junit-jupiter-api")
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


sourceSets {
    main {
        java {
            setSrcDirs(listOf("${rootDir}/src/main/java"))
        }
    }
}

tasks {
    getByName<Delete>("clean") {
        delete.add("${rootDir}/src/main/java")
    }
    compileKotlin {
        dependsOn("openApiGenerate")
    }
}

openApiGenerate {
    generatorName.set("spring")
    inputSpec.set("https://raw.githubusercontent.com/nicholas-j-b/schachfish-api/master/schachfish/src/main/resources/openapi.yml")
    outputDir.set("$rootDir")

    systemProperties.set(mapOf(
            "modelDocs" to "false",
            "models" to "",
            "apis" to "",
            "supportingFiles" to "files"
    ))

    configOptions.set(mapOf(
            "useOptional" to "true",
            "swaggerDocketConfig" to "false",
            "useBeanValidation" to "false",
            "useTags" to "true",
            "singleContentTypes" to "true",
            "title" to rootProject.name,
            "artifactId" to "${rootProject.name}.${version}",
            "java8" to "false",
            "generateSupportingFiles" to "false",
//            "supportingFilesToGenerate" to "ApiUtil.java",
            "serializableModel" to "true",
            "interfaceOnly" to "true",
            "groupName" to "com.nicholasbrooking.pkg.schachfish",
            "packageName" to "com.nicholasbrooking.pkg.schachfish.api",
            "apiPackage" to "com.nicholasbrooking.pkg.schachfish.api",
            "basePackage" to "com.nicholasbrooking.pkg.schachfish.api",
            "modelPackage" to "com.nicholasbrooking.pkg.schachfish.api.model",
            "invokerPackage" to "com.nicholasbrooking.pkg.schachfish.api"
    ))
}

