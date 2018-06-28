/*
 * Copyright (c) 2016, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.msf4j.example;

import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Hello service resource class.
 */
@Path("/bnyservice")
public class BNYService {
    @POST
    @Path("/check")
    @Consumes("application/json")
    public Response bnyResponse(Request request) {

        String msg = generateResponse(request.getAlertId(), request.getUserId());
        System.out.println(msg);
        return Response.ok().entity(msg).build();
    }

    private String generateResponse(String alertId, int userId) {
        String msg1 = "" +
                "{\"event\":" +
                "   {" +
                "       \"sendEmail\":" + shouldSendEmail(alertId, userId) + "," +
                "       \"amount\":" + generateAmount() + "," +
                "       \"attachmentAvailable\":" + true + "," +
                "       \"fileName\": \"http://localhost:8000/emailsample.siddhi\"" +
                "   }" +
                "}";

        return msg1;
    }

    private boolean shouldSendEmail(String alertId, int userId) {
        if ("alert1".equals(alertId)) {
            switch (userId) {
                case 1: case 2: case 5:
                    return true;
                default:
                    return false;
            }
        } else if ("alert2".equals(alertId)) {
            switch (userId) {
                case 2:
                case 4:
                    return true;
                default:
                    return false;
            }
        } else {
            return true;
        }
    }

    private int generateAmount() {
        Random r = new Random();
        return r.nextInt(100);
    }
}
