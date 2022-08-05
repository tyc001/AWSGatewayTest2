/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.List;

/**
 *
 * @author thamy
 */
public class RequestParamsHelper {
    private List<String> headers;
    private List<String> queryStringParams;

    public RequestParamsHelper() {
    }

    public RequestParamsHelper(List<String> headers, List<String> queryStringParams) {
        this.headers = headers;
        this.queryStringParams = queryStringParams;
    }

    /**
     * @return the headers
     */
    public List<String> getHeaders() {
        return headers;
    }

    /**
     * @param headers the headers to set
     */
    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    /**
     * @return the queryStringParams
     */
    public List<String> getQueryStringParams() {
        return queryStringParams;
    }

    /**
     * @param queryStringParams the queryStringParams to set
     */
    public void setQueryStringParams(List<String> queryStringParams) {
        this.queryStringParams = queryStringParams;
    }
}
