package com.sample.framework.utils.jira;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;



public class JiraUtils {

	public JiraUtils() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings({ "deprecation", "resource" })
	public static Map<String, String> getField(String url, String login, String password,
			String query, String field) throws Exception {
		Map<String, String> content = new HashMap<String, String>();

		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(login, password);
		provider.setCredentials(AuthScope.ANY, credentials);
		HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
		
        URI uri = UriBuilder.fromUri(url)
                .path("/rest/api/2/search")
                .queryParam("jql", query)
                .queryParam("fields", "key,summary," + field).build();
        HttpGet request = new HttpGet(uri);
        HttpResponse response = client.execute(request);
        String responseText = EntityUtils.toString(response.getEntity());
        JSONObject json = new JSONObject(responseText);
        JSONArray array = json.getJSONArray("issues");
        for (int i = 0; i < array.length(); i++) {
        	String key = array.getJSONObject(i).getString("key");
        	String value = null;
            if (!array.getJSONObject(i).has("fields")) {
                continue;
            }
            if (!array.getJSONObject(i).getJSONObject("fields").has(field)) {
                continue;
            }
            String summary = array.getJSONObject(i).getJSONObject("fields").getString("summary");
            value = array.getJSONObject(i).getJSONObject("fields").getString(field);
            if (value != null && !value.equals("null")) {
                value = String.format("Feature: %s %s%s  %s", key, summary, System.lineSeparator(), value);
                content.put(String.format("%s %s",  key, summary), value);
            }
        }
        client.getConnectionManager().shutdown();
		return content;
	}
}
