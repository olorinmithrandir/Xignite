package com.serverless.mstar.rest.service;

import java.io.IOException;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.mstar.domain.ExchangeResult;
import com.serverless.mstar.domain.XCompanyEstimates;
import com.serverless.mstar.domain.XCompanyRecommendations;

public class XigniteService {

	
	RestTemplate restTemplate=new RestTemplate();
	
	public ExchangeResult getExchanges(){
		return this.restTemplate.getForObject("https://factsetestimates.xignite.com/xFactSetEstimates.json/ListExchanges?&_token=AE4A02E0271A4E77B78B314AEE9A132D",ExchangeResult.class);
		
		
	}
	
	public String getExchangesAsStr(){
		return this.restTemplate.getForObject("https://factsetestimates.xignite.com/xFactSetEstimates.json/ListExchanges?&_token=AE4A02E0271A4E77B78B314AEE9A132D",String.class);
		
		
	}
	
	public String getEstimatesAsStr(){
		return this.restTemplate.getForObject("https://factsetestimates.xignite.com/xFactSetEstimates.json/GetEstimates?IdentifierType=Symbol&Identifiers=MSFT,GOOG&EstimateTypes=EPS,Sales&ReportType=Annual&EstimateFiscalPeriod=2018FY&AsOfDate=10/25/2017&UpdatedSince=&_token=AE4A02E0271A4E77B78B314AEE9A132D",String.class);
	}
	
	public String getRecommendationsAsStr(){
		return this.restTemplate.getForObject("https://factsetestimates.xignite.com/xFactSetEstimates.json/GetLatestRecommendationSummaries?IdentifierType=Symbol&Identifiers=MSFT,GOOG&UpdatedSince=&_token=AE4A02E0271A4E77B78B314AEE9A132D",String.class);
	}
	
	
	public static void main(String s[]){
		XigniteService svc = new XigniteService();
		String str = svc.getRecommendationsAsStr();
		System.out.println("str is = \n"+str);
		ObjectMapper mapper=new ObjectMapper();
		//List<XCompanyEstimates> resList = mapper.readValue(str,List<XCompanyEstimates>.class>);
		XCompanyRecommendations[] myObjects = null;
		try {
			myObjects = mapper.readValue(str, XCompanyRecommendations[].class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(myObjects!=null) {
			System.out.println(myObjects.toString());
		}
		//List<XCompanyEstimates> myObjects = mapper.readValue(str, new TypeReference<List<XCompanyEstimates>>(){});
	}
	
}
