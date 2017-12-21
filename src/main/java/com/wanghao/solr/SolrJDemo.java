package com.wanghao.solr;

import java.io.IOException;
import java.util.Iterator;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

public class SolrJDemo {

	public static void main(String[] args) throws Exception{
		
		String urlString = "http://localhost:8983/solr/db5";
		
		SolrClient solr = new HttpSolrClient.Builder(urlString).build();
		
		
		SolrQuery query = new SolrQuery();
		//query.set("q", "name:呐喊 OR authorName:鲁迅1");
		query.set("q", "鲁迅呐喊");
		QueryResponse response = solr.query(query);
		SolrDocumentList list = response.getResults();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		System.out.println("this is end ");
	}

}
