/*
 * The MIT License
 *
 * Copyright (c) 2016 The Things Network
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
package org.thethingsnetwork.account.sync.auth.token;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import org.thethingsnetwork.account.async.auth.token.AsyncJsonWebToken;

/**
 *
 * @author Romain Cambier
 */
public class JsonWebToken implements OAuth2Token {

    private final AsyncJsonWebToken wrapped;

    public JsonWebToken(AsyncJsonWebToken _wrap) {
        wrapped = _wrap;
    }

    @Override
    public boolean hasRefresh() {
        return wrapped.hasRefresh();
    }

    @Override
    public JsonWebToken refresh() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean isExpired() {
        return wrapped.isExpired();
    }

    @Override
    public String getToken() {
        return wrapped.getToken();
    }

    @Override
    public String getRawToken() {
        return wrapped.getRawToken();
    }

    @Override
    public URI getAccountServer() {
        return wrapped.getAccountServer();
    }

    public JsonWebToken restrict(List<String> _claims) {
        return wrapped
                .restrict(_claims)
                .map((AsyncJsonWebToken t) -> new JsonWebToken(t))
                .toBlocking()
                .single();
    }

    public JsonWebToken restrict(String... _claims) {
        return restrict(Arrays.asList(_claims));
    }

    @Override
    public AsyncJsonWebToken async() {
        return wrapped;
    }

}
