/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.streampipes.connect.container.master.health;

import org.apache.streampipes.connect.api.exception.AdapterException;
import org.apache.streampipes.connect.container.master.management.AdapterMasterManagement;
import org.apache.streampipes.model.connect.adapter.AdapterDescription;
import org.apache.streampipes.storage.api.IAdapterStorage;
import org.apache.streampipes.storage.couchdb.impl.AdapterStorageImpl;

import java.util.List;

public class AdapterHealthCheck {

    public AdapterHealthCheck() {
    }

    public void checkAndRestoreAdapters() {
        AdapterMasterManagement adapterMasterManagement = new AdapterMasterManagement();
        IAdapterStorage adapterStorage = new AdapterStorageImpl();


        // Get all adapters
        List<AdapterDescription> allRunningInstancesAdaperDescription = adapterStorage.getAllAdapters();

        // Group them by worker
//        AdapterDescription decryptedAdapterDescription =
//                new AdapterEncryptionService(new Cloner().adapterDescription(ad)).decrypt();

        for (AdapterDescription adapterDescription : allRunningInstancesAdaperDescription) {
            try {
                adapterMasterManagement.startStreamAdapter(adapterDescription.getElementId());
            } catch (AdapterException e) {
                e.printStackTrace();
            }

        }

        // Ask worker if they are up and running

        // If not

            // Find a worker to run them

            // Invoke the adapters

   }

}
