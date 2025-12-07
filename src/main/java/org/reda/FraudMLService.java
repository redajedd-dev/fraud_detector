package org.reda;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.Remove;

import java.io.File;

@Service
public class FraudMLService {

    private J48 classifier;
    private Instances dataStructure;

    @PostConstruct
    public void initModel() throws Exception {
        System.out.println(">>> Chargement des données Kaggle...");

        File file = new ClassPathResource("creditcard.csv").getFile();
        CSVLoader loader = new CSVLoader();
        loader.setSource(file);
        Instances rawData = loader.getDataSet();

        // On garde V1, V2, V3, Amount (indices 2,3,4,29) et Class (30)
        Remove removeFilter = new Remove();
        removeFilter.setAttributeIndices("2-4,30-last");
        removeFilter.setInvertSelection(true);
        removeFilter.setInputFormat(rawData);
        Instances filteredData = Filter.useFilter(rawData, removeFilter);

        NumericToNominal convert = new NumericToNominal();
        convert.setAttributeIndices("last");
        convert.setInputFormat(filteredData);
        dataStructure = Filter.useFilter(filteredData, convert);

        dataStructure.setClassIndex(dataStructure.numAttributes() - 1);

        classifier = new J48();
        classifier.buildClassifier(dataStructure);

        System.out.println(">>> SUCCÈS : Modèle entraîné !");
    }

    // --- C'EST ICI QUE J'AI CHANGÉ LE NOM ---
    // Avant : checkTransaction
    // Maintenant : predict
    public String predict(TransactionRequest request) {
        try {
            double[] values = new double[]{
                    request.getV1(),
                    request.getV2(),
                    request.getV3(),
                    request.getAmount(),
                    0
            };

            DenseInstance newTransaction = new DenseInstance(1.0, values);
            newTransaction.setDataset(dataStructure);

            double result = classifier.classifyInstance(newTransaction);
            String prediction = dataStructure.classAttribute().value((int) result);

            return prediction.replace("\"", "").equals("1") ? "FRAUD" : "NORMAL";

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}