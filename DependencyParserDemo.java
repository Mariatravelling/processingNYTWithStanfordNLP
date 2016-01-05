package com.dependencyParse;


import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.parser.nndep.DependencyParser;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.util.CoreMap;

import java.io.StringReader;
import java.util.Collection;
import java.util.List;


public class DependencyParserDemo {
  public static void main(String[] args) {
    String modelPath = DependencyParser.DEFAULT_MODEL;
    String taggerPath = "edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger";
    String text = "Few have tried, and far fewer have succeeded.";
    for (int argIndex = 0; argIndex < args.length; ) {
        switch (args[argIndex]) {
          case "-tagger":
            taggerPath = args[argIndex + 1];
            argIndex += 2;
            break;
          case "-model":
            modelPath = args[argIndex + 1];
            argIndex += 2;
            break;
          default:
            throw new RuntimeException("Unknown argument " + args[argIndex]);
        }
      }
    MaxentTagger tagger = new MaxentTagger(taggerPath);
    DependencyParser parser = DependencyParser.loadFromModelFile(modelPath);
    DocumentPreprocessor tokenizer = new DocumentPreprocessor(new StringReader(text));
    for (List<HasWord> sentence : tokenizer) {
    	tokenizer.iterator().toString();
    	System.out.println(sentence);
    	List<TaggedWord> tagged = tagger.tagSentence(sentence);
    	GrammaticalStructure gs = parser.predict(tagged);
    	System.out.println(gs.allTypedDependencies());
    	System.out.println(gs);
    }
    
    /*Properties props = new Properties();
    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    Annotation document;
	document = new Annotation(text);
    pipeline.annotate(document);
    List<CoreMap> sentences = document.get(SentencesAnnotation.class);
    for(CoreMap sentence: sentences) {
      for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
        String word = token.get(TextAnnotation.class);
        String pos = token.get(PartOfSpeechAnnotation.class); 
        System.out.println("word:"+word+" POS:"+pos);
      }
    }*/
  }
}
