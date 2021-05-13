/*
 * Copyright (c) 2018, 2020 Oracle and/or its affiliates.
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

package io.helidon.workshop;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.helidon.workshop.entity.Items;
import io.helidon.workshop.jpa.JPAItems;
import io.helidon.workshop.service.WorkshopService;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

/**
 * A simple JAX-RS resource to greet you. Examples:
 * <p>
 * Get default greeting message:
 * curl -X GET http://localhost:8080/greet
 * <p>
 * Get greeting message for Joe:
 * curl -X GET http://localhost:8080/greet/Joe
 * <p>
 * Change greeting
 * curl -X PUT -H "Content-Type: application/json" -d '{"greeting" : "Howdy"}' http://localhost:8080/greet/greeting
 * <p>
 * The message is returned as a JSON object.
 */
@Path("/workshop")
@RequestScoped
public class WorkshopResource {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());


    @Inject
    WorkshopService workshopService;

    /**
     * Return a items list using the name that was provided.
     *
     * @param
     * @return {@link JsonObject}
     */
    @SuppressWarnings("checkstyle:designforextension")
    @Path("/items")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Items> getItems() {

        List<Items> items = new ArrayList<>();
        try {
            items = workshopService.selectAllItems();
        } catch (SQLException e) {
            // do nothing as it's a workshop
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Return a items list using the name that was provided.
     *
     * @param
     * @return {@link JsonObject}
     */
    @SuppressWarnings("checkstyle:designforextension")
    @Path("/jpaitems")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JPAItems> getJPAItems() {

        List<JPAItems> items = new ArrayList<>();
        items = workshopService.selectAllJPAItems();

        return items;
    }
}