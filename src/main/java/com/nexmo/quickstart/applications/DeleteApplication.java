/*
 * Copyright (c) 2011-2017 Nexmo Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.nexmo.quickstart.applications;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.applications.ApplicationClient;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.SignatureAuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class DeleteApplication {
    public static void main(String[] argv) throws Exception {
        configureLogging();

        String NEXMO_API_KEY = envVar("API_KEY");
        String NEXMO_API_SECRET = envVar("API_SECRET", null);
        String NEXMO_SIGNATURE_SECRET = envVar("SIGNATURE_SECRET", null);

        String APPLICATION_TO_DELETE = envVar("APPLICATION_TO_DELETE");

        AuthMethod auth;
        if (NEXMO_SIGNATURE_SECRET != null) {
            auth = new SignatureAuthMethod(NEXMO_API_KEY, NEXMO_SIGNATURE_SECRET);
        } else if (NEXMO_API_SECRET != null) {
            auth = new TokenAuthMethod(NEXMO_API_KEY, NEXMO_API_SECRET);
        } else {
            throw new IllegalArgumentException(
                    "You must provide the API_SECRET or SIGNATURE_SECRET environment variable!");
        }

        ApplicationClient client = new NexmoClient(auth).getApplicationClient();
        client.deleteApplication(APPLICATION_TO_DELETE);

        System.out.printf("Application %s deleted!\n", APPLICATION_TO_DELETE);
    }
}
