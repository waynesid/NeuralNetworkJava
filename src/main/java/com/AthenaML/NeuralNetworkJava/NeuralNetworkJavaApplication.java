package com.AthenaML.NeuralNetworkJava;

import ai.djl.training.dataset.Dataset;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class NeuralNetworkJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeuralNetworkJavaApplication.class, args);
		String file = "/home/wayne/Downloads/ARG.csv";
		DataPreprocessing.dataProcessing(new File(file));
	}

}
