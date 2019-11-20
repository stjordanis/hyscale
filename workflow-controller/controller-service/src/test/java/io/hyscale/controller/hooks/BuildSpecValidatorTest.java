/**
 * Copyright 2019 Pramati Prism, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.hyscale.controller.hooks;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import io.hyscale.commons.exception.HyscaleException;
import io.hyscale.controller.model.WorkflowContext;
import io.hyscale.controller.util.ServiceSpecTestUtil;
import io.hyscale.servicespec.commons.model.service.ServiceSpec;

public class BuildSpecValidatorTest {

    private BuildSpecValidatorHook buildSpecValidatorHook = new BuildSpecValidatorHook();

    @Test
    public void NoServiceSpec() {
        WorkflowContext context = new WorkflowContext();
        assertThrows(HyscaleException.class, () -> {
            buildSpecValidatorHook.preHook(context);
        });
    }

    @Test
    public void noStackImage() {
        WorkflowContext context = new WorkflowContext();
        ServiceSpec serviceSpec = null;
        try {
            serviceSpec = ServiceSpecTestUtil.getServiceSpec("/servicespecs/invalid_buildSpec1.hspec.yaml");
        } catch (IOException e1) {
            fail();
        }
        context.setServiceSpec(serviceSpec);
        assertThrows(HyscaleException.class, () -> {
            buildSpecValidatorHook.preHook(context);
        });
    }

    @Test
    public void inValidArtifacts() {
        WorkflowContext context = new WorkflowContext();
        ServiceSpec serviceSpec = null;
        try {
            serviceSpec = ServiceSpecTestUtil.getServiceSpec("/servicespecs/invalid_buildSpec2.hspec.yaml");
        } catch (IOException e1) {
            fail();
        }
        context.setServiceSpec(serviceSpec);
        assertThrows(HyscaleException.class, () -> {
            buildSpecValidatorHook.preHook(context);
        });
    }

    @Test
    public void validBuildSpec() {
        WorkflowContext context = new WorkflowContext();
        ServiceSpec serviceSpec = null;
        try {
            serviceSpec = ServiceSpecTestUtil.getServiceSpec("/servicespecs/myservice.hspec.yaml");
        } catch (IOException e1) {
            fail();
        }
        context.setServiceSpec(serviceSpec);
        try {
            buildSpecValidatorHook.preHook(context);
        } catch (HyscaleException e) {
            fail();
        }
    }

}