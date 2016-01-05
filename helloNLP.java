package com.dependencyParse;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class helloNLP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    Properties props = new Properties();
	    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);	   
		String text = "Few have tried, and far fewer have succeeded." ;// Add your text here!
		Annotation document;
		document = new Annotation(text);
	    pipeline.annotate(document);
	    List<CoreMap> sentences = document.get(SentencesAnnotation.class);
	    //int n=1;
	    for(CoreMap sentence: sentences) {
	      for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
	        String word = token.get(TextAnnotation.class);
	        String pos = token.get(PartOfSpeechAnnotation.class); 
	        System.out.println("word:"+word+" POS:"+pos);
	      }
	    }

	}

}
