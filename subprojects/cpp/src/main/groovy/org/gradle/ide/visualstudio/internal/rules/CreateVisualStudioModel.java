/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.ide.visualstudio.internal.rules;

import org.gradle.ide.visualstudio.internal.DefaultVisualStudioExtension;
import org.gradle.ide.visualstudio.internal.DefaultVisualStudioProject;
import org.gradle.ide.visualstudio.internal.VisualStudioProjectConfiguration;
import org.gradle.runtime.base.BinaryContainer;
import org.gradle.model.ModelRule;
import org.gradle.nativebinaries.ProjectNativeBinary;

public class CreateVisualStudioModel extends ModelRule {
    @SuppressWarnings("UnusedDeclaration")
    public void createVisualStudioModelForBinaries(DefaultVisualStudioExtension visualStudioExtension, BinaryContainer binaryContainer) {
        for (ProjectNativeBinary binary : binaryContainer.withType(ProjectNativeBinary.class)) {
            VisualStudioProjectConfiguration configuration = visualStudioExtension.getProjectRegistry().addProjectConfiguration(binary);

            // Only create a solution if one of the binaries is buildable
            if (binary.isBuildable()) {
                DefaultVisualStudioProject visualStudioProject = configuration.getProject();
                visualStudioExtension.getSolutionRegistry().addSolution(visualStudioProject);
            }
        }
    }
}

