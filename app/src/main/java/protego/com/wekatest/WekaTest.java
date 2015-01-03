package protego.com.wekatest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.core.FastVector;
import weka.core.Instances;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

/**
 * Created by chanijindal on 03/01/15.
 */
public class WekaTest {

    Context context ;
    public BufferedReader readDataFile(String filename)
    {

        BufferedReader inputReader =null;
        try {
            File myFile=context.getFileStreamPath(filename);
            if(myFile.exists())
            inputReader=new BufferedReader(new FileReader(myFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputReader;
    }

    public static Evaluation classify(Classifier model,Instances trainingSet,Instances testingSet ) throws Exception
    {
        Evaluation evaluation = new Evaluation(trainingSet);
        model.buildClassifier(trainingSet);
        evaluation.evaluateModel(model,testingSet);

        return evaluation;

    }

    public static double calculateAccuracy(FastVector predictions)
    {
        double correct=0;
        for(int i=0;i<predictions.size();i++)
        {
            NominalPrediction np = (NominalPrediction) predictions.elementAt(i);
            if(np.predicted()==np.actual())
                correct++;

        }
          return 100*correct/predictions.size();

    }

    public static Instances[][] crossValidationSplit(Instances data, int numberOfFolds) {
        Instances[][] split = new Instances[2][numberOfFolds];

        for (int i = 0; i < numberOfFolds; i++) {
            split[0][i] = data.trainCV(numberOfFolds, i);
            split[1][i] = data.testCV(numberOfFolds, i);
        }

        return split;
    }


    public void showAlertDialog(Context context,String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.show();

    }


    public  void hereWeGo() throws Exception {

        BufferedReader datafile = readDataFile("weather.txt");
        Instances data = new Instances(datafile);
        data.setClassIndex(data.numAttributes() - 1);
        Instances[][] split = crossValidationSplit(data, 10);
        Instances[] trainingSplits = split[0];
        Instances[] testingSplits = split[1];
        Classifier[] models = {
                new J48(), // a decision tree
                new PART(),
                new DecisionTable(),//decision table majority classifier
                new DecisionStump()
        };


        for (int j = 0; j < models.length; j++) {

            // Collect every group of predictions for current model in a FastVector
            FastVector predictions = new FastVector();

            // For each training-testing split pair, train and test the classifier
            for (int i = 0; i < trainingSplits.length; i++) {
                Evaluation validation = classify(models[j], trainingSplits[i], testingSplits[i]);

                predictions.appendElements(validation.predictions());

                // Uncomment to see the summary for each training-testing pair.
                //System.out.println(models[j].toString());
            }

            // Calculate overall accuracy of current classifier on all splits
            double accuracy = calculateAccuracy(predictions);

            // Print current classifier's name and accuracy in a complicated,
            // but nice-looking way.
            Log.e("Hello", "Accuracy of " + models[j].getClass().getSimpleName() + ": "
                    + String.format("%.2f%%", accuracy)
                    + "\n---------------------------------");

            showAlertDialog(context,"Accuracy of " + models[j].getClass().getSimpleName() + ": "
                    + String.format("%.2f%%", accuracy)
                    + "\n---------------------------------");

        }

    }
}
