package com.bulkdownload.zip.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


	@Component
	public class ReportGeneratorRegistry {

	    private final Map<String, ReportGenerator> generatorMap;

	    @Autowired
	    public ReportGeneratorRegistry(Map<String, ReportGenerator> generatorMap) {
	        this.generatorMap = generatorMap;
	    }

	    public ReportGenerator getGenerator(String format) {
	        ReportGenerator generator = generatorMap.get(format.toLowerCase());
	        if (generator == null) {
	            throw new IllegalArgumentException("No report generator found for format: " + format);
	        }
	        return generator;
	    }
	}

