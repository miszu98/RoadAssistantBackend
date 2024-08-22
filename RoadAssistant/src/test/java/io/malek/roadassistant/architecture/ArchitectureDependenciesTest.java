package io.malek.roadassistant.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

class ArchitectureDependenciesTest {

    final JavaClasses importedClasses = new ClassFileImporter().importPackages("io.malek.roadassistant");

    @Test
    void checkDependencies() {
        layeredArchitecture().consideringOnlyDependenciesInLayers()
                .layer("schedulers").definedBy("io.malek.roadassistant.schedulers..")
                .layer("api").definedBy("io.malek.roadassistant.api..")
                .layer("events").definedBy("io.malek.roadassistant.events..")
                .layer("websockets").definedBy("io.malek.roadassistant.websockets..")
                .whereLayer("schedulers").mayOnlyAccessLayers("api")
                .whereLayer("events").mayOnlyAccessLayers("websockets")
                .whereLayer("api").mayOnlyAccessLayers("websockets")
                .check(importedClasses);
    }

}
